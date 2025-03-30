package com.mballem.demoajax.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mballem.demoajax.repository.PromocaoRepository;

@Service
public class NotificaoService {

	@Autowired
	private PromocaoRepository repository;
	
	
	
}
