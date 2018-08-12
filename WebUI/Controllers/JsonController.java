package Controllers;

import BLL.StudentBLL;
import Common.FastJsonUtil;
import Common.HttpHelper;
import Common.JsonHelper;
import btpEntity.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.junit.Test;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonController {


    @Test
    public void getString() throws Exception {
        List<ExpressTrace> objlist = new ArrayList<ExpressTrace>();
        String url = "http://api.jisuapi.com/express/query?appkey=88aebbe67b75c0a8&type=auto&number=VA42963637150";
        String jsonString = HttpHelper.SendGetRequest(url);
        System.out.printf("物流信息:%s", jsonString);
        JSONObject obj = JSONObject.fromObject(jsonString);
        if (obj.getString("status").equals("0")) {
            String jsonresult = obj.getString("result");
            System.out.printf("\n" + "物流信息:%s", jsonresult);
            JSONObject objresult = JSONObject.fromObject(jsonresult);
            JSONArray objArray = JSONArray.fromObject(objresult.getString("list"));

            /*for (int i = 0; i < objArray.size(); i++) {
                ExpressTrace model = new ExpressTrace();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                model.setTime(sdf.parse(objArray.getJSONObject(i).getString("time")));
                model.setAddress(objArray.getJSONObject(i).getString("status"));
                objlist.add(model);
            }*/
            objlist = (List<ExpressTrace>) objArray.toCollection(objArray, ExpressTrace.class);

        }

        System.out.print("\n");
        JSONArray jsonArray = JSONArray.fromObject(objlist);
        System.out.printf("物流信息:%s", jsonArray.toString());


    }


    @Test
    public void postString() throws Exception {
        String token = "xNAOsrJrWMHno1kJ5R5t4op6m";
        String url = "https://bizapi.jd.com/api/message/get";
        String data = "token=" + token + "&type=1";
        String jsonString = HttpHelper.SendPostRequest(url, data);
        JSONObject json = JSONObject.fromObject(jsonString);
    }

    @Test
    public void objSerizable() throws Exception {
        List<ExpressTrace> objlist = new ArrayList<ExpressTrace>();
        String url = "http://api.jisuapi.com/express/query?appkey=88aebbe67b75c0a8&type=auto&number=VA42963637150";
        String jsonString = HttpHelper.SendGetRequest(url);
        JSONObject obj = JSONObject.fromObject(jsonString);
        if (obj.getString("status").equals("0")) {
            String jsonresult = obj.getString("result");
            JSONObject objresult = JSONObject.fromObject(jsonresult);
            objlist = FastJsonUtil.toList(objresult.getString("list"), ExpressTrace.class);

        }
        System.out.print("\n");
        JSONArray jsonArray = JSONArray.fromObject(objlist);
        System.out.printf("物流信息:%s", jsonArray.toString());



    }


}
