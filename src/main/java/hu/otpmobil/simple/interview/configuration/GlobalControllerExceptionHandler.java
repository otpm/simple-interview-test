package hu.otpmobil.simple.interview.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerExceptionHandler {

	private static final Pattern LOCALIZABLE_MESSAGE_PATTERN = Pattern.compile("\\{([^}]+)\\}");

	@Data
	@AllArgsConstructor
	private static class ErrorResponse {
		private List<ErrorResponseItem> errors;

		public ErrorResponse(String message) {
			errors = Arrays.asList(new ErrorResponseItem(message));
		}
	}

	@Data
	private static class ErrorResponseItem {
		private String field;
		private String message;

		public ErrorResponseItem(ObjectError error) {
			message = error.getDefaultMessage();
			if (error instanceof FieldError) {
				field = ((FieldError) error).getField();
			}
		}

		public ErrorResponseItem(String message) {
			this.message = message;
		}

	}

	private final ReloadableResourceBundleMessageSource resourceBundle;

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		List<ErrorResponseItem> items = exception.getBindingResult().getAllErrors().stream().map(ErrorResponseItem::new)
				.collect(toList());
		return new ErrorResponse(items);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException exception,
			HttpServletResponse response) {
		String localizedMessage = getLocalizedExceptionMessage(exception);
		return new ErrorResponse(localizedMessage);
	}

	@ExceptionHandler({ IllegalArgumentException.class, IllegalStateException.class })
	public ErrorResponse handleException(Exception exception, HttpServletResponse response) {
		log.warn("", exception);
		String localizedMessage = getLocalizedExceptionMessage(exception);
		response.setStatus(
				exception.getCause() == null || exception.equals(exception.getCause()) ? HttpStatus.BAD_REQUEST.value()
						: HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ErrorResponse(localizedMessage);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public ErrorResponse handleConstraintViolationException(ConstraintViolationException exception) {
		String message = exception.getMessage();
		if (message.contains(":")) {
			message = message.substring(message.indexOf(":") + 1, message.length()).trim();
		}

		return new ErrorResponse(message);
	}

	private String getLocalizedExceptionMessage(Exception exception) {
		return getLocalizedMessage(exception.getMessage());
	}

	private String getLocalizedMessage(String message) {
		if (!StringUtils.isBlank(message)) {
			Matcher matcher = LOCALIZABLE_MESSAGE_PATTERN.matcher(message);
			if (matcher.find()) {
				String key = matcher.group(1);
				return resourceBundle.getMessage(key, null, key, LocaleContextHolder.getLocale());
			}
		}

		return message;
	}

}
