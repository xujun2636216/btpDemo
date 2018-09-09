package Controllers;


import Common.LogHelper;

public class Log4jController {

    public static void main(String[] args) {

        try {
            LogHelper.Debug("你好", 1, 2);
            LogHelper.Info("你好");
            throw new Exception("你错了");
        } catch (Exception ex) {
            LogHelper.Error(ex.getMessage(), ex);
        }
    }
}
