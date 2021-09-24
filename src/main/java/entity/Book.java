package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Book implements Serializable {
  @Id
  private String uuid;
  private String title;

  public Book(String title) {
    this.title = title;
  }

  public Book() {
  }

  public String getUuid() {
    return uuid;
  }

  public String getTitle() {
    return title;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
