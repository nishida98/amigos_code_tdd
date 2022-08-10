package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class DemoApplicationTests {

	Calculator underTest = new Calculator();
	
	@Test
	void itShouldAddNumbers() {
	
		//given
		int numberOne = 10;
		int numberTwo = 20;
		int expectedResult = numberOne + numberTwo;
		
		//when
		int result = underTest.add(numberOne, numberTwo);
		
		//then
		assertThat(result).isEqualTo(expectedResult);
	
	}

	class Calculator {
		
		int add(int a, int b) { 
			return a + b; 
		}; 
		
	}
	
}
