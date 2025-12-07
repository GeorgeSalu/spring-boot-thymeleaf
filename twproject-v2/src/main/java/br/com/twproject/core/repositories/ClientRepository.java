package br.com.twproject.core.repositories;

import br.com.twproject.core.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
