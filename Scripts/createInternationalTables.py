
import csv
import pandas as pd
import os

def create_fetility_table(connection,countries_iso2_dict):
    #TABLE NAME: fertilities
    #csv file: age_specific_fertility_rates.csv

    with open('../Datasets/international-data/age_specific_fertility_rates.csv', 'r') as infile:
        infile_df = pd.read_csv(infile)
        min_year = infile_df['year'].min()
        max_year = infile_df['year'].max()

        cur = connection.cursor()
        print('Creating fertilities table...')
        cur.execute(
            f"""CREATE TABLE IF NOT EXISTS fertilities (
                id serial,
                iso_code integer,
                range varchar(50),
                {', '.join([f'"{i}" numeric' for i in range(min_year, max_year+1)])},
                PRIMARY KEY(id),
                FOREIGN KEY(iso_code)
                REFERENCES countries(iso_code)
                )"""
        )

        if os.path.exists('../Datasets/international-data/age_specific_fertility_rates_updated.csv'):
            with open('../Datasets/international-data/age_specific_fertility_rates_updated.csv', 'r') as infile:
                next(infile) # skip header row
                cur.copy_from(infile, 'fertilities', sep=',', null='')
                cur.close()
                print('Fertilities table created!')
            return

        country_code = infile_df["country_code"].unique()
        ranges = infile_df.columns[3:]
        serial_id = 1

        outfile_df = pd.DataFrame(columns=['serial_id','iso_code', 'range', *range(min_year, max_year+1)])
        with open('../Datasets/international-data/age_specific_fertility_rates_updated.csv', 'w+') as outfile:
            for code in country_code:
                if code not in countries_iso2_dict:
                    continue
                for rng in ranges:
                    row = [serial_id, countries_iso2_dict[code], rng]
                    for year in range(min_year, max_year+1):
                        value = infile_df[(infile_df['country_code'] == code) & (infile_df['year'] == year)][rng].values
                        if len(value) == 0:
                            row.append('')
                        else:
                            row.append(value[0])
                    serial_id += 1
                    outfile_df.loc[len(outfile_df)] = row

            outfile_df.to_csv(outfile, index=False)
            outfile.seek(0)
            next(outfile)
            cur.copy_from(outfile, 'fertilities', sep=',', null='')
    cur.close()
    print('Fertilities table created!')

def create_vital_statistics_table(connection, countries_iso2_dict):
    #TABLE NAME: vital_statistics
    #csv file: birth_death_growth_rates.csv
    with open('../Datasets/international-data/birth_death_growth_rates.csv', 'r') as infile:
        infile_df = pd.read_csv(infile)
        min_year = infile_df['year'].min()
        max_year = infile_df['year'].max()

        cur = connection.cursor()
        print('Creating vital_statistics table...')
        cur.execute(
            f"""CREATE TABLE IF NOT EXISTS vital_statistics (
                id serial,
                iso_code integer,
                field varchar(50),
                {', '.join([f'"{i}" numeric' for i in range(min_year, max_year+1)])},
                PRIMARY KEY(id),
                FOREIGN KEY(iso_code)
                REFERENCES countries(iso_code)
                )"""
        )

        if os.path.exists('../Datasets/international-data/birth_death_growth_rates_updated.csv'):
            with open('../Datasets/international-data/birth_death_growth_rates_updated.csv', 'r') as infile:
                next(infile) # skip header row
                cur.copy_from(infile, 'vital_statistics', sep=',', null='')
                cur.close()
                print('Vital statistics table created!')
            return

        country_code = infile_df["country_code"].unique()
        fields = infile_df.columns[3:]
        serial_id = 1

        outfile_df = pd.DataFrame(columns=['serial_id','iso_code', 'range', *range(min_year, max_year+1)])
        with open('../Datasets/international-data/birth_death_growth_rates_updated.csv', 'w+') as outfile:
            for code in country_code:
                if code not in countries_iso2_dict:
                    continue
                for field in fields:
                    row = [serial_id, countries_iso2_dict[code], field]
                    for year in range(min_year, max_year+1):
                        value = infile_df[(infile_df['country_code'] == code) & (infile_df['year'] == year)][field].values
                        if len(value) == 0:
                            row.append('')
                        else:
                            row.append(value[0])
                    serial_id += 1
                    outfile_df.loc[len(outfile_df)] = row

            outfile_df.to_csv(outfile, index=False)
            outfile.seek(0)
            next(outfile)
            cur.copy_from(outfile, 'vital_statistics', sep=',', null='')
    cur.close()
    print('Vital statistics table created!')


def create_mortality_life_expectancy_statistics_table(connection, countries_iso2_dict):
    #TABLE NAME: mortality_life_expectancy_statistics
    #csv file: mortality_life_expectancy.csv
    with open('../Datasets/international-data/mortality_life_expectancy.csv', 'r') as infile:
        infile_df = pd.read_csv(infile)
        min_year = infile_df['year'].min()
        max_year = infile_df['year'].max()

        cur = connection.cursor()
        print('Creating mortality_life_expectancy_statistics table...')
        cur.execute(
            f"""CREATE TABLE IF NOT EXISTS mortality_life_expectancy_statistics (
                id serial,
                iso_code integer,
                field varchar(50),
                {', '.join([f'"{i}" numeric' for i in range(min_year, max_year+1)])},
                PRIMARY KEY(id),
                FOREIGN KEY(iso_code)
                REFERENCES countries(iso_code)
                )"""
        )

        if os.path.exists('../Datasets/international-data/mortality_life_expectancy_updated.csv'):
            with open('../Datasets/international-data/mortality_life_expectancy_updated.csv', 'r') as infile:
                next(infile) # skip header row
                cur.copy_from(infile, 'mortality_life_expectancy_statistics', sep=',', null='')
                cur.close()
                print('Mortality life expectancy statistics table created!')
            return

        country_code = infile_df["country_code"].unique()
        fields = infile_df.columns[3:]
        serial_id = 1

        outfile_df = pd.DataFrame(columns=['serial_id','iso_code', 'range', *range(min_year, max_year+1)])
        with open('../Datasets/international-data/mortality_life_expectancy_updated.csv', 'w+') as outfile:
            for code in country_code:
                if code not in countries_iso2_dict:
                    continue
                for field in fields:
                    row = [serial_id, countries_iso2_dict[code], field]
                    for year in range(min_year, max_year+1):
                        value = infile_df[(infile_df['country_code'] == code) & (infile_df['year'] == year)][field].values
                        if len(value) == 0:
                            row.append('')
                        else:
                            row.append(value[0])
                    serial_id += 1
                    outfile_df.loc[len(outfile_df)] = row

            outfile_df.to_csv(outfile, index=False)
            outfile.seek(0)
            next(outfile)
            cur.copy_from(outfile, 'mortality_life_expectancy_statistics', sep=',', null='')
    cur.close()
    print('Mortality life expectancy statistics table created!')


def create_five_year_age_groups_population_table(connection, countries_iso2_dict):
    #TABLE NAME: five_year_age_groups_population
    #csv file: midyear_population_5yr_age_sex.csv
    with open('../Datasets/international-data/midyear_population_5yr_age_sex.csv', 'r') as infile:
        infile_df = pd.read_csv(infile)       
        rows = []
        for index, row in infile_df.iterrows():
            rng = None
            if row["total_flag"]=="*":
                rng = "total"
            else:
                if row["age_group_indicator"]=="+":
                    rng = f"{row['ending_age']}_{row['starting_age']}"
                else:
                    rng = f"{row['starting_age']}_{row['ending_age']}"
            rows.append((row["country_code"],row['country_name'], row['year'], rng, row["midyear_population"], row["midyear_population_male"], row["midyear_population_female"]))
        updated_infile_df = pd.DataFrame.from_records(rows, columns=["country_code","country_name","year","ranges","midyear_population","midyear_population_male","midyear_population_female"])
        min_year = updated_infile_df['year'].min()
        max_year = updated_infile_df['year'].max()

        cur = connection.cursor()
        print('Creating five_year_age_groups_population table...')
        cur.execute(
            f"""CREATE TABLE IF NOT EXISTS five_year_age_groups_population (
                id serial,
                iso_code integer,
                ranges varchar(50),
                field varchar(100),
                {', '.join([f'"{i}" numeric' for i in range(min_year, max_year+1)])},
                PRIMARY KEY(id),
                FOREIGN KEY(iso_code)
                REFERENCES countries(iso_code)
                )"""
        )

        
        if os.path.exists('../Datasets/international-data/midyear_population_5yr_age_sex_updated.csv'):
            with open('../Datasets/international-data/midyear_population_5yr_age_sex_updated.csv', 'r') as infile:
                next(infile) # skip header row
                cur.copy_from(infile, 'five_year_age_groups_population', sep=',', null='')
                cur.close()
                print('Five year age groups population table created!')
            return
    

        country_code = updated_infile_df["country_code"].unique()
        
        countries_dfs = {}

        for code in country_code:
            if code not in countries_iso2_dict:
                continue
            countries_dfs[code] = updated_infile_df[updated_infile_df["country_code"]==code]

        ranges = updated_infile_df["ranges"].unique()
        fields = updated_infile_df.columns[4:]
        serial_id = 1

        outfile_df = pd.DataFrame(columns=['serial_id','iso_code', 'range','field', *range(min_year, max_year+1)])
        with open('../Datasets/international-data/midyear_population_5yr_age_sex_updated.csv', 'w+') as outfile:
            total_work = len(countries_dfs)
            for coutry_code, country_df in countries_dfs.items():
                print(f"Processing {coutry_code}... {total_work} countries left")
                for field in fields:
                    for rng in ranges:
                        row = [serial_id, countries_iso2_dict[coutry_code], rng, field]
                        for year in range(min_year, max_year+1):
                            value = country_df[(country_df['year'] == year) & (country_df['ranges'] == rng)][field].values
                            if len(value) == 0:
                                row.append('')
                            else:
                                row.append(value[0])
                        serial_id += 1
                        outfile_df.loc[len(outfile_df)] = row
                total_work -= 1
                        
            outfile_df.to_csv(outfile, index=False)
            outfile.seek(0)
            next(outfile)
            cur.copy_from(outfile, 'five_year_age_groups_population', sep=',', null='')
    cur.close()
    print('Five year age groups population table created!')

def create_population_by_age_sex_table(connection, countries_iso2_dict):
    #TABLE NAME: population_by_age_sex
    #csv file: midyear_population_age_sex.csv

    with open('../Datasets/international-data/midyear_population_age_sex.csv', 'r') as infile:
        infile_df = pd.read_csv(infile) 
        min_year = infile_df['year'].min()
        max_year = infile_df['year'].max()

        cur = connection.cursor()
        
        print("Creating population_by_age_sex table...")
        cur.execute(
            f"""
                CREATE TABLE IF NOT EXISTS population_by_age_sex (
                id serial,
                iso_code integer,
                sex varchar(50),
                age varchar(50),
                {', '.join([f'"{i}" numeric' for i in range(min_year, max_year+1)])},
                PRIMARY KEY(id),
                FOREIGN KEY(iso_code)
                REFERENCES countries(iso_code)
                )
            """
        )

        if os.path.exists('../Datasets/international-data/midyear_population_age_sex_updated.csv'):
            with open('../Datasets/international-data/midyear_population_age_sex_updated.csv', 'r') as infile:
                next(infile) # skip header row
                cur.copy_from(infile, 'population_by_age_sex', sep=',', null='')
                cur.close()
                print("Population by age sex table craeted!")
            return

        country_code = infile_df["country_code"].unique()
        countries_dfs = {}

        for code in country_code:
            if code not in countries_iso2_dict:
                continue
            countries_dfs[code] = infile_df[infile_df["country_code"]==code]

        ages = infile_df["age"].unique()
        genders = infile_df["sex"].unique()
        ages = infile_df.columns[5:]
        print("Ages: ", ages)

        serial_id = 1

        outfile_df = pd.DataFrame(columns=['serial_id','iso_code','sex', 'age', *range(min_year, max_year+1)])

        with open('../Datasets/international-data/midyear_population_age_sex_updated.csv', 'w+') as outfile:
            total_work = len(countries_dfs)
            for coutry_code, country_df in countries_dfs.items():
                print(f"Processing {coutry_code}... {total_work} countries left")
                for sex in genders:
                    for age in ages:
                        row = [serial_id, countries_iso2_dict[coutry_code], sex, age]
                        for year in range(min_year, max_year+1):
                            value = country_df[(country_df['year'] == year) & (country_df['sex'] == sex)][age].values
                            if len(value) == 0:
                                row.append('')
                            else:
                                row.append(value[0])
                        serial_id += 1
                        outfile_df.loc[len(outfile_df)] = row
                total_work -= 1
            outfile_df.to_csv(outfile, index=False)
            outfile.seek(0)
            next(outfile)
            cur.copy_from(outfile, 'population_by_age_sex', sep=',', null='')
    cur.close()
    print("Population by age sex table created!")

def create_internanional_tables(connection, countries_iso2_dict):
    create_fetility_table(connection,countries_iso2_dict)
    create_vital_statistics_table(connection, countries_iso2_dict)
    create_mortality_life_expectancy_statistics_table(connection, countries_iso2_dict)
    create_five_year_age_groups_population_table(connection, countries_iso2_dict)
    create_population_by_age_sex_table(connection, countries_iso2_dict)

