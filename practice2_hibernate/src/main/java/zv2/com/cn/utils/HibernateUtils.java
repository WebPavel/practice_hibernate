package zv2.com.cn.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author liubao
 * @date 2019/4/28 0:51
 */
public class HibernateUtils {
    private static Configuration configuration;
    private static SessionFactory sessionFactory;
    static {
        configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }
    public static Session openSession() {
        return sessionFactory.openSession();
    }

    public static void main(String[] args) {
        openSession();
    }
}
