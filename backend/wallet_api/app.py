from flask import Flask
from flask_restful import Api
from dotenv import load_dotenv


from db import db
from ma import ma
from resources.wallet import Wallet, WalletAmount, AddWallet

app = Flask(__name__)
load_dotenv(".env")
app.config.from_object("default_config")
app.config.from_envvar("APPLICATION_SETTINGS")
api = Api(app)


@app.before_first_request
def create_tables():
    db.create_all()


api.add_resource(Wallet, "/wallet/<string:mobile_number>")
api.add_resource(WalletAmount, "/wallet/getamount")
api.add_resource(AddWallet, "/addwallet")

if __name__ == "__main__":
    db.init_app(app)
    ma.init_app(app)
    app.run(port=5000, debug=True)


