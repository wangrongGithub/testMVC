package cn.wr.SpringBoot;

import com.rabbitmq.client.*;
import sun.awt.image.PNGImageDecoder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestRMQ
{
    static String EXCHANGE_NAME="exchange_1";
    static String ROUTING_KEY="routingkey_1";
    static String QUEUE_NMAE="queue_1";
    static  String IP_ADDRESS="127.0.0.1";
    static int PORT=5672;
    static ConnectionFactory factory=new  ConnectionFactory();

    static {
        factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        factory.setUsername("root1");
        factory.setPassword("1234");
    }
    public static void main(String[]args) throws IOException, TimeoutException, InterruptedException {
        System.out.println("111111111111111111111111");
        Thread pro=new Thread(new product());
        System.out.println(Thread.currentThread());
        Thread con=new Thread(new consummer());
        pro.start();
        Thread.sleep(1000);
        con.start();
        con.join();
        Thread.sleep(1000);

    }
}
class product implements Runnable{
    ConnectionFactory factory=TestRMQ.factory;
    static String EXCHANGE_NAME="exchange_6";
    static String ROUTING_KEY="routingkey_1";
    static String QUEUE_NMAE="queue_1";
    @Override
    public void run() {
        Connection con= null;
        Channel channel=null;
        try {
            con = factory.newConnection();
             channel=con.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME,"direct",true,false,null);
            channel.queueDeclare(QUEUE_NMAE,true,false,false,null);
            //绑定出错怎么办
            channel.queueBind(QUEUE_NMAE,EXCHANGE_NAME,ROUTING_KEY);//
            String message="I am wangrong!!!";
            System.out.println("product");
            System.out.println(Thread.currentThread());
            channel.basicPublish(EXCHANGE_NAME,ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
            channel.basicPublish(EXCHANGE_NAME,ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN,(message+"2").getBytes());
            channel.basicPublish(EXCHANGE_NAME,ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN,(message+"3").getBytes());
            channel.basicPublish(EXCHANGE_NAME,ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
            channel.basicPublish(EXCHANGE_NAME,ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN,(message+"2").getBytes());
            channel.basicPublish(EXCHANGE_NAME,ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN,(message+"3").getBytes());
            channel.close();
            con.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
class consummer implements Runnable{
    ConnectionFactory factory=TestRMQ.factory;
    static String EXCHANGE_NAME="exchange_1";
    static String ROUTING_KEY="routingkey_1";
    static String QUEUE_NMAE="queue_1";
    @Override
    public void run() {
        Connection con= null;

        try {
            con = factory.newConnection();
            final Channel channel=con.createChannel();
            channel.basicQos(64);
           // channel.exchangeDeclare(EXCHANGE_NAME,"direct",true,false,null);
            Consumer consumer=new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("recv message"+new String(body));
                    channel.basicAck(envelope.getDeliveryTag(),false);
                    System.out.println("consumer  "+Thread.currentThread());
                }
            };
            System.out.println("consumer");
            //消费是个异步消费的过程吗;是的，消息的消费是异步消费的啊
           channel.basicConsume(QUEUE_NMAE,consumer);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            channel.close();
            con.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}