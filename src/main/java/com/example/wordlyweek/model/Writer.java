package com.example.wordlyweek.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "writer")
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int writerId;

    @Column(name = "name")
    private String name;

    @Column(name = "bio")
    private String bio;

    @ManyToMany
    @JoinTable(name = "writer_magazine", joinColumns = @JoinColumn(name = "writerid"), inverseJoinColumns = @JoinColumn(name = "magazineid"))
    @JsonIgnoreProperties("writers")
    private List<Magazine> magazines;

    public Writer() {
    }

    public int getWriterId() {
        return writerId;
    }

    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Magazine> getMagazine() {
        return magazines;
    }

    public void setMagazine(List<Magazine> magazines) {
        this.magazines = magazines;
    }
}
