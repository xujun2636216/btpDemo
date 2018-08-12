package RabbitMQ;

import com.rabbitmq.client.BasicProperties;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import com.sun.nio.sctp.MessageInfo;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.util.function.Consumer;

public class Receiver extends BaseConnector implements Runnable, Consumer {


    public Receiver(String queueName) throws IOException {
        super(queueName);
    }

    @Override
    public void run() {

        try {
            channel.basicConsume(queueName, true, (com.rabbitmq.client.Consumer) this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 下面这些方法都是实现Consumer接口的
     **/
    //当消费者注册完成自动调用
    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer " + consumerTag + " registered");
    }

    //当消费者接收到消息会自动调用
    public void handleDelivery(String consumerTag, Envelope env, BasicProperties props, byte[] body) throws IOException {
        MessageInfo messageInfo = (MessageInfo) SerializationUtils.deserialize(body);

    }

    //下面这些方法可以暂时不用理会
    public void handleCancelOk(String consumerTag) {
    }

    public void handleCancel(String consumerTag) throws IOException {
    }

    public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
    }

    public void handleRecoverOk(String consumerTag) {
    }


    @Override
    public void accept(Object o) {

    }
}
