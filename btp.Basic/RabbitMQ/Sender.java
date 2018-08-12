package RabbitMQ;

import Common.LogHelper;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;

public class Sender extends BaseConnector {

    public Sender(String queueName) throws IOException {
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

}
