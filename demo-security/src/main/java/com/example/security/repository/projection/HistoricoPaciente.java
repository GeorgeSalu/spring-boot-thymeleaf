package com.example.security.repository.projection;

import com.example.security.domain.Especialidade;
import com.example.security.domain.Medico;
import com.example.security.domain.Paciente;

public interface HistoricoPaciente {

	Long getId();
	Paciente getPaciente();
	String getDataConsulta();
	Medico getMedico();
	Especialidade getEspecialidade();
	
}
