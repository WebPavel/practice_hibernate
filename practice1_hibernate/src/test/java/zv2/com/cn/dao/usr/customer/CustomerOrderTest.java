package zv2.com.cn.dao.usr.customer;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import zv2.com.cn.entity.biz.order.Order;
import zv2.com.cn.entity.usr.customer.Customer;
import zv2.com.cn.utils.HibernateUtils;

/**
 * @author liubao
 * @date 2019/4/27 2:40
 */
public class CustomerOrderTest {

    /**
     * inverse默认是false，表示不放弃外键维护
     * 注意区分cascade、inverse
     */
    @Test
    public void testInverse() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = new Customer();
        customer.setName("tony");
        customer.setAge(24);
        customer.setPassword("123456");
        Order order = new Order();
        order.setName("tony的订单2019042800");
        order.setAddress("sz");
        customer.getOrders().add(order);
        // 客户是否保存到数据库了?订单是否保存到数据库了?都保存到数据库了，这是由cascade控制的.但是order的外键为null，是由inverse控制的
//        order.setCustomer(customer); // 将外键维护交给order
        session.save(customer);
        transaction.commit();
        session.close();
    }

    /**
     * 双向维护外键产生多余SQL
     */
    @Test
    public void testUpdate() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = (Customer) session.get(Customer.class, 1);
        Order order = (Order) session.get(Order.class, 2);
        customer.getOrders().add(order);
        order.setCustomer(customer);
        transaction.commit();
        session.close();
    }

    /**
     * 孤儿删除，解除父子关系
     */
    @Test
    public void testDeleteCascadeOrphan() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = (Customer) session.get(Customer.class, 1);
        Order order = (Order) session.get(Order.class, 1);
        customer.getOrders().remove(order);
        transaction.commit();
        session.close();
    }

    @Test
    public void testDeleteCascade() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Order order = (Order) session.get(Order.class, 1);
        session.delete(order);
        transaction.commit();
        session.close();
    }

    @Test
    public void testDelete() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = (Customer) session.get(Customer.class, 1);
        session.delete(customer);
        transaction.commit();
        session.close();
    }

    @Test
    public void testCascade() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = new Customer();
        customer.setName("小明");
        customer.setAge(22);
        customer.setPassword("123456");
        Order order = new Order();
        order.setName("iphone xr");
        order.setAddress("gz,1nd Ave");
        Order order1 = new Order();
        order1.setName("iphone x");
        order1.setAddress("gz,2nd Ave");
        Order order2 = new Order();
        order2.setName("iphone xs");
        order2.setAddress("gz,3nd Ave");
        customer.getOrders().add(order);
        customer.getOrders().add(order1);
        order2.setCustomer(customer);
//        session.save(order2); // 4条insert语句
//        session.save(customer); // 3条insert语句
        session.save(order1); // 1条insert语句
        transaction.commit();
        session.close();
    }
    @Test
    public void testAddCascade() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = new Customer();
        customer.setName("小喵");
        customer.setAge(21);
        customer.setPassword("123456");
        Order order = new Order();
        order.setName("iphone xr");
        order.setAddress("gz,2nd Ave");
        customer.getOrders().add(order);
        order.setCustomer(customer);
        session.save(customer);
        transaction.commit();
        session.close();
    }
    @Test
    public void testAdd() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = new Customer();
        customer.setName("小姐姐");
        customer.setAge(23);
        customer.setPassword("123456");
        Order order = new Order();
        order.setName("iphone xr");
        order.setAddress("gz,2nd Ave");
        Order order1 = new Order();
        order1.setName("iphone x");
        order1.setAddress("gz,2nd Ave");
        customer.getOrders().add(order);
        customer.getOrders().add(order1);
        order.setCustomer(customer);
        order1.setCustomer(customer);
        session.save(customer);
        session.save(order);
        session.save(order1);
        transaction.commit();
        session.close();
    }
}
