package com.example.security.controller;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.security.domain.Agendamento;
import com.example.security.domain.Especialidade;
import com.example.security.domain.Paciente;
import com.example.security.domain.PerfilTipo;
import com.example.security.service.AgendamentoService;
import com.example.security.service.EspecialidadeService;
import com.example.security.service.PacienteService;

@Controller
@RequestMapping("agendamentos")
public class AgendamentoController {
	
	@Autowired
	private AgendamentoService agendamentoService;
	
	@Autowired
	private PacienteService pacienteService;

	@Autowired
	private EspecialidadeService especialidadeService;
	
	@GetMapping({"/agendar"})
	public String agendarConsulta(Agendamento agendamento) {
		
		return "agendamento/cadastro";
	}
	
	@GetMapping("/horario/medico/{id}/data/{data}")
	public ResponseEntity<?> getHorarios(@PathVariable("id") Long id,
			                             @PathVariable("data") @DateTimeFormat(iso = ISO.DATE) LocalDate data) {
		
		return ResponseEntity.ok(agendamentoService.buscarHorarioNaoAgendadosPorMedicoIdEData(id, data));
	}

	@PostMapping({"/salvar"})
	public String salvar(Agendamento agendamento,RedirectAttributes attr,@AuthenticationPrincipal User user) {
		
		Paciente paciente = pacienteService.buscarPorUsuarioEmail(user.getUsername());
		
		String titulo = agendamento.getEspecialidade().getTitulo();
		
		Especialidade especialidade = especialidadeService
				.buscarPorTitulo(new String[] {titulo})
				.stream()
				.findFirst()
				.get();
		
		agendamento.setEspecialidade(especialidade);
		agendamento.setPaciente(paciente);
		
		agendamentoService.salvar(agendamento);
		
		attr.addFlashAttribute("sucesso", "sua consulta foi agendada com sucesso");
		
		return "redirect:/agendamentos/agendar";
	}
	
	@GetMapping({"/historico/paciente", "/historico/consultas"})
	public String historico() {
		
		return "agendamento/historico-paciente";
	}
	
	@GetMapping({"/datatables/server/historico"})
	public ResponseEntity<?> historicoAgendamentoPorPaciente(HttpServletRequest request,@AuthenticationPrincipal User user) {
		
		if(user.getAuthorities().contains(new SimpleGrantedAuthority(PerfilTipo.PACIENTE.getDesc()))) {
		
			return ResponseEntity.ok(agendamentoService.buscarHistoricoPorPacienteEmail(user.getUsername(), request));
		}
		
		if(user.getAuthorities().contains(new SimpleGrantedAuthority(PerfilTipo.MEDICO.getDesc()))) {
			
			return ResponseEntity.ok(agendamentoService.buscarHistoricoPorMedicoEmail(user.getUsername(), request));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/editar/consulta/{id}")
	public String preEditarConsultaPaciente(@PathVariable("id") Long id,
											ModelMap model,@AuthenticationPrincipal User user) {
		
		Agendamento agendamento = agendamentoService.buscarPorId(id);
		
		model.addAttribute("agendamento", agendamento);
		return "agendamento/cadastro";
	}
	
	@PostMapping("/editar")
	public String editarConsulta(Agendamento agendamento,RedirectAttributes attr,@AuthenticationPrincipal User user) {
		
		String titulo = agendamento.getEspecialidade().getTitulo();
		
		Especialidade especialidade = especialidadeService
				.buscarPorTitulo(new String[] {titulo})
				.stream()
				.findFirst()
				.get();
		agendamento.setEspecialidade(especialidade);
		
		agendamentoService.editar(agendamento, user.getUsername());
		attr.addFlashAttribute("sucesso", "Sua consulta foi alterada com sucesso");
		return "redirect:/agendamentos/agendar";
	}
	
}







