#TABLE NAME: countries
#csv file: countries.csv
import csv
import os
def create_countries_table(connection):
    # create a cursor
    cur = connection.cursor()
    # execute a statement
    print('Creating countries table...')
    cur.execute(
        "CREATE TABLE IF NOT EXISTS countries ("
            "ISO VARCHAR(2),"
            "ISO3 VARCHAR(3),"
            "ISO_Code INTEGER PRIMARY KEY,"
            "FIPS VARCHAR(50),"
            "Display_Name VARCHAR(50),"
            "Official_Name VARCHAR(100),"
            "Capital VARCHAR(50),"
            "Continent VARCHAR(50),"
            "CurrencyCode VARCHAR(50),"
            "CurrencyName VARCHAR(50),"
            "Phone VARCHAR(50),"
            "Region_Code VARCHAR(50),"
            "Region_Name VARCHAR(50),"
            "Subregion_Code VARCHAR(50),"
            "Subregion_Name VARCHAR(50),"
            "Intermediate_Region_Code VARCHAR(50),"
            "Intermediate_Region_Name VARCHAR(50),"
            "Status VARCHAR(50),"
            "Developed_or_Developing VARCHAR(50),"
            "Small_Island_Developing_States BOOLEAN,"
            "Land_Locked_Developing_Countries BOOLEAN,"
            "Least_Developed_Countries BOOLEAN,"
            "Area_SqKm NUMERIC,"
            "Population INTEGER"
        ")"
    )
    #create a new csv file
    
    countries_names_dict = {}
    countries_fips_dict = {}

    if os.path.exists('../Datasets/countries/countries_updated.csv'):
        with open('../Datasets/countries/countries_updated.csv', 'r', encoding='utf-8') as infile:
            reader = csv.reader(infile)
            for row in reader:
                countries_names_dict[row[5]] = row[2]
                if row [3] != '':
                    countries_fips_dict[row[3]] = row[2]
                else:
                    countries_fips_dict[row[0]] = row[2]
            infile.seek(0) # move the file pointer to the beginning of the file
            next(infile) # skip header row
            cur.copy_from(infile, 'countries', sep=',', null='')
    else:
        with open('../Datasets/countries/countries.csv', 'r', encoding='iso-8859-1') as infile:
            with open('../Datasets/countries/countries_updated.csv', 'w+', encoding='utf-8') as outfile:
                reader = csv.reader(infile)
                writer = csv.writer(outfile)
                for row in reader:
                    for i in range(len(row)):
                        if "," in row[i]:
                            row[i] = row[i].replace(",", "")
                        elif "#N/A" in row[i]:
                            row[i] = ""
                        
                    countries_names_dict[row[5]] = row[2]
                    if row [3] != '':
                        countries_fips_dict[row[3]] = row[2]
                    else:
                        countries_fips_dict[row[0]] = row[2]
                    writer.writerow(row)   
                outfile.seek(0) # move the file pointer to the beginning of the file         
                next(outfile) # skip header row
                cur.copy_from(outfile, 'countries', sep=',', null='')

    # close the cursor and connection
    cur.close()
    print('Counties table created!')
    return countries_names_dict, countries_fips_dict