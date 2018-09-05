import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KProducer {
    private Properties props;
    private Producer<String, String> producer;
    private String topicName;

    public KProducer(String topic) {
        if (props == null) {
            props = new Properties();
            props.put("bootstrap.servers", "192.168.2.105:9092");
            props.put("acks", "all");
            props.put("retries", 0);
            props.put("batch.size", 16384);
            props.put("linger.ms", 1);
            props.put("buffer.memory", 33554432);
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        }
        topicName = new String(topic);
        producer = new KafkaProducer<String, String>(props);
    }

    public void produce() {
        for (int i=0; i<10; i++) {
            producer.send(
                    new ProducerRecord<String, String>(topicName, Integer.toString(i), Integer.toString(i))
            );
            System.out.println("Message sent successfully");

        }
        producer.close();
    }

    public static void main(String args[]) {
        KProducer producer = new KProducer("test");
        producer.produce();
    }
}
