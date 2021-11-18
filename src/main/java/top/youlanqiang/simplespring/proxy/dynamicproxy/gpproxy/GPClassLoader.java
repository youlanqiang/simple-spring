package top.youlanqiang.simplespring.proxy.dynamicproxy.gpproxy;

import java.io.*;

public class GPClassLoader extends ClassLoader{


    private File classPathFile;

    public GPClassLoader(){
        String classPath = GPClassLoader.class.getResource("").getPath();
        this.classPathFile = new File(classPath);
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException{
        String className = GPClassLoader.class.getPackage().getName()+"."+name;
        if(classPathFile != null){
            File classFile = new File(classPathFile,name.replaceAll("\\.","/")+".class");
            System.out.println(classFile.toPath().toString());
            if(classFile.exists()){
                FileInputStream in = null;
                ByteArrayOutputStream out = null;
                try{
                    in = new FileInputStream(classFile);
                    out = new ByteArrayOutputStream();
                    byte[] buff = new byte[1024]; // 1mb
                    int len;
                    while((len = in.read(buff))!= -1){
                        // off 偏移量 读取buff数组 0到len
                        out.write(buff, 0, len);
                    }
                    //defineClass方法接受一组字节，然后将其具体化为一个Class类型实例，它一般从磁盘上加载一个文件，
                    //然后将文件的字节传递给JVM，通过JVM（native 方法）对于Class的定义，将其具体化，实例化为一个Class类型实例。
                    return defineClass(className, out.toByteArray(), 0 , out.size());
                } catch (IOException e) {
                    e.printStackTrace();
                }finally{
                    if(null != in){
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if(out != null){
                        try{
                            out.close();;
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

}
