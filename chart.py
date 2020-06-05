import csv 
with open('test.csv', newline='') as csvfile:
    csvData = csv.reader(csvfile, delimiter=',', quotechar='|')
    dataArg = []
    dataVal = []
    headers = [];
    for row in csvData:
        if not(headers):
            headers.append(row[0])
            headers.append(row[2])
        else:
            dataArg.append(float(row[1]));
            dataVal.append(float(row[2]));
        
import matplotlib.pyplot as plt
plt.plot(dataVal,'o')
plt.xlabel(headers[0])
plt.ylabel(headers[1])
plt.show()
