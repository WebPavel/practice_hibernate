package zv2.com.cn.dao.biz;

import org.hibernate.Session;
import org.hibernate.Transaction;
import zv2.com.cn.entity.biz.Order;
import zv2.com.cn.utils.HibernateSessionFactory;

import java.util.List;

/**
 * @author lb
 * @date 2019/5/13
 */
public class OrderDao {
    public List<Order> findByCustomerId(String customerId) {
        Session session = HibernateSessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
//        List<Order> orders = session.createQuery("from Order o where o.customer=?").setEntity(0, customer).list();
        List<Order> orders = session.createQuery("from Order o where o.customer.id=?").setParameter(0, customerId).list();
        transaction.commit();
        return orders;
    }
}
