package com.app.validations;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.app.cms.Customer;
import com.app.custom_exception.DuplicateCustomerException;
import com.app.custom_exception.NotAlphanumericPasswordException;
import com.app.custom_exception.NotEmailFormatException;
import com.app.custom_exception.ServicePlanDetailsMisMatchedException;
import com.app.custom_exception.ServicePlanNotFoundException;
import com.app.enums.ServicePlan;

public class CustomerValidation {

    public static Customer validAllInputs(String firstName, String lastName, String email, String pass,
                                          double registrationAmount, String dateOfBirth, String servicePlan, Map<String,Customer> list) throws DuplicateCustomerException, ServicePlanDetailsMisMatchedException, ServicePlanNotFoundException, NotEmailFormatException, NotAlphanumericPasswordException {

        String validatedEmail = validateDuplicateEmailAndCheckPattern(list, email);
        LocalDate dob = LocalDate.parse(dateOfBirth);
        ServicePlan servPlan = validateServicePlanAndCharges(servicePlan, registrationAmount);
        String password = validatePassword(pass);

        return new Customer(firstName, lastName, validatedEmail, password, registrationAmount, dob, servPlan);
    }

    public static String validateDuplicateEmailAndCheckPattern(Map<String,Customer> list, String email) throws DuplicateCustomerException, NotEmailFormatException {

//        Customer customer = new Customer(email);

        // Check for duplicate email(already registered)
        if (list.containsKey(email)) {
            throw new DuplicateCustomerException("Already registered Email....Use another email");
        }

        // Check for email contains .com , .org, .net and @ in between
        String emailRegex = "^[A-Za-z0-9+_.-]+@(?:[A-Za-z0-9-]+\\.)+(com|org|net)$";

        // Create a pattern from the regex
//        Pattern pattern = Pattern.compile(emailRegex);

        // Match the given string with the pattern
//        boolean matches = pattern.matcher(email).matches();

        // Return validation result
        if (email.matches(emailRegex)) {
            return email;
        } else {
            throw new NotEmailFormatException("Invalid email. It should contain .com, .org, or .net and include '@'.");
        }

    }

    public static ServicePlan validateServicePlanAndCharges(String plan, double charges) throws ServicePlanDetailsMisMatchedException, ServicePlanNotFoundException {

        for (ServicePlan servicePlan : ServicePlan.values()) {
            if (servicePlan.name().equalsIgnoreCase(plan)) {
                if (servicePlan.getCharges() == charges) {
                    return servicePlan;
                }
                throw new ServicePlanDetailsMisMatchedException("Service plan details not matched");
            }
        }
        throw new ServicePlanNotFoundException("Service plan does not exist...Please check again.");
    }

    public static String validatePassword(String password) throws NotAlphanumericPasswordException {
        return password;
    }
}