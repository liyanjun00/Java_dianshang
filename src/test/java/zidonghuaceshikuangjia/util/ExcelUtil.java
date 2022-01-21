package zidonghuaceshikuangjia.util;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import zidonghuaceshikuangjia.daomain.TestCaseq;
import zidonghuaceshikuangjia.testcase.LoginTest;

import java.io.File;
import java.util.List;

public class ExcelUtil {
    public static Object[] importExcel(int sheetindex) {
        ImportParams params = new ImportParams();
        params.setStartSheetIndex(sheetindex);
        List<LoginTest> list = ExcelImportUtil.importExcel(new File("src/test/resources/shujvqudong.xls"), TestCaseq.class, params);
        Object[] obbj = list.toArray();
        return obbj;
    }
}
