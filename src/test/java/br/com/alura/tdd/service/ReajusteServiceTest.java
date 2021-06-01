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

	ReajusteService service = new ReajusteService();
	Funcionario funcionario;

	private Faker fake = new Faker(new Locale("pt-BR"));

	@Test
	public void deveRetornarReajusteDeTresPorcentoParaDesemepenhoADesejar() {
		funcionario = new Funcionario(fake.name().name(),
				LocalDate.now().minusDays(fake.random().nextInt(365)), new BigDecimal("1000.00"));
		service.calcularDesempenho(funcionario, Desempenho.A_DESEJAR);
		assertEquals(new BigDecimal("1030.00"), funcionario.getSalario());
	}

	@Test
	public void deveRetornarReajusteDeTresPorcentoParaDesemepenhoBom() {
		service = new ReajusteService();
		funcionario = new Funcionario(fake.name().name(),
				LocalDate.now().minusDays(fake.random().nextInt(365)), new BigDecimal("2000.00"));
		service.calcularDesempenho(funcionario, Desempenho.BOM);
		assertEquals(new BigDecimal("2300.00"), funcionario.getSalario());
	}

	@Test
	public void deveRetornarReajusteDeTresPorcentoParaDesemepenhoOtimo() {
		service = new ReajusteService();
		funcionario = new Funcionario(fake.name().name(),
				LocalDate.now().minusDays(fake.random().nextInt(365)), new BigDecimal("3000.00"));
		service.calcularDesempenho(funcionario, Desempenho.OTIMO);
		assertEquals(new BigDecimal("3600.00"), funcionario.getSalario());
	}
}
