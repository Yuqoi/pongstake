import os
import numpy
from confluent_kafka import Consumer
from dotenv import load_dotenv
import json

from KafkaProducerObject import KafkaProducerObject
from model.InitModel import predictOutput
from utils.CreateTestDataFrame import createTestCaseDF
from utils.ManagePlayerDetails import createPlayerDetailsDataFrame

load_dotenv()
from confluent_kafka import Producer
import socket

from confluent_kafka.serialization import Serializer


if __name__ == '__main__':

    config = {
        'bootstrap.servers': os.environ['hostname'],
        'group.id':          os.environ['group_id'],
        'auto.offset.reset': 'earliest'
    }

    consumer = Consumer(config)
    topic = os.environ['topic_getter']
    consumer.subscribe([topic])

    conf = {
        'bootstrap.servers': os.environ['hostname'],
        'client.id': socket.gethostname()
    }

    producer = Producer(conf)

    try:
        while True:
            msg = consumer.poll(3.0)
            if msg is None:
                print("Waiting...")
            elif msg.error():
                print("ERROR: %s".format(msg.error()))
            else:
                key = msg.key().decode('utf-8')
                data = json.loads(msg.value().decode('utf-8'))

                playerX = data['metadata']['playerX']
                playerY = data['metadata']['playerY']
                country = data['metadata']['country']

                PlayerDetails = createPlayerDetailsDataFrame(playerX, playerY, country)
                TestCase = createTestCaseDF(PlayerDetails, playerX, playerY, country)
                result: numpy.ndarray = predictOutput(TestCase)
                result = result.tolist()

                kafkaProducerObject = KafkaProducerObject(orderId=data['orderId'], result=result)
                serialized = kafkaProducerObject.__dict__
                serialized = json.dumps(serialized).encode('utf-8')

                producer.produce("predictions_made", key=key, value=serialized)


    except KeyboardInterrupt:
        pass
    finally:
        consumer.close()