package zidonghuaceshikuangjia.util;

import com.alibaba.fastjson.JSONObject;
import zidonghuaceshikuangjia.common.Envment;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceparamUtil {
    public static String paramReplace(String sts) {
        String strdemo = "#(.+?)#";
        Pattern pattern = Pattern.compile(strdemo);
        Matcher matcher = pattern.matcher(sts);
        while (matcher.find()) {
            String str1 = matcher.group(0);
            String str2 = matcher.group(1);
            sts = sts.replace(str1, Envment.getEnv().get(str2) + "");
        }
        return sts;
    }

    public static Map paramReplace(Map map) {
        String str = JSONObject.toJSONString(map);
        Map map1 = JSONObject.parseObject(paramReplace(str));
        return map1;
    }
}
