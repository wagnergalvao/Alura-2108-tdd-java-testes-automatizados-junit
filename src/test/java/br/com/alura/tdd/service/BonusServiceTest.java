package br.com.alura.tdd.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

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
	private BigDecimal _salario;
	private int _limite = 10_000;

	@Test
	public void naoDeveCalcularBonusParaSalarioAcimaDoLimite_1() {

		_salario = new BigDecimal(fake.random().nextInt(9999) + _limite);
		funcionario = new Funcionario(fake.name().name(), LocalDate.now().minusDays(fake.random().nextInt(365)),
				_salario);

		assertThrows(IllegalArgumentException.class, () -> service.calcularBonus(funcionario));
	}

	@Test
	public void naoDeveCalcularBonusParaSalarioAcimaDoLimite_2() {

		_salario = new BigDecimal(fake.random().nextInt(9999) + _limite);
		funcionario = new Funcionario(fake.name().name(), LocalDate.now().minusDays(fake.random().nextInt(365)),
				_salario);

		try {
			service.calcularBonus(funcionario);
			fail("Não impediu o cálculo do bônus");

		} catch (Exception e) {
			assertEquals("Salário acima do limite não pode receber bônus", e.getMessage());
		}

	}

	@Test
	public void deveRetornarBonusDeDezPorcentoParaSalarioAbaixoDoLimite() {

		_salario = new BigDecimal(fake.random().nextInt(9999));
		funcionario = new Funcionario(fake.name().name(), LocalDate.now().minusDays(fake.random().nextInt(365)),
				_salario);

		assertEquals(bonusEsperado(_salario), service.calcularBonus(funcionario));
	}
	@Test
	public void deveRetornarBonusDeDezPorcentoParaSalarioIgualOLimite() {

		_salario = new BigDecimal(_limite);
		funcionario = new Funcionario(fake.name().name(), LocalDate.now().minusDays(fake.random().nextInt(365)),
				_salario);

		assertEquals(bonusEsperado(_salario), service.calcularBonus(funcionario));
	}

	private BigDecimal bonusEsperado(BigDecimal _salario) {
		return _salario.multiply(new BigDecimal("0.1"));
	}

}
