package controller;

import entity.Book;
import exception.DataException;
import exception.NoBookException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.StratService;

import java.util.List;

@RestController
public class StratController {
  private static final Logger LOGGER = LoggerFactory.getLogger(StratController.class);

  @Autowired
  private StratService stratService;

  @ApiOperation(value = "Collects a book title and returns the generated UUID once the book is saved.")
  @ApiImplicitParam(name = "book", value = "The book to save.", required = true, paramType = "body")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "The uuid of the book saved.", response = String.class),
          @ApiResponse(code = 400, message = "The book was not valid and could not be saved.")
  })
  @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
  public HttpEntity<String> saveValue(@RequestBody Book book) {
    try {
      return new ResponseEntity<>(stratService.saveData(book), HttpStatus.OK);
    } catch (DataException e) {
      return new ResponseEntity<>("data was not valid", HttpStatus.BAD_REQUEST);
    }
  }

  @ApiOperation(value = "Retrieves a specific book.")
  @ApiImplicitParam(name = "uuid", value = "The book to load.", required = true, paramType = "path")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "The the book saved with that uuid.", response = Book.class),
          @ApiResponse(code = 400, message = "The uuid was not valid."),
          @ApiResponse(code = 404, message = "There was no book with that uuid.")
  })
  @RequestMapping(value = "/load/{uuid}", method = RequestMethod.GET)
  public HttpEntity<Book> loadValue(@PathVariable(value = "uuid") String uuid) {
    try {
      return new ResponseEntity<>(stratService.loadBook(uuid), HttpStatus.OK);
    } catch (DataException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } catch (NoBookException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @ApiOperation(value = "Retrieves all the books.")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "All the books saved.", response = List.class)
  })
  @RequestMapping(value = "/load", method = RequestMethod.GET)
  public HttpEntity<List<Book>> loadValue() {
    return new ResponseEntity<>(stratService.loadAll(), HttpStatus.OK);
  }
}
