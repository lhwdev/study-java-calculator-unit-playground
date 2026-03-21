package calculator;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("문자열 계산기")
public class StringCalculatorTest {
	@DisplayName("각 숫자의 합을 반환")
	@Test
	void testSumValues() {
		assertThat(new StringCalculator("1").executeSum()).isEqualTo(1);
		assertThat(new StringCalculator("2,3").executeSum()).isEqualTo(5);
		assertThat(new StringCalculator("1:4").executeSum()).isEqualTo(5);
		assertThat(new StringCalculator("2:1,5").executeSum()).isEqualTo(8);
	}
	
	@DisplayName("구분자 변경시 각 숫자의 합을 반환")
	@Test
	void testParsing() {
		assertThat(new StringCalculator("//;\n4;5;6").executeSum())
				.isEqualTo(15);
		
		assertThat(new StringCalculator("//hallo\n" + "4" + "hallo" + "5" + "hallo" + "6").executeSum())
				.isEqualTo(15);
		
		assertThat(new StringCalculator("//11\n" + "4" + "11" + "5" + "11" + "6").executeSum())
				.isEqualTo(4115116);
	}
	
	@DisplayName("'표현식의 끝' 오류 발생")
	@Test
	void testThrowEndOfExpression() {
		assertThrowMessageIncludes(
				"숫자를 예상했지만 표현식이 끝",
				() -> new StringCalculator("").executeSum(),
				() -> new StringCalculator("1,").executeSum()
		);
	}
	
	@DisplayName("잘못된 숫자 입력시 오류 발생")
	@Test
	void testThrowMalformedNumber() {
		assertThrowMessageIncludes(
				"올바른 숫자를 입력",
				() -> new StringCalculator("abc").executeSum(),
				() -> new StringCalculator("/k").executeSum(),
				() -> new StringCalculator("\n").executeSum(),
				() -> new StringCalculator("1:aa").executeSum()
		);
	}
	
	@DisplayName("너무 큰 숫자 입력시 오류 발생")
	@Test
	void testThrowLargeNumber() {
		assertThrowMessageIncludes(
				"올바르지 않은 숫자",
				() -> new StringCalculator("12345678901234567890").executeSum()
		);
	}
	
	@DisplayName("'숫자 뒤에는 구분자가 와야...' 오류 발생")
	@Test
	void testThrowSeparatorExpected() {
		assertThrowMessageIncludes(
				"숫자 뒤에는 구분자가 와",
				() -> new StringCalculator("1a").executeSum(),
				() -> new StringCalculator("12345;;").executeSum()
		);
	}
	
	@DisplayName("execute는 한번만 호출하라는 오류 발생")
	@Test
	void testThrowCallExecuteOnce() {
		assertThrowMessageIncludes(
				"execute는 한번만 호출",
				() -> {
					StringCalculator calculator = new StringCalculator("1,2");
					calculator.executeSum();
					calculator.executeSum();
				}
		);
	}
	
	@DisplayName("음수 입력시 오류 발생")
	@Test
	void testThrowOnNegativeNumber() {
		assertThrowMessageIncludes(
				"음수는 지원되지 않습니다",
				() -> new StringCalculator("-3").executeSum(),
				() -> new StringCalculator("1,-5").executeSum()
		);
	}
	
	@DisplayName("구분자를 잘못 입력할 경우 오류 발생")
	@Test
	void testMalformedSeparator() {
		assertThrowMessageIncludes(
				"구분자 입력 형식이 잘못",
				() -> new StringCalculator("//").executeSum()
		);
	}
	
	
	/// 유틸리티
	
	private void assertThrowMessageIncludes(String message, ThrowableAssert.ThrowingCallable... executables) {
		for(ThrowableAssert.ThrowingCallable executable : executables) {
			assertThatThrownBy(executable).hasMessageContaining(message);
		}
	}
}
