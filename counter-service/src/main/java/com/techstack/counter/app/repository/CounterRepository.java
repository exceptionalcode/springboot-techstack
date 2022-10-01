package com.techstack.counter.app.repository;

import com.techstack.counter.app.entity.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends JpaRepository<Counter, Integer> {

	@Query(value = "SELECT COUNT(counter_name) FROM counter_table WHERE counter_name = ?1",
			nativeQuery = true)
	int existByName(String countername);

	@Query(value = "SELECT counter_value FROM COUNTER_TABLE WHERE counter_name = ?1",
			nativeQuery = true)
	Integer findValueByName(String countername);

	@Modifying
	@Query(value = "UPDATE COUNTER_TABLE Set counter_value = counter_value + 1 Where counter_name = ?1",
			nativeQuery = true)
	Integer updateCounterByOne(String countername);
}
