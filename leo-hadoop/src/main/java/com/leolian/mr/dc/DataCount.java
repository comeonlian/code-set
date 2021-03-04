package com.leolian.mr.dc;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 统计文本中上下行流量
 * @author Lian
 */
public class DataCount {
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		conf.set("mapreduce.client.submit.file.replication", "2");
		
		Job job = Job.getInstance(conf);
		job.setJarByClass(DataCount.class);
		
		job.setMapperClass(DCMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(DataBean.class);
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		
		job.setReducerClass(DCReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DataBean.class);
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.waitForCompletion(true);
	}
	
	/**
	 * mapper
	 * @author Lian
	 */
	public static class DCMapper extends Mapper<LongWritable, Text, Text, DataBean>{
		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, DataBean>.Context context)
				throws IOException, InterruptedException {
			String[] datas = value.toString().split("\t");
			String phoneNum = datas[1];
			String upPayLoadString = datas[8];
			String downPayLoadString = datas[9];
			long upPayLoad = 0, downPayLoad = 0;
			if(null!=upPayLoadString&&upPayLoadString.length()>0)
				upPayLoad = Long.parseLong(upPayLoadString);
			if(null!=downPayLoadString&&downPayLoadString.length()>0)
				downPayLoad = Long.parseLong(downPayLoadString);
			DataBean bean = new DataBean("", upPayLoad, downPayLoad);
			context.write(new Text(phoneNum), bean);
		}
	}
	
	/**
	 * reducer
	 * @author Lian
	 */
	public static class DCReducer extends Reducer<Text, DataBean, Text, DataBean>{
		@Override
		protected void reduce(Text key, Iterable<DataBean> values, Reducer<Text, DataBean, Text, DataBean>.Context context)
				throws IOException, InterruptedException {
			long upSum = 0, downSum = 0;
			for (DataBean bean : values) {
				upSum += bean.getUpPayLoad();
				downSum += bean.getDownPayLoad();
			}
			DataBean dataBean = new DataBean("", upSum, downSum);
			context.write(key, dataBean);
		}
	}
}
