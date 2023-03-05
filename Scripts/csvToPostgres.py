import psycopg2
import pandas as pd

# define the connection parameters
conn = psycopg2.connect(
    host="localhost",
    database="mye030-new",
    user="myusername",
    password="1223"
)
conn.autocommit = True

cur = conn.cursor()
# define the schema and table name
schema_name = "MYE030"
table_name = "COUNTRIES"

cur.execute(f"CREATE TABLE IF NOT EXISTS {table_name} ("
            "ISO TEXT,"
            "ISO3 TEXT,"
            "ISO_Code TEXT,"
            "FIPS TEXT,"
            "Display_Name TEXT,"
            "Official_Name TEXT,"
            "Capital TEXT,"
            "Continent TEXT,"
            "CurrencyCode TEXT,"
            "CurrencyName TEXT,"
            "Phone TEXT,"
            "Region_Code TEXT,"
            "Region_Name TEXT,"
            "Sub_region_Code TEXT,"
            "Sub_region_Name TEXT,"
            "Intermediate_Region_Code TEXT,"
            "Intermediate_Region_Name TEXT,"
            "Status TEXT,"
            "Developed_or_Developing TEXT,"
            "Small_Island_Developing_States_SIDS TEXT,"
            "Land_Locked_Developing_Countries_LLDC TEXT,"
            "Least_Developed_Countries_LDC TEXT,"
            "Area_SqKm TEXT,"
            "Population TEXT"
            ")")

# close the cursor and connection
cur.close()
conn.close()
