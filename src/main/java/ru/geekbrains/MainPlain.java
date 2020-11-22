package ru.geekbrains;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Map;

public class MainPlain {

    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();      /* входная точка в hibernate для каждого источника данных
                                                обычно создается отдельный объект сущности*/

        EntityManager em = emFactory.createEntityManager(); // содаем экземпляр EntityManager

        // найти всех покупотелей товара:
        List<Customer> customers= em.createQuery("    SELECT  c FROM Customer as c" +
                "        join Ties  as t  on t.customer_id=c.id\n" +
                "        join Product   p on p.id=t.product_id\n" +
                "        where p.title='Iphone_11'",Customer.class).getResultList();
        System.out.println(customers);

        // найти все покупки определенного покупателя
        List<Product> products= em.createQuery("    SELECT  p FROM Product as p" +
                "        join Ties  as t  on t.customer_id=p.id\n" +
                "        join Customer  as c on c.id=t.product_id\n" +
                "        where c.first_name='Alex'",Product.class).getResultList();
        System.out.println(products);


       em.close();  //  закрыть менеджер сущностей EntityManager


    }
}
