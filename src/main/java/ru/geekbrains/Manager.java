package ru.geekbrains;

import javassist.NotFoundException;
import org.hibernate.annotations.NotFound;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Manager {
  private   EntityManagerFactory emFactory;
  private EntityManager em;
    public Manager(){
      this.emFactory = new Configuration()
              .configure("hibernate.cfg.xml")
              .buildSessionFactory();
      this.em=emFactory.createEntityManager();

  }

    public EntityManager getEm() {
        return em;
    }

    public void showCustomers(){
       List<Customer> customerList=em.createNamedQuery("Customer.findAll",Customer.class).getResultList();
       System.out.println(customerList);
   }
   public void showProducts(){
       List<Product> productList=em.createNamedQuery("Product.findAll",Product.class).getResultList();
        System.out.println(productList);;
   }
    public  void makePurchase(Customer customer,Product product){

    }
    public void addCustmer(Integer id,String first_name,String family_name){
  //      Customer customer=new Customer(id,first_name,family_name);
        em.getTransaction().begin();
        em.persist(new Customer(id,first_name,family_name));
        em.getTransaction().commit();
    }
    public void showPurchaseByName1(String name){
        List<Product> products= em.createQuery("    SELECT  p FROM Product as p" +
                "        join Ties  as t  on t.customer_id=p.id\n" +
                "        join Customer  as c on c.id=t.product_id\n" +
                "        where c.first_name='Alex' " ,Product.class).getResultList();
        System.out.println(products);
    }
    public  void showPurchaseByName(String name ){
        List<Product> products= em.createQuery(" SELECT  p FROM Product as p" +
                "        join Ties  as t  on t.customer_id=p.id\n" +
                "        join Customer  as c on c.id=t.product_id\n" +
                "        where c.first_name=:param",Product.class)
                .setParameter("param", name).getResultList();
        System.out.println("Покупатель "+name+" купил: \n "+products);
    }
    public  void showPurchaseByName2(String name,String fname ){
        List<Product> products= em.createQuery(" SELECT  p FROM Product as p" +
                "        join Ties  as t  on t.customer_id=p.id\n" +
                "        join Customer  as c on c.id=t.product_id\n" +
                "        where c.first_name=:param",Product.class)
                .setParameter("param", name).getResultList();
        System.out.println("Покупатель "+name+" "+ fname+" купил: \n "+products);
    }
    public void showAllPurchases(){
        System.out.println("метод ALL");
        List<Customer> customerList=  em.createQuery("select c from Customer as c").getResultList();
        for (Customer c:customerList) {
            this.showPurchaseByName2(c.getFirst_name(),c.getFamily_name());
        }
    }
    public void showProductsCustomers(String product){
        List<Customer> customers= em.createQuery("    SELECT  c FROM Customer as c" +
                "        join Ties  as t  on t.customer_id=c.id \n" +
                "        join Product  as p on p.id=t.product_id \n" +
                "        where p.title= :p ",Customer.class)
                .setParameter("p",product).getResultList();
        // вот так можно передать ПЕРЕМЕННУЮ в строку запроса
        System.out.println("Товар "+product+" купили: \n" +customers);
    }
    public void showPurchaseNamedQuery(String name){
        List<Product> products= em.createNamedQuery("Customer.customerPurchases",Product.class)
                .setParameter("param", name).getResultList();
        // вот так можно передать ПЕРЕМЕННУЮ в строку запроса
        System.out.println("Покупатель "+name+" купил: \n "+products);
    }
    public void deleteCustomerById(Integer id){
     try {
         em.getTransaction().begin();
         Customer customer=em.createNamedQuery("Customer.findById",Customer.class).
                 setParameter("param",id).
                 getSingleResult();
         em.remove(customer);
         em.getTransaction().commit();
     } catch (Throwable e) {
         e.getMessage();
     }
    }
    public void deleteProductById(Integer id)  {
        try {
            em.getTransaction().begin();
                Product product =em.createNamedQuery("Product.findById",Product.class).
                        setParameter("param",id).
                        getSingleResult();
                em.remove(product);
            em.getTransaction().commit();
        } catch
        (Throwable e){
            e.getMessage(); }

    }
}
