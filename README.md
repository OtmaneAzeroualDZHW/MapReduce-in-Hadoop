# MapReduce in Hadoop
MapReduce is a programming model that has gained notoriety in recent years, primarily through its new discovery and use on Google. It is the predominant programming model in Hadoop. 
A complete MapReduce calculation receives a list of key and value pairs as input and also calculates a list of key and value pairs as the result: 
MapReduce (list (k1, v1))→list (k3, v3) MapReduce consists of two consecutive steps. 
The programmer has to specify these steps in the form of a map and a reduce function: 

Map (k1, v1)→list (k2, v2) 

Reduce (k2, list(v2))→list(k3, v3)
