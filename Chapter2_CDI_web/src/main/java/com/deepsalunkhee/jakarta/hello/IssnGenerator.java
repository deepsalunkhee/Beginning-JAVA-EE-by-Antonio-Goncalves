package com.deepsalunkhee.jakarta.hello;
import java.util.Random;
import java.util.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
@EightDigits
public class IssnGenerator implements NumberGenerator {

  // ======================================
  // =             Attributes             =
  // ======================================

	private static final Logger logger = Logger.getLogger(IssnGenerator.class.getName());

  // ======================================
  // =          Business methods          =
  // ======================================

  @Loggable
  public String generateNumber() {
    String issn =  "8-" + Math.abs(new Random().nextInt());
    logger.info("Generated ISBN : " + issn);
    return issn;
  }
}