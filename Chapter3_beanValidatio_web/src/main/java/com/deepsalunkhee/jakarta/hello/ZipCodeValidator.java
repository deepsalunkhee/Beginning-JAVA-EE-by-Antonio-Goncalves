package com.deepsalunkhee.jakarta.hello;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ZipCodeValidator implements ConstraintValidator<ZipCode, String> {

  @Inject
  @USA
  private ZipCodeChecker checker = new ZipCodeChecker();
  private Pattern zipPattern = Pattern.compile("\\d{5}(-\\d{5})?");

  @Override
  public void initialize(ZipCode zipCode) {
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null)
      return true;

    Matcher m = zipPattern.matcher(value);
    if (!m.matches())
      return false;
    return checker.isZipCodeValid(value);
  }
}
