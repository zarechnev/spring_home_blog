package org.zarechnev.blog.repository;

import javax.persistence.*;

@Entity
@SequenceGenerator(name="seq", allocationSize=100)
public class SectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private int id;

    @Column(name = "section")
    public String section;

    protected SectionEntity(){}

    public SectionEntity(String section){
        this.section = section;
    }

}
