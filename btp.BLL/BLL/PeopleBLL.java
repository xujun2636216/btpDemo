package BLL;
import IBLL.IPeopleBLL;
import btpEntity.People;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  class PeopleBLL implements IPeopleBLL {

    //创建 SingleObject 的一个对象
    private static PeopleBLL instance = new PeopleBLL();

    //让构造函数为 private，这样该类就不会被实例化
    private PeopleBLL(){}

    //获取唯一可用的对象
    public static PeopleBLL getInstance(){
        return instance;
    }

    @Override
    public  List<People> getlist() {
        List<People> objlist = new ArrayList<People>();
        for (int i=0;i<10;i++) {
            People model=new People();
            model.setAge(27);
            model.setName("徐军");
            objlist.add(model);
        }
        return  objlist;
    }

    @Override
    public Map<String, People> getPeoplelist() {
        Map<String,People> map=new HashMap<String,People>();
        for (int i=0;i<10;i++) {
            People model=new People();
            model.setAge(27);
            model.setName("徐军");
            map.put(""+i+"", model);
        }
        return map;
    }

}
