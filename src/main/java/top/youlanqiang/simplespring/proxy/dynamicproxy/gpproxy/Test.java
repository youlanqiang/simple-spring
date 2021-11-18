package top.youlanqiang.simplespring.proxy.dynamicproxy.gpproxy;

import top.youlanqiang.simplespring.proxy.dynamicproxy.common.Customer;
import top.youlanqiang.simplespring.proxy.dynamicproxy.common.Person;

public class Test {


    public static void main(String[] args) {
        Person customer =(Person) new GPMeipo().getInstance(new Customer());
        //System.out.println(customer.getClass());
        customer.findLove();
    }


}
