package ru.geekbrains;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.SequenceGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Properties;
import java.util.stream.Stream;


public class MyGenerator2 extends SequenceGenerator
        implements Configurable {

    private String prefix;
    private String year = Integer.toString(LocalDate.now().getYear());

    @Override
    public Serializable generate(
            SharedSessionContractImplementor session, Object obj)
            throws HibernateException {

//        String query = String.format("select %s from %s",
//                session.getEntityPersister(obj.getClass().getName(), obj)
//                        .getIdentifierPropertyName(),
//                obj.getClass().getSimpleName());

//        String query = "select p.id  from Product2 as p";
        String query = "select p.name  from Product2 as p";


        Stream ids = session.createQuery(query).stream();

//        Integer max = ids.map(o -> o.toString().replace(prefix + "_", "")
//                .replace( "_"+ year,""))
//                .mapToInt(o->Integer.parseInt(o.toString()))
//                .max()
//                .orElse(0);

        Integer max = ids.filter(f -> f.toString().endsWith(year)).
                map(o -> o.toString()
                        .replace(prefix + "-", "")
                        .replace("-" + year, ""))
                .mapToInt(o -> Integer.parseInt(o.toString()))
                .max()
                .orElse(0);

        return prefix + "-" + (max + 1) + "-" + year;

    }

    @Override
    public void configure(Type type, Properties properties,
                          ServiceRegistry serviceRegistry) throws MappingException {
        prefix = properties.getProperty("prefix");
    }
}