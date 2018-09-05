import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class KConsumer {

    private Properties props;
    private KafkaConsumer<String, String> consumer;
    private String topicName;
    public KConsumer(String topic) {
        props = new Properties();

        props.put("bootstrap.servers", "192.168.2.105:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<String, String>(props);
        topicName = topic;
        consumer.subscribe(Arrays.asList(topic));

    }

    public void subscribe() {
        System.out.println("subscribed to topic:" + topicName);
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("offset = " + record.offset() + " key = " + record.key() +
                        " value = " + record.value());
            }

        }
    }

    public static void main(String[] args) {
        KConsumer consumer = new KConsumer("test");
        consumer.subscribe();

    }
}

