package com.oopclass.breadapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oopclass.breadapp.models.Schedule;

/**
 * OOP Class 20-21
 * @author Gerald Villaran
 */

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	//User findByEmail(String email);
}
