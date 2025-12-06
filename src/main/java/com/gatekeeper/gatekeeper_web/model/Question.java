package com.gatekeeper.gatekeeper_web.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String questionText;
    private int weight;

    @ManyToOne 
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private List<Option> options = new ArrayList<>();

    public Question() {

    }

    public Question(int id, Category category, String questionText, int weight) {
        this.id = id;
        this.category = category;
        this.questionText = questionText;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void addOption(Option option) {
        options.add(option);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Question{");
        sb.append("ID=").append(id);
        sb.append(", questionText=").append(questionText);
        sb.append(", weight=").append(weight);
        sb.append(", category=").append(category);
        sb.append(options);
        sb.append('}');
        return sb.toString();
    }

}
