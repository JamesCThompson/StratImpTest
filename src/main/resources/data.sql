DROP TABLE IF EXISTS books;

CREATE TABLE books (
  uuid VARCHAR(16) PRIMARY KEY,
  title VARCHAR(250) NOT NULL
);

INSERT INTO books(uuid, title) VALUES
  ('testuuid1', 'The Jungle Book'),
  ('testuuid2', 'The Book of Life');