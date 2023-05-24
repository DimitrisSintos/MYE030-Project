import os
import pandas


def create_income_index_table(connection):
    cur = connection.cursor()
    
    df = pandas.read_excel('../Datasets/income-by-country/Income by Country.xlsx', sheet_name='Income Index', header=0, skipfooter=17)
    print('Creating Income Index table...')

    # Remove the first header (Country)
    headers = df.columns.tolist()
    country_header = headers.pop(0)

    # Extract the years and convert them to integers
    years = [int(header) for header in headers]
    min_year = min(years)
    max_year = max(years)
 
    cur.execute(
        f"""CREATE TABLE IF NOT EXISTS income_indexes (
            iso_code INTEGER PRIMARY KEY REFERENCES countries (iso_code),
            {', '.join([f'"{i}" numeric' for i in range(min_year, max_year+1)])}
        )"""
    )

    # check if the tmp csv file exists and buy time
    if os.path.exists('../Datasets/income-by-country/income_indexes_tmp.csv'):
        with open('../Datasets/income-by-country/income_indexes_tmp.csv', 'w+', encoding='utf-8') as outfile:
            outfile.seek(0)
            next(outfile)
            cur.copy_from(outfile, 'income_indexes', sep=',', null='')
        return

    df = prepare_df(df, cur)

    with open('../Datasets/income-by-country/income_indexes_tmp.csv', 'w+', encoding='utf-8') as outfile:
        df.to_csv(outfile, index=False)
        outfile.seek(0)
        next(outfile)
        cur.copy_from(outfile, 'income_indexes', sep=',', null='')
    
    cur.close()

        


def create_labour_share_of_GDP_table(connection):
    cur = connection.cursor()
    
    df = pandas.read_excel('../Datasets/income-by-country/Income by Country.xlsx', sheet_name='Labour share of GDP', header=0, skipfooter=17)
    print('Creating Income Index table...')

    # Remove the first header (Country)
    headers = df.columns.tolist()
    country_header = headers.pop(0)

    # Extract the years and convert them to integers
    years = [int(header) for header in headers]

    cur.execute(
        f"""CREATE TABLE IF NOT EXISTS labour_shares (
            iso_code INTEGER PRIMARY KEY REFERENCES countries (iso_code),
            {', '.join([f'"{i}" numeric' for i in years])}
        )"""
    )

    # check if the tmp csv file exists and buy time
    if os.path.exists('../Datasets/income-by-country/labour_share_of_gdp_tmp.csv'):
        with open('../Datasets/income-by-country/labour_share_of_gdp_tmp.csv', 'w+', encoding='utf-8') as outfile:
            outfile.seek(0)
            next(outfile)
            cur.copy_from(outfile, 'labour_share_of_gdp', sep=',', null='')
        return

    df = prepare_df(df, cur)

    with open('../Datasets/income-by-country/labour_share_of_gdp_tmp.csv', 'w+', encoding='utf-8') as outfile:
        df.to_csv(outfile, index=False)
        outfile.seek(0)
        next(outfile)
        cur.copy_from(outfile, 'labour_shares', sep=',', null='')
    
    cur.close()



def create_gross_fixed_capital_formation_table(connection):
    cur = connection.cursor()
    
    df = pandas.read_excel('../Datasets/income-by-country/Income by Country.xlsx', sheet_name='Gross fixed capital formation', header=0, skipfooter=17)
    print("Creating gross fixed capital formation table...")

    # Remove the first header (Country)
    headers = df.columns.tolist()
    country_header = headers.pop(0)

    # Extract the years and convert them to integers
    years = [int(header) for header in headers]

    cur.execute(
        f"""CREATE TABLE IF NOT EXISTS gross_fixed_capital_formation (
            iso_code INTEGER PRIMARY KEY REFERENCES countries (iso_code),
            {', '.join([f'"{i}" numeric' for i in years])}
        )"""
    )

    # check if the tmp csv file exists and buy time
    if os.path.exists('../Datasets/income-by-country/gross_fixed_capital_formation_tmp.csv'):
        with open('../Datasets/income-by-country/gross_fixed_capital_formation_tmp.csv', 'w+', encoding='utf-8') as outfile:
            outfile.seek(0)
            next(outfile)
            cur.copy_from(outfile, 'gross_fixed_capital_formation', sep=',', null='')
        return

    df = prepare_df(df, cur)

    with open('../Datasets/income-by-country/gross_fixed_capital_formation_tmp.csv', 'w+', encoding='utf-8') as outfile:
        df.to_csv(outfile, index=False)
        outfile.seek(0)
        next(outfile)
        cur.copy_from(outfile, 'gross_fixed_capital_formation', sep=',', null='')
    
    cur.close()


def create_gdp_total_table(connection):
    cur = connection.cursor()
    
    df = pandas.read_excel('../Datasets/income-by-country/Income by Country.xlsx', sheet_name='GDP total', header=0, skipfooter=17)
    # remove last column of dataframe (junk)
    df = df.iloc[:, :-1]

    print("Creating GDP total table...")
    # Remove the first header (Country)
    headers = df.columns.tolist()
    country_header = headers.pop(0)

    # Extract the years and convert them to integers
    years = [int(header) for header in headers]

    cur.execute(
        f"""CREATE TABLE IF NOT EXISTS gdp_total (
            iso_code INTEGER PRIMARY KEY REFERENCES countries (iso_code),
            {', '.join([f'"{i}" numeric' for i in years])}
        )"""
    )

    # check if the tmp csv file exists and buy time
    if os.path.exists('../Datasets/income-by-country/gdp_total_tmp.csv'):
        with open('../Datasets/income-by-country/gdp_total_tmp.csv', 'w+', encoding='utf-8') as outfile:
            outfile.seek(0)
            next(outfile)
            cur.copy_from(outfile, 'gdp_total', sep=',', null='')
        return

    df = prepare_df(df, cur)


    with open('../Datasets/income-by-country/gdp_total_tmp.csv', 'w+', encoding='utf-8') as outfile:
        df.to_csv(outfile, index=False)
        outfile.seek(0)
        next(outfile)
        cur.copy_from(outfile, 'gdp_total', sep=',', null='')
    
    cur.close()


def create_gdp_per_capita_table(connection):
    cur = connection.cursor()
    
    df = pandas.read_excel('../Datasets/income-by-country/Income by Country.xlsx', sheet_name='GDP per capita', header=0, skipfooter=17)
    # remove last column of dataframe (junk)
    df = df.iloc[:, :-1]

    print("Creating GDP per capita table...")
    # Remove the first header (Country)
    headers = df.columns.tolist()
    country_header = headers.pop(0)

    # Extract the years and convert them to integers
    years = [int(header) for header in headers]

    cur.execute(
        f"""CREATE TABLE IF NOT EXISTS gdp_capita (
            iso_code INTEGER PRIMARY KEY REFERENCES countries (iso_code),
            {', '.join([f'"{i}" numeric' for i in years])}
        )"""
    )

    # check if the tmp csv file exists and buy time
    if os.path.exists('../Datasets/income-by-country/gdp_capita_tmp.csv'):
        with open('../Datasets/income-by-country/gdp_capita_tmp.csv', 'w+', encoding='utf-8') as outfile:
            outfile.seek(0)
            next(outfile)
            cur.copy_from(outfile, 'gdp_capita', sep=',', null='')
        return


    df = prepare_df(df, cur)


    with open('../Datasets/income-by-country/gdp_capita_tmp.csv', 'w+', encoding='utf-8') as outfile:
        df.to_csv(outfile, index=False)
        outfile.seek(0)
        next(outfile)
        cur.copy_from(outfile, 'gdp_capita', sep=',', null='')
    
    cur.close()


def create_gni_per_capita_table(connection):
    cur = connection.cursor()
    
    df = pandas.read_excel('../Datasets/income-by-country/Income by Country.xlsx', sheet_name='GNI per capita', header=0, skipfooter=17)
    # remove last column of dataframe (junk)
    df = df.iloc[:, :-1]
    
    print("Creating GNI per capita table...")
    # Remove the first header (Country)
    headers = df.columns.tolist()
    country_header = headers.pop(0)

    # Extract the years and convert them to integers
    years = [int(header) for header in headers]

    cur.execute(
        f"""CREATE TABLE IF NOT EXISTS gni_capita (
            iso_code INTEGER PRIMARY KEY REFERENCES countries (iso_code),
            {', '.join([f'"{i}" numeric' for i in years])}
        )"""
    )

    # check if the tmp csv file exists and buy time
    if os.path.exists('../Datasets/income-by-country/gni_capita_tmp.csv'):
        with open('../Datasets/income-by-country/gni_capita_tmp.csv', 'w+', encoding='utf-8') as outfile:
            outfile.seek(0)
            next(outfile)
            cur.copy_from(outfile, 'gni_capita', sep=',', null='')
        return



    df = prepare_df(df, cur)


    with open('../Datasets/income-by-country/gni_capita_tmp.csv', 'w+', encoding='utf-8') as outfile:
        df.to_csv(outfile, index=False)
        outfile.seek(0)
        next(outfile)
        cur.copy_from(outfile, 'gni_capita', sep=',', null='')
    
    cur.close()


def create_gni_male_table(connection):
    cur = connection.cursor()
    
    df = pandas.read_excel('../Datasets/income-by-country/Income by Country.xlsx', sheet_name='Estimated GNI male', header=0, skipfooter=17)    # remove last column of dataframe (junk)
    # remove last column of dataframe (junk)
    df = df.iloc[:, :-1]

    print("Creating Estimated GNI male table...")
    # Remove the first header (Country)
    headers = df.columns.tolist()
    country_header = headers.pop(0)

    # Extract the years and convert them to integers
    years = [int(header) for header in headers]

    cur.execute(
        f"""CREATE TABLE IF NOT EXISTS gni_male (
            iso_code INTEGER PRIMARY KEY REFERENCES countries (iso_code),
            {', '.join([f'"{i}" numeric' for i in years])}
        )"""
    )

    # check if the tmp csv file exists and buy time
    if os.path.exists('../Datasets/income-by-country/gni_male_tmp.csv'):
        with open('../Datasets/income-by-country/gni_male_tmp.csv', 'w+', encoding='utf-8') as outfile:
            outfile.seek(0)
            next(outfile)
            cur.copy_from(outfile, 'gni_male', sep=',', null='')
        return


    df = prepare_df(df, cur)


    with open('../Datasets/income-by-country/gni_male_tmp.csv', 'w+', encoding='utf-8') as outfile:
        df.to_csv(outfile, index=False)
        outfile.seek(0)
        next(outfile)
        cur.copy_from(outfile, 'gni_male', sep=',', null='')
    
    cur.close()

    
def create_gni_female_table(connection):
    cur = connection.cursor()
    
    df = pandas.read_excel('../Datasets/income-by-country/Income by Country.xlsx', sheet_name='Estimated GNI female', header=0, skipfooter=17)    # remove last column of dataframe (junk)
    # remove last column of dataframe (junk)
    df = df.iloc[:, :-1]

    print("Creating Estimated GNI female table...")
    # Remove the first header (Country)
    headers = df.columns.tolist()
    country_header = headers.pop(0)

    # Extract the years and convert them to integers
    years = [int(header) for header in headers]

    cur.execute(
        f"""CREATE TABLE IF NOT EXISTS gni_female (
            iso_code INTEGER PRIMARY KEY REFERENCES countries (iso_code),
            {', '.join([f'"{i}" numeric' for i in years])}
        )"""
    )

    # check if the tmp csv file exists and buy time
    if os.path.exists('../Datasets/income-by-country/gni_female_tmp.csv'):
        with open('../Datasets/income-by-country/gni_female_tmp.csv', 'w+', encoding='utf-8') as outfile:
            outfile.seek(0)
            next(outfile)
            cur.copy_from(outfile, 'gni_female', sep=',', null='')
        return


    df = prepare_df(df, cur)


    with open('../Datasets/income-by-country/gni_female_tmp.csv', 'w+', encoding='utf-8') as outfile:
        df.to_csv(outfile, index=False)
        outfile.seek(0)
        next(outfile)
        cur.copy_from(outfile, 'gni_female', sep=',', null='')
    
    cur.close()


def create_domestic_credits_table(connection):
    cur = connection.cursor()
    
    df = pandas.read_excel('../Datasets/income-by-country/Income by Country.xlsx', sheet_name='Domestic credits', header=0, skipfooter=17)  
    print("Creating Domestic credits table...")
    # Remove the first header (Country)
    headers = df.columns.tolist()
    country_header = headers.pop(0)

    # Extract the years and convert them to integers
    years = [int(header) for header in headers]

    cur.execute(
        f"""CREATE TABLE IF NOT EXISTS domestic_credits (
            iso_code INTEGER PRIMARY KEY REFERENCES countries (iso_code),
            {', '.join([f'"{i}" numeric' for i in years])}
        )"""
    )

    # check if the tmp csv file exists and buy time
    if os.path.exists('../Datasets/income-by-country/domestic_credits_tmp.csv'):
        with open('../Datasets/income-by-country/domestic_credits_tmp.csv', 'w+', encoding='utf-8') as outfile:
            outfile.seek(0)
            next(outfile)
            cur.copy_from(outfile, 'domestic_credits', sep=',', null='')
        return


    df = prepare_df(df, cur)


    with open('../Datasets/income-by-country/domestic_credits_tmp.csv', 'w+', encoding='utf-8') as outfile:
        df.to_csv(outfile, index=False)
        outfile.seek(0)
        next(outfile)
        cur.copy_from(outfile, 'domestic_credits', sep=',', null='')
    
    cur.close()



#~ A helper routine to prepare the dataframe for insertion to the database
def prepare_df(df, cur):
    # replace all '..' with None
    df.replace('..', None, inplace=True)


    # add a new column for the iso_code in the begining of the dataframe
    df.insert(0, 'iso_code', None)
    
    
    # iterate through each country and find its iso_code
    for index, country_name in df['Country'].items():
        iso_code = find_iso_code(country_name, cur)
        if iso_code:
            # print(f'Found iso_code {iso_code} for {country_name}')

            # add the iso_code to the dataframe
            df.at[index, 'iso_code'] = iso_code

        else:
            print(f'Could not find iso_code for {country_name}')

    # drop the country name column
    df.drop(columns=['Country'], inplace=True)

    return df
        

#~ A helper routine to find the iso_code for a given country name using the countries table ~#
def find_iso_code(country_name, cur):
    country_name = country_name.replace("'", "''") # escape single quotes

    # Special cases
    if (country_name == 'Congo (Democratic Republic of the)'): return 180
    if (country_name == 'Congo'): return 178
    if (country_name.endswith("Ivoire")): return 384
    if (country_name == 'Eswatini (Kingdom of)'): return 748
    if (country_name == 'Hong Kong; China (SAR)'): return 344
    if (country_name == 'Korea (Republic of)'): return 410
    if (country_name == 'Moldova (Republic of)'): return 498
    if (country_name == 'North Macedonia'): return 807
    if (country_name == 'Palestine; State of'): return 275
    if (country_name == 'Tanzania (United Republic of)'): return 834
    if (country_name == 'Guinea'): return 324
    if (country_name == 'Sudan'): return 729

    cur.execute(
        "SELECT iso_code FROM COUNTRIES WHERE official_name LIKE E'%%{}%%'".format(country_name)
    )
    if cur.rowcount != 0:
        correcponding_iso_code = cur.fetchone()[0]
        return correcponding_iso_code

    # print(f'Could not find {country_name} in display_name\nLooking in official_name')
    cur.execute(
        "SELECT iso_code FROM COUNTRIES WHERE display_name LIKE E'%%{}%%'".format(country_name)
    )
    if cur.rowcount != 0:
        correcponding_iso_code = cur.fetchone()[0]
        return correcponding_iso_code
    
    return None
        
    


# write the main function

def create_income_tables(connection):
    create_income_index_table(connection)
    create_labour_share_of_GDP_table(connection)
    create_gross_fixed_capital_formation_table(connection)
    create_gdp_total_table(connection)
    create_gdp_per_capita_table(connection)
    create_gni_per_capita_table(connection)
    create_gni_male_table(connection)
    create_gni_female_table(connection)
    create_domestic_credits_table(connection)
    
     