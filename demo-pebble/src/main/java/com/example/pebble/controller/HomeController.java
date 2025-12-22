package com.example.pebble.controller;

import com.example.pebble.model.Pessoa;
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

    @GetMapping("/pessoas")
    public String listarPessoas(Model model) {
        List<Pessoa> pessoas = Arrays.asList(
                new Pessoa("Ana", 25),
                new Pessoa("Bruno", 30),
                new Pessoa("Carlos", 22)
        );
        model.addAttribute("pessoas", pessoas);
        return "lista-pessoas";
    }
}