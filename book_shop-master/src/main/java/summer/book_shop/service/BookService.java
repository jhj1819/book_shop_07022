package summer.book_shop.service;

import summer.book_shop.domain.Book;
import summer.book_shop.domain.BookState;
import summer.book_shop.domain.User;
import summer.book_shop.repository.BookRepository;
import java.util.ArrayList;
import java.util.List;

public class BookService {

    private BookRepository bookRepository;

    public void registerBook(Book book) {
        //예외처리 필요
        bookRepository.save(book);
    }

    public void removeBook(Long bookId) throws Exception {
        if (!bookRepository.existByBookId(bookId)) {
            throw new RuntimeException("책을 찾을 수 없습니다.");
        }

        bookRepository.delete(bookId);
    }

    public Book getBookInfo(long bookId) { //Id를 이용해 Book정보 가져옴
        return bookRepository.findByBookId(bookId);
    }

    public List<Book> searchBookByTitle(String title) throws Exception {
        return bookRepository.findByTitle(title);
    }
    public List<Book> searchBookByAuthor(String author) throws Exception {
        return bookRepository.findByTitle(author);
    }
    public List<Book> searchBookByPublisher(String publisher) throws Exception {
        return bookRepository.findByTitle(publisher);
    }

}
