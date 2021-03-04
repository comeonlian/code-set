package com.leolian.code.fragment.book.nettyaction.chapter13;

import java.net.InetSocketAddress;

public class LogEvent {
	public static final byte SEPARATOR = ':';
	
	private InetSocketAddress source;
	private String logFile;
	private String msg;
	private long received;
	
	public LogEvent(String logFile, String msg) {
		this(null, -1, logFile, msg);
	}
	
	public LogEvent(InetSocketAddress source, long received, String logFile, String msg) {
		this.source = source;
		this.received = received;
		this.logFile = logFile;
		this.msg = msg;
	}
	
	public InetSocketAddress getSource() {
		return source;
	}
	public String getLogFile() {
		return logFile;
	}
	public String getMsg() {
		return msg;
	}
	public long getReceived() {
		return received;
	}
}
