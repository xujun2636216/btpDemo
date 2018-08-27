package BLL;


import btpEntity.ResultDTO;
import btpEntity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentsBLL {

    public static List<Student> getList(String Name) {
        List<Student> objlist = new ArrayList<Student>();
        return objlist;
    }


    /**
     * 添加数据
     */
    public static ResultDTO AddStudent() {

        ResultDTO result = new ResultDTO();
        int count = 0;
        try {

        } catch (Exception e) {
            result.setResultCode(1);
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }

        return result;
    }

    /**
     * 更新数据
     */
    public static ResultDTO UpdateStudent() {
        ResultDTO result = new ResultDTO();
        int count = 0;
        try {

        } catch (Exception e) {
            result.setResultCode(1);
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }


    /**
     * @删除学生
     */
    public static ResultDTO DelStudent() {

        ResultDTO result = new ResultDTO();
        int count = 0;
        try {

        } catch (Exception e) {
            result.setResultCode(1);
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }
}
