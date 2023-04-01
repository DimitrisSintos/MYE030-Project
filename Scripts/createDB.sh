#!/bin/bash

# Step 1: Start psql as the postgres user
sudo -u postgres psql <<EOF
DROP DATABASE IF EXISTS MYE030;
CREATE DATABASE MYE030;
CREATE USER myuser WITH PASSWORD 'mye030';
GRANT ALL PRIVILEGES ON DATABASE MYE030 TO myuser;
EOF
