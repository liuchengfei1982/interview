package com.eagle.interview.aop;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * 普通的通知（前置，后置，异常等，没有实现MethodInterceptor接口）
 * 环绕通知（实现了MethodInterceptor接口）
 * 引入通知（需要提供额外的引入的信息，实现了MethodInterceptor接口）
 *
 * 虽然我们普通的通知没有直接实现MethodInterceptor接口，
 * 但其实它的底层也是依赖于拦截器来完成的
 */
public class Main {
	public static void main(String[] args) {

		ProxyFactory proxyFactory = new ProxyFactory();

		// 一个Advisor代表的是一个已经跟指定切点绑定了的通知
		// 在这个例子中意味着环绕通知不会作用到toString方法上
		Advisor advisor = new DefaultPointcutAdvisor(new EaglePointcut(), new EagleAroundAdvice());

		// 添加一个绑定了指定切点的环绕通知
		proxyFactory.addAdvisor(advisor);

		// 添加一个返回后的通知
		//一种是添加一个Advice，后者也会被转换成一个Advisor然后再进行添加，
		// 没有指定切点的通知是没有任何意义的
		proxyFactory.addAdvice(new EagleAfterReturnAdvice());

		// 添加一个方法执行前的通知
		proxyFactory.addAdvice(new EagleBeforeAdvice());

		// 为代理类引入一个新的需要实现的接口--Runnable
		proxyFactory.addAdvice(new EagleIntroductionAdvice());

		// 设置目标类
		proxyFactory.setTarget(new EagleService());

		// 因为要测试代理对象自己定义的方法，所以这里启用cglib代理
		proxyFactory.setProxyTargetClass(true);

		// 创建代理对象
		Object proxy = proxyFactory.getProxy();

		// 调用代理类的toString方法，通过控制台查看代理逻辑的执行情况
		proxy.toString();


		if (proxy instanceof EagleService) {
			((EagleService) proxy).testAop();
		}

		// 判断引入是否成功，并执行引入的逻辑
		if (proxy instanceof Runnable) {
			((Runnable) proxy).run();
		}
	}
}
