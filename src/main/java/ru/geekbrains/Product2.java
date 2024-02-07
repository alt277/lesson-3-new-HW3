package ru.geekbrains;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import org.hibernate.annotations.Parameter;
@Entity
@Table(name = "Product2")  //@Table javax.persistence не HIBERNATE

        public class Product2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

  //  @MapsId
    @GeneratedValue(generator = "prod-generator")
    @GenericGenerator(name = "prod-generator",
            parameters = @Parameter(name = "prefix", value = "ВП"),
            strategy = "ru.geekbrains.MyGenerator2")
    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;


    public void setTitle(String title) {
        this.title = title; }
    public String getTitle() {
        return title;
    }


}
