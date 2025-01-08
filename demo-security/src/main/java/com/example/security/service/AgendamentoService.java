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
import com.example.security.exception.AcessoNegadoException;
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

	@Transactional(readOnly = false)
	public void editar(Agendamento agendamento, String email) {
		
		Agendamento ag = buscarPorIdEUsuario(agendamento.getId(), email);
		ag.setDataConsulta(agendamento.getDataConsulta());
		ag.setEspecialidade(agendamento.getEspecialidade());
		ag.setHorario(agendamento.getHorario());
		ag.setMedico(agendamento.getMedico());
		
	}

	@Transactional(readOnly = true)
	public Agendamento buscarPorIdEUsuario(Long id, String email) {

		return agendamentoRepository.findByIdAndPacienteOrMedicoEmail(id, email)
				.orElseThrow(() -> new AcessoNegadoException("acesso negado ao usuario "+email));
	}

	@Transactional(readOnly = false)
	public void remover(Long id) {
		
		agendamentoRepository.deleteById(id);
	}

}
