package org.zarechnev.blog.entity;

import javax.persistence.*;

/**
 * Section of articles.
 */
@Entity
@SequenceGenerator(name="seq", initialValue=1, allocationSize=100)
public class ArticleSectionModel {
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
    protected ArticleSectionModel(){}

    /**
     * Instantiates a new Article section entity.
     *
     * @param section the section
     */
    public ArticleSectionModel(String section){
        this.section = section;
    }
}
