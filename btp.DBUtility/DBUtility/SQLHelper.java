package DBUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.sql.*;

public abstract class SQLHelper {

    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://211.159.175.227/btp";
    private static final String user = "root";
    private static final String password = "123456";

    /**
     * 连接数据库
     *
     * @return
     */
    private static Connection getConnect() {
        try {

            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 用于执行语句（eg：insert语句，update语句，delete语句）
     *
     * @param cmdtext cmdtext,SQL语句
     * @param parms   parms,参数集合
     * @return int, SQL语句影响的行数
     */
    public static int ExecuteNonQuery(String cmdtext, String[] parms)
            throws Exception {
        PreparedStatement pstmt = null;
        Connection conn = null;

        try {
            conn = getConnect();
            pstmt = conn.prepareStatement(cmdtext);
            prepareCommand(pstmt, parms);

            return pstmt.executeUpdate();

        } catch (Exception e) {
            throw new Exception("executeNonQuery方法出错:" + e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                throw new Exception("执行executeNonQuery方法出错:" + e.getMessage());
            }
        }
    }

    /**
     * 用于获取结果集语句（eg：selete * from table）
     *
     * @param cmdtext
     * @param parms
     * @return ResultSet
     * @throws Exception
     */
    public static ArrayList ExecuteReader(String cmdtext, String[] parms)
            throws Exception {
        PreparedStatement pstmt = null;
        Connection conn = null;

        try {
            conn = getConnect();

            pstmt = conn.prepareStatement(cmdtext);

            prepareCommand(pstmt, parms);
            ResultSet rs = pstmt.executeQuery();

            ArrayList al = new ArrayList();
            ResultSetMetaData rsmd = rs.getMetaData();
            int column = rsmd.getColumnCount();

            while (rs.next()) {
                Object[] ob = new Object[column];
                for (int i = 1; i <= column; i++) {
                    ob[i - 1] = rs.getObject(i);
                }
                al.add(ob);
            }

            rs.close();
            return al;

        } catch (Exception e) {
            throw new Exception("executeSqlResultSet方法出错:" + e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                throw new Exception("executeSqlResultSet方法出错:" + e.getMessage());
            }
        }
    }

    /**
     * 用于获取单字段值语句（用名字指定字段）
     *
     * @param cmdtext SQL语句
     * @param name    列名
     * @param parms   OracleParameter[]
     * @return Object
     * @throws Exception
     */
    public static Object ExecuteScalar(String cmdtext, String name,
                                       String[] parms) throws Exception {
        PreparedStatement pstmt = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = getConnect();

            pstmt = conn.prepareStatement(cmdtext);
            prepareCommand(pstmt, parms);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getObject(name);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("executeSqlObject方法出错:" + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                throw new Exception("executeSqlObject方法出错:" + e.getMessage());
            }
        }
    }

    /**
     * 用于获取单字段值语句（用序号指定字段）
     *
     * @param cmdtext
     *            SQL语句
     * @param index
     *            列名索引
     * @param parms
     *            OracleParameter[]
     * @return Object
     * @throws Exception
     */
    public static Object ExecuteScalar(String cmdtext, int index, String[] parms)
            throws Exception {
        PreparedStatement pstmt = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = getConnect();

            pstmt = conn.prepareStatement(cmdtext);
            prepareCommand(pstmt, parms);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getObject(index);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("executeSqlObject方法出错:" + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                throw new Exception("executeSqlObject方法出错:" + e.getMessage());
            }
        }
    }

    /**
     * @param pstmt
     * @param parms Object[]
     * @throws Exception
     */
    private static void prepareCommand(PreparedStatement pstmt, String[] parms)
            throws Exception {
        try {
            if (parms != null) {
                for (int i = 0; i < parms.length; i++) {
                    try {
                        pstmt.setDate(i + 1, java.sql.Date.valueOf(parms[i]));
                    } catch (Exception e) {
                        try {
                            pstmt
                                    .setDouble(i + 1, Double
                                            .parseDouble(parms[i]));
                        } catch (Exception e1) {
                            try {
                                pstmt.setInt(i + 1, Integer.parseInt(parms[i]));
                            } catch (Exception e2) {
                                try {
                                    pstmt.setString(i + 1, parms[i]);
                                } catch (Exception e3) {
                                    System.out
                                            .print("SQLHelper-PrepareCommand Err1:"
                                                    + e3);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e1) {
            System.out.print("SQLHelper-PrepareCommand Err2:" + e1);
        }
    }
}
