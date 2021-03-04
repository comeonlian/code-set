package com.leolian.code.fragment.book.concurrence.chapter07;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ReaderThread extends Thread {
	private final Socket socket;
	private final InputStream in;
	
	private static final int BUFSZ = 1024;
	
	public ReaderThread(Socket socket) throws IOException {
		this.socket = socket;
		this.in = socket.getInputStream();
	}

	public void interrupt() {
		try {
			socket.close();
		} catch (IOException e) {
			//
		} finally {
			super.interrupt();
		}
	}

	public void run() {
		try {
			byte[] buf = new byte[BUFSZ];
			while (true) {
				int count = in.read(buf);
				if (count < 0)
					break;
				else if (count > 0)
					processBuffer(buf, count);
			}
		} catch (IOException e) {
			//
		}
	}

	/**
	 * @param buf
	 * @param count
	 */
	private void processBuffer(byte[] buf, int count) {
		
	}

}