import psycopg2
import json

# Connect to the PostgreSQL database
conn = psycopg2.connect(
    dbname="verceldb",
    user="default",
    password="Cw2GdvWlNBF4",
    host="ep-proud-darkness-62978950-pooler.ap-southeast-1.postgres.vercel-storage.com",
)

# Open a cursor to perform database operations
cursor = conn.cursor()

# Load JSON data from file
with open("wards.json", "r") as file:
    wards = json.load(file)
with open("provinces.json", "r") as file:
    provinces = json.load(file)
with open("districts.json", "r") as file:
    districts = json.load(file)

# Iterate through the data and insert into the PostgreSQL table
for item in provinces:
    _item = {
        "id": item["id"],
        "name": item["name"],
        "type": item["type"],
        "isActive": "True" if item["status"] == 1 else "False",
    }
    print(_item)
    cursor.execute(
        """
        INSERT INTO province (id, name, type, is_active)
        VALUES (%s, %s, %s, %s);
    """,
        (_item["id"], _item["name"], _item["type"], _item["isActive"]),
    )

for item in districts:
    _item = {
        "id": item["id"],
        "name": item["name"],
        "province_id": item["provinceId"],
        "type": item["type"],
        "isActive": "True" if item["status"] == 1 else "False",
    }
    print(_item)
    cursor.execute(
        """
        INSERT INTO district (id, name, province_id, type, is_active)
        VALUES (%s, %s, %s, %s, %s);
    """,
        (
            _item["id"],
            _item["name"],
            _item["province_id"],
            _item["type"],
            _item["isActive"],
        ),
    )

for item in wards:
    _item = {
        "id": item["id"],
        "name": item["name"],
        "province_id": item["provinceId"],
        "district_id": item["districtId"],
        "type": item["type"],
        "isActive": "True" if item["status"] == 1 else "False",
    }
    print(_item)
    cursor.execute(
        """
        INSERT INTO ward (id, name, province_id, district_id, type, is_active)
        VALUES (%s, %s, %s, %s, %s, %s);
    """,
        (
            _item["id"],
            _item["name"],
            _item["province_id"],
            _item["district_id"],
            _item["type"],
            _item["isActive"],
        ),
    )

# Commit the transaction
conn.commit()

# # Close communication with the database
cursor.close()
conn.close()
