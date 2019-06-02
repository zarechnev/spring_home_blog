package org.zarechnev.blog.entity;

import javax.persistence.*;

/**
 * Section of articles.
 */
@Entity
@SequenceGenerator(name="seq", allocationSize=100)
public class SectionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private int id;

    /**
     * The Section.
     */
    @Column(name = "section")
    public String section;

    /**
     * Instantiates a new Article section entity.
     */
    protected SectionModel(){}

    /**
     * Instantiates a new Article section entity.
     *
     * @param section the section
     */
    public SectionModel(String section){
        this.section = section;
    }
}
