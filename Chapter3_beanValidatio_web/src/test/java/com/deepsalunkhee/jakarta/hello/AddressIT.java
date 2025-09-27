package com.deepsalunkhee.jakarta.hello;

import org.junit.Test;


import java.util.Set;

import static org.junit.Assert.assertEquals;


public class AddressIT {

  // ======================================
  // =              Methods               =
  // ======================================

  @Test
  public void shouldRaiseConstraintViolationCauseInvalidZipCode() {

    jakarta.validation.ValidatorFactory vf = jakarta.validation.Validation.buildDefaultValidatorFactory();
    jakarta.validation.Validator validator = vf.getValidator();

    Address address = new Address("233 Spring Street", "New York", "NY", "DummyZip", "USA");

    Set<jakarta.validation.ConstraintViolation<Address>> violations = validator.validate(address);
    assertEquals(1, violations.size());

    vf.close();
  }
}