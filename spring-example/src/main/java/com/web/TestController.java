package com.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TestController {

	@GetMapping("/teste")
	public String action(Model model) {
		model.addAttribute("nome", "george");
		return "teste";
	}

    @GetMapping("/teste2")
    public ModelAndView action2() {
        var modelAndView = new ModelAndView("teste");
        modelAndView.addObject("nome", "treinaweb");
        return modelAndView;
    }

    @GetMapping("/teste3")
    public String action3(Model model) {
        model.addAttribute("client", new Client());
        return "form";
    }

    @PostMapping("/teste3")
    public String action4(Client client) {
        System.out.println(client);
        return "form";
    }

    // QueryString
    // https://www.treinaweb.com.br/blog/posts?q=spring&age=27&skills=python,java,csharp
    // https://www.treinaweb.com.br/blog/posts?q=spring&age=27&skills=python&skills=java&skills=csharp
    @GetMapping("/teste5")
    public String action5(@RequestParam(name = "q", required = false, defaultValue = "treinaweb") String q,
                          @RequestParam int age,
                          @RequestParam List<String> skills) {
        System.out.println(q);
        System.out.println(age);
        System.out.println(skills);
        return "teste";
    }

    // http://localhost:8080/produtos/1
    // http://localhost:8080/produtos/2
    // http://localhost:8080/produtos/3
    @GetMapping("/teste6/{id}")
    public String action6(@PathVariable("id") Long id) {
        System.out.println(id);
        return "teste";
    }

}
