# MapReduce in Hadoop
MapReduce is a programming model that has gained notoriety in recent years, primarily through its new discovery and use on Google. It is the predominant programming model in Hadoop. 
A complete MapReduce calculation receives a list of key and value pairs as input and also calculates a list of key and value pairs as the result: 
MapReduce (list (k1, v1))→list (k3, v3) MapReduce consists of two consecutive steps. 
The programmer has to specify these steps in the form of a map and a reduce function: 

Map (k1, v1)→list (k2, v2) 

Reduce (k2, list(v2))→list(k3, v3)

The map function receives one of the key-value pairs of the input (k1, v1) as input parameters. It calculates an intermediate result, again consisting of a list of keys and associated values list (k2, v2). The execution environment sorts and groups the outputs of all map calls based on their keys. 
The reduce function receives a pair of keys and a list of the outputs of all map calls with this key (k2, list (v2)) as input parameters. From this, it calculates part of the final results of the entire calculation list (k3, v3), which are finally put together by the execution environment. 
It is crucial that the map and reduce functions each receive part of the input or the intermediate results as parameters and that these partial calculations are each independent of one another. Only then can they be implemented in a distributed manner. 

In Hadoop, a MapReduce calculation is called a job. Each job has to be assigned a class that implements the generic mapper interface from the org.apache.hadoop.mapred package - a mapper. In addition, in most cases it makes sense to create a reducer class that implements the generic reducer interface accordingly. If no reducer is configured, the results of the mapper are interpreted and output as final results. This can also be useful if the partial map results do not have to be further summarized or post-processed. The source code 1 for configuring and starting a job in Hadoop shows the configuration of a job in Hadoop. The configuring class (class WordCount) is called the driver in the context of Hadoop. 

