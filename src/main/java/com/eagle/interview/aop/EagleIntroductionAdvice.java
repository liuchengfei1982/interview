package com.eagle.interview.aop;

import org.springframework.aop.support.DelegatingIntroductionInterceptor;

/**
 * 引入通知的主要作用是可以让生成的代理类实现额外的接口。
 * 例子中，为EagleService创建一个代理对象，同时为其定义了一个引入通知
 * 在这个引入通知中，我们为其引入了一个新的需要实现的接口Runnable，
 * 同时通知本身作为这个接口的实现类
 *
 * 通过这个引入通知，我们可以将生成的代理类强转成Runnable类型
 * 然后执行其run方法，同时，run方法也会被前面定义的前置通知，后置通知等拦截
 *
 * 其实就是在方法执行的时候加了一层拦截，当判断这个方法是
 * 被引入的接口提供的方法的时候，那么就执行委托类中的逻辑
 * 而不是目标类中的方法
 */
public class EagleIntroductionAdvice extends DelegatingIntroductionInterceptor implements Runnable {
	@Override
	public void run() {
		System.out.println("running!!!!");
	}
}
