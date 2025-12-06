package com.gatekeeper.gatekeeper_web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gatekeeper.gatekeeper_web.model.Option;
import com.gatekeeper.gatekeeper_web.model.Question;
import com.gatekeeper.gatekeeper_web.repository.QuestionRepository;
import com.gatekeeper.gatekeeper_web.service.GameSession;

@Controller
@SessionAttributes("gameSession")
public class GameController {

    private final QuestionRepository questionRepository;

    public GameController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping("/start-game")
    public String startGame(Model model) {
        List<Question> allQuestions = questionRepository.findAll();
        
        GameSession session = new GameSession();
        session.setQuestions(allQuestions);
        
        model.addAttribute("gameSession", session);
        
        return "redirect:/play";
    }

    @GetMapping("/play")
    public String showQuestion(@ModelAttribute("gameSession") GameSession session, Model model) {

        if (session.getCurrentQuestionIndex() >= session.getQuestions().size()) {
            return "redirect:/"; 
        }
        
        Question currentQ = session.getQuestions().get(session.getCurrentQuestionIndex());
        
        model.addAttribute("question", currentQ);
        
        return "game"; 
    }

    @GetMapping("/submit-answer")
    public String submitAnswer(@RequestParam("optionId") int optionId, 
                               @ModelAttribute("gameSession") GameSession session) {
        
        Question currentQ = session.getQuestions().get(session.getCurrentQuestionIndex());

        for(Option opt : currentQ.getOptions()) {
            if(opt.getId() == optionId) {
                int points = opt.getScore() * currentQ.getWeight();
                int maxPoints = 10 * currentQ.getWeight(); 

                session.setCurrentScore(session.getCurrentScore() + points);
                session.setMaxPossibleScore(session.getMaxPossibleScore() + maxPoints);
                
                break;
            }
        }

        session.nextQuestion();

        return "redirect:/play";
    }
	
}
