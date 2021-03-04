package com.leolian.code.fragment.book.concurrence.chapter06;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

import com.leolian.code.fragment.book.distributed.chapter01.httprpc.Request;

public class LifecycleWebServer {
	private final ExecutorService exec = Executors.newFixedThreadPool(8);

	public void start() throws Exception {
		ServerSocket socket = new ServerSocket(8282);
		while (!exec.isShutdown()) {
			try {
				final Socket conn = socket.accept();
				exec.execute(new Runnable() {
					public void run() {
						handleRequest(conn);
					}
				});
			} catch (RejectedExecutionException e) {
				if (!exec.isShutdown()) {
					System.out.println("task submission rejected" + e);
				}
			}
		}

	}

	public void stop() {
		exec.shutdown();
	}

	public void handleRequest(Socket connection) {
		Request req = readRequest(connection);
		if (isShutdownRequest(req))
			stop();
		else
			dispatchRequest(req);
	}

	/**
	 * @param req
	 */
	private void dispatchRequest(Request req) {
		
	}

	/**
	 * @param req
	 * @return
	 */
	private boolean isShutdownRequest(Request req) {
		return false;
	}

	/**
	 * @param connection
	 * @return
	 */
	private Request readRequest(Socket connection) {
		return null;
	}

}
