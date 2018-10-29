package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Student.class)
										.buildSessionFactory();
							
		// create session
		Session session = factory.getCurrentSession();
						
		try {
			session.beginTransaction();
			
			List<Student> students = session.createQuery("from Student").getResultList();
			
			displayStudents(students);
			
			// query students: lastname='Doe'
			students = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
			
			System.out.println("\nStudents who have last name of Doe");
			displayStudents(students);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			session.close();
			factory.close();
		}
		
	}

	private static void displayStudents(List<Student> students) {
		for(Student student : students) {
			System.out.println(student);
		}
	}

}
