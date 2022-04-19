#! /bin/bash
mongoimport --db chatbot --collection persona --authenticationDatabase admin  -u "root" -p "example" --type json --file /app/resources/persons.json --jsonArray
#mongo chatbot -authenticationDatabase "admin" -u "root" -p "example" --eval "db.intents.createIndex( { \"text\": \"text\"} )"
#mongo chatbot -authenticationDatabase "admin" -u "root" -p "example" --eval "db.vehicles.createIndex( { \"fields.make\": \"text\", \"fields.model\": \"text\", \"fields.vclass\": \"text\" } )"
