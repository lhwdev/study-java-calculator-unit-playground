package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("계산기")
public class CalculatorTest {
	
	@DisplayName("사칙연산")
	@Nested
	class ArithmeticTest {
		@DisplayName("인자 두 개를 받아서 더한 값을 반환한다.")
		@ParameterizedTest
		@MethodSource("binaryArgumentsOverloaded")
		void testAdd(int a, int b) {
			// given
			Calculator calculator = new Calculator();
			
			// when
			int result = calculator.add(a, b);
			
			// then
			assertEquals(result, a + b);
		}
		
		@DisplayName("인자 두 개를 받아서 뺸 값을 반환한다.")
		@ParameterizedTest
		@MethodSource("binaryArguments")
		void testSubtract(int a, int b) {
			// given
			Calculator calculator = new Calculator();
			
			// when
			int result = calculator.subtract(a, b);
			
			// then
			assertEquals(result, a - b);
		}
		
		@DisplayName("인자 두 개를 받아서 곱한 값을 반환한다.")
		@ParameterizedTest
		@MethodSource("binaryArguments")
		void testMultiply(int a, int b) {
			// given
			Calculator calculator = new Calculator();
			
			// when
			int result = calculator.multiply(a, b);
			
			// then
			assertEquals(result, a * b);
		}
		
		@DisplayName("인자 두 개를 받아서 나눈 값을 반환한다.")
		@ParameterizedTest
		@MethodSource("binaryArguments")
		void testDivide(int a, int b) {
			if(b == 0) return;
			
			// given
			Calculator calculator = new Calculator();
			
			// when
			int result = calculator.divide(a, b);
			
			// then
			assertEquals(result, a / b);
		}
		
		private static Stream<Arguments> binaryArguments() {
			return Stream.of(
					Arguments.arguments(0, 0),
					Arguments.arguments(0, 2),
					Arguments.arguments(0, -3),
					Arguments.arguments(2, 4),
					Arguments.arguments(-2, -8),
					Arguments.arguments(3, 5),
					Arguments.arguments(1, -1),
					Arguments.arguments(100, 200)
			);
		}
		
		private static Stream<Arguments> binaryArgumentsOverloaded() {
			return IntStream.range(-10, 20)
					.boxed()
					.flatMap(a -> IntStream.range(-10, 20).mapToObj(b -> Arguments.arguments(a, b)));
		}
	}
}
