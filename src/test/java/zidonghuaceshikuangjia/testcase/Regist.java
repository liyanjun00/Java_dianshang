package zidonghuaceshikuangjia.testcase;

import io.restassured.response.Response;
import org.apache.commons.dbutils.QueryRunner;
import org.testng.Assert;
import org.testng.annotations.Test;
import zidonghuaceshikuangjia.apicall.ApiCall;
import zidonghuaceshikuangjia.common.Envment;
import zidonghuaceshikuangjia.util.JDBCUtils;
import zidonghuaceshikuangjia.util.RedomUtil;

import java.util.Optional;

public class Regist {
    @Test
    public void regist() {
        String rdphone=RedomUtil.redom();
        Envment.addEnv("phone",rdphone);
        String phone = "{\"mobile\":\"#phone#\"}";
        ApiCall.sendVC(phone);
        QueryRunner qr = new QueryRunner();
        String sql = "select mobile_code from tz_sms_log where id=(select  max(id)from tz_sms_log)";
        String vc = (String) JDBCUtils.getsingresule(sql);
        System.out.println(vc);
        Envment.addEnv("vc", vc);
        String para = "{\"mobile\":\"#phone#\",\"validCode\":\"#vc#\"}";
        String ass = ApiCall.checkVC(para).getBody().asString();
        Envment.addEnv("ass", ass);
        String name=RedomUtil.redomname();
        Envment.addEnv("name",name);
        String body = "{\"appType\":3,\"checkRegisterSmsFlag\":\"#ass#\",\"mobile\":\"#phone#\",\n" +
                "  \"userName\":\"#name#\",\"password\":\"123456\",\"registerOrBind\":1,\"validateType\":1}";
        Response re=ApiCall.register(body);
        Assert.assertEquals(re.getStatusCode(),200);
        String nickName=re.jsonPath().get("nickName");
        String sql1="select count(*) from tz_user where nick_name="+"'"+nickName+"'";
        String sql2="select count(*) from tz_user where user_mobile=#phone#";
        Assert.assertEquals((long)JDBCUtils.getsingresule(sql1),1);
        Assert.assertEquals(JDBCUtils.getsingresule(sql2).toString(),"1");

    }
}
