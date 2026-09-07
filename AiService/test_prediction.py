import requests

url = "http://127.0.0.1:5000/api/ai/predict"

data = {
    "day": 6,
    "hour": 10,
    "previous_crowd": 100,
    "bookings": 50
}

response = requests.post(url, json=data)

print("Status Code:", response.status_code)
print("Response:")
print(response.json())