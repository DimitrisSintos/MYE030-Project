#TABLE NAME: fertilities
#csv file: age_specific_fertility_rates.csv
import csv
import pandas as pd

def create_fetility_table(connection,countries_iso2_dict):
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
                            # print("row: ", row)
                            row.append(value[0])
                    serial_id += 1
                    outfile_df.loc[len(outfile_df)] = row

            outfile_df.to_csv(outfile, index=False)
            outfile.seek(0)
            next(outfile)
            cur.copy_from(outfile, 'fertilities', sep=',', null='')
    cur.close()
    print('Fertilities table created!')





def create_internanional_tables(connection, countries_iso2_dict):
    create_fetility_table(connection,countries_iso2_dict)


