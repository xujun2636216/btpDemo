package DBUtility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateHelper {

    private static final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    private static Session sqlSessionFactory;

    static {
        try {
            SessionFactory sessionFactory = new MetadataSources(registry)
                    .buildMetadata().buildSessionFactory();
            sqlSessionFactory = sessionFactory.openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Session getSession() {
        return sqlSessionFactory;
    }
}
