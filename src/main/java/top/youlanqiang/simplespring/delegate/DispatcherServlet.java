package top.youlanqiang.simplespring.delegate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author youlanqiang
 * created in 2021/11/18 11:40 上午
 * spring中的DispatchServlet类似于委派模式
 */
public class DispatcherServlet extends HttpServlet {

    private void doDispatch(HttpServletRequest request, HttpServletResponse response)
    throws Exception{
        String uri = request.getRequestURI();
        if(uri.equals("login")){
            // 调用LoginController
        }
        // ...
        else{
            response.getWriter().write("404 not found!");
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
