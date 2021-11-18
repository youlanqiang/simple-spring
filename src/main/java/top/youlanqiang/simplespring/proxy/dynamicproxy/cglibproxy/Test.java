package top.youlanqiang.simplespring.proxy.dynamicproxy.cglibproxy;

import top.youlanqiang.simplespring.proxy.dynamicproxy.common.Customer;

/**
 * @author youlanqiang
 * created in 2021/11/14 7:41 下午
 * cglib可以代理普通类
 */
public class Test {

    public static void main(String[] args) {
        Customer customer = (Customer) new CglibMeipo().getInstance(Customer.class);
        customer.findLove();
    }
}
