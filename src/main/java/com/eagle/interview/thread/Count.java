package com.eagle.interview.thread;

import com.beust.jcommander.internal.Lists;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Count {
	private static Map<String, AtomicInteger> countTasks = new ConcurrentHashMap<>();
	public static void  main(String[] args) throws InterruptedException{
		//threadPoolExecutor.execute会共用一个线程
		//threadPoolExecutor.submit会新起一个线程（封装成TaskFuther）
//		shareThread();

		//threadPoolExecutor.submit内部会吃掉异常
		//threadPoolExecutor.execute抛出异常
		testException();
	}

	static void testException() throws InterruptedException {
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>()){
//			@Override
//			protected void afterExecute(Runnable r, Throwable t) {
//				super.afterExecute(r, t);
//				if (t != null) {
//					t.printStackTrace();
//				} else {
//					if (r instanceof Future<?>) {
//						try {
//							//get这里会首先检查任务的状态，然后将上面的异常包装成ExecutionException
//							Object result = ((Future<?>) r).get();
//						} catch (CancellationException ce) {
//							t = ce;
//						} catch (ExecutionException ee) {
//							t = ee.getCause();
//							t.printStackTrace();
//						} catch (InterruptedException ie) {
//							Thread.currentThread().interrupt(); // ignore/reset
//						}
//					}
//				}
//			}
		};

		List<Integer> list = Lists.newArrayList(1, 2, 3, null);

		Future<?> f = threadPoolExecutor.submit(() -> {
			List<String> result = list.stream().map(a -> a.toString()).collect(Collectors.toList());
			System.out.println(result);
		});

		CountDownLatch cd = new CountDownLatch(1);
		cd.await(2, TimeUnit.SECONDS);
		threadPoolExecutor.shutdownNow();
	}


	static void shareThread() throws InterruptedException {
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>()){
			/**
			 * 任务执行完后统计任务执行的数量
			 * @param r
			 * @param t
			 */
			@Override
			protected void afterExecute(Runnable r, Throwable t) {
				super.afterExecute(r, t);
				countTasks.compute(r.toString(), (s, atomicInteger) ->
						new AtomicInteger(atomicInteger == null ? 0 : atomicInteger.incrementAndGet()));
			}
		};

		/**
		 * 源源不断的任务添加进线程池被执行
		 */
		for (int i =0; i < 100; i++) {
//			threadPoolExecutor.submit(new SimpleRunnable());
			threadPoolExecutor.execute(new SimpleRunnable());
		}
		CountDownLatch cd = new CountDownLatch(1);
		cd.await(10, TimeUnit.SECONDS);
		System.out.println("map="+countTasks);
		threadPoolExecutor.shutdownNow();
	}
	static class SimpleRunnable implements Runnable{

		@Override
		public void run() {
			System.out.println("run simple task");
		}

		@Override
		public String toString(){
			return this.getClass().getSimpleName();
		}
	}
}
