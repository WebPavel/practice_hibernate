package zv2.com.cn.dao.usr;

import org.hibernate.Session;
import org.hibernate.Transaction;
import zv2.com.cn.entity.usr.Customer;
import zv2.com.cn.utils.HibernateSessionFactory;

import java.util.List;

/**
 * @author lb
 * @date 2019/5/13
 */
public class CustomerDao {

    public List<Customer> findAll() {
        Session session = HibernateSessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Customer> customers = session.createQuery("from Customer").list();
        transaction.commit();
        return customers;
    }

    public void delete(Customer customer) {
        Session session = HibernateSessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        // 注意级联删除问题，解决方案：先查找，后删除
        Customer c = (Customer) session.get(Customer.class, customer.getId());
        session.delete(c);
        transaction.commit();
    }
}
