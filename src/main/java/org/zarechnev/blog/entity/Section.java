package org.zarechnev.blog.entity;

import javax.persistence.*;

@Entity
@SequenceGenerator(name="seq", allocationSize=100)
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private int id;

    @Column(name = "section")
    public String section;

    protected Section(){}

    public Section(String section){
        this.section = section;
    }
}
