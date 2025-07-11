import json
class KafkaProducerObject():
    def __init__(self, orderId, result):
        self.orderId = orderId
        self.result = result

    def serialize(self):
        return json.dumps(self)


