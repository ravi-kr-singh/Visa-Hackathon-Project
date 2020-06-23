import requests

port = 'http://127.0.0.1:5000/'
url_details = "wallet/"
url_get_amount = "wallet/getamount"
headers = {'Accept': 'application/json', 'Content-Type': 'application/json'}
timeout = 10
INTERNAL_SERVER_ERROR = 'INTERNAL SERVER ERROR'


class Wallet:

    def authorize(self, mobile_number: str):

        url = port + url_details + mobile_number
        try:
            response_get = requests.get(url, headers=headers, timeout=timeout)
        except:
            return None

        # print(response_get.json())
        return response_get

    def get_amount(self, mobile_number: str, amount: int):
        url = port+url_get_amount
        payload = {
            "mobile_number": mobile_number,
            "amount": amount
        }
        try:
            response_amount = requests.put(url, json=payload, headers=headers, timeout=timeout)

        except:
            return None
        # print(response_amount.text,response_amount.status_code)
        return response_amount
