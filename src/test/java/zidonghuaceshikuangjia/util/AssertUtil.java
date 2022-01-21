package zidonghuaceshikuangjia.util;

import com.alibaba.fastjson.JSON;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Map;

public class AssertUtil {
    public static void assertMthod(String expResults, Response re) {
        Map<String, Object> map = (Map) JSON.parse(expResults);
        for (String key : map.keySet()) {
            if (key.equals("statuscode")) {
                System.out.println("实际结果" + re.getStatusCode() + "," + "预期结果" + map.get(key));
                Assert.assertEquals(re.getStatusCode(), map.get(key));
            } else {
                System.out.println("实际结果" + re.jsonPath().get(key) + "," + "预期结果" + map.get(key));
                Assert.assertEquals(re.jsonPath().get(key), map.get(key));
            }
        }
    }
    public static void assertMthodDB(String expResults) {
        Map<String, Object> map = (Map) JSON.parse(expResults);
        for (String key : map.keySet()) {
                Assert.assertEquals((long)JDBCUtils.getsingresule(key), (int)map.get(key));
            }

    }
}
