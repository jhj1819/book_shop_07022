package summer.book_shop.repository;

import summer.book_shop.domain.Book;
import summer.book_shop.domain.BookState;

import java.util.ArrayList;
import java.util.List;

import java.sql.*;

public class BookRepository {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public BookRepository() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String hostName = "localhost";
        String databaseName = "book_shop";
        String utf8Connection = "?useUnicode=true&characterEncoding=utf8";

        String url = "jdbc:mysql://" + hostName + ":3306/" + databaseName + utf8Connection;
        String userName = "user_root";
        String password = "1234";

        try {
            conn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(Book book) {
        String sql = "insert into book values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1, book.getBookId());
            pstmt.setString(2, book.getBookCode());
            pstmt.setLong(3, book.getPrice());
            pstmt.setTimestamp(4, Timestamp.valueOf(book.getCreatedAt()));
            pstmt.setString(5, book.getState().name());
            pstmt.setString(6, book.getTitle());
            pstmt.setLong(7, book.getStock());
            pstmt.setString(8, book.getAuthor());
            pstmt.setString(9, book.getPublisher());

            pstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Long bookId) {
        String sql = "delete from book where book_id=?";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1, bookId);

            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBookState(Long bookId, BookState newState) {
        String sql = "update book set state=? where book_id=?";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, newState.name());
            pstmt.setLong(2, bookId);

            pstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean existByBookId(Long bookId) {
        String sql = "select * from book where book_id=?";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1, bookId);
            return pstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Book findByBookId(Long bookId) {
        String sql = "select * from book where book_id=?";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1, bookId);

            rs = pstmt.executeQuery();

            Book book = new Book();

            while(rs.next()) {
                book.setBookId(rs.getLong(1));
                book.setBookCode(rs.getString(2));
                book.setPrice(rs.getLong(3));
                book.setCreatedAt(rs.getTimestamp(4).toLocalDateTime());
                book.setState(BookState.valueOf(rs.getString(5)));
                book.setTitle(rs.getString(6));
                book.setStock(rs.getLong(7));
                book.setAuthor(rs.getString(8));
                book.setPublisher(rs.getString(9));
            }
            return book;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Book> findByTitle(String title){
        String sql = "select * from book where title like ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + title + "%");
            rs = pstmt.executeQuery();

            List<Book> books = new ArrayList<>();
            Book book = new Book();

            while(rs.next()) {
                books.add(createBookFromResultSet(rs));
            }
            if(books.isEmpty()) {
                throw new Exception("책을 찾을 수 없습니다.");
            }

            return books;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Book> findByAuthor(String author){
        String sql = "select * from book where title like ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + author + "%");
            rs = pstmt.executeQuery();

            List<Book> books = new ArrayList<>();
            Book book = new Book();

            while(rs.next()) {
                books.add(createBookFromResultSet(rs));
            }
            if(books.isEmpty()) {
                throw new Exception("책을 찾을 수 없습니다.");
            }

            return books;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Book> findByPublisher(String publisher){
        String sql = "select * from book where title like ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + publisher + "%");
            rs = pstmt.executeQuery();

            List<Book> books = new ArrayList<>();
            Book book = new Book();

            while(rs.next()) {
                books.add(createBookFromResultSet(rs));
            }
            if(books.isEmpty()) {
                throw new Exception("책을 찾을 수 없습니다.");
            }

            return books;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public Book createBookFromResultSet(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setBookId(rs.getLong(1));
        book.setBookCode(rs.getString(2));
        book.setPrice(rs.getLong(3));
        book.setCreatedAt(rs.getTimestamp(4).toLocalDateTime());
        book.setState(BookState.valueOf(rs.getString(5)));
        book.setTitle(rs.getString(6));
        book.setStock(rs.getLong(7));
        book.setAuthor(rs.getString(8));
        book.setPublisher(rs.getString(9));
        return book;
    }


}
