package top.youlanqiang.simplespring.proxy.dynamicproxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author youlanqiang
 * created in 2021/11/14 7:42 下午
 */
public class CglibMeipo implements MethodInterceptor {



    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib 动态代理");
        return methodProxy.invokeSuper(o, objects);
    }


    public Object getInstance(Class<?> clazz){
        Enhancer enhancer = new Enhancer();
        //即将生成的新类的父类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }
}
