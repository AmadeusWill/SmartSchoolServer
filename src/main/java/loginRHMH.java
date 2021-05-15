//import com.gargoylesoftware.htmlunit.BrowserVersion;
//import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
//import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
//import com.gargoylesoftware.htmlunit.WebClient;
//import com.gargoylesoftware.htmlunit.html.HtmlElement;
//import com.gargoylesoftware.htmlunit.html.HtmlInput;
//import com.gargoylesoftware.htmlunit.html.HtmlPage;
//
//import java.io.IOException;
//
//public class loginRHMH {
//    private static String rhmhUrl="http://authserver.cumt.edu.cn/authserver/login?service=http%3A//portal.cumt.edu.cn/casservice";
//    public static void main(String[] args) {
//        WebClient webClient=new WebClient(BrowserVersion.CHROME);
//        //模拟浏览器
//        webClient.setCssErrorHandler(new SilentCssErrorHandler());
//
//        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
//
//        webClient.getOptions().setJavaScriptEnabled(true);
//        //支持js
//        webClient.getOptions().setCssEnabled(false);
//        //CSS渲染禁止，不加载页面
//        webClient.getOptions().setTimeout(50000);
//        webClient.getOptions().setThrowExceptionOnScriptError(false);
//        webClient.getOptions().setUseInsecureSSL(true);
//        webClient.getOptions().setRedirectEnabled(true);
//        //允许重定向
//        webClient.getCookieManager().setCookiesEnabled(true);
//        HtmlPage page=null;
//        try{
//            page=webClient.getPage(rhmhUrl);
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        webClient.waitForBackgroundJavaScript(1000*3);
//        HtmlElement loginMethod=(HtmlElement) page.getElementById("userNameLogin_a");
//        try {
//            loginMethod.click();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        webClient.waitForBackgroundJavaScript(3*1000);
//        HtmlInput username=(HtmlInput) page.getElementById("username");
//        HtmlInput password=(HtmlInput) page.getElementById("password");
//        HtmlElement loginButton=(HtmlElement) page.getElementById("login_submit");
//        username.setValueAttribute("08173018");
//        password.setValueAttribute("wfq249108012");
//        try{
//            loginButton.click();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        webClient.waitForBackgroundJavaScript(1000*3);
//        try{
//            page=webClient.getPage("http://portal.cumt.edu.cn/");
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        System.out.println(page.asXml());
//    }
//}
