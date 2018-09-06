package BLL;

import Common.LogHelper;
import DBUtility.HibernateUtils;
import btpEntity.ListResultDTO;
import btpEntity.ResultDTO;
import btpEntity.Student;
import btpExEntity.StudentSearch;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class StudentsBLL {


    /**
     * 查询学生信息
     */
    public static List<StudentSearch> Search() {

        final Session session = HibernateUtils.getSession();
        Transaction tx = session.beginTransaction();
        List<StudentSearch> objlist = new ArrayList<>();
        try {
            String sql = " select t1.*,t2.phone  from Student t1 left join Students t2 on t1.id=t2.studentId ";
            Query query = session.createSQLQuery(sql).addEntity(StudentSearch.class);
            objlist = query.list();
            tx.commit();
        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            session.close();
        }
        return objlist;
    }

    /**
     * @param model
     * @查询学生信息
     */
    public static List<Object[]> getStudentList() {

        final Session session = HibernateUtils.getSession();
        Transaction tx = session.beginTransaction();
        List<Object[]> objlist = null;
        try {
            String sql = " select age,name from Student where 1=1 ";
            Query query = session.createSQLQuery(sql);
            objlist = query.list();
            tx.commit();
        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            session.close();
        }
        return objlist;
    }


    /**
     * @param model
     * @param page
     * @param pageSize 查询学生信息
     */
    public static ListResultDTO<Student> getList(Student model, int page, int pageSize) {

        final Session session = HibernateUtils.getSession();
        int Count = 0;
        ListResultDTO<Student> resultDTO = null;
        Transaction tx = session.beginTransaction();
        List<Student> objlist = null;
        try {
            String sql = " select * from Student where 1=1 ";

            if (model.getAge() > 0) {
                sql += " and age=:age";
            }
            if (model.getName() != null && !model.getName().isEmpty()) {
                sql += " and name=:name";
            }
            Query query = session.createSQLQuery(sql).addEntity(Student.class);
            //参数化编程
            query.setProperties(model);
            Count = query.list().size();
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            objlist = query.list();
            tx.commit();
            resultDTO = new ListResultDTO<Student>(true, 0, "", Count, objlist);
        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            session.close();
        }
        return resultDTO;
    }


    /**
     * @param id 查询学生信息
     */
    public static Student getListbyId(int id) {
        final Session session = HibernateUtils.getSession();
        Transaction tx = session.beginTransaction();
        Student model = null;
        try {
            String sql = " select * from Student where 1=1 and id=" + id + "";
            Query query = session.createSQLQuery(sql).addEntity(Student.class);
            model = (Student) query.list().get(0);
            tx.commit();
        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        } finally {
            session.close();
        }
        return model;
    }


    /**
     * @param model 添加学生信息
     */
    public static ResultDTO AddStudent(Student model) {
        final Session session = HibernateUtils.getSession();
        ResultDTO result = null;
        Transaction tx = session.beginTransaction();
        try {
            //适用于长会话流程
            session.persist(model);
            //session.save(model);
            tx.commit();
            result = new ResultDTO(true, 0, "添加成功");
        } catch (Exception ex) {
            //事物的回滚
            tx.rollback();
            result = new ResultDTO(false, 1, ex.getMessage());
        } finally {
            session.close();
        }
        return result;
    }


    /**
     * @param model 更新学生信息
     */
    public static ResultDTO UpdateStudent(Student model) {
        final Session session = HibernateUtils.getSession();
        ResultDTO result = null;
        Transaction tx = session.beginTransaction();
        try {
            session.update(model);
            tx.commit();
            result = new ResultDTO(true, 0, "更新成功");
        } catch (Exception ex) {
            //事物的回滚
            tx.rollback();
            result = new ResultDTO(false, 1, ex.getMessage());
        } finally {
            session.close();
        }
        return result;
    }


    /**
     * @param model 更新学生信息
     */
    public static ResultDTO ModifyStudent(Student model) {
        final Session session = HibernateUtils.getSession();
        ResultDTO result = null;
        Transaction tx = session.beginTransaction();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" update Student set ");
            if (model.getAge() > 0) {
                sql.append(" age=:age,");
            }
            if (!model.getName().isEmpty()) {
                sql.append(" name=:name");
            }
            sql.append(" where id=:id");
            Query query = session.createSQLQuery(sql.toString());
            query.setProperties(model);
            query.executeUpdate();
            tx.commit();
            result = new ResultDTO(true, 0, "更新成功");
        } catch (Exception ex) {
            //事物的回滚
            tx.rollback();
            result = new ResultDTO(false, 1, ex.getMessage());
        } finally {
            session.close();
        }
        return result;
    }


    /**
     * @param model 删除实体
     */
    public static ResultDTO DelStudent(Student model) {
        final Session session = HibernateUtils.getSession();
        ResultDTO result = null;
        Transaction tx = session.beginTransaction();
        try {
            session.delete(model);
            tx.commit();
            result = new ResultDTO(true, 0, "删除成功");
        } catch (Exception ex) {
            //事物的回滚
            tx.rollback();
            result = new ResultDTO(false, 1, ex.getMessage());
        } finally {
            session.close();
        }
        return result;
    }

    /**
     * 删除实体
     */
    public static ResultDTO _DelStudent(int id) {
        final Session session = HibernateUtils.getSession();
        ResultDTO result = null;
        Transaction tx = session.beginTransaction();
        try {
            String sql = "delete from Student where 1=1 and id=:id";
            Query query = session.createSQLQuery(sql);
            Student model = new Student();
            model.setId(id);
            query.setProperties(model);
            query.executeUpdate();
            tx.commit();
            result = new ResultDTO(true, 0, "删除成功");
        } catch (Exception ex) {
            //事物的回滚
            tx.rollback();
            result = new ResultDTO(false, 1, ex.getMessage());
        } finally {
            session.close();
        }
        return result;
    }


}
