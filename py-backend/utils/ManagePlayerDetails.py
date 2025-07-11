import pandas as pd
from database_config.database import conn

def createPlayerDetailsDataFrame(playerX, playerY, country)-> pd.DataFrame:
    cur = conn.cursor()

    PlayerDetails = pd.DataFrame({
        "age": [],
        "all_senior_titles": [],
        "country": [],
        "gender": [],
        "hand": [],
        "loses": [],
        "matches": [],
        "player_id": [],
        "player_name": [],
        "playtype": [],
        "style": [],
        "wins": [],
        "elo": [],
        "win_ratio": [],
        "lose_ratio": [],
        "form": []
    })
    for x, y, c in zip(playerX, playerY, country):
        cur.execute("""SELECT * FROM wtt_player_details WHERE player_id = %s;""", (x,))
        playerXData = cur.fetchone()
        playerXDF = pd.DataFrame({
            "age": [playerXData[0]],
            "all_senior_titles": [playerXData[1]],
            "country": [playerXData[2]],
            "gender": [playerXData[3]],
            "hand": [playerXData[4]],
            "loses": [playerXData[5]],
            "matches": [playerXData[6]],
            "player_id": [playerXData[7]],
            "player_name": [playerXData[8]],
            "playtype": [playerXData[9]],
            "style": [playerXData[10]],
            "wins": [playerXData[11]],
            "elo": [playerXData[12]],
            "win_ratio": [playerXData[13]],
            "lose_ratio": [playerXData[14]],
            "form": [playerXData[15]]
        })
        cur.execute("""SELECT * FROM wtt_player_details WHERE player_id = %s;""", (y,))
        playerYData = cur.fetchone()
        playerYDF = pd.DataFrame({
            "age": [playerYData[0]],
            "all_senior_titles": [playerYData[1]],
            "country": [playerYData[2]],
            "gender": [playerYData[3]],
            "hand": [playerYData[4]],
            "loses": [playerYData[5]],
            "matches": [playerYData[6]],
            "player_id": [playerYData[7]],
            "player_name": [playerYData[8]],
            "playtype": [playerYData[9]],
            "style": [playerYData[10]],
            "wins": [playerYData[11]],
            "elo": [playerYData[12]],
            "win_ratio": [playerYData[13]],
            "lose_ratio": [playerYData[14]],
            "form": [playerYData[15]]
        })
        PlayerDetails = pd.concat([playerXDF, playerYDF], ignore_index=True)
    PlayerDetails.set_index(['player_id'], inplace=True)
    cur.close()

    return PlayerDetails