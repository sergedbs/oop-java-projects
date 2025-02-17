import json
import random
import os
import time
import shutil

# Constants, change something here, the whole program changes:)
OUTPUT_DIR = "../queue"
NR_OF_GENERATIONS = 30  # TODO change back to 30
MIN_WAIT = 500  # in ms
MAX_WAIT = 3000  # in ms
SECOND = 1000.0

# Car properties TODO maybe add more in the future year
# If there's a change, change stats also
CAR_TYPES = ["ELECTRIC", "GAS"]
PASSENGER_TYPES = ["PEOPLE", "ROBOTS"]
IS_DINING = [True, False]
CONSUMPTION_RANGE = (10, 50)

STATS = {
    "ELECTRIC": 0,
    "GAS": 0,
    "PEOPLE": 0,
    "ROBOTS": 0,
    "DINING": 0,
    "NOT_DINING": 0,
    "CONSUMPTION": {"ELECTRIC": 0, "GAS": 0}
}

# Sort of program main
if __name__ == "__main__":

    if os.path.exists(OUTPUT_DIR):
        shutil.rmtree(OUTPUT_DIR)
    os.makedirs(OUTPUT_DIR, exist_ok=True)

    for i in range(1, NR_OF_GENERATIONS + 1):
        cartype = random.choice(CAR_TYPES)
        passengers = random.choice(PASSENGER_TYPES)
        isDining = random.choice(IS_DINING)
        consumption = random.randint(*CONSUMPTION_RANGE)

        # update stats
        STATS[cartype] += 1

        STATS[passengers] += 1

        if isDining:
            STATS["DINING"] += 1
        else:
            STATS["NOT_DINING"] += 1

        STATS["CONSUMPTION"][cartype] += consumption

        car = {
            "id": i,
            "type": cartype,
            "passengers": passengers,
            "isDining": isDining,
            "consumption": consumption,
        }

        file_path = os.path.join(OUTPUT_DIR, f"Car{i}.json")
        with open(file_path, "w") as f:
            json.dump(car, f)

        print(json.dumps(car))
        sleep_time = random.randint(MIN_WAIT, MAX_WAIT) / SECOND
        time.sleep(sleep_time)

    print(json.dumps(STATS))
