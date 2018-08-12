package Controllers;

import RabbitMQ.Receiver;
import RabbitMQ.Sender;
import btpEntity.People;
import java.io.IOException;

public class RabbitmqController {
    public static void main(String[] args) throws IOException {
        Receiver receiver = new Receiver("testQueue");
        Thread receiverThread = new Thread(receiver);
        receiverThread.start();
        Sender sender = new Sender("testQueue");
        for (int i = 0; i < 5; i++) {
            People model = new People();
            model.setAge(10);
            model.setName("徐军");
            sender.sendMessage(model.getClass());
        }
    }
}
