package DBUtility;

import org.apache.commons.dbutils.AsyncQueryRunner;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


public abstract class DBUtilsHelper {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://211.159.175.227/btp";
    private static final String USER = "root";
    private static final String PASS = "123456";

    /**
     * 连接数据库
     *
     * @return
     */
    private static Connection getConnect() {
        try {

            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * @param sql(增删改)
     * @param params
     * @return
     * @throws Exception
     */
    public static int ExecuteNonQuery(String sql, Object[] params) throws Exception {
        int count = 0;
        QueryRunner queryRunner = new QueryRunner();
        DbUtils.loadDriver(JDBC_DRIVER);
        Connection conn = getConnect();
        try {
            if (params == null) {
                count = queryRunner.update(conn, sql);
            } else {
                count = queryRunner.update(conn, sql, params);
            }

        } finally {
            DbUtils.close(conn);
        }
        return count;
    }


    /**
     * @param sql(线程安全增删改)
     * @param params
     * @return
     * @throws Exception
     */
    public static int AsyncQueryRunner(String sql, Object[] params) throws Exception {
        int count = 0;
        AsyncQueryRunner asyncQueryRunner = new AsyncQueryRunner(Executors.newCachedThreadPool());
        DbUtils.loadDriver(JDBC_DRIVER);
        Connection conn = getConnect();
        Future<Integer> future = null;
        try {
            if (params == null) {
                future = asyncQueryRunner.update(conn, sql);
            } else {
                future = asyncQueryRunner.update(conn, sql, params);
            }
            count = future.get(10, TimeUnit.SECONDS);
        } finally {
            DbUtils.close(conn);
        }
        return count;
    }


    /**
     * @param sql(查询)
     * @param params
     * @param entityClass
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T QueryRunner(String sql, Object[] params, Class<T> entityClass) throws Exception {
        QueryRunner queryRunner = new QueryRunner();
        DbUtils.loadDriver(JDBC_DRIVER);
        Connection conn = getConnect();
        T obj = null;
        try {
            if (params == null) {
                obj = (T) queryRunner.query(conn, sql, new BeanHandler(entityClass));
            } else {
                obj = (T) queryRunner.query(conn, sql, new BeanHandler(entityClass), params);
            }

        } finally {
            DbUtils.close(conn);
        }
        return obj;
    }


    /**
     * @param sql
     * @param params
     * @param entityClass
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> BeanListHandler(String sql, Object[] params, Class<T> entityClass) throws Exception {
        QueryRunner queryRunner = new QueryRunner();
        DbUtils.loadDriver(JDBC_DRIVER);
        Connection conn = getConnect();
        List<T> objlist = null;
        try {
            if (params == null) {
                objlist = (List<T>) queryRunner.query(conn, sql, new BeanListHandler(entityClass));
            } else {
                objlist = (List<T>) queryRunner.query(conn, sql, new BeanListHandler(entityClass), params);
            }

        } finally {
            DbUtils.close(conn);
        }
        return objlist;
    }


    /**
     * @param sql
     * @param params
     * @return
     * @throws Exception
     */
    public static List<Object[]> ArrayListHandler(String sql, Object[] params) throws Exception {
        QueryRunner queryRunner = new QueryRunner();
        DbUtils.loadDriver(JDBC_DRIVER);
        Connection conn = getConnect();
        List<Object[]> objlist = null;
        try {
            if (params == null) {
                objlist = (List<Object[]>) queryRunner.query(conn, sql, new ArrayListHandler());
            } else {
                objlist = (List<Object[]>) queryRunner.query(conn, sql, new ArrayListHandler(), params);
            }

        } finally {
            DbUtils.close(conn);
        }
        return objlist;
    }


    /**
     * @param sql
     * @param params
     * @return
     * @throws Exception
     */
    public static List<Map<String, Object>> MapListHandler(String sql, Object[] params) throws Exception {
        QueryRunner queryRunner = new QueryRunner();
        DbUtils.loadDriver(JDBC_DRIVER);
        Connection conn = getConnect();
        List<Map<String, Object>> objlist = null;
        try {
            if (params == null) {
                objlist = (List<Map<String, Object>>) queryRunner.query(conn, sql, new MapListHandler());
            } else {
                objlist = (List<Map<String, Object>>) queryRunner.query(conn, sql, new MapListHandler(), params);
            }

        } finally {
            DbUtils.close(conn);
        }
        return objlist;
    }


}
