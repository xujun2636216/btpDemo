package Controllers;

import BLL.StudentsBLL;
import btpEntity.ListResultDTO;
import btpEntity.ResultDTO;
import btpEntity.Student;
import org.junit.Test;

import java.util.List;

public class HibernateController {


    /**
     * 查询数据
     */
    @Test
    public void SearchgetStudentList() {
        Student model = new Student();
        List<Object[]> objlist=StudentsBLL.getStudentList(model);
        objlist.forEach(p->{
            Object[] obj=p;
        });
    }

    /**
     * 查询数据
     */
    @Test
    public void Search() {
        Student model = new Student();
        ListResultDTO<Student> studentList = StudentsBLL.getList(model, 1, 10);
        if(studentList!=null&&!studentList.getDatalist().isEmpty()){

        }
    }

    /**
     * 查询数据
     */
    @Test
    public void Searchbyid() {

        Student exEntity = StudentsBLL.getListbyId(1);

    }

    /**
     * 添加数据
     */
    @Test
    public void AddStudent() {
        Student model = new Student();
        model.setAge(33);
        model.setName("xujun");
        ResultDTO result = StudentsBLL.AddStudent(model);

    }

    /**
     * 更新数据
     */
    @Test
    public void UpdateStudent() {
        Student model = new Student();
        model.setId(7);
        model.setAge(99);
        model.setName("小刚");
        ResultDTO result = StudentsBLL.UpdateStudent(model);

    }

    /**
     * 更新数据
     */
    @Test
    public void _UpdateStudent() {
        Student model = new Student();
        model.setId(6);
        model.setAge(99);
        model.setName("小刚");
        ResultDTO result = StudentsBLL.ModifyStudent(model);

    }

    /**
     * 删除数据
     */
    @Test
    public void DelStudent() {
        Student model = new Student();
        model.setId(7);
        model.setAge(99);
        model.setName("小刚");
        ResultDTO result = StudentsBLL.DelStudent(model);

    }

    /**
     * 删除数据
     */
    @Test
    public void _DelStudent() {

        ResultDTO result = StudentsBLL._DelStudent(8);

    }

}
