package com.kaishengit.pojo;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Card {

    @Id
    @GenericGenerator(name = "fk",strategy = "foreign",
            parameters = @Parameter(name = "property",value = "person"))
    @GeneratedValue(generator = "fk")
    private Integer id;
    @Column(name = "card_num")
    private String cardNum;

    @OneToOne(mappedBy = "card")
    @PrimaryKeyJoinColumn
    private Person person;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
