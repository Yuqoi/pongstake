import psycopg2
import os
from dotenv import load_dotenv
import pandas as pd

load_dotenv()
pd.set_option('display.max_columns', None)
conn = psycopg2.connect(
    host=os.environ['psql_host'],
    dbname=os.environ['psql_db_name'],
    user=os.environ['psql_user'],
    password=os.environ['psql_password'],
    port=os.environ['psql_port']
)
conn.autocommit = True
