package com.codedynamic.clinica;

import com.codedynamic.clinica.dao.postgresql.PSQLPaciente;
import com.codedynamic.clinica.modelo.Paciente;

public class Tester {

	public static void main(String[] args) {
		PSQLPaciente pacienteTemp = new PSQLPaciente();
		Paciente paciente = pacienteTemp.obtenerPorID((short)6);
		
		System.out.println(paciente.getFecha_nacimiento());
	}

}
