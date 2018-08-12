package RabbitMQ;

import Common.LogHelper;
import com.rabbitmq.client.BasicProperties;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import com.sun.nio.sctp.MessageInfo;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;

public class RabbitmqHelper extends BaseConnector {

    public RabbitmqHelper(String queueName) throws IOException {
        super(queueName);
    }

    /**
     * 发送消息(创建生产者)
     */
    public void sendMessage(Serializable object) {
        try {
            channel.basicPublish("", queueName, null, SerializationUtils.serialize(object));
        } catch (IOException e) {
            LogHelper.Error(e.getMessage(), e);
        }
    }

    /**
     * 实现Runnable的run方法
     */
    public void run() {
        try {
            channel.basicConsume(queueName, true, (Consumer) this);
        } catch (IOException e) {
            LogHelper.Error(e.getMessage(), e);
        }
    }

    /**
     * 当消费者注册完成自动调用
     **/
    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer " + consumerTag + " registered");
    }

    /**
     * 当消费者接收到消息会自动调用
     * @param env
     * @param props
     * @param body
     * @throws IOException
     */
    public void handleDelivery(String consumerTag, Envelope env, BasicProperties props, byte[] body) throws IOException {
        MessageInfo messageInfo = (MessageInfo) SerializationUtils.deserialize(body);
    }
}
