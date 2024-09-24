package com.ufuture.library.services;

import com.ufuture.library.models.Book;
import com.ufuture.library.utill.BookNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private static Long COUNTER = 0L;
    private List<Book> books;

    {
        books = new ArrayList<>();
        books.add(new Book(COUNTER++, "Мастер и Маргарита", "Михаил Булгаков", "В 1985 году Геннадий Калиновский (1929–2006) создал серию иллюстраций к «Мастеру и Маргарите».", 3600.0));
        books.add(new Book(COUNTER++, "Война и мир", "Лев Толстой", "Эпопея о жизни России в эпоху наполеоновских войн.", 4500.0));
        books.add(new Book(COUNTER++, "Преступление и наказание", "Фёдор Достоевский", "Роман о нравственных терзаниях бедного студента, совершившего преступление.", 3800.0));
        books.add(new Book(COUNTER++, "Анна Каренина", "Лев Толстой", "Трагическая история любви замужней женщины и молодого офицера.", 4200.0));
        books.add(new Book(COUNTER++, "Братья Карамазовы", "Фёдор Достоевский", "Философский роман о взаимоотношениях в семье Карамазовых.", 3900.0));
        books.add(new Book(COUNTER++, "Доктор Живаго", "Борис Пастернак", "Роман о судьбе врача Юрия Живаго на фоне революции и гражданской войны в России.", 3700.0));
    }

    public List<Book> getBooks() {
        return books;
    }

    public Book getBookById(Long id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst().orElseThrow(BookNotFoundException::new);
    }

    public void saveBook(Book book) {
        book.setId(COUNTER);
        books.add(book);
    }

    public void updateBook(Long id, Book book) {
        Book oldBook = getBookById(id);
        oldBook.setTitle(book.getTitle());
        oldBook.setAuthor(book.getAuthor());
        oldBook.setDescription(book.getDescription());
        oldBook.setPrice(book.getPrice());
    }

    public void deleteBook(Long id) {
       Book book = getBookById(id);
       books.remove(book);
    }

}
