package Controllers;

import BLL.PeopleBLL;
import btpEntity.People;
import net.sf.json.JSONArray;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HomeController {


    /**
     * 主函数
     */
    public static void main(String[] args) {

        //使用单例模式创建与BLL的连接
        PeopleBLL objbll = PeopleBLL.getInstance();
        List<People> objlist = objbll.getlist();

        //第一种循环方式
        for (People item : objlist) {
            System.out.printf("姓名为：%s 年龄为：%d", item.getName(), item.getAge());
        }

        System.out.print("\n");

        //第二种循环方式
        objlist.forEach(p->{
            System.out.printf("姓名为：%s 年龄为：%d", p.getName(), p.getAge());
        });
        objlist.forEach(p->System.out.printf("姓名为：%s 年龄为：%d", p.getName(), p.getAge()));

        System.out.print("\n");

        //第三种循环方式
        for (int i = 0; i < objlist.size(); i++) {
            System.out.printf("姓名为：%s 年龄为：%d", objlist.get(i).getName(), objlist.get(i).getAge());
        }

        System.out.print("\n");

        //第四种循环方式使用迭代器循环
        Iterator<People> ite = objlist.iterator();
        while (ite.hasNext())//判断下一个元素之后有值
        {
            System.out.printf("姓名为：%s 年龄为：%d", ite.next().getName(), ite.next().getAge());
        }


    }

    @Test
    public void getobjlist() {

        //使用单例模式创建与BLL的连接
        PeopleBLL objbll = PeopleBLL.getInstance();
        Map<String, People> objlist = objbll.getPeoplelist();


        //第一种：普遍使用，二次取值
        for (String key : objlist.keySet()) {
            System.out.printf("主键key:%s,姓名为:%s,年龄为:%d", key, objlist.get(key).getName(), objlist.get(key).getAge());
        }
        System.out.println("\n");

        //第二种使用迭代器
        Iterator<Map.Entry<String, People>> ite = objlist.entrySet().iterator();
        while (ite.hasNext()) {
            Map.Entry<String, People> entry = ite.next();
            System.out.printf("主键key:%s,姓名为:%s,年龄为:%d", entry.getKey(), entry.getValue().getName(), entry.getValue().getAge());
        }

        System.out.println("\n");

        //第三种：推荐，尤其是容量大时
        for (Map.Entry<String, People> entry : objlist.entrySet()) {
            System.out.printf("主键key:%s,姓名为:%s,年龄为:%d", entry.getKey(), entry.getValue().getName(), entry.getValue().getAge());
        }

        System.out.println("\n");

        if (!objlist.get("1").getName().isEmpty() && objlist.get("1").getName() != null) {
            System.out.println("不为空");
        }

    }


}
