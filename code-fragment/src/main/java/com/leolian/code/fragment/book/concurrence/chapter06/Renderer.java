package com.leolian.code.fragment.book.concurrence.chapter06;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import com.leolian.code.fragment.book.concurrence.chapter05.Preloader;

public class Renderer {
	private final ExecutorService exec;

	public Renderer(ExecutorService exec) {
		this.exec = exec;
	}

	public void renderPage(CharSequence source) {
		List<ImageInfo> info = scanForImageInfo(source);
		CompletionService<ImageData> completionService = new ExecutorCompletionService<ImageData>(exec);
		for (final ImageInfo imageInfo : info) {
			completionService.submit(new Callable<ImageData>() {
				public ImageData call() {
					return imageInfo.downloadImage();
				}
			});
		}
		renderText(source);
		try {
			for (int i = 0; i < info.size(); i++) {
				Future<ImageData> f = completionService.take();
				ImageData imageData = f.get();
				renderImage(imageData);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} catch (ExecutionException e) {
			throw Preloader.launderThrowable(e.getCause());
		}
	}

	/**
	 * @param imageData
	 */
	private void renderImage(ImageData imageData) {
		
	}

	/**
	 * @param source
	 */
	private void renderText(CharSequence source) {
		
	}

	/**
	 * @param source
	 * @return
	 */
	private List<ImageInfo> scanForImageInfo(CharSequence source) {
		return null;
	}

}