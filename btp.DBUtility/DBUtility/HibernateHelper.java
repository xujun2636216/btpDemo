package DBUtility;

import Common.LogHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateHelper {

    private static SessionFactory sessionFactory = null;

    static {
        try {
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        }
    }

    public static Session getSession() {
        return (Session) sessionFactory.openSession();
    }
}
