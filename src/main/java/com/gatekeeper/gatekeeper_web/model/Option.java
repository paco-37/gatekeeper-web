package com.gatekeeper.gatekeeper_web.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "option_text")
    private String text;
    @Column(name = "score_value")
    private int score;

    public Option(int id, int score, String text) {
        this.id = id;
        this.score = score;
        this.text = text;
    }

    public Option() {}

    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Option{");
        sb.append("id=").append(id);
        sb.append(", text=").append(text);
        sb.append(", score=").append(score);
        sb.append('}');
        return sb.toString();
    }
    
}
