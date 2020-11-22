package ru.geekbrains;


import javax.persistence.*;
import java.util.List;
           // объявляем сущность
@Entity
@Table(name = "users")           // можно объявить таблицу в которой хранятся сущности
//@NamedQuery(name = "User.findAll",query ="SELECT a from User a")

@NamedQueries( {            // аннотция применяется к КЛАССУ
        @NamedQuery(name = "User.findAll",query ="SELECT a from User a") ,
        @NamedQuery(name = "User.findByLogiтPetr",query ="SELECT a from User a where a.login='petr' ")
}  )                  /* будет работать с любым именем,но рекамендуется писать имя класса как префикс
                         иначе в коде будет неясно,что делает метод         */
public class User {

    @Id     //поле является первичным ключои
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //автогенерицию и автоинкремент
    private Integer id;
                                   // тогда конструктор NULL примет
    @Column
    private String login;

    @Column
    private String password;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    private List<Contact> contacts;

    public User() {
    }

    public User(Integer id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override            // toString имеет возвращаемое значение
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

//    можно  и так, как я сделал:
               public void  toPrint() {

                   System.out.println("User{" +
                       "id=" + id + ", login='" + login + '\'' +
                               ", password='" + password + '\'' +
                               '}');
               }
}
