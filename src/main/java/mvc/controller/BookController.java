package mvc.controller;

import mvc.entity.BookEntity;
import mvc.entity.CategoryEntity;
import mvc.repository.BookRepository;
import mvc.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String showBooks(Model model){
        List<BookEntity> bookList = (List<BookEntity>) bookRepository.findAll();
        model.addAttribute("bookList", bookList);

        return "home";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search (@RequestParam("searchInput") String searchInput, Model model) {
        List<BookEntity> resultList;
        if(searchInput.isEmpty()){
            resultList = (List<BookEntity>) bookRepository.findAll();
        }else {
            resultList = bookRepository.findByNameContainingOrAuthorContaining(searchInput, searchInput);
        }
        model.addAttribute("bookList", resultList);
        return "home";
    }
    @RequestMapping(value = "/newBook", method = RequestMethod.GET)
    public String showNewBook(Model model) {
        model.addAttribute("book", new BookEntity());
        model.addAttribute("msg", "Add a new Book");
        model.addAttribute("action", "newBook");

        setCategoryDropDownlist(model);
        return "book";
    }

//    @RequestMapping(value = "/newBook", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
//    public String saveBook(BookEntity book) {
//        bookRepository.save(book);
//        return "redirect:/";
//    }

    @RequestMapping(value = "/newBook", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public String save(@Valid @ModelAttribute("book") BookEntity book, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            setCategoryDropDownlist(model);
            return "book";
        }
        bookRepository.save(book);
        return "redirect:/";
    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String showEditBook(Model model, @PathVariable int id){
        model.addAttribute("book", bookRepository.findById(id));
        model.addAttribute("msg", "Update book information");
        model.addAttribute("type", "update");
        model.addAttribute("action", "/updateBook");

        setCategoryDropDownlist(model);
        return "book";
    }
    @RequestMapping(value = "/updateBook", method = RequestMethod.POST)
    public String updateBook(@ModelAttribute BookEntity book) {
        bookRepository.save(book);
        return "redirect:/";
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable int id) {
        bookRepository.deleteById(id);
        return "redirect:/";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    private void setCategoryDropDownlist(Model model) {
        List<CategoryEntity> cateList = (List<CategoryEntity>) categoryRepository.findAll();
        if(!cateList.isEmpty()){
            Map<Integer, String> cateMap = new LinkedHashMap<>();
            for (CategoryEntity categoryEntity : cateList) {
                cateMap.put(categoryEntity.getId(), categoryEntity.getName());
            }
            model.addAttribute("categoryList", cateMap);
        }
    }
}
