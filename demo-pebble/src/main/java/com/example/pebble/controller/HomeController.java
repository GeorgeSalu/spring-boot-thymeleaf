package com.example.pebble.controller;

import com.example.pebble.model.Pedido;
import com.example.pebble.model.Pessoa;
import com.example.pebble.model.PessoaRecord;
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

    @GetMapping("/pessoas-pojo")
    public String listarPessoas(Model model) {
        List<Pessoa> pessoas = Arrays.asList(
                new Pessoa("Ana", 25),
                new Pessoa("Bruno", 30),
                new Pessoa("Carlos", 22)
        );
        model.addAttribute("pessoas", pessoas);
        return "lista-pessoas-pojo";
    }

    @GetMapping("/pessoas-record")
    public String listarPessoasRecord(Model model) {
        List<PessoaRecord> pessoas = List.of(
                new PessoaRecord("George", 35),
                new PessoaRecord("Camila", 40),
                new PessoaRecord("Leo", 22)
        );
        model.addAttribute("pessoas", pessoas);
        return "lista-pessoas-record";
    }

    @GetMapping("/pedidos")
    public String listarPedidos(Model model) {
        List<Pedido> pedidos = List.of(); // Lista vazia para testar o tratamento
        model.addAttribute("pedidos", pedidos);
        return "lista-pedidos";
    }

    @GetMapping("/filtros")
    public String filtros(Model model) {
        model.addAttribute("lista", List.of("StackSpot", "Spring", "Pebble"));
        model.addAttribute("nome", "George");
        model.addAttribute("valor", 1234.5678);
        return "filtros";
    }
}