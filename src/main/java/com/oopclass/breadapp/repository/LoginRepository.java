package com.oopclass.breadapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oopclass.breadapp.models.Login;

/**
 * OOP Class 20-21
 * @author Gerald Villaran
 */

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

	//User findByEmail(String email);
}
