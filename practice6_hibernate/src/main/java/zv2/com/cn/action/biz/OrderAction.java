package zv2.com.cn.action.biz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import zv2.com.cn.entity.biz.Order;
import zv2.com.cn.service.biz.OrderService;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author lb
 * @date 2019/5/13
 */
public class OrderAction extends ActionSupport {
    public String findByCustomer() {
        OrderService orderService = new OrderService();
        List<Order> orders = orderService.findByCustomer(customerId);
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        Set<String> excludes = filter.getExcludes();
        excludes.add("id");
        excludes.add("customer");
        // 关闭循环引用
        String orderJson = JSON.toJSONString(orders, filter, SerializerFeature.DisableCircularReferenceDetect);
        System.out.println(orderJson);
        ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
        try {
            ServletActionContext.getResponse().getWriter().print(orderJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * 注意这里返回值为"none",且设置返回类型为text/json;charset=UTF-8
         */
        return Action.NONE;
    }

    private String customerId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
