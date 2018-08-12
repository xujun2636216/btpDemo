package Controllers;

import BLL.StudentBLL;
import btpEntity.ResultDTO;
import btpEntity.Student;
import org.junit.Test;

import java.util.List;

public class StudentController {

    /**
     * @获取学生信息列表
     */
    @Test
    public void getList() throws Exception {
        List<Student> objlist = StudentBLL.getList("xujun");
        if (!objlist.isEmpty()) {
            for (Student item : objlist) {
                System.out.println("\n");
                System.out.printf("姓名为:%s ,年龄为:%d", item.getName(), item.getAge());
            }
        }

    }

    @Test
    public  void Add()
    {
        ResultDTO result=StudentBLL.AddStudent();
    }

    @Test
    public  void Update() {
        ResultDTO result=StudentBLL.UpdateStudent();
    }

    @Test
    public  void  Del() {
        ResultDTO result=StudentBLL.DelStudent();
    }
}
