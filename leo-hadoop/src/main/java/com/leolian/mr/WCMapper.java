package com.leolian.mr;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WCMapper extends Mapper<LongWritable, Text, Text, LongWritable>{
	
	/**
	 * map{偏移量, "行内容"}
	 */
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, LongWritable>.Context context)
			throws IOException, InterruptedException {
		String[] words = value.toString().split(" ");
		for (String string : words) {
			context.write(new Text(string), new LongWritable(1));
		}
	}
	
}
