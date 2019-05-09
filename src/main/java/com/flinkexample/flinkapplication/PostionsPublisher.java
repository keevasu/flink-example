package com.flinkexample.flinkapplication;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class PostionsPublisher {
    private static final Logger LOGGER = Logger.getLogger(PostionsPublisher.class.getName());
    private static ConcurrentHashMap<String, AtomicLong> counts = new ConcurrentHashMap<>();
    private Random rand = new Random();

    @Autowired
    private KafkaProperties properties;

    private KafkaProducer<String, Position> kafkaProducer;
    //private KafkaProducer<String, Status> statusProducer;
    //private ProducerRecord<String, Position> record = null;
    private JsonSerde<Position> serde = new JsonSerde<>(Position.class);
    //private JsonSerde<Status> serdeStatus = new JsonSerde<>(Status.class);

    @PostConstruct
    public void PricePublisher1()
    {
        Properties kafkaProps = new Properties();
        kafkaProps.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstarp_server_config());
        kafkaProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, properties.getKey_serializer_class_config());
        kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, serde.serializer().getClass().getName());
        kafkaProps.put(ProducerConfig.ACKS_CONFIG, properties.getAcks_config());
        kafkaProducer = new KafkaProducer<>(kafkaProps, new StringSerializer(), serde.serializer());
       // kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, serdeStatus.serializer().getClass().getName());
        //statusProducer = new KafkaProducer<String, Status>(kafkaProps, new StringSerializer(), serdeStatus.serializer());
    }


    public String producer(int ref_id) {
        final String TOPIC_PRICES = properties.getTopic_prices();
        for (int i = 1; i <= 10000; i++) {
            int tierAccountId = rand.nextInt(300) + 1;
            int valuableitem_id = Math.abs(rand.nextInt(1000) + 1);
            double startLong = rand.nextInt(100) + 1;
            double startShort = rand.nextInt(100) + 1;
            double currentLong = rand.nextInt(100) + 1;
            double currentShort = rand.nextInt(100) + 1;


            java.sql.Timestamp businessdate = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

            Position p = new Position();
            p.setTierAccountId(tierAccountId);
            p.setValuableitem_id(valuableitem_id);
            p.setStartLong(startLong);
            p.setStartShort(startShort);
            p.setCurrentLong(currentLong);
            p.setCurrentShort(currentShort);
            p.setBusinessdate(businessdate);

            Serializer<Position> serializer = new JsonSerde<>(Position.class).serializer();
            //byte[] m = serializer.serialize(null, p);

            ProducerRecord<String, Position> record = null;

            record = new ProducerRecord<>(TOPIC_PRICES, String.format("%s %s", ref_id, valuableitem_id), p);
            kafkaProducer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (exception != null) {
                        LOGGER.log(Level.WARNING, "Error sending message with key {0}\n{1}", new Object[]{"test", exception.getMessage()});
                    } else {
//                           LOGGER.log(Level.INFO, "Partition for key-value {0}::{1} is {2}", new Object[]{"test", query.getResults(), metadata.partition()});
                    }
                }
            });
            //LOGGER.log(Level.INFO, "Generated {0} key={1} value={2}", new Object[]{getCount(String.valueOf(ref_id)), record.key(), record.value()});
        }
        return "Thread ran successfully";
    }
   public void kafkaProducerClose(){
       kafkaProducer.close();
   }
}