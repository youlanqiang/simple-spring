package top.youlanqiang.simplespring.proxy.dynamicproxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JDKMeipo implements InvocationHandler {

    private final Object target;

    public JDKMeipo(Object target){
        this.target = target;
    }
    

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk动态代理");
        return method.invoke(target, args);
    }
}
