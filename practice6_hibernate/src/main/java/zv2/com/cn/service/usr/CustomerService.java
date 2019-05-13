package zv2.com.cn.service.usr;

import zv2.com.cn.dao.usr.CustomerDao;
import zv2.com.cn.entity.usr.Customer;

import java.util.List;

/**
 * @author lb
 * @date 2019/5/13
 */
public class CustomerService {
    private CustomerDao customerDao = new CustomerDao();
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    public void delete(Customer customer) {
        customerDao.delete(customer);
    }
}
