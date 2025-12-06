package com.gatekeeper.gatekeeper_web.service;

import java.util.ArrayList;
import java.util.List;

import com.gatekeeper.gatekeeper_web.model.Question;

public class GameSession {

    int currentQuestionIndex;
    int currentScore;
    int maxPossibleScore;
    List<Question> questions = new ArrayList<>();

    public void nextQuestion() {
        currentQuestionIndex++;
    }

    public GameSession(int currentQuestionIndex, int currentScore, int maxPossibleScore, List<Question> questions) {
        this.currentQuestionIndex = currentQuestionIndex;
        this.currentScore = currentScore;
        this.maxPossibleScore = maxPossibleScore;
        this.questions = questions;
    }

    public GameSession() {

        this.currentQuestionIndex = 0;
        this.currentScore = 0;
        this.maxPossibleScore = 0;
        this.questions = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GameSession{");
        sb.append("currentQuestionIndex=").append(currentQuestionIndex);
        sb.append(", currentScore=").append(currentScore);
        sb.append(", maxPossibleScore=").append(maxPossibleScore);
        sb.append(", questions=").append(questions);
        sb.append('}');
        return sb.toString();
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public int getMaxPossibleScore() {
        return maxPossibleScore;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setCurrentQuestionIndex(int currentQuestionIndex) {
        this.currentQuestionIndex = currentQuestionIndex;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public void setMaxPossibleScore(int maxPossibleScore) {
        this.maxPossibleScore = maxPossibleScore;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

}
