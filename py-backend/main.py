import os
from confluent_kafka import Consumer
from dotenv import load_dotenv
import json
from model.InitModel import predictOutput
from utils.CreateTestDataFrame import createTestCaseDF
from utils.ManagePlayerDetails import createPlayerDetailsDataFrame

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


    try:
        while True:
            msg = consumer.poll(3.0)
            if msg is None:
                print("Waiting...")
            elif msg.error():
                print("ERROR: %s".format(msg.error()))
            else:
                key=msg.key().decode('utf-8')
                data = json.loads(msg.value().decode('utf-8'))

                playerX = data['playerX']
                playerY = data['playerY']
                country = data['country']

                PlayerDetails = createPlayerDetailsDataFrame(playerX, playerY, country)
                TestCase = createTestCaseDF(PlayerDetails, playerX, playerY, country)
                predictOutput(TestCase)

    except KeyboardInterrupt:
        pass
    finally:
        consumer.close()