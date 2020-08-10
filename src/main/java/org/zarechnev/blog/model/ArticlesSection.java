package org.zarechnev.blog.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@SequenceGenerator(name = "seq", allocationSize = 100)
public class ArticlesSection {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private int id;

    @Column(name = "section")
    public String section;

    public ArticlesSection(String section) {
        this.section = section;
    }

}
