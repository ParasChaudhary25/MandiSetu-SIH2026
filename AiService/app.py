from flask import Flask, request, jsonify
import pandas as pd
from sklearn.linear_model import LinearRegression

app = Flask(__name__)

# Training data
training_data = {
    "day": [1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7],
    "hour": [10, 10, 10, 10, 10, 10, 10, 14, 14, 14, 14, 14, 14, 14],
    "previous_crowd": [40, 50, 45, 60, 70, 80, 75, 50, 65, 70, 80, 90, 100, 95],
    "bookings": [20, 25, 22, 30, 35, 40, 38, 25, 35, 40, 45, 50, 55, 52],
    "crowd": [45, 55, 50, 65, 75, 85, 80, 55, 70, 75, 85, 95, 105, 100]
}

df = pd.DataFrame(training_data)

X = df[["day", "hour", "previous_crowd", "bookings"]]
y = df["crowd"]

model = LinearRegression()
model.fit(X, y)

# Demo live crowd data for different procurement centres
mandi_crowd_data = {
    "Rampur": 43,
    "Meerut": 85,
    "Bijnor": 12,
    "Moradabad": 60,
    "Amroha": 25
}


@app.route("/", methods=["GET"])
def home():
    return jsonify({
        "message": "MandiSetu AI Service is running"
    })


@app.route("/api/ai/grade", methods=["POST"])
def grade_crop():
    data = request.get_json(silent=True)

    if not data:
        return jsonify({"error": "JSON request body is required"}), 400

    moisture = data.get("moisture")
    foreign_matter = data.get("foreign_matter")
    damaged_grains = data.get("damaged_grains")

    if moisture is None or foreign_matter is None or damaged_grains is None:
        return jsonify({
            "error": "moisture, foreign_matter and damaged_grains are required"
        }), 400

    try:
        moisture = float(moisture)
        foreign_matter = float(foreign_matter)
        damaged_grains = float(damaged_grains)
    except (TypeError, ValueError):
        return jsonify({
            "error": "moisture, foreign_matter and damaged_grains must be numeric"
        }), 400

    # Temporary demo grading rules.
    # These are NOT official government procurement standards.
    if moisture < 14 and foreign_matter < 2 and damaged_grains < 3:
        grade = "A"
        deduction = 0
    elif moisture <= 17:
        grade = "B"
        deduction = 100
    else:
        grade = "C"
        deduction = 200

    return jsonify({
        "grade": grade,
        "deduction": deduction,
        "input": {
            "moisture": moisture,
            "foreign_matter": foreign_matter,
            "damaged_grains": damaged_grains
        }
    })


@app.route("/api/ai/predict", methods=["POST"])
def predict_crowd():
    data = request.get_json(silent=True)

    if not data:
        return jsonify({"error": "JSON request body is required"}), 400

    day = data.get("day")
    hour = data.get("hour")
    previous_crowd = data.get("previous_crowd")
    bookings = data.get("bookings")

    if day is None or hour is None or previous_crowd is None or bookings is None:
        return jsonify({
            "error": "day, hour, previous_crowd and bookings are required"
        }), 400

    try:
        day = float(day)
        hour = float(hour)
        previous_crowd = float(previous_crowd)
        bookings = float(bookings)
    except (TypeError, ValueError):
        return jsonify({
            "error": "day, hour, previous_crowd and bookings must be numeric"
        }), 400

    new_data = pd.DataFrame({
        "day": [day],
        "hour": [hour],
        "previous_crowd": [previous_crowd],
        "bookings": [bookings]
    })

    prediction = model.predict(new_data)
    predicted_crowd = max(0, round(float(prediction[0])))

    if predicted_crowd >= 80:
        crowd_level = "High"
        recommendation = "High crowd expected. Consider visiting later."
    elif predicted_crowd >= 50:
        crowd_level = "Medium"
        recommendation = "Moderate crowd expected."
    else:
        crowd_level = "Low"
        recommendation = "Low crowd expected. This is a good time to visit."

    return jsonify({
        "predicted_crowd": predicted_crowd,
        "crowd_level": crowd_level,
        "recommendation": recommendation
    })


@app.route("/api/crowd/live", methods=["GET"])
def live_crowd():
    current_mandi = request.args.get("mandi", "Rampur")
    current_crowd = mandi_crowd_data.get(current_mandi, 43)

    lowest_crowd_mandi = min(
        mandi_crowd_data,
        key=mandi_crowd_data.get
    )
    lowest_crowd_value = mandi_crowd_data[lowest_crowd_mandi]

    recommendation = (
        f"{current_mandi} me {current_crowd} farmers ki "
        f"expected/live bheed hai."
    )

    if current_crowd > 40 and lowest_crowd_mandi != current_mandi:
        recommendation += (
            f" Alternative centre {lowest_crowd_mandi} me "
            f"kam bheed hai ({lowest_crowd_value} farmers). "
            f"Aap wahan consider kar sakte hain."
        )

    return jsonify({
        "mandi": current_mandi,
        "live_bheed": current_crowd,
        "alternative_mandi": lowest_crowd_mandi,
        "alternative_crowd": lowest_crowd_value,
        "recommendation": recommendation
    })


if __name__ == "__main__":
    app.run(debug=True)
