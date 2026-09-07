import requests

url = "http://127.0.0.1:5000/api/ai/grade"

data = {
    "moisture": 14.5,
    "foreign_matter": 1.2,
    "damaged_grains": 2.0
}

response = requests.post(url, json=data)

print("Status Code:", response.status_code)
print("Response:")
print(response.json())