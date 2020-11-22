package ru.geekbrains;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class MainHW_WithManager {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = emFactory.createEntityManager();

  Manager manager=new Manager();
        manager.addCustmer(null,"Vanya","Tyapkin");


  // найти все покупки определенного покупателя
  manager.showPurchaseByName("Alex");
  manager.showPurchaseNamedQuery("Ivan");

  // найти всех покупотелей товара:
  manager.showProductsCustomers("Galaxy_fold2");

  // покать покупки каждого покупателя:
  manager.showAllPurchases();

  manager.showCustomers();
  manager.deleteCustomerById(50);
  manager.showCustomers();

manager.showProducts();
manager.deleteProductById(18);
manager.showProducts();

        manager.showPurchaseNamedQuery("Alex")  ;
        manager.showProductsCustomers("Iphone_11");

manager.getEm().close();
    }

}
