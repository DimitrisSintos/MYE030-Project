# [MYE030-Project-2023](https://www.cse.uoi.gr/~pvassil/courses/db_III/info.html)

This project is a Spring Boot application that uses a PostgreSQL database. Users can generate dynamic line charts, bar charts, and scatter plots utilizing geopolitical
data.

## Contributors

- [Dimitrios Sintos](https://github.com/DimitrisSintos)
- [Athanasios Kaliviotis](https://github.com/thankal)
- [Dimitrios Giannitsakis](https://github.com/dimgiann)

## Project Setup

This application was developed in Debian based Linux OS.

1. **Install Postgres and pgAdmin:**

   - Postgres: `sudo apt install postgresql postgresql-client`
   - Database Management Tool: Install either pgAdmin or DBeaver.

2. **Create Python Virtual Environment:**

   ```bash
   sudo apt install python3.10-venv
   python3 -m venv .venv --prompt mye030-project
   source .venv/bin/activate
    ```

3. **Install Python Requirements:**

   ```bash
   pip3 install -r requirements.pip
   ```

4. **Create Database:**

- Extract Datasets.zip
- Run the following commands in the terminal:

    ```bash
    cd Scripts
    sudo ./createDB.sh
    python3 csvToPostgres.py
    ```

5. **MUST Run this Queries:**

- In pgAdmin query tool, execute:

    ```sql
        update five_year_age_groups_population
        set field = field || '_' || ranges;

        alter table public.population_by_age_sex
        add column field varchar;

        update population_by_age_sex

        set field = sex || '_' || age;

        vacuum full five_year_age_groups_population;

        vacuum full population_by_age_sex;
    ```

6. **Install Java :**

    ```bash
        sudo apt-get install openjdk-8-jdk -y
    ```

    - In Visual Studio Code, install "Extension Pack for Java".

## How to RUN

1. In Visual Studio Code, select CountriesApplication.java.
2. Click the play button to launch the app at http://localhost:8080/.
