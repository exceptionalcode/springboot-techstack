package com.techstack.counter.app.service;

import com.techstack.counter.app.entity.Counter;
import com.techstack.counter.app.exception.CounterExistException;
import com.techstack.counter.app.exception.CounterNotFoundException;
import com.techstack.counter.app.model.CounterDTO;
import com.techstack.counter.app.repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CounterService {

	@Autowired
	private CounterRepository counterRepository;

	public Counter addNewCounter(CounterDTO counterDTO) throws CounterExistException {
		Counter counter = Counter.builder()
				.counterName(counterDTO.getCounterName())
				.counterValue(counterDTO.getCounterValue())
				.build();

		int exist = counterRepository.existByName(counter.getCounterName());
		if (exist > 0) {
			throw new CounterExistException("Counter already exists with name : " + counter.getCounterName());
		}
		return counterRepository.save(counter);
	}

	public Integer getCurrentValueByCounter(String counterName) throws CounterNotFoundException {
		Integer currentValue = counterRepository.findValueByName(counterName);
		if (Objects.isNull(currentValue)) {
			throw new CounterNotFoundException("Counter not found with name : " + counterName);
		}
		return currentValue;
	}

	public List<CounterDTO> getAllCounter() {
		List<Counter> counters = counterRepository.findAll();
		return counters.stream()
				.map(c -> new CounterDTO(c.getCounterName(), c.getCounterValue()))
				.collect(Collectors.toList());
	}

	@Transactional
	public Boolean incrementCounterByOne(CounterDTO counterDTO) throws CounterNotFoundException {
		if (!StringUtils.isEmpty(counterDTO.getCounterName())) {
			Integer incremented = counterRepository.updateCounterByOne(counterDTO.getCounterName().trim());
			if (1 == incremented.intValue()) {
				return Boolean.TRUE;
			}
		}
		throw new CounterNotFoundException("Counter not found with name : " + counterDTO.getCounterName());
	}
}
