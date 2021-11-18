package top.youlanqiang.simplespring.proxy.dynamicproxy.jdkproxy;

import top.youlanqiang.simplespring.proxy.dynamicproxy.common.Customer;
import top.youlanqiang.simplespring.proxy.dynamicproxy.common.Person;

import java.lang.reflect.Proxy;

/**
 * @author youlanqiang
 * created in 2021/11/14 7:38 下午
 */
public class Test {

    public static void main(String[] args) {
        Person person = new Customer();
        Person proxy = (Person)Proxy.newProxyInstance(Test.class.getClassLoader(),
                new Class[]{Person.class},new JDKMeipo(person) );
        proxy.findLove();
    }
}
