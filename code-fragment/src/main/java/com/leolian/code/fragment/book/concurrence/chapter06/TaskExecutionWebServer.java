package com.leolian.code.fragment.book.concurrence.chapter06;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TaskExecutionWebServer {
	private static final int NTHREADS = 100;
	private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);

	public static void main(String[] args) throws Exception {
		ServerSocket socket = new ServerSocket(8081);
		while (true) {
			final Socket connnection = socket.accept();
			Runnable task = new Runnable() {
				public void run() {
					handleRequest(connnection);
				}
			};
			exec.execute(task);
		}
	}
	
	public static void handleRequest(Socket connnection) {
		
	}
}