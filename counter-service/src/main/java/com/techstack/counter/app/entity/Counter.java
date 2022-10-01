package com.techstack.counter.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "counter_table")
public class Counter {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "counter_name")
	private String counterName;
	@Column(name = "counter_value")
	private int counterValue;
}
