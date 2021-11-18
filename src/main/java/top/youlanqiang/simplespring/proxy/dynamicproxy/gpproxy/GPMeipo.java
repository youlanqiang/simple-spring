package top.youlanqiang.simplespring.proxy.dynamicproxy.gpproxy;

import java.lang.reflect.Method;

public class GPMeipo implements GPInvocationHandler{

    //被代理的对象，把引用保存下来
    private Object target;


    public Object getInstance(Object target){
        this.target = target;
        Class<?> clazz = target.getClass();
        return GPProxy.newProxyInstance(new GPClassLoader(), clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        method.invoke(this.target, args);
        after();
        return null;
    }

    private void before(){
        System.out.println("before");
    }

    private void after(){
        System.out.println("after");
    }
}
