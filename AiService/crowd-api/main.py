from flask_cors import CORS
# ...
app = Flask(__name__)
CORS(app) # ye line add kar - isse team ka frontend tera API dekh payega

# last line aise honi chahiye
app.run(host='0.0.0.0', port=5000)
from flask import Flask, jsonify, request

app = Flask(__name__)

mandi_data = {
    "Rampur": {"in": 132, "out": 89},
    "Ghaziabad": {"in": 95, "out": 60}
}

@app.route('/')
def home():
    return "MandiSetu API is Live! Use /api/crowd/live?mandi=Rampur"

@app.route('/api/crowd/live')
def live():
    mandi = request.args.get('mandi', 'Rampur')
    data = mandi_data.get(mandi, mandi_data["Rampur"])
    live_count = data["in"] - data["out"]
    return jsonify({
        "mandi": mandi,
        "live_bheed": live_count,
        "avg_wait_min": live_count * 2,
        "message": f"{mandi} me abhi {live_count} kisan hain"
    })

app.run(host='0.0.0.0', port=5000)
