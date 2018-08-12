package RabbitMQ;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class BaseConnector {
    protected Channel channel;
    protected Connection connection;
    protected String queueName;

    public BaseConnector(String queueName) throws IOException {
        try {
            this.queueName = queueName;
            //打开连接和创建频道
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("211.159.175.227");
            //创建连接
            connection = factory.newConnection();
            //创建频道
            channel = connection.createChannel();
            //声明创建队列
            channel.queueDeclare(queueName, false, false, false, null);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
