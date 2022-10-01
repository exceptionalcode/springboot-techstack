package com.techstack.counter.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CounterDTO {

	@NotEmpty
	@Size(min = 1, max = 10)
	private String counterName;
	@Range(min = 1, max = 5)
	private int counterValue;
}
