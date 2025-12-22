package com.example.pebble.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Hello World!");
        return "index";
    }

    @GetMapping("/nomes")
    public String mostraNomes(Model model) {
        List<String> nomes = Arrays.asList("Ana", "Bruno", "Carlos", "Diana");
        model.addAttribute("nomes", nomes);
        return "lista-nomes";
    }
}