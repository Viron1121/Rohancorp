package com.oopclass.breadapp.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oopclass.breadapp.models.Schedule;
import com.oopclass.breadapp.repository.ScheduleRepository;
import com.oopclass.breadapp.services.IScheduleService;

/**
 * OOP Class 20-21
 * @author Gerald Villaran
 */

@Service
public class ScheduleService implements IScheduleService {
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Override
	public Schedule save(Schedule entity) {
		return scheduleRepository.save(entity);
	}

	@Override
	public Schedule update(Schedule entity) {
		return scheduleRepository.save(entity);
	}

	@Override
	public void delete(Schedule entity) {
		scheduleRepository.delete(entity);
	}

	@Override
	public void delete(Long id) {
		scheduleRepository.deleteById(id);
	}

	@Override
	public Schedule find(Long id) {
		return scheduleRepository.findById(id).orElse(null);
	}

	@Override
	public List<Schedule> findAll() {
		return scheduleRepository.findAll();
	}

	@Override
	public void deleteInBatch(List<Schedule> schedules) {
		scheduleRepository.deleteInBatch(schedules);
	}
	
}
