package hu.otpmobil.simple.interview.domain.common;

import hu.otpmobil.simple.interview.common.CrossFieldValidationGroup1;
import lombok.Data;

import javax.validation.GroupSequence;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@GroupSequence(value = { AbstractListFilter.class, CrossFieldValidationGroup1.class })
public abstract class AbstractListFilter {

	public static final int MAX_ROW_PER_PAGE = 1000;

	@Min(0)
	private Integer pageNumber;

	@Min(1)
	@Max(MAX_ROW_PER_PAGE)
	private Integer rowPerPage;

	@AssertTrue(message = "{AbstractListFilter.invalid.paginator.settings}", groups = CrossFieldValidationGroup1.class)
	public boolean isPaginatorValid() {
		return pageNumber == null && rowPerPage == null || pageNumber != null && rowPerPage != null;
	}

	public boolean isPaging() {
		return pageNumber != null;
	}

}
