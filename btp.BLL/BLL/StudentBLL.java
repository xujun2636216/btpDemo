package BLL;

import DBUtility.DBUtilsHelper;
import DBUtility.SQLHelper;
import btpEntity.ListResultDTO;
import btpEntity.ResultDTO;
import btpEntity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class StudentBLL {

    public static List<Student> getList(String Name) throws Exception {
        List<Student> objlist = new ArrayList<Student>();
        String[] params = new String[1];
        StringBuilder sb = new StringBuilder();
        sb.append(" select*from Student where 1=1 ");
        if (Name != null && Name.length() != 0) {
            sb.append(" and Name=? ");
            params[0] = Name;
        }
        int count = (int) SQLHelper.ExecuteScalar(sb.toString(), 1, params);
        List<Object[]> arrayList = SQLHelper.ExecuteReader(sb.toString(), params);
        for (int i = 0; i < arrayList.size(); i++) {
            Student model = new Student();
            model.setId((Integer) arrayList.get(i)[0]);
            model.setName((String) arrayList.get(i)[1]);
            model.setAge((Integer) arrayList.get(i)[2]);
            objlist.add(model);
        }
        return objlist;
    }


    /**
     * 添加数据
     */
    public static ResultDTO AddStudent() {

        ResultDTO result = null;
        int count = 0;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO Student (Id, NAME, Age) VALUES(5, 'xiaogang', 24)");
            count = SQLHelper.ExecuteNonQuery(sb.toString(), null);
            if (count > 0) {
                result = new ResultDTO(true, 0, "添加成功");
            }
        } catch (Exception ex) {
            result = new ResultDTO(false, 1, ex.getMessage());
        }

        return result;
    }

    /**
     * 更新数据
     */
    public static ResultDTO UpdateStudent() {
        ResultDTO result = null;
        int count = 0;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("update Student set Age=27 where Id=3");
            count = SQLHelper.ExecuteNonQuery(sb.toString(), null);
            if (count > 0) {
                result = new ResultDTO(true, 0, "更新成功");
            }
        } catch (Exception ex) {
            result = new ResultDTO(false, 1, ex.getMessage());
        }
        return result;
    }


    /**
     * @删除学生
     * @param model
     */
    public static ResultDTO DelStudent(Student model) {

        ResultDTO result = null;
        int count = 0;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(" delete from  Student where Id=4 ");
            count = SQLHelper.ExecuteNonQuery(sb.toString(), null);
            if (count > 0) {
                result = new ResultDTO(true, 0, "删除成功");
            }
        } catch (Exception ex) {
            result = new ResultDTO(false, 1, ex.getMessage());
        }
        return result;
    }


    /**
     * @return
     * @添加学生信息
     */
    public static ResultDTO Add(Student model) {
        ResultDTO result = null;
        int count = 0;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO Student (id,age,name) VALUES(?,?,?)");
            //Object[] params={model.getId(),model.getAge(),""+model.getName()+""};
            Object[] params = new Object[3];
            params[0] = model.getId();
            params[1] = model.getAge();
            params[2] = model.getName();
            count = DBUtilsHelper.ExecuteNonQuery(sb.toString(), params);
            if (count > 0) {
                result = new ResultDTO(true, 0, "添加成功");
            }
        } catch (Exception ex) {
            result = new ResultDTO(false, 1, ex.getMessage());
        }
        return result;
    }


    /**
     * @return
     * @修改学生信息
     */
    public static ResultDTO Update(Student model) {
        ResultDTO result = null;
        int count = 0;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("update Student set age=? where id=? ");
            Object[] params = {model.getAge(), model.getId()};
            count = DBUtilsHelper.ExecuteNonQuery(sb.toString(), params);
            if (count > 0) {
                result = new ResultDTO(true, 0, "修改成功");
            }
        } catch (Exception ex) {
            result = new ResultDTO(false, 1, ex.getMessage());
        }
        return result;
    }


    /**
     * @return
     * @删除学生信息
     */
    public ResultDTO Del(Student model) {
        ResultDTO result = null;
        int count = 0;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("delete from Student where id=?");
            Object[] params = {model.getId()};
            count = DBUtilsHelper.ExecuteNonQuery(sb.toString(), params);
            if (count > 0) {
                result = new ResultDTO(true, 0, "删除成功");
            }
        } catch (Exception ex) {
            result = new ResultDTO(false, 1, ex.getMessage());
        }
        return result;
    }

    /**
     * @return
     * @查询学生实体
     */
    public static Student Search() {
        Student exEntity = null;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("select*from Student where id=1");
            exEntity = DBUtilsHelper.QueryRunner(sb.toString(), null, Student.class);
        } catch (Exception ex) {
            exEntity = null;
        }
        return exEntity;
    }


    /**
     * @return
     * @查询学生实体
     */
    public static ListResultDTO BeanListHandler() {
        ListResultDTO result = null;
        try {
            List<Student> studentList=new ArrayList<Student>();
            StringBuilder sb = new StringBuilder();
            sb.append("select*from Student");
            studentList = DBUtilsHelper.BeanListHandler(sb.toString(), null, Student.class);
            result=new ListResultDTO(true,0,"",studentList.size(),studentList);
        } catch (Exception e) {
            result=new ListResultDTO(false,0,"",0,null);;
        }
        return result;
    }


    /**
     * @return
     * @查询学生实体
     */
    public static List<Object[]> ArrayListHandler() {
        List<Object[]> result = null;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("select*from Student");
            result = DBUtilsHelper.ArrayListHandler(sb.toString(), null);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * @return
     * @查询学生实体
     */
    public static List<Map<String, Object>> MapListHandler() {
        List<Map<String, Object>> result = null;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("select*from Student");
            result = DBUtilsHelper.MapListHandler(sb.toString(), null);
        } catch (Exception e) {
        }
        return result;
    }


}
