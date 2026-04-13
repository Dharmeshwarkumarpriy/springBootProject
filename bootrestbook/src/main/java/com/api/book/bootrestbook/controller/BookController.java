package com.api.book.bootrestbook.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.bootrestbook.entities.Book;
import com.api.book.bootrestbook.services.BookService;

@RestController
public class BookController {

//	@GetMapping("/books")
//	public Book getBooks() {		
//		Book book=new Book();
//		book.setId(32323);
//		book.setTitle("java component reference");
//		book.setAuthor("zyx");		
//		return book;
//	}

	@Autowired
	private BookService bookService;

	// views all books...
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks() {
		List<Book> list = this.bookService.getAllBooks();
		if (list.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 204 error...
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}

	// view single books...
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
		Book book = bookService.getBookById(id);
		if (book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));
	}

	// new book handler...
	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		Book b = null;
		try {
			b = this.bookService.addBook(book);

			return ResponseEntity.status(HttpStatus.CREATED).body(b);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

//	public ResponseEntity<Book> addBook(@RequestBody Book book) {
//	Book addedBook=bookService.addBook(book);
//	return ResponseEntity.status(HttpStatus.CREATED).body(addedBook);
//	}

//	public Book addBook(@RequestBody Book book) {
//		Book b = this.bookService.addBook(book);
//		System.out.println(book);
//		return b;
//	}

	// delete book handler...
	@DeleteMapping("/books/{bookId}")
	public ResponseEntity<Integer> deleteBook(@PathVariable("bookId") int bookId) {
		try {
			this.bookService.deleteBook(bookId);
			System.out.println(bookId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// update book handler...
	@PutMapping("/books/{bookId}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("bookId") int bookId) {
		try {
			this.bookService.updateBook(book, bookId);
			return ResponseEntity.ok().body(book);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
