package Book.Manager.demo.controllers;

import Book.Manager.demo.models.Person;
import Book.Manager.demo.repository.Pository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/people")
public class PersonController {

    @Autowired
    private Pository repository;

    @GetMapping()
    public String index(Model model)
    {
        Iterable<Person> ListPerson =repository.findAll();
        model.addAttribute("index",ListPerson);
        return "/people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id,Model model )
    {
        Optional<Person> ListPerson =repository.findById(id);
        ArrayList<Person> list = new ArrayList<>();
        ListPerson.ifPresent(list::add);
        model.addAttribute("show",list);
        return "/people/show";
    }

    @GetMapping("/add")
    public String add()
    {
        return "/people/add";
    }

    @PostMapping("/add")
    public String add(@RequestParam String name, @RequestParam String email, Model model)
    {
        Person person = new Person(name,email);
        repository.save(person);
        return  "redirect:/people";
    }

    @GetMapping("/{id}/update")
    public String edit(@PathVariable("id") Long id, Model model)
    {
        Optional<Person> ListPerson =repository.findById(id);
        ArrayList<Person> list = new ArrayList<>();
        ListPerson.ifPresent(list::add);
        model.addAttribute("update",list);
        return "/people/update";
    }

    @PostMapping("/{id}/update")
    public String update(Person person,@PathVariable("id") Long id,@RequestParam String name,@RequestParam String email)
    {
        person =repository.findById(id).orElseThrow();
        person.setName(name);
        person.setEmail(email);
        repository.save(person);
        return "redirect:/people";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") Long id)
    {
        repository.deleteById(id);
        return "redirect:/people";
    }
}
