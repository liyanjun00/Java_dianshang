package zidonghuaceshikuangjia.apicall;

import io.restassured.response.Response;
import zidonghuaceshikuangjia.common.Envment;
import zidonghuaceshikuangjia.util.ReplaceparamUtil;
import zidonghuaceshikuangjia.util.SendRespondUtil;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiCall {
    /*PUT http://mall.lemonban.com:8107/user/sendRegisterSms HTTP/1.1
    Content-Type: application/json; charset=UTF-8
    {"mobile":"15111111111"}
     */
    public static void sendVC(String phone) {
        String url = "http://mall.lemonban.com:8107/user/sendRegisterSms";
        Map headers = new HashMap();
        headers.put("Content-Type", "application/json");
        SendRespondUtil.sendRespond("发送验证码", "put", url, headers, phone);

    }

    /*
    PUT http://mall.lemonban.com:8107/user/checkRegisterSms HTTP/1.1
    Content-Type: application/json; charset=UTF-8
    {"mobile":"15111111111","validCode":"029019"}
    e454db65e94a4a5192fc1e87acc9dce9
     */
    public static Response checkVC(String body) {
        String url = "http://mall.lemonban.com:8107/user/checkRegisterSms";
        Map<String, String> headers = new HashMap();
        headers.put("Content-Type", "application/json");
        return SendRespondUtil.sendRespond("检验验证码", "put", url, headers, body);
    }

    /*
    PUT http://mall.lemonban.com:8107/user/registerOrBindUser HTTP/1.1
    application/json; charset=UTF-8
    {"appType":3,"checkRegisterSmsFlag":"e454db65e94a4a5192fc1e87acc9dce9","mobile":"15111111111",
    "userName":"liyanyan","password":"123456","registerOrBind":1,"validateType":1}
     */
    public static Response register(String body) {
        Map headers = new HashMap();
        headers.put("Content-Type", "application/json");
        return SendRespondUtil.sendRespond("注册账号", "put", "http://mall.lemonban.com:8107/user/registerOrBindUser", headers, body);
    }

    /**
     * 调用登录接口
     *
     * @param ptpara
     * @return Response
     */
    public static Response login(String ptpara) {
        String url = "http://mall.lemonban.com:8107/login ";
        Map<String, String> headers = new HashMap();
        headers.put("Content-Type", "application/json");
        System.out.println(headers.get("Content-Type"));
        Response res = SendRespondUtil.sendRespond("登录", "post", url, headers, ptpara);
        return res;

    }

    /**
     * 调用搜索商品接口
     *
     * @param
     * @return Response
     */
    public static Response search() {
        String url = "http://mall.lemonban.com:8107/search/searchProdPage";
        Map headers = new HashMap();
        String inputpara = "0";
        return SendRespondUtil.sendRespond("搜索商品", "get", url, headers, inputpara);
    }

    /**
     * 调用选择商品接口
     *
     * @param id
     * @return Response
     */
    public static Response choice(Integer id) {
        String url = "http://mall.lemonban.com:8107/prod/prodInfo";
        String inputpara = "prodId=" + id;
        Map hesders = new HashMap();
        return SendRespondUtil.sendRespond("选择商品", "get", url, hesders, inputpara);
    }

    /**
     * 调用添加购物车接口
     *
     * @param
     * @param
     * @return Response res
     */
    public static Response add_Shoping_Car(String str) {

        String url = "http://mall.lemonban.com:8107/p/shopCart/changeItem";
        Map headers = new HashMap();
        headers.put("Authorization", "bearer" + Envment.getEnv("token"));
        headers.put("Content-Type", "application/json");
        return SendRespondUtil.sendRespond("添加购物车", "post", url, headers, str);
    }

    /**
     * 确认商品
     *
     * @param input
     * @return
     */
    public static Response confirmProduct(String input) {
        String newinput = ReplaceparamUtil.paramReplace(input);
        String url = "http://mall.lemonban.com:8107/p/order/confirm";
        Map headers = new HashMap();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "bearer" + Envment.getEnv("token"));
        /*String input="{\"addrId\":0,\"orderItem\":{\"prodId\":83,\"skuId\":415,\"prodCount\":1," +
                "\"shopId\":1},\"couponIds\":[],\"isScorePay\":0,\"userChangeCoupon\":0," +
                "\"userUseScore\":0,\"uuid\":\"06490a36-edd8-4b76-8dcf-6094c7cbf17e\"}"*/
        return SendRespondUtil.sendRespond("确认商品", "post", url, headers, newinput);
    }

    public static Response submitProduct(String input) {
        String newinput = ReplaceparamUtil.paramReplace(input);
        String url = "http://mall.lemonban.com:8107/p/order/submit";
        Map headers = new HashMap();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "bearer" + Envment.getEnv("token"));
        /*String input={"orderShopParam":[{"remarks":"","shopId":1}],"uuid":"06490a36-edd8-4b76-8dcf-6094c7cbf17e"}"*/
        return SendRespondUtil.sendRespond("提交订单", "post", url, headers, newinput);
    }

    public static Response payProduct(String input) {
        String newinput = ReplaceparamUtil.paramReplace(input);
        String url = "http://mall.lemonban.com:8107/p/order/pay";
        Map headers = new HashMap();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "bearer" + Envment.getEnv("token"));
        /*String input={"payType":3,"orderNumbers":"1481439611900399616"}"*/
        return SendRespondUtil.sendRespond("付款", "post", url, headers, newinput);
    }

    public static Response mockPay(String inputParams) {
        Map<String, Object> headMap = new HashMap<>();
        headMap.put("Content-Type", "application/json");
        headMap.put("Authorization", "bearer" + Envment.getEnv("token"));
        return SendRespondUtil.sendRespond("模拟回调接口", "POST", "http://mall.lemonban.com:8107/notice/pay/3", headMap, inputParams);
    }

    /**
     * 删除购物车
     */
    public static Response deletecart(String inputpara) {
        String url = "http://mall.lemonban.com:8107/p/shopCart/info";
        Map header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Authorization", "bearer" + Envment.getEnv("token"));
        Response re = SendRespondUtil.sendRespond("查询购物车ID", "post", url, header, inputpara);
        Integer baskid=re.jsonPath().get("[0].shopCartItemDiscounts[0].shopCartItems[0].basketId");
        System.out.println(baskid);
        String url1="http://mall.lemonban.com:8107/p/shopCart/deleteItem";
        Map header1 = new HashMap<>();
        header1.put("Content-Type", "application/json");
        header1.put("Authorization", "bearer" + Envment.getEnv("token"));
         return SendRespondUtil.sendRespond("删除购物车","post",url1,header1,"["+baskid.toString()+"]");

    }
}
