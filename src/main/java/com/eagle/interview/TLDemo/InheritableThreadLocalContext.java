package com.eagle.interview.TLDemo;

import java.util.concurrent.*;

public class InheritableThreadLocalContext {
	private static InheritableThreadLocal<Context> context = new InheritableThreadLocal<>();

	static class Context {
		String name;
		int value;
	}

	public static void main(String[] args) {
//		v1();
		v2();
	}

	public static void v1(){
		// 固定线程池
		ExecutorService executorService = Executors.newFixedThreadPool(4);

		for (int i = 1; i <= 10; i++) {
			int finalI = i;
			new Thread(
					() -> {
						// 生成任务的线程对context进行赋值
						Context contextMain = new Context();
						contextMain.name = String.format("Thread%s name", finalI);
						contextMain.value = finalI * 20;
						InheritableThreadLocalContext.context.set(contextMain);
						// 提交任务
						for (int j = 1; j <= 10; j++) {
							System.out.println("Thread" + finalI + " produce task " + (finalI * 20 + j));
							executorService.execute(() -> {
								// 执行任务的子线程
								Context contextChild = InheritableThreadLocalContext.context.get();
								System.out.println(Thread.currentThread().getName() + " execute task, name : " + contextChild.name + " value : " + contextChild.value);
							});
						}

					}
			).start();
		}
	}

	public static void v2(){
		// 固定线程池
		ThreadPoolExecutor executorService = new ThreadPoolExecutor(
				4,
				4,
				0L,
				TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<>() );
		// 在main线程中赋值
		Context context = new Context();
		context.name = "Thread0 name";
		context.value = 0;
		InheritableThreadLocalContext.context.set(context);
		// 开启所有线程
		executorService.prestartAllCoreThreads();

		for (int i = 1; i <= 10; i++) {
			int finalI = i;
			new Thread(
					() -> {
						// 生成任务的线程对context进行赋值
						Context contextMain = new Context();
						contextMain.name = String.format("Thread%s name", finalI);
						contextMain.value = finalI * 20;
						InheritableThreadLocalContext.context.set(contextMain);
						// 提交任务
						for (int j = 1; j <= 10; j++) {
							System.out.println("Thread" + finalI + " produce task " + (finalI * 20 + j));
							executorService.execute(() -> {
								// 执行任务的子线程
								Context contextChild = InheritableThreadLocalContext.context.get();
								System.out.println(Thread.currentThread().getName() + " execute task, name : " + contextChild.name + " value : " + contextChild.value);
							});
						}

					}
			).start();
		}
	}
}
