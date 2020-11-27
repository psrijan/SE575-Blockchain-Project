#! /bin/bash
cp ./data-db-full.json ./data-db.json
json-server --watch ./data-db.json