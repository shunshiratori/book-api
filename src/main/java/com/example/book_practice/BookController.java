package com.example.book_practice;

import jdk.jfr.Frequency;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {
        "http://localhost:3001",
        "https://java-practice-book-management.vercel.app/"
})
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public Optional<Book> getBook(@PathVariable int id) {
        return bookService.findById(id);
    }

    // 本の追加
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    // 全ての本の表示
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return bookService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // 本の情報更新
    @PutMapping("/{id}")
//    public Book putBook(@PathVariable int id, @RequestBody Book updateBook) {
//        return bookService.putBook(id,updateBook);
//    }
    public Book updateBook(@PathVariable int id, @RequestBody Book book) {
        return bookService.update(id, book);
    }

    // 本の削除
    @DeleteMapping("/{id}")
//    public void deleteBook(@PathVariable int id) {
//        bookService.deleteBook(id);
//    }
    public boolean deleteBook(@PathVariable int id) {
        return bookService.delete(id);
    }

    // 検索
    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String title) {
        return bookService.searchByTitle(title);
    }
}
