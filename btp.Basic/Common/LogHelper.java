package Common;

import org.apache.log4j.Logger;

public class LogHelper {
    private static final Logger logger = Logger.getLogger(LogHelper.class);

    /**
     * @param Msg
     * @param i
     * @param i1
     */
    public static void Debug(String Msg, int i, int i1) {
        if (logger.isDebugEnabled()) {
            logger.debug(Msg);
        }
    }


    /**
     * @param Msg
     */
    public static void Info(String Msg) {
        if (logger.isInfoEnabled()) {
            logger.info(Msg);
        }
    }

    /**
     * @param Msg
     */
    public static void Error(String Msg, Exception ex) {
        try {
            logger.error(Msg, ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
