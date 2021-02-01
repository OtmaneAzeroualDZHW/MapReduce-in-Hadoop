public class Map
implements Mapper<LongWritable, Text,
Text, IntWritable> {
	
@Override
public void map(LongWritable key, Text value,
OutputCollector<Text, IntWritable> output,
Reporter reporter) throws IOException {
	
String line = value.toString();
StringTokenizer tokenizer = new StringTokenizer(line);
while (tokenizer.hasMoreTokens()) {
output.collect(
new Text(tokenizer.nextToken()),
new IntWritable(1));
}
}
}