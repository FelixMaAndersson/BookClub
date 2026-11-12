package se.yrgo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import se.yrgo.data.BookRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class BookController {
    private final BookRepository data;

    public BookController(BookRepository data) {
        this.data = data;
    }

    @GetMapping("/home")
    public String firstPage(Model model) {

        Date dateAndTime = new Date();

        model.addAttribute("dateAndTime", dateAndTime);
        model.addAttribute("numbers", List.of(1, 2, 3, 4, 5));

        return "home";
    }

    @GetMapping("/books")
    public String books(Model model) {
        model.addAttribute("books", data.findAll());
        return "booklist";
    }


    @GetMapping("/genre")
    public String showBooksByGenre(
            @RequestParam(value = "type", required = false) String genre,
            Model model) {

        if (genre == null || genre.isBlank()) {
            model.addAttribute("books", data.findAll());
        } else {
            model.addAttribute("books", data.findByGenre(genre));
        }

        model.addAttribute("genre", genre);

        return "genre";
    }
}
