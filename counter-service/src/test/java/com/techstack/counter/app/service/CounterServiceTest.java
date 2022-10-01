package com.techstack.counter.app.service;

import com.techstack.counter.app.entity.Counter;
import com.techstack.counter.app.exception.CounterExistException;
import com.techstack.counter.app.model.CounterDTO;
import com.techstack.counter.app.repository.CounterRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ishaan.solanki
 * This class cover Junit test for {@link CounterService}
 * <p>It is used to cover serivce layer unit test code.</p>
 */
@SpringBootTest
public class CounterServiceTest {

	@InjectMocks
	CounterService counterService;
	@Mock
	CounterRepository counterRepository;

	@Test
	public void addNewCounterTest() throws CounterExistException {
		Mockito.when(counterRepository.existByName(Mockito.anyString())).thenReturn(1);
		Mockito.when(counterRepository.save(Mockito.any())).thenReturn(Counter.builder().counterName("Test").counterValue(1).build());
		Assert.notNull(counterService.addNewCounter(new CounterDTO()));
	}

	@Test
	public void getAllCounterTest() {
		Mockito.when(counterRepository.findAll()).thenReturn(counters());
		Assert.notEmpty(counterService.getAllCounter(),"test");
	}

	private List<Counter> counters() {
		Counter counter = new Counter();
		counter.setCounterName("test");
		counter.setCounterValue(1);
		List<Counter> counters = new ArrayList<>();
		counters.add(counter);
		return counters;
	}
}
