package com.deepsalunkhee.jakarta.hello;

import java.util.Random;
import java.util.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
@ThirteenDigits
public class IsbnGenerator implements NumberGenerator {

  // ======================================
  // =             Attributes             =
  // ======================================

	private static final Logger logger = Logger.getLogger(IsbnGenerator.class.getName());

  // ======================================
  // =          Business methods          =
  // ======================================

  @Loggable
  public String generateNumber() {
    String isbn = "13-84356-" + Math.abs(new Random().nextInt());
    logger.info("Generated ISBN : " + isbn);
    return isbn;
  }
}