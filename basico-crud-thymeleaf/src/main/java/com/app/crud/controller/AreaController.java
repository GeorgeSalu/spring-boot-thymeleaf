package com.app.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.app.crud.service.AreaService;

@Controller
public class AreaController {

	@Autowired
	private AreaService areaService;
	
}
