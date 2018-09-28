package Controllers;

import BLL.StudentBLL;
import RabbitMQ.Receiver;
import RabbitMQ.Sender;
import btpEntity.People;
import btpEntity.Student;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.junit.Test;
import org.quartz.ExecuteInJTATransaction;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;

public class RabbitmqController {
    public static void main(String[] args) throws IOException {

        Sender sender = new Sender("testquea");
        Student model=new Student(12,13,"xujun");
        sender.sendMessage(model.getClass());

    }


    /**
     * 接受事件
     */
    @Test
    public void geetreceiver() throws IOException {
        Receiver receiver = new Receiver("testquea");
        Thread receiverThread = new Thread(receiver);
        receiverThread.start();
    }


}
