package com.example.demo.objects;

public class KafkaProducerMessage {

    private long orderId;
    private Metadata metadata;

    public KafkaProducerMessage() {}
    public KafkaProducerMessage(long orderId, Metadata metadata) {
        this.orderId = orderId;
        this.metadata = metadata;
    }

    public long getOrderId() {
        return orderId;
    }
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Metadata getMetadata() {
        return metadata;
    }
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
}
