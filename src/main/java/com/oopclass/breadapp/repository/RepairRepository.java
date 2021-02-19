package com.oopclass.breadapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oopclass.breadapp.models.Repair;

/**
 * OOP Class 20-21
 * @author Gerald Villaran
 */

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {

	//User findByEmail(String email);
}
