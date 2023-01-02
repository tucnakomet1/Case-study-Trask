package com.example.case_study.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "candidates")
public class Candidates {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "candidate")
    private String candidate;
    @Column(name = "technologies")
    private String technologies;
    @Column(name = "knowledge")
    private String knowledge;
    @Column(name = "note")
    private String note;

    public Candidates() {}

    public Candidates(String candidate, String technologies, String knowledge, String note) {
        this.candidate = candidate;
        this.technologies = technologies;
        this.knowledge = knowledge;
        this.note = note;
    }

    // uchazec getter, setter
    public String getCandidate() { return candidate; }
    public void setCandidate(String candidate) { this.candidate = candidate; }

    // technologie getter, setter
    public String getTechnologies() { return technologies; }
    public void setTechnologies(String technologies) { this.technologies = technologies; }

    // uroven znalosti getter, setter
    public String getKnowledge() { return knowledge; }
    public void setKnowledge(String knowledge) { this.knowledge = knowledge; }

    // poznamka getter, setter
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
