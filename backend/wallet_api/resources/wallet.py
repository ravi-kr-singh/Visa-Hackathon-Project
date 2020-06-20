from flask import request
from flask_restful import Resource

from schemas.wallet import WalletSchema
from models.wallet import WalletModel
from libs.strings import gettext

wallet_schema = WalletSchema()


class Wallet(Resource):
    @classmethod
    def get(cls, mobile_number: str):
        """
        Checks whether wallet exists or not. If it exists Then checks whether Kyc is done or not.
        :param mobile_number:
        :return:
        """
        wallet = WalletModel.find_by_mobile_number(mobile_number)
        if wallet is None:
            return {"message": gettext("WALLET_NOT_FOUND").format(mobile_number)}, 404

        if not wallet.kyc_status:
            return {"message": gettext("KYC_NOT_DONE").format(mobile_number)}, 404

        return wallet_schema.dump(wallet), 200


class WalletAmount(Resource):
    @classmethod
    def put(cls):
        """
        data is received in the form like {"mobile_number = "*****", "amount" = "***"}
        :return:
        """
        data = request.get_json()
        wallet = WalletModel.find_by_mobile_number(data["mobile_number"])
        if wallet is None:
            return {"message": gettext("WALLET_NOT_FOUND").format(data["mobile_number"])}, 404

        if not wallet.kyc_status:
            return {"message": gettext("KYC_NOT_DONE").format(data["mobile_number"])}, 404

        if wallet.amount < data["amount"]:
            return {"message": gettext("NOT_ENOUGH_BALANCE").format(data["mobile_number"])}, 404

        wallet.reduce_amount(data["amount"])
        try:
            wallet.save_to_db()
        except:
            return {"message": gettext("ERROR_IN_SENDING_MONEY")}, 500
        return {"message": gettext("PAYMENT_SUCCESSFUL")}, 200


class AddWallet(Resource):
    @classmethod
    def put(cls):
        """This endpoint is mainly for Testing purposes...
            data is received in the form like {"mobile_number = "*****", "amount" = 8090 , "kyc_status" : true/false}
        """
        data = request.get_json()
        wallet = WalletModel.find_by_mobile_number(data["mobile_number"])
        if wallet:
            wallet.add_amount(data["amount"])
        else:
            wallet = wallet_schema.load(data)
        try:
            wallet.save_to_db()
        except:
            return {"message": gettext("ERROR_IN_SAVING_WALLET").format(data["mobile_number"])}, 500
        return wallet_schema.dump(wallet), 201
