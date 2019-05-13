package zv2.com.cn.service.biz;

import zv2.com.cn.dao.biz.OrderDao;
import zv2.com.cn.entity.biz.Order;

import java.util.List;

/**
 * @author lb
 * @date 2019/5/13
 */
public class OrderService {
    private OrderDao orderDao = new OrderDao();
    public List<Order> findByCustomer(String customerId) {
        return orderDao.findByCustomerId(customerId);
    }
}
