package com.eagle.interview.queue;

import org.eclipse.jetty.util.ConcurrentHashSet;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TestLeak {
	public static void main(String[] args)
	{
		ConcurrentLinkedQueue<Object> queue = new ConcurrentLinkedQueue();
//		ConcurrentHashSet<Object> queue=new ConcurrentHashSet<Object>();
		queue.add(new Object()); //Required for the leak to appear.
		Object object=new Object();
		int loops=0;
		Runtime rt=Runtime.getRuntime();
		long last=System.currentTimeMillis();
		while(true)
		{
			if(loops%10000==0)
			{
				long now=System.currentTimeMillis();
				long duration=now-last;
				last=now;
				System.err.printf("duration=%d q.size=%d memory max=%d free=%d total=%d%n",duration,queue.size(),rt.maxMemory(),rt.freeMemory(),rt.totalMemory());
			}
			queue.add(object);
			queue.remove(object);
			++loops;
		}
	}
}
