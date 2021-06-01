package br.com.alura.tdd.service;

import br.com.alura.tdd.modelo.Desempenho;
import br.com.alura.tdd.modelo.Funcionario;

public class ReajusteService {

	public void calcularDesempenho(Funcionario funcionario, Desempenho desempenho) {
		funcionario.reajustarSalario(funcionario.getSalario().multiply(desempenho.percentualReajuste()));
	}

}
