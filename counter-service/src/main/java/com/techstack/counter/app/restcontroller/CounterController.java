package com.techstack.counter.app.restcontroller;

import com.techstack.counter.app.entity.Counter;
import com.techstack.counter.app.exception.CounterExistException;
import com.techstack.counter.app.exception.CounterNotFoundException;
import com.techstack.counter.app.model.CounterDTO;
import com.techstack.counter.app.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/")
public class CounterController {

	@Autowired
	private CounterService counterService;

	@PostMapping("/counter")
	public ResponseEntity<Counter> saveCounter(@RequestBody @Valid CounterDTO counterDTO) throws CounterExistException {
		Counter addedCounter = counterService.addNewCounter(counterDTO);
		return new ResponseEntity<>(addedCounter, HttpStatus.CREATED);
	}

	@GetMapping("/counter/{name}")
	public ResponseEntity<Integer> getCurrentValue(@PathVariable String name) throws CounterNotFoundException {
		return ResponseEntity.ok(counterService.getCurrentValueByCounter(name));
	}

	@GetMapping("/counter")
	public ResponseEntity<List<CounterDTO>> getAllCounter() {
		return ResponseEntity.ok(counterService.getAllCounter());
	}

	@PostMapping(value = "/counter/plus")
	public ResponseEntity<Boolean> incrementCounter(@RequestBody CounterDTO counterDTO) throws CounterNotFoundException {
		return ResponseEntity.ok(counterService.incrementCounterByOne(counterDTO));
	}
}
