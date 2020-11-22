package ru.geekbrains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
                    // при созданнии новой сущности не забыть прописать ее в Hibernate конфиг. файле
                    // при созданиипри созданнии  СОСТАВНОЙ сущности СОСТАВНОЙ ПЕРВИЧНЫЙ ключ не подойдет
@Entity            // - не сработает на запросах,  нужен простой отдельный ID
@Table(name = "ties")
public class Ties{
    @Id
    @Column
    private Integer id;

    @Column
    private Integer product_id;

    @Column
    private Integer customer_id;

    public Ties(){};
    public Ties(Integer id,Integer customer_id,Integer product_id){
        this.id=id;
        this.customer_id=customer_id;
        this.product_id=product_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }
    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "Ties{" +
                "id=" + id +
                ", product_id=" + product_id +
                ", customer_id=" + customer_id +
                '}';
    }
}
