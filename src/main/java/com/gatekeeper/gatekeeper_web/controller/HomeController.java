package com.gatekeeper.gatekeeper_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gatekeeper.gatekeeper_web.repository.QuestionRepository;

import java.util.List;
import com.gatekeeper.gatekeeper_web.model.Question;


@Controller
public class HomeController {

    private final QuestionRepository questionRepository;

    public HomeController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Question> questions = questionRepository.findAll();
        model.addAttribute("myQuestions", questions);
        return "home";
    }
    
}
