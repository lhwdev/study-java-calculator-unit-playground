package calculator;

public class StringCalculator {
	String expression;
	int offset = 0;
	String[] separators = {",", ":"};
	
	StringCalculator(String expression) {
		this.expression = expression;
	}
	
	
	int executeSum() {
		if(offset != 0) throw new RuntimeException("execute는 한번만 호출해주세요!");
		
		int result = 0;
		parseSeparatorInput();
		
		Integer output;
		if((output = parseInteger()) == null)
			throw new RuntimeException("올바른 숫자를 입력해주세요.");
		result += output;
		
		while(offset < expression.length()) {
			if(parseSeparator() == null)
				throw new RuntimeException("숫자 뒤에는 구분자가 와야 합니다.");
			if((output = parseInteger()) == null)
				throw new RuntimeException("올바른 숫자를 입력해주세요.");
			result += output;
		}
		
		return result;
	}
	
	
	/// 파서
	
	void parseSeparatorInput() {
		String separatorPrefix = "//";
		String separatorSuffix = "\n";
		if(!expression.startsWith(separatorPrefix, offset)) return;
		
		int separatorEnd = expression.indexOf(separatorSuffix, offset);
		if(separatorEnd == -1)
			throw new RuntimeException("구분자 입력 형식이 잘못되었습니다. //과 \\n 사이에 구분자를 넣어주세요.");
		
		String separator = expression.substring(offset + separatorPrefix.length(), separatorEnd);
		separators = new String[] {separator};
		offset = separatorEnd + separatorSuffix.length();
	}
	
	String parseSeparator() {
		for(String separator : separators) {
			if(!expression.startsWith(separator, offset)) continue;
			
			offset += separator.length();
			return separator;
		}
		
		return null;
	}
	
	Integer parseInteger() {
		int startOffset = offset;
		if(startOffset >= expression.length())
			throw new RuntimeException("숫자를 예상했지만 표현식이 끝났습니다...");
		
		char first = expression.charAt(startOffset);
		if(!isDigit(first)) {
			if(first == '-') throw new RuntimeException("음수는 지원되지 않습니다.");
			return null;
		}
		
		offset++;
		
		while(offset < expression.length()) {
			char current = expression.charAt(offset);
			if(isDigit(current)) offset++;
			else break;
		}
		
		try {
			return Integer.parseInt(expression.substring(startOffset, offset));
		} catch(NumberFormatException e) {
			throw new RuntimeException("올바르지 않은 숫자입니다.", e);
		}
	}
	
	
	/// 유틸리티
	
	private boolean isDigit(char c) {
		return "0123456789".indexOf(c) != -1;
	}
}
