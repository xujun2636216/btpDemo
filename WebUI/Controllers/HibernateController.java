package Controllers;

import BLL.StudentsBLL;
import btpEntity.Student;
import org.junit.Test;
import java.util.List;


public class HibernateController {


    /**
     * 查询数据 helloword
     */
    @Test
    public void Search() {
        List<Student> studentList = StudentsBLL.getList();

    }

}
