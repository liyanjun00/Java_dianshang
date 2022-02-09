package zidonghuaceshikuangjia.service;

import io.restassured.response.Response;
import zidonghuaceshikuangjia.apicall.ApiCall;
import zidonghuaceshikuangjia.common.Envment;
import zidonghuaceshikuangjia.util.SendRespondUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Login_Search_Choice {

    public static Response
    login_search_addcart(String para) {
        //登录
        Response res = ApiCall.login(para);
        Envment.addEnv("token", (String) res.jsonPath().get("access_token"));
        System.out.println((String) res.jsonPath().get("access_token"));
        //搜索
        Response searchresult = ApiCall.search();
        List<Integer> prid = searchresult.jsonPath().getList("records.prodId");
        System.out.println(prid.get(1));
        for (Integer id:prid){
            String url="http://mall.lemonban.com:8107/prod/prodInfo";
            Map headers = new HashMap();
            headers.put("Content-Type", "application/json");
            Response re=SendRespondUtil.sendRespond("查询库存","get",url,headers,"prodId="+id);
            System.out.println(re.jsonPath().get("totalStocks").getClass());
            if( (int)re.jsonPath().get("totalStocks")>0){
                Envment.addEnv("prodId", id);
                break;
            }
        }
        //选择商品
        Response choiceresult = ApiCall.choice( (Integer) Envment.getEnv("prodId"));
        Envment.addEnv("cprid", choiceresult.jsonPath().get("prodId"));
        return choiceresult;
    }

}
