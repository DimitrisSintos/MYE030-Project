def create_countries_table(connection):
    # create a cursor
    cur = connection.cursor()
    # execute a statement
    print('Creating countries table...')
    cur.execute(
        "CREATE TABLE IF NOT EXISTS countries ("
            "ISO TEXT,"
            "ISO3 TEXT,"
            "ISO_Code INTEGER PRIMARY KEY,"
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
            "Subregion_Code TEXT,"
            "Subregion_Name TEXT,"
            "Intermediate_Region_Code TEXT,"
            "Intermediate_Region_Name TEXT,"
            "Status TEXT,"
            "Developed_or_Developing TEXT,"
            "Small_Island_Developing_States BOOLEAN,"
            "Land_Locked_Developing_Countries BOOLEAN,"
            "Least_Developed_Countries BOOLEAN,"
            "Area_SqKm NUMERIC,"
            "Population INTEGER"
        ")"
    )

    with open('../Datasets/countries/countries.csv', 'r', encoding='iso-8859-1') as f:
        next(f) # skip header row
        cur.copy_from(f, 'countries', sep=',', null='')

    # close the cursor and connection
    cur.close()
    connection.close()
    print('Counties table created!')