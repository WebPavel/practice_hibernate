package zv2.com.cn.dao.usr.customer;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import zv2.com.cn.entity.usr.customer.Customer;
import zv2.com.cn.utils.HibernateUtils;

/**
 * @author liubao
 * @date 2019/4/25 1:13
 */
public class CustomerDaoTest {

    /**
     * save、persist增
     * update改、saveOrUpdate添加或更新
     * delete 删
     * get、load根据主键查询
     * createQuery 创建Query接口
     * createSQLQuery创建SQLQuery接口
     * createCriteria条件查询
     */

    public void add(Customer customer) {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        session.close();
    }

    public void saveOrUpdate(Customer customer) {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(customer);
        transaction.commit();
        session.close();
    }

    @Test
    public void testAdd() {
        Customer customer = new Customer();
        customer.setName("nancy");
        customer.setAge(18);
        add(customer);
    }
    @Test
    public void testSaveOrUpdate() {
//        Customer customer = new Customer();
//        customer.setName("nancy");
//        customer.setAge(18);
//        customerDao.saveOrUpdate(customer);
        Customer customer = new Customer();
        customer.setId(3);
        customer.setName("nancy");
        customer.setAge(18);
        saveOrUpdate(customer);
    }
}
