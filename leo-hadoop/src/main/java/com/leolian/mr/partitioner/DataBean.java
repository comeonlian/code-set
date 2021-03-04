package com.leolian.mr.partitioner;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

/**
 * 自定义bean
 * @author Lian
 */
public class DataBean implements Writable{
	private String phoneNum;
	private long upPayLoad;
	private long downPayLoad;
	private long totalPayLoad;
	
	public DataBean() {}
	
	
	public DataBean(String phoneNum, long upPayLoad, long downPayLoad) {
		this.phoneNum = phoneNum;
		this.upPayLoad = upPayLoad;
		this.downPayLoad = downPayLoad;
		this.totalPayLoad = upPayLoad + downPayLoad;
	}
	
	
	@Override
	public void readFields(DataInput in) throws IOException {
		this.phoneNum = in.readUTF();
		this.upPayLoad = in.readLong();
		this.downPayLoad = in.readLong();
		this.totalPayLoad = in.readLong();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(this.phoneNum);
		out.writeLong(this.upPayLoad);
		out.writeLong(this.downPayLoad);
		out.writeLong(this.totalPayLoad);
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public long getUpPayLoad() {
		return upPayLoad;
	}

	public void setUpPayLoad(long upPayLoad) {
		this.upPayLoad = upPayLoad;
	}

	public long getDownPayLoad() {
		return downPayLoad;
	}

	public void setDownPayLoad(long downPayLoad) {
		this.downPayLoad = downPayLoad;
	}

	public long getTotalPayLoad() {
		return totalPayLoad;
	}

	public void setTotalPayLoad(long totalPayLoad) {
		this.totalPayLoad = totalPayLoad;
	}
	
	@Override
	public String toString() {
		return " " + upPayLoad + " " + downPayLoad + " " + totalPayLoad;
	}
	
}
