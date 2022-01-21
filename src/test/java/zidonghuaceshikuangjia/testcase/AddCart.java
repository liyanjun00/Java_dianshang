package zidonghuaceshikuangjia.testcase;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import zidonghuaceshikuangjia.apicall.ApiCall;
import zidonghuaceshikuangjia.common.Envment;
import zidonghuaceshikuangjia.daomain.TestCaseq;
import zidonghuaceshikuangjia.service.Login_Search_Choice;
import zidonghuaceshikuangjia.util.AssertUtil;
import zidonghuaceshikuangjia.util.ExcelUtil;
import zidonghuaceshikuangjia.util.JDBCUtils;

import java.util.Map;

public class AddCart extends AssertUtil {
    @Test
    public void testYwu() {
        //准备参数
        String para = "{\"principal\":\"liyanyan\",\"credentials\":\"123456\",\"appType\":3,\"loginType\":0}";
        //登录搜选择商品
        Response choiceresult = Login_Search_Choice.login_search_addcart(para);
        //添加购物车
        String str = "{\"basketId\":0,\"count\":1,\"prodId\":\"#prodId#\",\"shopId\":1,\"skuId\":415}";
        Response resule = ApiCall.add_Shoping_Car(str);
        //断言状态码
        Assert.assertEquals(resule.getStatusCode(), 200);
        String sql="select * from tz_basket where user_id=(select user_id  from tz_user where user_name='liyanyan')";
        JDBCUtils.getMapResult(sql);
        Assert.assertEquals( JDBCUtils.getMapResult(sql).get("basket_count"),1);
    }
    @Test(dataProvider ="addcart")
    public void addCart(TestCaseq testCaseq) {
        //准备参数
        String para = "{\"principal\":\"liyanyan\",\"credentials\":\"123456\",\"appType\":3,\"loginType\":0}";
        //登录
        String token=ApiCall.login(para).jsonPath().get("\"access_token\"");
        Envment.addEnv("token",token);
        Response resule = ApiCall.add_Shoping_Car(testCaseq.getInputParams());
        //断言状态码
        Assert.assertEquals(resule.getStatusCode(), 200);
        AssertUtil.assertMthod(testCaseq.getExpected(),resule);
        //数据库断言
        AssertUtil.assertMthodDB(testCaseq.getAssertdb());
    }
    @DataProvider
    public Object[] addcart(){
      return   ExcelUtil.importExcel(2);
    }
}
