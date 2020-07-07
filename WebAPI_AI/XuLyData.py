# -*- coding: utf-8 -*-
"""
Created on Fri Jul  3 13:02:29 2020

@author: Admin
"""

from datetime import timedelta
from datetime import datetime
import pandas as pd
import matplotlib.pyplot as plt
from sklearn import linear_model
import statsmodels.api as sm

data= pd.read_json('https://cambiennhietdodoam.firebaseio.com/DuLieu.json', orient = 'index')
df_model = pd.DataFrame(data)
df_model.reset_index( inplace = True)
df_model.rename(columns = {'index':'time'} , inplace = True)
df_model.dropna(how='all', inplace =True)
df_model['humidity'] = pd.to_numeric(df_model['humidity'],errors='coerce')
df_model['temperature'] = pd.to_numeric(df_model['temperature'],errors='coerce')
df_model['humidity'].fillna(df_model['humidity'].mean(), inplace=True)
df_model['temperature'].fillna(df_model['temperature'].mean(), inplace=True)

print(df_model)


def TrainModel(time, df):
    list_x1 = []
    list_x2 = []
    list_y = []
    df_train = pd.DataFrame(columns=["x1","x2","y"])
    delta = timedelta(seconds = time)
    for i in range (len(df)- int(time/8)):
        date1 = datetime.strptime( df['time'][i], '%Y-%m-%d*%H:%M:%S')
        date2 = date1 + delta
        for j in range (len(df)-2):
            if date2 in  df['time'].values:
                list_x1.append(float(df['temperature'][i]))
                list_x2.append(float(df['humidity'][i]))
                list_y.append(float(df[df['time'] == date2].temp))
            else:
                if((date2 > (datetime.strptime(df['time'][j] , '%Y-%m-%d*%H:%M:%S'))) and 
                   (date2 < (datetime.strptime(df['time'][j+1] , '%Y-%m-%d*%H:%M:%S')))):
                    temp = float(df['temperature'][j] + df['temperature'][j+1] )/2
                    print(date2,datetime.strptime(df['time'][j] , '%Y-%m-%d*%H:%M:%S'),datetime.strptime(df['time'][j+1] , '%Y-%m-%d*%H:%M:%S'))
                    print(df['temperature'][j],df['temperature'][j+1],temp)
                    list_x1.append(float(df['temperature'][i]))
                    list_x2.append(float(df['humidity'][i]))
                    list_y.append(float(temp))
                    break
    df_train['x1']= list_x1[:]
    df_train['x2']= list_x2[:]
    df_train['y']= list_y[:]
    return df_train

#df_model_trained = TrainModel(3600,df_model[:])

#df_model_trained.to_csv(r'F:\N3\II\Cloud\Group\api\model.csv', index = False )
df_model_trained = pd.read_csv(r'F:\N3\II\Cloud\Group\api\model.csv')

df_model_trained.plot.scatter(x='x1', y='y', c='DarkBlue')

size_train= int(len(df_model_trained)*0.8)
# du lieu de train 80%
df_train = df_model_trained[0: size_train]
#du lieu de test 20%
df_test = df_model_trained[size_train:]
#dfmodeltrain.to_csv(r'F:\N3\II\Cloud\Group\api\model.csv', index = False )

X = df_train [['x1','x2']] # here we have 2 variables for multiple regression. If you just want to use one variable for simple linear regression, then use X = df['Interest_Rate'] for example.Alternatively, you may add additional variables within the brackets
Y = df_train['y']

regr = linear_model.LinearRegression()
regr.fit(X, Y)
print('Intercept: \n', regr.intercept_)
print('Coefficients: \n', regr.coef_)


a = regr.coef_[0]
b = regr.coef_[1]
c = regr.intercept_



def cal(x, y): 
    return regr.coef_[0] * x +  regr.coef_[1] *y + regr.intercept_

print(cal(31,85))
plt.scatter(df_test['x1'], df_test['y'])
plt.plot(df_test['x1'], cal(df_test['x1'],df_test['x2']))


