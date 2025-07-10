import pandas as pd


def createTestCaseDF(PlayerDetails: pd.DataFrame, playerX, playerY, country):
    testingDataFrame = pd.DataFrame({
        "player_x": playerX,
        "player_y": playerY,
        "year": [2025] * len(playerX),
        "home_country": country,
        "player_x_won": [0] * len(playerX),
    }).set_index(['player_x', 'player_y'])

    testingDataFrame = testingDataFrame.merge(
        PlayerDetails,
        how='left',
        left_on='player_x',
        right_index=True,
    )
    testingDataFrame = testingDataFrame.rename(columns={col: f"{col}_x" for col in PlayerDetails.columns})
    testingDataFrame = testingDataFrame.merge(
        PlayerDetails,
        how='left',
        left_on='player_y',
        right_index=True,
    )
    testingDataFrame = testingDataFrame.rename(columns={col: f"{col}_y" for col in PlayerDetails.columns})

    testingDataFrame['age_diff'] = testingDataFrame['age_x'].values[0] - testingDataFrame['age_y'].values[0]
    testingDataFrame['delta_senior_titles'] = testingDataFrame['all_senior_titles_x'].values[0] - testingDataFrame['all_senior_titles_y'].values[0]
    testingDataFrame['elo_diff'] = testingDataFrame['elo_x'].values[0] - testingDataFrame['elo_y'].values[0]
    testingDataFrame['form_diff'] = testingDataFrame['form_x'].values[0] - testingDataFrame['form_y'].values[0]
    testingDataFrame['same_gender'] = (testingDataFrame['gender_x'].values[0] == testingDataFrame['gender_y'].values[0]).astype(int)
    testingDataFrame['win_ratio_diff'] = testingDataFrame['win_ratio_x'].values[0] - testingDataFrame['win_ratio_y'].values[0]
    testingDataFrame['lose_ratio_diff'] = testingDataFrame['lose_ratio_x'].values[0] - testingDataFrame['lose_ratio_y'].values[0]
    testingDataFrame['wins_diff'] = testingDataFrame['wins_x'].values[0] - testingDataFrame['wins_y'].values[0]
    testingDataFrame['loses_diff'] = testingDataFrame['loses_x'].values[0] - testingDataFrame['loses_y'].values[0]
    testingDataFrame['matches_diff'] = testingDataFrame['matches_x'].values[0] - testingDataFrame['matches_y'].values[0]
    testingDataFrame['same_playtype'] = (testingDataFrame['playtype_x'].values[0] == testingDataFrame['playtype_y'].values[0]).astype(int)
    testingDataFrame['same_hand'] = (testingDataFrame['hand_x'].values[0] == testingDataFrame['hand_y'].values[0]).astype(int)
    testingDataFrame['same_style'] = (testingDataFrame['style_x'].values[0] == testingDataFrame['style_y'].values[0]).astype(int)
    testingDataFrame['x_home_country'] = (testingDataFrame['country_x'].values[0] == testingDataFrame['home_country'].values[0]).astype(int)
    testingDataFrame['y_home_country'] = (testingDataFrame['country_y'].values[0] == testingDataFrame['home_country'].values[0]).astype(int)

    testingDataFrame.drop(columns=['player_name_x', 'player_name_y', 'age_x', 'age_y',
                       'country_x', 'country_y', 'home_country', 'style_y', 'style_x',
                       'hand_y', 'hand_x', 'playtype_y', 'playtype_x', 'matches_x',
                       'loses_x', 'loses_y', 'wins_x', 'wins_y', 'lose_ratio_x',
                       'lose_ratio_y', 'win_ratio_x', 'win_ratio_y', 'gender_x',
                       'gender_y', 'form_x', 'form_y', 'elo_x', 'elo_y',
                       'all_senior_titles_x', 'all_senior_titles_y', 'age_x', 'age_y',
                       'matches_y'], inplace=True)

    testingDataFrame = testingDataFrame.reindex(sorted(testingDataFrame.columns), axis=1)
    return testingDataFrame
