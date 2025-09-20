package com.deepsalunkhee.jakarta.hello;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
@Path("hello")
public class HelloWorldResource {
    
    @Inject
    private BookService bookService;
    

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Book hello(@QueryParam("name") String name) {
        Book book = bookService.createBook("H2G2", 12.5f, "Geeky scifi Book");
        System.out.println(book);
        return book;
    }
}