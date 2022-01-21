package zidonghuaceshikuangjia.testcase;

import org.testng.Assert;
import org.testng.annotations.Test;
import zidonghuaceshikuangjia.apicall.ApiCall;

public class SouSuo {
    @Test
    public void souSuo() {
        Assert.assertEquals(ApiCall.search().getStatusCode(),200);
    }

}
