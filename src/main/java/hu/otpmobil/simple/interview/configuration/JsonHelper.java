package hu.otpmobil.simple.interview.configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.io.IOException;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonHelper {

	private static final ObjectMapper MAPPER;
	private static final Validator VALIDATOR;

	static {
		MAPPER = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.configure(SerializationFeature.INDENT_OUTPUT, false)
				.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE)
				.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE)
				.setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE)
				.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).setDateFormat(new StdDateFormat())
				.registerModule(new JavaTimeModule()).setSerializationInclusion(JsonInclude.Include.NON_NULL);

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		VALIDATOR = factory.getValidator();
	}

	public static String toJson(Object object) {
		try {
			return MAPPER.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T fromJson(String json, Class<T> responseClass) {
		try {
			return MAPPER.readValue(json, responseClass);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void validate(Object object) {
		Set<ConstraintViolation<Object>> violations = VALIDATOR.validate(object);
		if (!violations.isEmpty()) {
			StringBuilder message = new StringBuilder();
			for(ConstraintViolation<?> violation: violations) {
				message.append(violation.getPropertyPath());
				message.append(": ");
				message.append(violation.getMessage());
				message.append("\r\n");
			}
			throw new ConstraintViolationException(message.toString(), violations);
		}
	}

}
