package com.example.security.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.security.datatables.Datatables;
import com.example.security.datatables.DatatablesColunas;
import com.example.security.domain.Agendamento;
import com.example.security.domain.Horario;
import com.example.security.repository.AgendamentoRepository;
import com.example.security.repository.projection.HistoricoPaciente;

@Service
public class AgendamentoService {
	
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@Autowired
	private Datatables datatables;

	@Transactional(readOnly = true)
	public List<Horario> buscarHorarioNaoAgendadosPorMedicoIdEData(Long id, LocalDate data) {

		return agendamentoRepository.findByMedicoIdAndDataNotHorarioAgendado(id, data);
	}

	@Transactional(readOnly = false)
	public void salvar(Agendamento agendamento) {
		
		agendamentoRepository.save(agendamento);
	}

	@Transactional(readOnly = true)
	public Map<String, Object> buscarHistoricoPorPacienteEmail(String email, HttpServletRequest request) {
		
		datatables.setRequest(request);
		datatables.setColunas(DatatablesColunas.AGENDAMENTOS);
		
		Page<HistoricoPaciente> page = agendamentoRepository.findHistoricoByPacienteEmail(email, datatables.getPageable());
		
		return datatables.getResponse(page);
	}

	@Transactional(readOnly = true)
	public Map<String, Object> buscarHistoricoPorMedicoEmail(String email, HttpServletRequest request) {
		datatables.setRequest(request);
		datatables.setColunas(DatatablesColunas.AGENDAMENTOS);
		
		Page<HistoricoPaciente> page = agendamentoRepository.findHistoricoByMedicoEmail(email, datatables.getPageable());
		
		return datatables.getResponse(page);
	}

	@Transactional(readOnly = true)
	public Agendamento buscarPorId(Long id) {

		return agendamentoRepository.findById(id).get();
	}

}
