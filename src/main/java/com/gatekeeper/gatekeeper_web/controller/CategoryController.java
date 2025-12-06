package com.gatekeeper.gatekeeper_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gatekeeper.gatekeeper_web.model.Category;
import com.gatekeeper.gatekeeper_web.repository.CategoryRepository;

@Controller
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Übersicht anzeigen
    @GetMapping("/categories")
    public String showCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("newCategory", new Category()); // Für das Formular
        return "categories";
    }

    // Neue Kategorie speichern
    @PostMapping("/save-category")
    public String saveCategory(@ModelAttribute("newCategory") Category category) {
        categoryRepository.save(category);
        return "redirect:/categories";
    }

    // Kategorie löschen (mit Schutz!)
    @GetMapping("/delete-category")
    public String deleteCategory(@RequestParam("id") int id) {
        try {
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            return "redirect:/categories?error=in_use";
        }
        return "redirect:/categories";
    }
}