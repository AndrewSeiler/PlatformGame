import json

level = "0"

airData = {"x": 0, "y": 0, "type": "AIR"}
groundData = {"x": 0, "y": 0, "type": "GROUND"}
goalData = {"x": 0, "y": 0, "type": "GOAL"}

playerData = {"x": 0, "y": 0, "type": "PLAYER", "ai": "PLAYER"}


contents = open("src/main/resources/levels/level" + level + ".txt", "r").read().split()
mapWidth = int(contents[0])
mapHeight = int(contents[1])
tileSize = int(contents[2])
data = {}
data["settings"] = {
    "mapWidth": mapWidth * tileSize,
    "mapHeight": mapHeight * tileSize,
    "totalTiles": mapWidth * mapHeight
}
contents = contents[3:]
data["tiles"] = []
for y in range(0, mapHeight):
    for x in range(0, mapWidth):
        num = int(contents[(y * mapWidth) + x])
        if num == 0:
            newAirData = airData.copy()
            newAirData["x"] = x * tileSize
            newAirData["y"] = y * tileSize
            data["tiles"].append(newAirData)
        elif num == 1:
            newGroundData = groundData.copy()
            newGroundData["x"] = x * tileSize
            newGroundData["y"] = y * tileSize
            data["tiles"].append(newGroundData)
        elif num == 2:
            newGoalData = goalData.copy()
            newGoalData["x"] = x * tileSize
            newGoalData["y"] = y * tileSize
            data["tiles"].append(newGoalData)
contents = contents[(mapHeight * mapWidth):]
totalEntities = 0
data["entities"] = []
for y in range(0, mapHeight):
    for x in range(0, mapWidth):
        num = int(contents[(y * mapWidth) + x])
        if num == 1:
            newPlayerData = playerData.copy()
            newPlayerData["x"] = x * tileSize
            newPlayerData["y"] = y * tileSize
            data["entities"].append(newPlayerData)
            totalEntities += 1
data["settings"]["totalEntities"] = totalEntities;
out = json.dumps(data, indent=4)
outFile = open("src/main/resources/levels/level" + level + ".json", "w+")
outFile.write(out);