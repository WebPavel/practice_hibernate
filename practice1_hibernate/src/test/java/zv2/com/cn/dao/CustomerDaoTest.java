package zv2.com.cn.dao;

import org.junit.Test;
import zv2.com.cn.dao.usr.customer.CustomerDao;
import zv2.com.cn.entity.usr.customer.Customer;

/**
 * @author liubao
 * @date 2019/4/25 1:13
 */
public class CustomerDaoTest {
    private CustomerDao customerDao = new CustomerDao();
    @Test
    public void testAdd() {
        Customer customer = new Customer();
        customer.setName("nancy");
        customer.setAge(18);
        customerDao.add(customer);
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
        customerDao.saveOrUpdate(customer);
    }
}
