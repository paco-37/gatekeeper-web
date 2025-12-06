package com.gatekeeper.gatekeeper_web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.gatekeeper.gatekeeper_web.model.Option;
import com.gatekeeper.gatekeeper_web.model.Question;
import com.gatekeeper.gatekeeper_web.repository.ApplicantRepository;
import com.gatekeeper.gatekeeper_web.repository.QuestionRepository;
import com.gatekeeper.gatekeeper_web.service.GameSession;

@Controller
@SessionAttributes("gameSession")
public class GameController {

    private final QuestionRepository questionRepository;
    private final ApplicantRepository applicantRepository;

    public GameController(QuestionRepository questionRepository, ApplicantRepository applicantRepository) {
        this.questionRepository = questionRepository;
        this.applicantRepository = applicantRepository;
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
            return "redirect:/result";
        }

        Question currentQ = session.getQuestions().get(session.getCurrentQuestionIndex());

        model.addAttribute("question", currentQ);

        return "game";
    }

    @GetMapping("/submit-answer")
    public String submitAnswer(@RequestParam("optionId") int optionId,
            @ModelAttribute("gameSession") GameSession session) {

        Question currentQ = session.getQuestions().get(session.getCurrentQuestionIndex());

        for (Option opt : currentQ.getOptions()) {
            if (opt.getId() == optionId) {
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

    @GetMapping("/result")
    public String showResult(@ModelAttribute("gameSession") GameSession session, Model model) {
        double percentage = 0;
        if (session.getMaxPossibleScore() > 0) {
            percentage = ((double) session.getCurrentScore() / session.getMaxPossibleScore()) * 100;
        }

        model.addAttribute("percentage", (int) percentage);
        model.addAttribute("applicant", new com.gatekeeper.gatekeeper_web.model.Applicant());

        return "result";
    }

    @PostMapping("/save-applicant")
    public String saveApplicant(@ModelAttribute("applicant") com.gatekeeper.gatekeeper_web.model.Applicant applicant,
            @ModelAttribute("gameSession") GameSession session,
            SessionStatus status) {

        double percentage = 0;
        if (session.getMaxPossibleScore() > 0) {
            percentage = ((double) session.getCurrentScore() / session.getMaxPossibleScore()) * 100;
        }
        applicant.setTotalScore((int) percentage);

        applicantRepository.save(applicant);

        status.setComplete();

        return "redirect:/";
    }

}
