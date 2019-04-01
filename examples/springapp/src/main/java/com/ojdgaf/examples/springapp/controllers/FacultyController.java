package com.ojdgaf.examples.springapp.controllers;

import com.ojdgaf.examples.springapp.entities.Faculty;
import com.ojdgaf.examples.springapp.services.Service;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("faculties")
public class FacultyController {
    private Service<Faculty> service;

    public FacultyController(Service<Faculty> service) {
        this.service = service;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("faculties", service.all());
        return "faculties/index";
    }

    @GetMapping("{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("faculty", service.get(id));
        return "faculties/show";
    }

    @GetMapping("create")
    public String create(Model model) {
        model.addAttribute("faculty", new Faculty());
        return "faculties/create";
    }

    @GetMapping("{id}/edit")
    public String create(@PathVariable Integer id,  Model model) {
        model.addAttribute("faculty", service.get(id));
        return "faculties/edit";
    }

    @PostMapping("store")
    public String store(@ModelAttribute("faculty") @Valid Faculty faculty) {
        service.saveOrUpdate(faculty);
        return "redirect:/faculties";
    }

    @PostMapping("{id}/update")
    public String update(@PathVariable Integer id, @ModelAttribute("faculty") @Valid Faculty faculty) {
        faculty.setId(id);
        service.saveOrUpdate(faculty);
        return "redirect:/faculties";
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable Integer id) {
        service.delete(service.get(id));
        return "redirect:/faculties";
    }

    @InitBinder
    public void trimStrings(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
