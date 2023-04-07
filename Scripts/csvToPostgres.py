import psycopg2
import pandas as pd
from createCountriesTable import create_countries_table


def connect_to_db():
    # define the connection parameters
    conn = psycopg2.connect(
        host="localhost",
        database="mye030",
        user="myuser",
        password="mye030"
    )
    conn.autocommit = True

    return conn


#main function
if __name__ == "__main__":
    connection = connect_to_db()
    print("Connection:", connection)
    create_countries_table(connection)
    connection.close()
