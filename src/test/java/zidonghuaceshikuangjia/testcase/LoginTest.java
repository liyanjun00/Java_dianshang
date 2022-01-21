package zidonghuaceshikuangjia.testcase;

import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import zidonghuaceshikuangjia.apicall.ApiCall;
import zidonghuaceshikuangjia.daomain.TestCaseq;
import zidonghuaceshikuangjia.util.AssertUtil;
import zidonghuaceshikuangjia.util.ExcelUtil;

public class LoginTest extends AssertUtil {

    @Test(dataProvider = "dataImport")
    public void testLogin(TestCaseq testCase) {
        //String para = "{\"principal\":\"waiwai\",\"credentials\":\"lemon123456\",\"appType\":3,\"loginType\":0}";
        Response re = ApiCall.login(testCase.getInputParams());
        assertMthod(testCase.getExpected(), re);
    }

    @DataProvider
    public Object[] dataImport() {
        return ExcelUtil.importExcel(1);
    }
}
