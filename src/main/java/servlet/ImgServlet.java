package servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ImgServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp); 需要去掉
        ServletOutputStream out=null;
        BufferedInputStream buf=null;
        File file=LoginUtil.getImage();
        try{
            buf=new BufferedInputStream(new FileInputStream(file));
            resp.setContentType("multipart/form-data");
            out=resp.getOutputStream();
            byte[] buffer=new byte[1024];
            while(buf.read(buffer)!=-1){
                out.write(buffer);
            }
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(buf!=null){
                buf.close();
            }
            if(out!=null){
                out.close();
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        doGet(req,resp);
    }
}
