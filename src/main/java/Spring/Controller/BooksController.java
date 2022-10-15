package Spring.Controller;

import Spring.Dao.BooksDao;
import Spring.Model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {

    private BooksDao booksDao;

    @Autowired
    public BooksController(BooksDao booksDao) {
        this.booksDao = booksDao;
    }

    @GetMapping()
    public String listBook(Model model) {
        model.addAttribute("listBooks", booksDao.list());
        return "book";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "new";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book") Book book) {
        booksDao.save(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksDao.delete(id);
        return "redirect:/books";
    }
}
