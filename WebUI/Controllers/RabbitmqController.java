package Controllers;

import BLL.StudentBLL;
import RabbitMQ.Receiver;
import RabbitMQ.Sender;
import btpEntity.People;
import btpEntity.Student;

import java.io.IOException;

public class RabbitmqController {
    public static void main(String[] args) throws IOException {

        Sender sender = new Sender("testquea");
        Student model = StudentBLL.Search();;
        sender.sendMessage(model.getClass());

        Receiver receiver = new Receiver("testquea");
        Thread receiverThread = new Thread(receiver);
        receiverThread.start();

    }
}
