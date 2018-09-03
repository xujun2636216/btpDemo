package BLL;

import Common.LogHelper;
import DBUtility.HibernateUtils;
import btpEntity.ResultDTO;
import btpEntity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class StudentsBLL {

    // 获取Session连接
    private static final Session session = HibernateUtils.getSession();

    public static List<Student> getList() {

        Transaction tx=session.beginTransaction();
        List<Student> objlist = new ArrayList<Student>();
        try {
            String sql=" select * from Student";
            objlist = session.createSQLQuery(sql).list();
        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            session.close();
        }
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
