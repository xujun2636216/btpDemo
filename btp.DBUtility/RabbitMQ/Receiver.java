package RabbitMQ;

import java.io.IOException;

import com.sun.nio.sctp.MessageInfo;
import org.apache.commons.lang.SerializationUtils;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.ShutdownSignalException;


public class Receiver extends BaseConnector implements Runnable,Consumer {


    public Receiver(String queueName) throws IOException {
        super(queueName);
    }

    @Override
    public void run() {

        try {
            String str= channel.basicConsume(queueName, true, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer " + consumerTag + " registered");
    }

    @Override
    public void handleCancelOk(String s) {
         String message=s.toString();
    }

    @Override
    public void handleCancel(String s) throws IOException {
         String message=s.toString();
    }

    @Override
    public void handleShutdownSignal(String s, ShutdownSignalException e) {
         String message=s.toString();
    }

    @Override
    public void handleRecoverOk(String s) {
        String message = s.toString();
    }

    @Override
    public void handleDelivery(String s, Envelope envelope, BasicProperties basicProperties, byte[] body) throws IOException {

        MessageInfo messageInfo = (MessageInfo) SerializationUtils.deserialize(body);
    }





}
