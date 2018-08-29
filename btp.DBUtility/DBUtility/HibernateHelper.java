package DBUtility;

import Common.LogHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateHelper {

    private static SessionFactory sessionFactory = null;

    static {
        try {
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            LogHelper.Error(e.getMessage(), e);
        }
    }

    public static Session getSession() {
        return (Session) sessionFactory;
    }
}
