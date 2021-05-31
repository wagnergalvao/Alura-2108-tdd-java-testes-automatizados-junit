package br.com.alura.tdd.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import br.com.alura.tdd.modelo.Funcionario;

class BonusServiceTest {

	BonusService service = new BonusService();
	Funcionario funcionario;

	private Faker fake = new Faker(new Locale("pt-BR"));
	private BigDecimal _salario, _bonus, _expectBonus;
	private int _limite = 10_000;

	@Test
	void deveRetornarBonusZeradoParaSalarioAcimaDoLimite() {

		_salario = new BigDecimal(fake.random().nextInt(9999) + _limite);
		_expectBonus = _salario.multiply(new BigDecimal("0"));
		funcionario = new Funcionario(fake.name().name(),
				LocalDate.now().minusDays(fake.random().nextInt(365)), _salario);
		_bonus = service.calcularBonus(funcionario);

		assertEquals(_expectBonus, _bonus);
	}

	@Test
	void deveRetornarBonusDeDezPorcentoParaSalarioAbaixoDoLimite() {

		_salario = new BigDecimal(fake.random().nextInt(9999));
		_expectBonus = _salario.multiply(new BigDecimal("0.1"));
		funcionario = new Funcionario(fake.name().name(),
				LocalDate.now().minusDays(fake.random().nextInt(365)), _salario);
		_bonus = service.calcularBonus(funcionario);

		assertEquals(_expectBonus, _bonus);
	}

	@Test
	void deveRetornarBonusDeDezPorcentoParaSalarioIgualOLimite() {

		_salario = new BigDecimal(_limite);
		_expectBonus = _salario.multiply(new BigDecimal("0.1"));
		funcionario = new Funcionario(fake.name().name(),
				LocalDate.now().minusDays(fake.random().nextInt(365)), _salario);
		_bonus = service.calcularBonus(funcionario);

		assertEquals(_expectBonus, _bonus);
	}
}
