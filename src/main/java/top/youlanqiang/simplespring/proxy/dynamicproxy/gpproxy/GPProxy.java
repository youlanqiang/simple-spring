package top.youlanqiang.simplespring.proxy.dynamicproxy.gpproxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

//用来生成源代码的工具类
public class GPProxy {

    public static final String ln = "\r\n";

    public static Object newProxyInstance(GPClassLoader classLoader,
                                          Class<?> [] interfaces,
                                          GPInvocationHandler handler){
        try{
            String src = generateSrc(interfaces);

            String filePath = GPProxy.class.getResource("").getPath();
            File f = new File(filePath+"$Proxy0.java");
            FileWriter fw = new FileWriter(f);
            fw.write(src);
            fw.flush();
            fw.close();

            //把生成的java文件编译为.class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null,null,null);
            Iterable iterable = manager.getJavaFileObjects(f);

            JavaCompiler.CompilationTask task = compiler.getTask(null, manager,null,null,null, iterable);
            task.call();
            manager.close();

            //把编译生成的.class文件加载到jvm中
            Class proxyClass = classLoader.findClass("$Proxy0");
            Constructor c = proxyClass.getConstructor(GPInvocationHandler.class);
            f.delete();

            return c.newInstance(handler);
        } catch (Exception  e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String generateSrc(Class<?>[] interfaces){
        StringBuffer sb = new StringBuffer();
        sb.append("package top.youlanqiang.simplespring.proxy.dynamicproxy.gpproxy;"+ln);
        sb.append("import java.lang.reflect.*;"+ln);
        sb.append("import top.youlanqiang.simplespring.proxy.dynamicproxy.common.Person;"+ln);
        sb.append("public class $Proxy0 implements "+interfaces[0].getName()+" {"+ln);
        sb.append("GPInvocationHandler handler;"+ln);
        sb.append("public $Proxy0(GPInvocationHandler h){"+ln);
        sb.append("this.handler = h;"+ln);
        sb.append("}"+ln);

        for (Method method : interfaces[0].getMethods()) {
            Class<?>[] params = method.getParameterTypes();

            StringBuffer paramNames = new StringBuffer();
            StringBuffer paramValues = new StringBuffer();
            StringBuffer paramClasses = new StringBuffer();

            for (int i = 0; i < params.length; i++) {
                Class clazz = params[i];
                String type = clazz.getName();
                String paramName = toLowerFirstCase(clazz.getSimpleName());
                paramNames.append(type + " " + paramName);
                paramValues.append(paramName);
                paramClasses.append(clazz.getName() + ".class");
                if (i > 0 && i < params.length - 1) {
                    paramNames.append(",");
                    paramClasses.append(",");
                    paramValues.append(",");
                }
            }

            // public void hello (int x){
            sb.append("public " + method.getReturnType().getName() + " " + method.getName() +
                    "(" + paramNames.toString() + "){" + ln);
            sb.append("try{" + ln);
            sb.append("Method m =" + interfaces[0].getName() + ".class" +
                    ".getMethod(\"" + method.getName() + "\",new Class[]{" + paramClasses.toString() + "});" + ln);
            sb.append((hasReturnValue(method.getReturnType()) ? "return" : "")
                    + getCaseCode("this.handler.invoke(this,m,new Object[]{" + paramValues + "})", method.getReturnType()) + ";" + ln);
            sb.append("}catch(Error _ex){}");
            sb.append("catch(Throwable e){" + ln);
            sb.append("throw new UndeclaredThrowableException(e);" + ln);
            sb.append("}");
            sb.append(getReturnEmptyCode(method.getReturnType()));
            sb.append("}");
        }
        sb.append("}"+ln);
        return sb.toString();
    }


    private static Map<Class,Class> mappings = new HashMap<>();

    static{
        mappings.put(int.class,Integer.class);
    }




    //设置返回值
    private static String getReturnEmptyCode(Class<?> returnClass){
       if(mappings.containsKey(returnClass)){
           return "return 0;";
       }else if(returnClass == void.class){
           return "";
       }else{
           return "return null;";
       }
    }

    private static String getCaseCode(String code, Class<?> returnClass){
        if(mappings.containsKey(returnClass)){
            return "(("+mappings.get(returnClass).getName()+")"+code+")."+returnClass.getSimpleName()+"Value()";
        }
        return code;
    }


    public static boolean hasReturnValue(Class<?> clazz){
        return clazz != void.class;
    }

    //字符串第一位转小写.
    public static String toLowerFirstCase(String src){
        char[] chars = src.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
