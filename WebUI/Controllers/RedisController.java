package Controllers;


import Common.FastJsonUtil;
import Common.StringHelper;
import Redis.RedisHelper;
import btpEntity.Employees;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class RedisController {

    @Test
    public void setHash1() {
        Date dt = new Date();
        Employees model = new Employees(100, "小红", 59, 6666.66);
        String key = StringHelper.getUUID();
        String value = FastJsonUtil.toJSONString(model);
        RedisHelper.setHash(1, "Employees", key, value);
    }


    @Test
    public void setHash2() {

        Employees model = RedisHelper.getHash(1, "Employees", "61274ca81c954ee0a06ac71ef2f0ee68", Employees.class);
    }


    @Test
    public void setHash3() {

        List<Employees> model1 = RedisHelper.getHash(1, "Employees", Employees.class);

        List<Employees> model2 = RedisHelper.gethvals(1, "Employees", Employees.class);

        Map<String, String> map = RedisHelper.getHash(1, "Employees");
    }


    @Test
    public void setHash4() {
        String[] arr = new String[2];
        arr[0]="61274ca81c954ee0a06ac71ef2f0ee68";
        arr[1]="7ed47c5dc2c7455c85224813bb555556";
        Boolean flag = RedisHelper.delHash(1, "Employees", arr);
    }

}
