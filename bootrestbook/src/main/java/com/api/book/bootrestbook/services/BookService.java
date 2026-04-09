package com.api.book.bootrestbook.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.api.book.bootrestbook.entities.Book;

@Component
public class BookService {

	private static List<Book> list = new ArrayList<>();

	static {
		list.add(new Book(12, "java complete reference", "zyx"));
		list.add(new Book(54, "head first to java ", "abc"));
		list.add(new Book(32, "think in java", "lml"));
	}

	// get all books...
	public List<Book> getAllBooks() {

		return list;
	}

	// get single book by id...
	public Book getBookById(int id) {
		Book book = null;
		try {
			book = list.stream().filter(e -> e.getId() == id).findFirst().get();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}
	
	// adding the book...
	public Book addBook(Book b) {		
		list.add(b);
		return b;
	}

	// delete book...
	public void deleteBook(int bid) {		
		list=list.stream().filter(book->
		{			
			System.out.println("bid:"+bid);
			System.out.println("book.getId():"+book.getId());
			if(book.getId()!=bid) {
				return true;
			}else {
				return false;
			}
		}
		).collect(Collectors.toList());
	}

	// update the book...
		public void updateBook(Book book, int bookId) {			
			list=list.stream().map(b->{				
				if(b.getId()==bookId) {
					b.setTitle(book.getTitle());
					b.setAuthor(book.getAuthor());
				}
				return b;
			}).collect(Collectors.toList());
		}

}
