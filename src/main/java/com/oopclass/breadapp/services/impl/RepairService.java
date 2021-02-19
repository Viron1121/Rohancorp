package com.oopclass.breadapp.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oopclass.breadapp.models.Repair;
import com.oopclass.breadapp.repository.RepairRepository;
import com.oopclass.breadapp.services.IRepairService;

/**
 * OOP Class 20-21
 * @author Gerald Villaran
 */

@Service
public class RepairService implements IRepairService {
	
	@Autowired
	private RepairRepository repairRepository;
	
	@Override
	public Repair save(Repair entity) {
		return repairRepository.save(entity);
	}

	@Override
	public Repair update(Repair entity) {
		return repairRepository.save(entity);
	}

	@Override
	public void delete(Repair entity) {
		repairRepository.delete(entity);
	}

	@Override
	public void delete(Long id) {
		repairRepository.deleteById(id);
	}

	@Override
	public Repair find(Long id) {
		return repairRepository.findById(id).orElse(null);
	}

	@Override
	public List<Repair> findAll() {
		return repairRepository.findAll();
	}

	@Override
	public void deleteInBatch(List<Repair> repairs) {
		repairRepository.deleteInBatch(repairs);
	}
	
}
