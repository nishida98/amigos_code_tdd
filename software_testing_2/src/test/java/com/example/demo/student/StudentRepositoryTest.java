package com.example.demo.student;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class StudentRepositoryTest {

	@Autowired
	private StudentRepository underTest;
	
	@AfterEach
	void delete() {
		underTest.deleteAll();
	}
	
	@Test
	void itShouldCheckIfStudentSelectEmailExists() {

		//given
		String emailTest = "leonardo@gmail.com";
		Student student = new Student("Leonardo", emailTest, Gender.MALE);
		underTest.save(student);
		
		//when
		Boolean exists = underTest.selectExistsEmail(emailTest);
		
		//then
		assertThat(exists).isTrue();
		
	}
	
	@Test
	void itShouldCheckIfStudentSelectEmailDoesNotExists() {

		//given
		String emailTest = "leonardo@gmail.com";
		
		//when
		Boolean exists = underTest.selectExistsEmail(emailTest);
		
		//then
		assertThat(exists).isFalse();
		
	}
	
}
