package com.example.security.service;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.security.domain.Agendamento;
import com.example.security.domain.Horario;
import com.example.security.repository.AgendamentoRepository;

@Service
public class AgendamentoService {
	
	@Autowired
	private AgendamentoRepository repository;

	@Transactional(readOnly = true)
	public List<Horario> buscarHorarioNaoAgendadosPorMedicoIdEData(Long id, LocalDate data) {

		return repository.findByMedicoIdAndDataNotHorarioAgendado(id, data);
	}

	@Transactional(readOnly = false)
	public void salvar(Agendamento agendamento) {
		
		repository.save(agendamento);
	}

	@Transactional(readOnly = true)
	public Object buscarHistoricoPorPacienteEmail(String username, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	public Object buscarHistoricoPorMedicoEmail(String username, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
