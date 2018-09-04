package BLL;

import Common.LogHelper;
import DBUtility.HibernateUtils;
import btpEntity.ListResultDTO;
import btpEntity.ResultDTO;
import btpEntity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class StudentsBLL {

    // 获取Session连接
    private static final Session session = HibernateUtils.getSession();

    public static ListResultDTO<Student> getList(Student model, int page, int pageSize) {
       int  Count=0;
        ListResultDTO<Student> resultDTO=null;
        Transaction tx=session.beginTransaction();
        List<Student> objlist = new ArrayList<Student>();
        try {
            String sql=" select * from Student where 1=1 ";

            if (model.getAge()>0) {
                sql+=" and age=:age";
            }
            if (!model.getName().isEmpty()) {
                sql+=" and name=:name";
            }
            Query  query = session.createSQLQuery(sql).setParameter(0,model.getAge()).setParameter(1,model.getName());
            Count=query.list().size();
            query.setFirstResult((page-1)*pageSize);
            query.setMaxResults(pageSize);
            objlist=query.list();
            resultDTO=new ListResultDTO<Student>(true,0,"",Count,objlist);
        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            session.close();
        }
        return resultDTO;
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
