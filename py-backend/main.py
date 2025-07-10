import os
from confluent_kafka import Consumer
from confluent_kafka import Producer
from dotenv import load_dotenv
import json

load_dotenv()

if __name__ == '__main__':

    config = {
        'bootstrap.servers': os.environ['hostname'],
        'group.id':          os.environ['group_id'],
        'auto.offset.reset': 'earliest'
    }

    consumer = Consumer(config)
    topic = os.environ['topic_getter']
    consumer.subscribe([topic])

    # producer = Producer(config)

    try:
        while True:
            msg = consumer.poll(3.0)
            if msg is None:
                print("Waiting...")
            elif msg.error():
                print("ERROR: %s".format(msg.error()))
            else:
                print("Consumed event from topic {topic}: key = {key:12} value = {value:12}".format(
                    topic=msg.topic(), key=msg.key().decode('utf-8'), value=msg.value().decode('utf-8')))
                data = json.loads(msg.value().decode('utf-8'))
                print(data['metadata'])

    except KeyboardInterrupt:
        pass
    finally:
        consumer.close()