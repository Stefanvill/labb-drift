package se.iths.stefan.labbdrift.validator;

import org.springframework.stereotype.Component;
import se.iths.stefan.labbdrift.exception.InvalidCustomerException;

@Component
public class CustomerValidator {

    public void validateCustomerFirstName(String firstName) {
        if (firstName == null || firstName.isBlank()) {
            throw new InvalidCustomerException("First name can not be empty");
        }
    }

    public void validateCustomerLastName(String lastName) {
        if (lastName == null || lastName.isBlank()) {
            throw new InvalidCustomerException("Last name can not be empty");
        }
    }

    public void validateCustomerEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new InvalidCustomerException("Email can not be empty");
        }
    }
}
