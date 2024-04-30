package com.cms.utility;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import com.cms.enums.ServicePlan;
import com.cms.exceptions.InvalidCredentials;
import com.cms.tester.Customer;
import static com.cms.validations.CustomerValidation.*;

public class SignUp {
//	String firstName, String lastName, String email, String password, double registrationAmount,
//	LocalDate dob, ServicePlan plan
	public static Customer signUp(ArrayList<Customer> c) throws InvalidCredentials {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter the first name");
		String firstname = sc.next();
		System.out.println("enter the last name");
		String lastname = sc.next();
		System.out.println("enter the email");
		String email = sc.next();
		checkForDup(email, c);
		System.out.println("enter the password");
		String password = sc.next();
		System.out.println("enter the registrationAmount");
		double registrationAmount = sc.nextDouble();
		System.out.println("enter the dob yyyy-MM-dd");
		String dob = sc.next();
		System.out.println("enter the plan");
		String plan = sc.next();

		LocalDate dob1 = LocalDate.parse(dob);

		Customer customer = new Customer(firstname, lastname, email, password, registrationAmount, dob1,
				ServicePlan.valueOf(plan));
		System.out.println(customer);
		sc.close();
		return customer;
	}
	
}