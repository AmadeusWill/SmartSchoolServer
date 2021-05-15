package servlet;
import bean.CookieDataBean;
import bean.CookieTotal;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<CookieDataBean> list=new ArrayList<>();
//        for(int i=0;i<10;i++){
//            list.add(new CookieDataBean(Integer.toString(i),"这是第"+i+"条休息"));
//        }
//        CookieTotal ct=new CookieTotal(list.size(),list);
//        Gson gson=new Gson();
//        String json=gson.toJson(ct);
//        resp.setContentType("text/plain");
//        resp.setCharacterEncoding("gb2312");
//        PrintWriter out=new PrintWriter(resp.getOutputStream());
//        out.print(json);
//        out.flush();
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String code=req.getParameter("code");
        Map<String,String> cookies=LoginUtil.login(username,password,code);
        List<CookieDataBean> list=new ArrayList<>();
        Set<String> keySet=cookies.keySet();
        for(String key:keySet){
            list.add(new CookieDataBean(key,cookies.get(key)));
        }
        CookieTotal ct=new CookieTotal(list.size(),list);
        Gson gson=new Gson();
        String json=gson.toJson(ct);
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("gb2312");
        PrintWriter out=new PrintWriter(resp.getOutputStream());
        out.print(json);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
