package Controllers;

import RabbitMQ.Receiver;
import RabbitMQ.Sender;
import btpEntity.People;
import java.io.IOException;

public class RabbitmqController {
    public static void main(String[] args) throws IOException {

        Sender sender = new Sender("testquea");
        for (int i = 0; i < 5; i++) {
            People model = new People();
            model.setAge(10);
            model.setName("徐军");
            sender.sendMessage(model.getClass());
        }

        Receiver receiver = new Receiver("xujun");
        Thread receiverThread = new Thread(receiver);
        receiverThread.start();

    }
}
