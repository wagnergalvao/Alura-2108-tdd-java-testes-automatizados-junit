package br.com.alura.tdd.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import br.com.alura.tdd.modelo.Desempenho;
import br.com.alura.tdd.modelo.Funcionario;

public class ReajusteServiceTest {

	ReajusteService service;
	Funcionario funcionario;

	private Faker fake = new Faker(new Locale("pt-BR"));

	@BeforeAll
	public static void antesDeTodos() {
		System.out.println("ANTES DE TODOS");
	}

	@BeforeEach
	public void inicializar() {
		this.service = new ReajusteService();
		this.funcionario = new Funcionario(fake.name().name(), LocalDate.now().minusDays(fake.random().nextInt(365)),
				new BigDecimal("1000.00"));
		System.out.println(
				funcionario.getNome() + " - " + funcionario.getDataAdmissao() + " - " + funcionario.getSalario());
	}

	@AfterEach
	public void finalizar() {
		System.out.println(
				funcionario.getNome() + " - " + funcionario.getDataAdmissao() + " - " + funcionario.getSalario());
		System.out.println("");
	}

	@AfterAll
	public static void depoisDeTodos() {
		System.out.println("DEPOIS DE TODOS");
	}

	@Test
	public void deveRetornarReajusteDeTresPorcentoParaDesemepenhoADesejar() {
		service.calcularDesempenho(funcionario, Desempenho.A_DESEJAR);
		assertEquals(new BigDecimal("1030.00"), funcionario.getSalario());
	}

	@Test
	public void deveRetornarReajusteDeQuizePorcentoParaDesemepenhoBom() {
		service = new ReajusteService();
		service.calcularDesempenho(funcionario, Desempenho.BOM);
		assertEquals(new BigDecimal("1150.00"), funcionario.getSalario());
	}

	@Test
	public void deveRetornarReajusteDeVintePorcentoParaDesemepenhoOtimo() {
		service = new ReajusteService();
		service.calcularDesempenho(funcionario, Desempenho.OTIMO);
		assertEquals(new BigDecimal("1200.00"), funcionario.getSalario());
	}
}
