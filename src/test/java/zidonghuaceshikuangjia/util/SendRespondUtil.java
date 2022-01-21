package zidonghuaceshikuangjia.util;

import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.response.Response;
import zidonghuaceshikuangjia.common.OverAll;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SendRespondUtil {
    public static Response sendRespond(String canshu, String method, String url, Map headers, String inputpara) {

        String fielpath=null;
        if (OverAll.oversuit) {
            PrintStream fileOutPutStream = null;
            fielpath = "log/test" + canshu + System.currentTimeMillis() + ".log";
            try {
                fileOutPutStream = new PrintStream(new File(fielpath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            RestAssured.config = RestAssured.config().logConfig(LogConfig.logConfig().defaultStream(fileOutPutStream));
        }
        String newurl = ReplaceparamUtil.paramReplace(url);
        String Str = ReplaceparamUtil.paramReplace(inputpara);
        Map newheaders = ReplaceparamUtil.paramReplace(headers);
        Response res = null;
        if (method.equalsIgnoreCase("post")) {
            res =
                    given().log().all().
                            headers(newheaders).body(Str).
                            when().
                            post(newurl).
                            then().log().all().
                            extract().response();
        } else if (method.equalsIgnoreCase("get")) {
            res =
                    given().log().all().headers(newheaders).
                            when().
                            get(newurl + "?" + Str).
                            then().log().all().
                            extract().response();
        } else if (method.equalsIgnoreCase("put")) {
            res =
                    given().log().all().
                            headers(newheaders).body(Str).
                            when().put(newurl).
                            then().log().all().
                            extract().response();
        } else {
            System.out.println("输入的请求方式不正确");
        }
        if (OverAll.oversuit){

            try {
                Allure.addAttachment("日志", new FileInputStream(fielpath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return res;
    }
}
