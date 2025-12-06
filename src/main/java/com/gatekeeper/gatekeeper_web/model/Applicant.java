package com.gatekeeper.gatekeeper_web.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="applicants")
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "first_name") // DB-Spalte heißt first_name
    private String firstName;    // Java-Variable heißt firstName

    @Column(name = "total_score") // DB-Spalte heißt total_score
    private int totalScore;       // Java-Variable heißt totalScore


    public Applicant() {}
    
    public Applicant(String firstName, int totalScore) {
        this.firstName = firstName;
        this.totalScore = totalScore;
    }


    public int getId() {
        return id;
    }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public int getTotalScore() { return totalScore; }
    public void setTotalScore(int totalScore) { this.totalScore = totalScore; }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Name: " + firstName + "| Score: " + totalScore + "%";
    }
    
}
