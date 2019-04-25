package zv2.com.cn.dao.usr.customer;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import zv2.com.cn.entity.usr.customer.Customer;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * CRUD
 * @author liubao
 * @date 2019/4/24 3:09
 */
public class CustomerTest {
    @Test
    public void testAdd() {
        Configuration configuration = new Configuration().configure();
        // 手动加载映射文件
//        configuration.addResource("zv2/com/cn/entity/usr/customer/Customer.hbm.xml");
//        configuration.addClass(Customer.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = new Customer();
        customer.setName("Lily");
        customer.setAge(19);
        customer.setGmtCreate(new Date());
        customer.setGmtModified(new Date());
        session.save(customer);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
    @Test
    public void testSelect() {
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
//        Customer customer = (Customer) session.get(Customer.class, 1);
        Customer customer = (Customer) session.load(Customer.class, 1);
        System.out.println(customer.getName());
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
    @Test
    public void testUpdate() {
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
//        Customer customer = new Customer();
//        customer.setId(1);
//        customer.setName("Jack");
//        customer.setAge(21);
//        customer.setGmtModified(new Date());
//        session.update(customer);
        Customer customer = (Customer) session.get(Customer.class, 2);
        customer.setName("Lucy");
        customer.setAge(20);
        customer.setGmtModified(new Date());
        session.update(customer);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
    @Test
    public void testList() {
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
//        Query query = session.createQuery("from Customer");
//        List<Customer> customers = query.list();
//        for (Customer customer : customers) {
//            System.out.println(customer.getName() + "-" + customer.getAge());
//        }
//        Query query = session.createQuery("from Customer where name = ?");
//        query.setParameter(0, "Lucy");
//        List<Customer> customers = query.list();
//        for (Customer customer : customers) {
//            System.out.println(customer.getName() + "-" + customer.getAge());
//        }
//        Query query = session.createQuery("from Customer where name = :name");
//        query.setParameter("name", "Jack");
//        List<Customer> customers = query.list();
//        for (Customer customer : customers) {
//            System.out.println(customer.getName() + "-" + customer.getAge());
//        }
//        Criteria criteria = session.createCriteria(Customer.class);
//        List<Customer> customers = criteria.list();
//        for (Customer customer : customers) {
//            System.out.println(customer.getName() + "-" + customer.getAge());
//        }
//        Criteria criteria = session.createCriteria(Customer.class);
//        criteria.add(Restrictions.eq("name", "Jack"));
//        List<Customer> customers = criteria.list();
//        for (Customer customer : customers) {
//            System.out.println(customer.getName() + "-" + customer.getAge());
//        }
//        SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM usr_customer");
//        List<Object[]> customers = sqlQuery.list();
//        for (Object[] customer : customers) {
//            System.out.println(Arrays.toString(customer));
//        }
        SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM usr_customer");
        sqlQuery.addEntity(Customer.class);
        List<Customer> customers = sqlQuery.list();
        for (Customer customer : customers) {
            System.out.println(customer.getName() + "-" + customer.getAge());
        }
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
    @Test
    public void testDelete() {
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
//        Customer customer = new Customer();
//        customer.setId(4);
//        session.delete(customer);
        Customer customer = (Customer) session.get(Customer.class, 3);
        session.delete(customer);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
