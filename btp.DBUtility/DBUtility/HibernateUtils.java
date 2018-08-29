package DBUtility;

import Common.LogHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {

    private static SessionFactory sessionFactory = null;
    private static final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    static {
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            LogHelper.Error(e.getMessage(), e);
        }
    }

    public static Session getSession() {
        return (Session) sessionFactory;
    }
}
