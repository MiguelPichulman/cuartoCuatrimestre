from fastapi import FastAPI

app = FastAPI()

fake_items_db = [
    {"item_name": "Momitor"},
    {"item_name": "Teclado"},
    {"item_name": "Mouse"},
]

# http:127.0.0.1:8000/items/?skip=0&limit=10
@app.get("/items/")
async def read_item(skip: int = 0, limit: int = 10):
    return fake_items_db[skip : skip + limit]

## parametros opcionales
@app.get("/items/{item_id}")
async def read_item(item_id: str, q:str | None = None):
    if q:
        return {"item_id": item_id, "q": q}
    return{"item_id":item_id}