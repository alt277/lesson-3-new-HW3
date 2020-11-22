package ru.geekbrains;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Customer")
@NamedQueries( {
        @NamedQuery(name = "Customer.findAll",query ="SELECT a from Customer a") ,
       @NamedQuery(name = "Customer.findById",query =" SELECT c from Customer c where c.id=:param"),

        @NamedQuery(name = "Customer.findByLogiтAlex",query ="SELECT a from Customer a where a.first_name='Alex' "),
        @NamedQuery(name = "Customer.customerPurchases",query =" SELECT  p FROM Product as p" +
                "        join Ties  as t  on t.customer_id=p.id\n" +
                "        join Customer  as c on c.id=t.product_id\n" +
                "        where c.first_name=:param")
} )
        public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "family_name")
    private String family_name;

    @ManyToMany (cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "ties",
            joinColumns=@JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")

)
    private List<Product> products;

    public Customer(){};
    public Customer(Integer id,String first_name,String family_name,List<Product> products){
        this.id=id;
        this.first_name=first_name;
        this.family_name=family_name;
        this.products=products;
    }

    public Customer(Integer id,String first_name,String family_name){
        this.id=id;
        this.first_name=first_name;
        this.family_name=family_name;
    }

    public void setId(Integer id) { this.id = id; }
    public Integer getId() {
        return id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getFirst_name() {
        return first_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }
    public String getFamily_name() {
        return family_name;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", family_name='" + family_name + '\'' +
                '}'+   '\n';     // -добавим, чтобы разделять вывод
    }

//    @Override
//    public String toString() {
//        return "Customer{" +
//                "id=" + id +
//                ", first_name='" + first_name + '\'' +
//                ", family_name='" + family_name + '\'' +
//                ", products=" + products +  // вывод листа продуктов приведет
//                                            // к переполнеию сложном запросе
//                '}'+'\n';
//    }
}

