package Controllers;

import BLL.StudentsBLL;
import btpEntity.ListResultDTO;
import btpEntity.Student;
import org.junit.Test;


public class HibernateController {


    /**
     * 查询数据
     */
    @Test
    public void Search() {
        Student model=new Student();
        model.setAge(0);
        model.setName("小明");
        ListResultDTO<Student> studentList = StudentsBLL.getList(model,2,2);

    }

}
