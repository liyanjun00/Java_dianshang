package zidonghuaceshikuangjia.service;

import io.restassured.response.Response;
import zidonghuaceshikuangjia.apicall.ApiCall;
import zidonghuaceshikuangjia.common.Envment;

public class Login_Search_Choice {

    public static Response
    login_search_addcart(String para) {
        //登录
        Response res = ApiCall.login(para);
        Envment.addEnv("token", (String) res.jsonPath().get("access_token"));
        System.out.println((String) res.jsonPath().get("access_token"));
        //搜索
        Response searchresult = ApiCall.search();
        int prid = searchresult.jsonPath().get("records[0].prodId");
        Envment.addEnv("prodId", prid);
        //选择商品
        Response choiceresult = ApiCall.choice(prid);
        Envment.addEnv("cprid", choiceresult.jsonPath().get("prodId"));
        return choiceresult;
    }

}
