package com.example.demo.student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.example.demo.student.exception.BadRequestException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class) //A better way to do what is commented below
public class StudentServiceTest {

	@Mock
	private StudentRepository studentRepository;
	private StudentService underTest;
//	private AutoCloseable openMocks;
	
	@BeforeEach
	void setUp() {
//		openMocks = MockitoAnnotations.openMocks(this);
		underTest = new StudentService(studentRepository);
	}
	
//	@AfterEach
//	void tearDown() throws Exception {
//		openMocks.close();
//	}
	
	@Test
	//@Disabled
	void canGetAllStudents() {
	
		//when
		underTest.getAllStudents();
		
		//then
		verify(studentRepository).findAll();
		
	}
	
	@Test
	//@Disabled
	void canAddStudent() {
		
		//given		
		Student student = new Student("Leonardo", "leonardo@gmail.com", Gender.MALE);
		
		//when
		underTest.addStudent(student);
		
		//then
		ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
		verify(studentRepository).save(studentArgumentCaptor.capture());
		
		Student capturedStudent = studentArgumentCaptor.getValue();
		assertThat(student).isEqualTo(capturedStudent);
		
	}

	@Test
	//@Disabled
	void willThrowWhenEmailIsTaken() {

		//given
		Student student = new Student("Leonardo", "leonardo@gmail.com", Gender.MALE);

		//setting the value of a condition
		given(studentRepository.selectExistsEmail(anyString())).willReturn(true);

		//when
		//then
		assertThatThrownBy(() -> underTest.addStudent(student))
				.isInstanceOf(BadRequestException.class)
				.hasMessageContaining("Email " + student.getEmail() + " taken");

		verify(studentRepository, never()).save(any());

	}

	@Test
	@Disabled
	void deleteStudent() {
		
		//given		
		Student student = new Student("Leonardo", "leonardo@gmail.com", Gender.MALE);
		
		//when
		underTest.addStudent(student);
		
		//then
		ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
		verify(studentRepository).save(studentArgumentCaptor.capture());
		
		Student capturedStudent = studentArgumentCaptor.getValue();
		assertThat(student).isEqualTo(capturedStudent);
		
	}
	
}
