package com.api.book.bootrestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public List<Book> getBooks() {

		return this.bookService.getAllBooks();
	}

	// view single books...
	@GetMapping("/books/{id}")
	public Book getBook(@PathVariable("id") int id) {

		return bookService.getBookById(id);
	}

	// new book handler...
	@PostMapping("/books")
	public Book addBook(@RequestBody Book book) {
		Book b = this.bookService.addBook(book);
		System.out.println(book);
		return b;
	}

	// delete book handler...
	@DeleteMapping("/books/{bookId}")
	public int deleteBook(@PathVariable("bookId") int bookId) {
		this.bookService.deleteBook(bookId);
		System.out.println(bookId);
		return bookId;
	}

	// update book handler...
	@PutMapping("/books/{bookId}")
	public Book updateBook(@RequestBody Book book, @PathVariable("bookId") int bookId) {
		this.bookService.updateBook(book, bookId);
		return book;
	}

}
