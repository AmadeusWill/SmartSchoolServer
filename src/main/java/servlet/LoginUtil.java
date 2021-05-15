package servlet;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LoginUtil {
    private static String jwxtUrl="http://jwxt.cumt.edu.cn/jwglxt/xtgl/login_slogin.html";
    private static String indexUrl="http://jwxt.cumt.edu.cn/jwglxt/xtgl/index_initMenu.html";
    private static WebClient webClient;
    private static HtmlPage loginPage=null;
    public static File getImage(){
        webClient=new WebClient(BrowserVersion.CHROME);
        //模拟浏览器
        webClient.setCssErrorHandler(new SilentCssErrorHandler());

        webClient.setAjaxController(new NicelyResynchronizingAjaxController());

        webClient.getOptions().setJavaScriptEnabled(true);
        //支持js
        webClient.getOptions().setCssEnabled(false);
        //CSS渲染禁止，不加载页面
        webClient.getOptions().setTimeout(50000);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setRedirectEnabled(true);
        //允许重定向
        webClient.getCookieManager().setCookiesEnabled(true);

        try {
            loginPage=webClient.getPage(jwxtUrl);
        }catch (IOException e){
            e.printStackTrace();
        }
        webClient.waitForBackgroundJavaScript(1000*3);
        HtmlImage image=(HtmlImage) loginPage.getElementById("yzmPic");
        File file=new File("D:\\IdeaProjects\\crawler.server\\src\\main\\resource\\img\\yzm.png");
        try{
            image.saveAs(file);
        }catch (IOException e){
            e.printStackTrace();
        }
        return file;
    }
    public static Map<String,String> login(final String name, final String pwd, final String code){
        HtmlInput username=(HtmlInput) loginPage.getElementById("yhm");
        HtmlInput password=(HtmlInput) loginPage.getElementById("mm");
        HtmlInput safeCode=(HtmlInput) loginPage.getElementById("yzm");
        HtmlButton login=(HtmlButton) loginPage.getByXPath("/html/body/div[1]/div[2]/div[2]/form/div/div/div[1]/div[5]/button").get(0);
        username.setValueAttribute(name);
        password.setValueAttribute(pwd);
        safeCode.setValueAttribute(code);
        try{
            login.click();
        }catch (IOException e){
            e.printStackTrace();
        }
        webClient.waitForBackgroundJavaScript(1000);
        HtmlPage page=null;
        try {
            page=webClient.getPage(indexUrl);
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(page.asXml());
        webClient.waitForBackgroundJavaScript(1000*3);
        Set<Cookie> cookieSet=webClient.getCookieManager().getCookies();
        Map<String,String> cookies=new HashMap<>();
        for(Cookie c:cookieSet){
            cookies.put(c.getName(),c.getValue());
        }
        webClient.close();
        return cookies;
    }
}
