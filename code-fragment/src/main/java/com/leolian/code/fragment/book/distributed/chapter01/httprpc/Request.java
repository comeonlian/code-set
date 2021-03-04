package com.leolian.code.fragment.book.distributed.chapter01.httprpc;

public class Request {
	private byte encode;
	
	private int commandLength;
	
	private String command;
	
	public byte getEncode() {
		return encode;
	}

	public void setEncode(byte encode) {
		this.encode = encode;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public int getCommandLength() {
		return commandLength;
	}

	public void setCommandLength(int commandLength) {
		this.commandLength = commandLength;
	}

	@Override
	public String toString() {
		return "Request [encode=" + encode + ", command=" + command + ", commandLength=" + commandLength + "]";
	}
}