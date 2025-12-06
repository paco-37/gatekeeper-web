package com.gatekeeper.gatekeeper_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gatekeeper.gatekeeper_web.model.Question;
import com.gatekeeper.gatekeeper_web.repository.ApplicantRepository;
import com.gatekeeper.gatekeeper_web.repository.CategoryRepository;
import com.gatekeeper.gatekeeper_web.repository.QuestionRepository;

@Controller
public class HomeController {

    private final QuestionRepository questionRepository;
    private final ApplicantRepository applicantRepository;
    private final CategoryRepository categoryRepository;

    public HomeController(QuestionRepository questionRepository, ApplicantRepository applicantRepository, CategoryRepository categoryRepository) {
        this.questionRepository = questionRepository;
        this.applicantRepository = applicantRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        model.addAttribute("myQuestions", questionRepository.findAll());
        model.addAttribute("applicants", applicantRepository.findTop10ByOrderByTotalScoreDesc());
        return "admin";
    }

    @GetMapping("/delete-question")
    public String deleteQuestion(@RequestParam("id") int id) {

        questionRepository.deleteById(id);

        return "redirect:/admin";
    }

    @GetMapping("/add-question")
    public String showCreateForm(Model model) {
        model.addAttribute("question", new Question());

        model.addAttribute("categories", categoryRepository.findAll());

        return "create_question";
    }

    @PostMapping("/save-question")
    public String saveQuestion(@ModelAttribute("question") Question question) {

        questionRepository.save(question);

        return "redirect:/admin";
    }

    @GetMapping("/manage-options")
    public String showManageOptions(@RequestParam("id") int id, Model model) {
        Question q = questionRepository.findById(id).orElse(null);

        model.addAttribute("question", q);
        model.addAttribute("newOption", new com.gatekeeper.gatekeeper_web.model.Option());

        return "manage_options";
    }

    @PostMapping("/add-option")
    public String addOption(@RequestParam("questionId") int questionId,
            @ModelAttribute("newOption") com.gatekeeper.gatekeeper_web.model.Option option) {

        Question q = questionRepository.findById(questionId).orElse(null);

        if (q != null) {
            q.addOption(option);

            questionRepository.save(q);
        }

        return "redirect:/manage-options?id=" + questionId;
    }

    @GetMapping("/delete-applicant")
    public String deleteApplicant(@RequestParam("id") int id) {

        applicantRepository.deleteById(id);

        return "redirect:/admin";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/edit-question")
    public String showEditForm(@RequestParam("id") int id, Model model) {
        Question q = questionRepository.findById(id).orElse(null);

        model.addAttribute("categories", categoryRepository.findAll());

        model.addAttribute("question", q);

        return "edit_question"; 
    }

    @PostMapping("/update-question")
    public String updateQuestion(@ModelAttribute("question") Question formData) {
        
        Question existingQuestion = questionRepository.findById(formData.getId()).orElse(null);
        
        if (existingQuestion != null) {
            existingQuestion.setQuestionText(formData.getQuestionText());
            existingQuestion.setWeight(formData.getWeight());
            existingQuestion.setCategory(formData.getCategory());
            
            questionRepository.save(existingQuestion);
        }
        
        return "redirect:/admin";
    }

}
