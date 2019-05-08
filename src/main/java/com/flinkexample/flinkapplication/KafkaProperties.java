package com.flinkexample.flinkapplication;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties("kafka.connection")
@Component
@Data
public class KafkaProperties {
    private String topic_prices;

    private String topic_app_statuses;

    private String bootstarp_server_config;

    private String key_serializer_class_config;

    public String getTopic_prices() {
        return topic_prices;
    }

    public void setTopic_prices(String topic_prices) {
        this.topic_prices = topic_prices;
    }

    public String getTopic_app_statuses() {
        return topic_app_statuses;
    }

    public void setTopic_app_statuses(String topic_app_statuses) {
        this.topic_app_statuses = topic_app_statuses;
    }

    public String getBootstarp_server_config() {
        return bootstarp_server_config;
    }

    public void setBootstarp_server_config(String bootstarp_server_config) {
        this.bootstarp_server_config = bootstarp_server_config;
    }

    public String getKey_serializer_class_config() {
        return key_serializer_class_config;
    }

    public void setKey_serializer_class_config(String key_serializer_class_config) {
        this.key_serializer_class_config = key_serializer_class_config;
    }

    public String getValue_serializer_class_config() {
        return value_serializer_class_config;
    }

    public void setValue_serializer_class_config(String value_serializer_class_config) {
        this.value_serializer_class_config = value_serializer_class_config;
    }

    public String getAcks_config() {
        return acks_config;
    }

    public void setAcks_config(String acks_config) {
        this.acks_config = acks_config;
    }

    private String value_serializer_class_config;

    private String acks_config;
}
