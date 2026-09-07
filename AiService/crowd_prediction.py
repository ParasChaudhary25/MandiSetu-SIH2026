import pandas as pd
from sklearn.linear_model import LinearRegression


# 1. Training data
data = {
    "day": [1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7],
    "hour": [10, 10, 10, 10, 10, 10, 10, 14, 14, 14, 14, 14, 14, 14],
    "previous_crowd": [40, 50, 45, 60, 70, 80, 75, 50, 65, 70, 80, 90, 100, 95],
    "bookings": [20, 25, 22, 30, 35, 40, 38, 25, 35, 40, 45, 50, 55, 52],
    "crowd": [45, 55, 50, 65, 75, 85, 80, 55, 70, 75, 85, 95, 105, 100]
}

df = pd.DataFrame(data)

print("Training Data:")
print(df)


# 2. Features used by the ML model
X = df[["day", "hour", "previous_crowd", "bookings"]]

# 3. Target — what we want to predict
y = df["crowd"]


# 4. Create the ML model
model = LinearRegression()


# 5. Train the model
model.fit(X, y)

print("\nModel trained successfully!")


# 6. Test prediction
new_data = pd.DataFrame({
    "day": [3],
    "hour": [14],
    "previous_crowd": [80],
    "bookings": [45]
})

prediction = model.predict(new_data)

print("\nPredicted Crowd:", round(prediction[0]))