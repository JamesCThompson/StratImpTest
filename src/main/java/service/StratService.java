package service;

import entity.Book;
import exception.DataException;
import exception.NoBookException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BookRepository;

import java.util.List;
import java.util.UUID;

@Service
public class StratService {
  private static final Logger LOGGER = LoggerFactory.getLogger(StratService.class);

  @Autowired
  private BookRepository bookRepository;

  public String saveData(Book book) throws DataException {
    if (validateData(book)) {
      book.setUuid(UUID.randomUUID().toString());

      return book.getUuid();
    }
    throw new DataException();
  }

  public Book loadBook(String uuid) throws DataException, NoBookException {
    if (null != uuid && !uuid.isBlank()) {
      return bookRepository.findById(uuid).orElseThrow(NoBookException::new);
    }
    throw new DataException();
  }

  public List<Book> loadAll() {
    return bookRepository.findAll();
  }

  private boolean validateData(Book book) {
    return (null != book && null != book.getTitle() && book.getTitle().isBlank());
  }
}
