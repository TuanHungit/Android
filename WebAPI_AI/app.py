# -*- coding: utf-8 -*-
"""
Created on Thu Jun 25 18:35:32 2020

@author: Admin
"""
import pandas as pd
from flask import Flask, jsonify

app = Flask(__name__)
app.config["DEBUG"] = True


@app.route('/', methods=['GET'])
def home():
    return "Hello Tuan Hung"

@app.route('/iot', methods=['GET'])
def getNextFromCurrent():
    data = pd.read_json('https://cambiennhietdodoam.firebaseio.com/DuLieu.json', orient = 'index')
    current_temp = data['temperature'].tolist()[-1]
    current_humid =data['humidity'].tolist()[-1]
    next_temp = cal(current_temp,current_humid);
    next_temp = round(next_temp, 2);
    location = "Cho Dau Moi,  Tam Binh, Thu Duc, Ho Chi Minh"
    return jsonify({'data':{'current_Tepm':current_temp,'current_Humid':current_humid,
                            'localtion':location,'next_Temp':next_temp}})

def cal(temperature,humid):
    next_temp = temperature*-0.2935911 + humid*0.03841237  + 33.345215133255536
    return next_temp;
@app.route('/iot/<float:temperature>/<float:humid>/', methods=['GET'])
def getNext(temperature,humid):
    next_temp = cal(temperature,humid);
    next_temp = round(next_temp, 2)
    return jsonify({'data':{'next_Temp':next_temp}})
if __name__ =="__main__":
    app.run()