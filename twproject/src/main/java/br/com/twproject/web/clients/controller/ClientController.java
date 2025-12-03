package br.com.twproject.web.clients.controller;

import br.com.twproject.core.repositories.ClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public ModelAndView index() {
        var clients = clientRepository.findAll();
        var model = Map.of("clients", clients);
        return new ModelAndView("clients/index", model);
    }
}
