import os
from xgboost import XGBClassifier, Booster

clf = XGBClassifier()
booster = Booster()
booster.load_model('ai_model.json')
clf._Booster = booster


def predictOutput(TestDF):
    Test_x = TestDF.drop(columns=['player_x_won'])
    return clf.predict(Test_x)