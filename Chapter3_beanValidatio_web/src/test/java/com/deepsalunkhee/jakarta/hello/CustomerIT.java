package com.deepsalunkhee.jakarta.hello;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CustomerIT {

  // ======================================
  // =             Attributes             =
  // ======================================

  private static ValidatorFactory vf;
  private static Validator validator;

  // ======================================
  // =          Lifecycle Methods         =
  // ======================================

  @BeforeClass
  public static void init() {
    vf = Validation.buildDefaultValidatorFactory();
    validator = vf.getValidator();
  }

  @AfterClass
  public static void close() {
    vf.close();
  }

  // ======================================
  // =              Methods               =
  // ======================================

  @Test
  public void shouldRaiseNoConstraintViolation() {

    Customer customer = new Customer("John", "Smith", "jsmith@gmail.com");

    Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
    assertEquals(0, violations.size());
  }

  @Test
//  @Ignore("Make sure your local is EN, if not use the following JVM parameters : -Duser.language=en -Duser.country=EN")
  public void shouldRaiseConstraintViolationCauseInvalidEmail() {

    Customer customer = new Customer("John", "Smith", "DummyEmail");

    Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
    assertEquals(1, violations.size());
    assertEquals("invalid email address", violations.iterator().next().getMessage());
    assertEquals("DummyEmail", violations.iterator().next().getInvalidValue());
    assertEquals("invalid email address", violations.iterator().next().getMessageTemplate());
  }
}
