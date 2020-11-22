package ru.geekbrains;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class MainWithCriteriaAPI {

    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        EntityManager em = emFactory.createEntityManager();

        CriteriaBuilder cb= em.getCriteriaBuilder();
        CriteriaQuery<Product> query=cb.createQuery(Product.class); // ЧТО выбрать - соответствует Select p
        Root<Product> from =query.from(Product.class);  //  ГДЕ выбрать   - соответствует from Product  p
        List<Predicate> predicateList =new ArrayList<>();
        predicateList.add(cb.like(from.get("title"),"%Iphone_11"));
        predicateList.add(cb.ge(from.get("price"),50000));

        CriteriaQuery<Product> result =query
                .select(from)
                .where(predicateList.toArray(new Predicate[0]));
      List<Product> products=em.createQuery(result).getResultList();
        System.out.println(products);

    }
}