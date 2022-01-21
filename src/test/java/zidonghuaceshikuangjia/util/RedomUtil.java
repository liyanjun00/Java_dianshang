package zidonghuaceshikuangjia.util;

import com.github.javafaker.Faker;

import java.util.Locale;

public class RedomUtil {
    public static String redom(){
        Faker faker = new Faker(Locale.CHINA);
        String s = faker.phoneNumber().cellPhone();
        String sql="select count(*)  from tz_user where user_mobile="+s;
        while (true){
            if(JDBCUtils.getsingresule(sql).equals(1)){
                s = faker.phoneNumber().cellPhone();
            }else {
                break;
            }
        }

        return s;

    }
    public static String redomname(){
        Faker faker = new Faker();
        String s = faker.name().lastName();
        System.out.println(s);
        String sql="select count(*)  from tz_user where user_name="+"'"+s+"'";
        while (true){
            if(JDBCUtils.getsingresule(sql).equals(1)){
                 s = faker.name().lastName();
            }else {
                break;
            }
        }

        return s;

    }
}
