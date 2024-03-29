# MapReduce in Hadoop
MapReduce is a programming model that has gained notoriety in recent years, primarily through its new discovery and use on Google. It is the predominant programming model in Hadoop. 
A complete MapReduce calculation receives a list of key and value pairs as input and also calculates a list of key and value pairs as the result: 
MapReduce (list (k1, v1)) → list (k3, v3) MapReduce consists of two consecutive steps. 
The programmer has to specify these steps in the form of a map and a reduce function: 

Map (k1, v1) → list (k2, v2) 

Reduce (k2, list(v2)) → list(k3, v3)

The map function receives one of the key-value pairs of the input (k1, v1) as input parameters. It calculates an intermediate result, again consisting of a list of keys and associated values list (k2, v2). The execution environment sorts and groups the outputs of all map calls based on their keys. 
The reduce function receives a pair of keys and a list of the outputs of all map calls with this key (k2, list (v2)) as input parameters. From this, it calculates part of the final results of the entire calculation list (k3, v3), which are finally put together by the execution environment. 
It is crucial that the map and reduce functions each receive part of the input or the intermediate results as parameters and that these partial calculations are each independent of one another. Only then can they be implemented in a distributed manner. 

In Hadoop, a MapReduce calculation is called a job. Each job has to be assigned a class that implements the generic mapper interface from the org.apache.hadoop.mapred package - a mapper. In addition, in most cases it makes sense to create a reducer class that implements the generic reducer interface accordingly. If no reducer is configured, the results of the mapper are interpreted and output as final results. This can also be useful if the partial map results do not have to be further summarized or post-processed. 

The source code 1 for configuring and starting a job in Hadoop shows the configuration of a job in Hadoop. The configuring class (class WordCount) is called the driver in the context of Hadoop. 

First, this configures the input. Since text documents are to be viewed, the Hadoop TextInput format is used. It is one of many input formats supplied. However, it is also possible to implement your own formats: TextInputFormat reads text documents line by line and generates input pairs (such as (k1, v1)) consisting of the position in the document and the line content. Then the output format is determined. The simple TextOutputFormat writes a line with text representations of key and value in a text file for each output pair (k3, v3). Since words are to be counted, the output key is set to text as the type and IntWritable as the value. The input path is usually a directory with many large files. For example, the content of the Wikipedia article can be used for WordCount. After execution, the output path will contain a text file for each reducer. Text and IntWritable are wrapper classes for integers and strings that implement the writable interface from org.apache.hadoop.io. The Writable Interface is one of Hadoops' ways to serialize objects in order to store them in files or to send them over the network. In Hadoop, all types that do this must implement Writable. The framework contains writables for a large number of types, but your own types can also implement it and are then automatically transferred by Hadoop between the compute nodes or stored in HDFS. 

At the end of the configuration, the mapper and reducer classes are set. 

Code 2 shows the implementation of a mapper for the WordCount problem. The generic mapper interface is parameterized by four types. The first two parameters are the types of key and value of the input pairs of the map function to be implemented. They have to match the types of the selected input format. The third and fourth parameters are the selected output types. 

The interface also prescribes the map method. It is called again by the execution environment for each input pair. The first two parameters are the key and value of the pair. The second parameter is an output collector that is used to pass output pairs to Hadoop. The reporter is used to report status messages to the environment. The map method for WordCount divides the transferred line into words and, as before, generates an output pair of word and a "1" for each word. 

Code 3 shows the implementation of a suitable reducer. The generic reducer interface, like that of the mapper, is parameterized by four types: input key and value and output key and value. 

The reduce method receives as a first parameter one of the output keys of the intermediate results of all mappers summarized by Hadoop and an iterator over all values for this key (k2, list (v2)). In addition, as with the mapper, an OutputCollector and Reporter are passed on to reduce. The reduce method to WordCount iterates over and adds the ones passed to it. It generates an output pair (k3, v3) with the word and the number of its occurrences in all documents of the input. 


More details can be found in this research paper:

Azeroual O, Fabre R. Processing Big Data with Apache Hadoop in the Current Challenging Era of COVID-19. Big Data and Cognitive Computing. 2021; 5(1):12. https://doi.org/10.3390/bdcc5010012 
