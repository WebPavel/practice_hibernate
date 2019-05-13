package zv2.com.cn.action.usr;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import zv2.com.cn.entity.usr.Customer;
import zv2.com.cn.service.usr.CustomerService;

import java.util.List;

/**
 * @author lb
 * @date 2019/5/13
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

    public String findAll() {
        CustomerService customerService = new CustomerService();
        List<Customer> customers = customerService.findAll();
        ActionContext actionContext = ActionContext.getContext();
        actionContext.getValueStack().set("customers", customers);
        return "findAllSuccess";
    }
    public String delete() {
        CustomerService customerService = new CustomerService();
        customerService.delete(customer);
        return "deleteSuccess";
    }

    @Override
    public Customer getModel() {
        return customer;
    }
    private Customer customer = new Customer();
}
