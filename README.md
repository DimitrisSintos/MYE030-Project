# MYE030-Project

## Contributors

[Dimitrios Sintos](https://github.com/DimitrisSintos)
[Athanasios Kaliviotis](https://github.com/thankal) 
[Dimitrios Giannitsakis](https://github.com/dimgiann)

## How to set up the project

- Install Postgres and pgAdmin.

  Postgres:
  `sudo apt install postgresql postgresql-client`

  PgAdmin:
  `curl -fsSL https://www.pgadmin.org/static/packages_pgadmin_org.pub | sudo gpg --dearmor -o /etc/apt/trusted.gpg.d/pgadmin.gpg`

`sudo sh -c 'echo "deb https://ftp.postgresql.org/pub/pgadmin/pgadmin4/apt/$(lsb_release -cs) pgadmin4 main" > /etc/apt/sources.list.d/pgadmin4.list'`

`sudo apt update`
`sudo apt install pgadmin4`

- Install Python requirements:
`pip3 install -r requirements.pip`

- Create the database:
`cd Scripts
sudo ./createDB.sh
python3 csvToPostgres.py`

- In gqAdmin query tool, run the following queries:
update five_year_age_groups_population
set field = field || '_' || ranges;

alter table public.population_by_age_sex
add column field varchar;

update population_by_age_sex
set field = sex || '_' || age;

select pg_size_pretty(pg_relation_size('five_year_age_groups_population'));

vacuum full five_year_age_groups_population;

select pg_size_pretty(pg_relation_size('population_by_age_sex'));

vacuum full population_by_age_sex;

- Install Java:
`sudo apt-get install openjdk-8-jdk -y`

In Visual Studio Code, install "Extension Pack for Java".

## How to run:
In Visual Studio, select `CountriesApplication.java` and click the play button. The app will launch at http://localhost:8080/.
