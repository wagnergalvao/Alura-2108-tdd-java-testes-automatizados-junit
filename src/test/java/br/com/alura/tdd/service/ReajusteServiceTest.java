package br.com.alura.tdd.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import br.com.alura.tdd.modelo.Desempenho;
import br.com.alura.tdd.modelo.Funcionario;

public class ReajusteServiceTest {

	private Faker fake = new Faker(new Locale("pt-BR"));

	@Test
	public void deveRetornarReajusteDeTresPorcentoParaDesemepenhoADesejar() {
		ReajusteService service = new ReajusteService();
		Funcionario funcionario = new Funcionario(fake.name().name(),
				LocalDate.now().minusDays(fake.random().nextInt(365)), new BigDecimal(1000.00));
		service.calcularDesempenho(funcionario, Desempenho.A_DESEJAR);
		System.out.println(funcionario.getNome());
		System.out.println(funcionario.getDataAdmissao());
		System.out.println(funcionario.getSalario());
		assertEquals(new BigDecimal("1030.00"), funcionario.getSalario());
	}
}
