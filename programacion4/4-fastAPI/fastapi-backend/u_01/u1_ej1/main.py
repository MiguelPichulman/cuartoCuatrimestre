from fastapi import FastAPI
app = FastAPI()
@app.get("/")
async def root():
    return{"message": "Hello World"}

###nuevo endpoint
@app.get("/ping")
async def ping():
    return{"status": "ok", "ts": "2026-10-08"}