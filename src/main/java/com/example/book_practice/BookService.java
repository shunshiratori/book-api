package com.example.book_practice;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    //配列作成
//    private List<Book> books = new ArrayList<>();
//    private int nextId = 1;
    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 検索
    public List<Book> findAll(){
        return bookRepository.findAll();
    }
    public Optional<Book> findById(long id) {
        return bookRepository.findById(id);
    }

    // 登録
//    public Book addBook(@RequestBody Book book) {
//        book.setId(nextId++);
//        books.add(book);
//        return book;
//    }
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    // 表示
//    public List<Book> showBook() {
//        return books;
//    }
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // 更新
//    public Book putBook(@PathVariable int id, @RequestBody Book updateBook) {
//        for(Book book : books) {
//            if(book.getId() == id) {
//                book.setTitle(updateBook.getTitle());
//                book.setAuthor(updateBook.getAuthor());
//                return book;
//            }
//        }
//        throw new RuntimeException(id + ":idが見つかりません");
//    }
    public Book update(long id, Book updateBook) {
        Optional<Book> exsiting = bookRepository.findById(id);
        if(exsiting.isPresent()) {
            Book b = exsiting.get();
            b.setTitle(updateBook.getTitle());
            b.setAuthor(updateBook.getAuthor());
            return bookRepository.save(b);
        }
        return null;
    }

    // 削除
//    public void deleteBook(@PathVariable int id) {
//        books.removeIf(book -> book.getId() == id);
//    }
    public boolean delete(long id) {
        if(bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Book> searchByTitle(String title) {
        return bookRepository.findByTitleContaining(title);
    }
}
