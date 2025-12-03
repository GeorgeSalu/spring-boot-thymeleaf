package br.com.twproject.web.clients.dto;

import br.com.twproject.core.models.Client;

public class ClientForm {

    private String name;

    private String email;

    private String phone;


    public ClientForm() {
    }

    public ClientForm(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String cleanedPhone() {
        return phone.replaceAll("[^0-9]", "");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Client toClient() {
        return new Client(this.name, this.email, cleanedPhone());
    }
}
