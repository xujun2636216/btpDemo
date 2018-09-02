package Controllers;

import BLL.StudentsBLL;
import btpEntity.Student;
import org.junit.Test;
import java.util.List;


public class HibernateController {

    private static StudentsBLL objbll=StudentsBLL.getInstance();
    /**
     * 查询数据 helloword
     */
    @Test
    public void Search() {
        List<Student> studentList = objbll.getList();
        String  Jsonstr="{\"name\":\"xujun\",\"age\":10}";

    }

}
