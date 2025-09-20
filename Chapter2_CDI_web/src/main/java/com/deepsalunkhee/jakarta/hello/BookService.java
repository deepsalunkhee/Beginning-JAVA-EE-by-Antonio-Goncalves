package com.deepsalunkhee.jakarta.hello;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
@Loggable
public class BookService {

  // ======================================
  // =             Attributes             =
  // ======================================

  @Inject
  @ThirteenDigits
  private NumberGenerator numberGenerator;

  // ======================================
  // =          Business methods          =
  // ======================================

  public Book createBook(String title, Float price, String description) {
    Book book = new Book(title, price, description);
    book.setNumber(numberGenerator.generateNumber());
    return book;
  }
}
