import psycopg2
import pandas as pd
from createCountriesTable import create_countries_table
from createInternationalTables import create_internanional_tables


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
    countries_names_dict, countries_fips_dict = create_countries_table(connection)
    create_internanional_tables(connection, countries_fips_dict)

    

    connection.close()
