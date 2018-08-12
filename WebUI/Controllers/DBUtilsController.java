package Controllers;

import BLL.StudentBLL;
import btpEntity.ResultDTO;
import btpEntity.Student;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class DBUtilsController {

    @Test
    public void Add() {
        ResultDTO result = null;
        Student model = new Student();
        model.setId(4);
        model.setAge(23);
        model.setName("徐军");
        result = StudentBLL.Add(model);

    }

    @Test
    public void Update() {
        ResultDTO result = null;
        Student model = new Student();
        model.setId(3);
        model.setAge(15);
        result = StudentBLL.Update(model);

    }

    @Test
    public void Del() {
        ResultDTO result = null;
        Student model = new Student();
        model.setId(4);
        result = StudentBLL.Del(model);

    }

    @Test
    public void Search() {
        Student model = null;
        model = StudentBLL.Search();
    }

    @Test
    public void SearchBeanListHandler() {
        List<Student> studentlist = null;
        studentlist = StudentBLL.BeanListHandler();
    }


    @Test
    public void SearchArrayListHandler() {
        List<Object[]> studentlist = null;
        studentlist = StudentBLL.ArrayListHandler();
    }

    @Test
    public void SearchMapListHandler() {
        List<Map<String, Object>> studentlist = null;
        studentlist = StudentBLL.MapListHandler();
    }
}
