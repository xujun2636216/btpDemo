package Controllers;
import RabbitMQ.Receiver;
import RabbitMQ.Sender;
import btpEntity.Student;
import org.junit.Test;
import java.io.IOException;

public class RabbitmqController {
    public static void main(String[] args) throws IOException {

        Sender sender = new Sender("testquea");
        Student model=new Student(12,13,"xujun");
        sender.sendMessage(model.getClass());

        Receiver receiver = new Receiver("testquea");
        Thread receiverThread = new Thread(receiver);
        receiverThread.start();

    }


    /**
     * 接受事件
     */
    @Test
    public void geetreceiver() throws IOException {

    }


}
