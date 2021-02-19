package com.oopclass.breadapp.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oopclass.breadapp.models.Login;
import com.oopclass.breadapp.repository.LoginRepository;
import com.oopclass.breadapp.services.ILoginService;

/**
 * OOP Class 20-21
 * @author Gerald Villaran
 */

@Service
public class LoginService implements ILoginService {
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Override
	public Login save(Login entity) {
		return loginRepository.save(entity);
	}

	@Override
	public Login update(Login entity) {
		return loginRepository.save(entity);
	}

	@Override
	public void delete(Login entity) {
		loginRepository.delete(entity);
	}

	@Override
	public void delete(Long id) {
		loginRepository.deleteById(id);
	}

	@Override
	public Login find(Long id) {
		return loginRepository.findById(id).orElse(null);
	}

	@Override
	public List<Login> findAll() {
		return loginRepository.findAll();
	}

	@Override
	public void deleteInBatch(List<Login> logins) {
		loginRepository.deleteInBatch(logins);
	}
	
}
