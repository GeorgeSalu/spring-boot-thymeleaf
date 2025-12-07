package br.com.twproject.web.clients.dto;

import br.com.twproject.core.models.Client;
import br.com.twproject.core.utils.StringUtils;

public class ClientViewModel {

    private Long id;

    private String name;

    private String phone;

    private String email;

    public ClientViewModel() {
    }

    public ClientViewModel(Long id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return StringUtils.formatPhone(phone);
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static ClientViewModel of(Client client) {
        return new ClientViewModel(client.getId(), client.getName(), client.getPhone(), client.getEmail());
    }
}
