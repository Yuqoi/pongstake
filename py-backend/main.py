from confluent_kafka import Consumer

if __name__ == '__main__':

    config = {
        'bootstrap.servers': 'localhost:9092',
        'group.id':          'pongstake',
        'auto.offset.reset': 'earliest'
    }

    consumer = Consumer(config)
    topic = "sent_orders"
    consumer.subscribe([topic])

    try:
        while True:
            msg = consumer.poll(1.0)
            if msg is None:
                print("Waiting...")
            elif msg.error():
                print("ERROR: %s".format(msg.error()))
            else:
                # Extract the (optional) key and value, and print.
                print("Consumed event from topic {topic}: key = {key:12} value = {value:12}".format(
                    topic=msg.topic(), key=msg.key().decode('utf-8'), value=msg.value().decode('utf-8')))
    except KeyboardInterrupt:
        pass
    finally:
        consumer.close()