package br.com.twproject.web.clients.controller;

import br.com.twproject.core.repositories.ClientRepository;
import br.com.twproject.web.clients.dto.ClientForm;
import br.com.twproject.web.clients.dto.ClientViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public ModelAndView index() {
        var clients = clientRepository.findAll()
                .stream()
                .map((client) -> ClientViewModel.of(client))
                .toList();
        var model = Map.of("clients", clients);
        return new ModelAndView("clients/index", model);
    }

    @GetMapping("/create")
    public ModelAndView create() {
        var model = Map.of("clientForm", new ClientForm());
        return new ModelAndView("clients/create", model);
    }

    @PostMapping("/create")
    public String create(ClientForm clientForm) {
        var client = clientForm.toClient();
        clientRepository.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        var client = clientRepository.findById(id);
        if (!client.isPresent()) {
            throw new NoSuchElementException("Cliente não encontrado");
        }
        var model = Map.of("clientForm", ClientForm.of(client.get()));
        return new ModelAndView("clients/edit", model);
    }
}
