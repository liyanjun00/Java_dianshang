package zidonghuaceshikuangjia.testcase;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import zidonghuaceshikuangjia.apicall.ApiCall;
import zidonghuaceshikuangjia.common.Envment;
import zidonghuaceshikuangjia.service.Login_Search_Choice;
import zidonghuaceshikuangjia.util.JDBCUtils;

public class OrderPayment {
    @Test
    public void orderpay(){
        String input1="{\"principal\":\"liyanyan\",\"credentials\":\"123456\",\"appType\":3,\"loginType\":0}";
        Login_Search_Choice.login_search_addcart(input1);
        String input2="{\"addrId\":0,\"orderItem\":{\"prodId\":#prodId#,\"skuId\":466,\"prodCount\":1," +
                "\"shopId\":1},\"couponIds\":[],\"isScorePay\":0,\"userChangeCoupon\":0," +
                "\"userUseScore\":0,\"uuid\":\"06490a36-edd8-4b76-8dcf-6094c7cbf17e\"}";
        ApiCall.confirmProduct(input2);
        String input3="{\"orderShopParam\":[{\"remarks\":\"\",\"shopId\":1}],\"uuid\":\"06490a36-edd8-4b76-8dcf-6094c7cbf17e\"}";
        Response res1=ApiCall.submitProduct(input3);
        Envment.addEnv("orderNumbers",res1.jsonPath().get("orderNumbers"));
        String input4="{\"payType\":3,\"orderNumbers\":\"#orderNumbers#\"}";
        Response res2=ApiCall.payProduct(input4);
        String mockPayData="{\n" +
                "    \"payNo\":#orderNumbers#, \n" +
                "    \"bizPayNo\":\"XXXX\",\n" +
                "    \"isPaySuccess\":true\n" +
                "}";
       Response res3=ApiCall.mockPay(mockPayData);

        Assert.assertEquals(res2.getStatusCode(),200);
        Assert.assertEquals(res3.getBody().asString(),"success");
        String sql="select status from tz_order where order_number='#orderNumbers#'";
        Assert.assertEquals(JDBCUtils.getsingresule(sql),2);

    }
}
