//import com.gargoylesoftware.htmlunit.BrowserVersion;
//import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
//import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
//import com.gargoylesoftware.htmlunit.WebClient;
//import com.gargoylesoftware.htmlunit.html.*;
//import com.gargoylesoftware.htmlunit.util.Cookie;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.jsoup.Connection;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.*;
//
//public class LoginJWXT {
//
//    private static String jwxtUrl="http://jwxt.cumt.edu.cn/jwglxt/xtgl/login_slogin.html";
//    private static Map<String,String> cookies=new HashMap<>();
//    private static void login(){
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
//        try {
//            page=webClient.getPage(jwxtUrl);
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        webClient.waitForBackgroundJavaScript(1000*3);
//        HtmlForm form=(HtmlForm) page.getByXPath("/html/body/div[1]/div[2]/div[2]/form").get(0);
//        HtmlInput username=(HtmlInput) page.getElementById("yhm");
//        HtmlInput password=(HtmlInput) page.getElementById("mm");
//        HtmlInput safeCode=(HtmlInput) page.getElementById("yzm");
//        HtmlButton login=(HtmlButton) page.getByXPath("/html/body/div[1]/div[2]/div[2]/form/div/div/div[1]/div[5]/button").get(0);
//        HtmlImage safeCodeImg=(HtmlImage) page.getElementById("yzmPic");
//        File file=new File("D:\\IdeaProjects\\crawler.server\\src\\main\\resource\\img\\yzm.png");
//        try{
//            safeCodeImg.saveAs(file);
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        Scanner scanner=new Scanner(System.in);
//        String code=scanner.nextLine();
//        username.setValueAttribute("08173018");
//        password.setValueAttribute("wfq249108012");
//        safeCode.setValueAttribute(code);
//        try {
//            login.click();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        webClient.waitForBackgroundJavaScript(1000);
//        try{
//            page=webClient.getPage("http://jwxt.cumt.edu.cn/jwglxt/xtgl/index_initMenu.html");
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        webClient.waitForBackgroundJavaScript(1000);
//        Set<Cookie> cookieSet=webClient.getCookieManager().getCookies();
//        for(Cookie c:cookieSet){
//            cookies.put(c.getName(),c.getValue());
//        }
//        webClient.close();
//        searchStandardInfo();
//    }
//    private static void searchPoint(String xnm,String xqm){
//        Connection connection=Jsoup.connect("http://jwxt.cumt.edu.cn/jwglxt/cjcx/cjcx_cxDgXscj.html?doType=query&gnmkdm=N305005&su=08173018");
//        connection.header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.85 Safari/537.36 Edg/90.0.818.49");
//        connection.header("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
//        connection.cookies(cookies);
//        connection.data("xnm",xnm)
//                .data("xqm",xqm)
//                .data("_search","false")
//                .data("nd",Integer.toString(new Date().getDate()))
//                .data("queryModel.showCount",Integer.toString(20))
//                .data("queryModel.currentPage",Integer.toString(1))
//                .data("queryModel.sortOrder","asc")
//                .data("time",Integer.toString(1));
//        Connection.Response response=null;
//        try {
//            response=connection.ignoreContentType(true).method(Connection.Method.POST).execute();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
////        System.out.println(response.body());
//        JSONObject jsonObject=new JSONObject(response.body());
//        JSONArray jsonArray=new JSONArray(jsonObject.getJSONArray("items"));
//        System.out.println("成绩信息：");
//        for(int i=0;i<jsonArray.length();i++){
//            jsonObject=jsonArray.getJSONObject(i);
//            System.out.println(jsonObject.getString("kcmc"));
//            System.out.println(jsonObject.getString("cj"));
//            System.out.println(jsonObject.getString("jd"));
//            System.out.println(jsonObject.getString("xf"));
//            System.out.println("");
//        }
//
//        searchTestInfo("2019","3");
//    }
//
//    private static void searchTestInfo(final String xnm,final String xqm){{
//        Connection connection=Jsoup.connect("http://jwxt.cumt.edu.cn/jwglxt/kwgl/kscx_cxXsksxxIndex.html?doType=query&gnmkdm=N358105&su=08173018");
//        connection.header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.85 Safari/537.36 Edg/90.0.818.49");
//        connection.header("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
//        connection.data("xnm",xnm)
//                .data("xqm",xqm)
//                .data("_search","false")
//                .data("nd",Integer.toString(new Date().getDate()))
//                .data("queryModel.showCount",Integer.toString(20))
//                .data("queryModel.currentPage",Integer.toString(1))
//                .data("queryModel.sortOrder","asc")
//                .data("time",Integer.toString(1));
//        Connection.Response response=null;
//        try{
//            response=connection.cookies(cookies).ignoreContentType(true).method(Connection.Method.POST).followRedirects(true).execute();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        JSONObject jsonObject=new JSONObject(response.body());
//        JSONArray jsonArray=new JSONArray(jsonObject.getJSONArray("items"));
//        System.out.println("考试信息：");
//        for(int i=0;i<jsonArray.length();i++){
//            jsonObject=jsonArray.getJSONObject(i);
//            System.out.println(jsonObject.getString("kcmc"));
//            System.out.println(jsonObject.getString("cdxqmc"));
//            System.out.println(jsonObject.getString("cdmc"));
//            System.out.println(jsonObject.getString("kssj"));
//            System.out.println(jsonObject.getString("xf"));
//            System.out.println("");
//        }
//    }
//
//    }
//    private static void searchStandardInfo(){
//        Connection connection=Jsoup.connect("http://jwxt.cumt.edu.cn/jwglxt/xsxxxggl/xsgrxxwh_cxXsgrxx.html?gnmkdm=N100801&layout=default&su=08173018");
//        connection.header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.85 Safari/537.36 Edg/90.0.818.49");
//        Connection.Response response =null;
//        try{
//            response = connection.cookies(cookies).ignoreContentType(false).followRedirects(true).execute();
//        }    catch (IOException e){
//            e.printStackTrace();
//        }
//        Document document =Jsoup.parse(response.body());
//        System.out.println("学生信息：");
//        Element element=document.select("#col_xm p").first();
//        System.out.println(element.text());
//        element=document.select("#col_xbm p").first();
//        System.out.println(element.text());
//        element=document.select("#col_njdm_id p").first();
//        System.out.println(element.text());
//        element=document.select("#col_jg_id p").first();
//        System.out.println(element.text());
//        element=document.select("#col_zyh_id p").first();
//        System.out.println(element.text());
//        element=document.select("#col_bh_id p").first();
//        System.out.println(element.text());
//        System.out.println("");
//
//        searchPoint("2018","3");
//    }
//    public static void main(String[] args) {
//        login();
//    }
//}
