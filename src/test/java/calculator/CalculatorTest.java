package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
	
	@DisplayName("인자 두 개를 받아서 더한 값을 반환한다.")
	@Test
	void testAdd() {
		// given
		Calculator calculator = new Calculator();
		
		// when
		int result = calculator.add(1, 2);
		
		// then
		assertEquals(3, result);
	}
	
	@DisplayName("인자 두 개를 받아서 뺸 값을 반환한다.")
	@Test
	void testSubtract() {
		// given
		Calculator calculator = new Calculator();
		
		// when
		int result = calculator.subtract(5, 1);
		
		// then
		assertEquals(4, result);
	}
	
	@DisplayName("인자 두 개를 받아서 곱한 값을 반환한다.")
	@Test
	void testMultiply() {
		// given
		Calculator calculator = new Calculator();
		
		// when
		int result = calculator.multiply(3, 5);
		
		// then
		assertEquals(15, result);
	}
	
	@DisplayName("인자 두 개를 받아서 나눈 값을 반환한다.")
	@Test
	void testDivide() {
		// given
		Calculator calculator = new Calculator();
		
		// when
		int result = calculator.divide(10, 5);
		
		// then
		assertEquals(2, result);
	}
}
