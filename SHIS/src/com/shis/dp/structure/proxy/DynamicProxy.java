package com.shis.dp.structure.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * 抽象主题
 * @project Subject    
 * @author 史帅
 * @date 2017-1-11
 * @since jdk1.6
 * @version
 */
interface Subject {
	//业务操作
	public void doSomething(String str); 
}

/**
 * 通知接口
 * @project IAdvice    
 * @author 史帅
 * @date 2017-1-11
 * @since jdk1.6
 * @version
 */
interface IAdvice {
	//通知只有一个方法，执行即可
	public void exec();
}

/**
 * 真实主题
 * @project RealSubject    
 * @author 史帅
 * @date 2017-1-11
 * @since jdk1.6
 * @version
 */
class RealSubject implements Subject {
	//业务操作 
	public void doSomething(String str) {
		System.out.println("do something!---->" + str);
	}
}

/**
 * 通知实现
 * @project BeforeAdvice    
 * @author 史帅
 * @date 2017-1-11
 * @since jdk1.6
 * @version
 */
class BeforeAdvice implements IAdvice{
	public void exec(){
		System.out.println("我是前置通知，我被执行了！");
	}
}

/**
 * 动态代理类
 * @project DynamicGamePlayIH    
 * @author 史帅
 * @date 2017-1-11
 * @since jdk1.6
 * @version
 */
class DynamicGamePlayIH implements InvocationHandler {
	//被代理者
	Class cls =null;
	//被代理的实例
	Object obj = null;
	//我要代理谁
	public DynamicGamePlayIH(Object _obj){
		this.obj = _obj;
	}
	//调用被代理的方法
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = method.invoke(this.obj, args);
		//如果是登录方法，则发送信息
		if(method.getName().equalsIgnoreCase("login")){
			System.out.println("有人在用我的账号登陆！");
		}
		return result;
	}
}

/**
 * 动态代理的Handler类
 * @project MyInvocationHandler    
 * @author 史帅
 * @date 2017-1-11
 * @since jdk1.6
 * @version
 */
class MyInvocationHandler implements InvocationHandler {
	//被代理的对象
	private Object target = null;
	//通过构造函数传递一个对象
	public MyInvocationHandler(Object _obj){
		this.target = _obj;
	}
	//代理方法
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//执行被代理的方法
		return method.invoke(this.target, args);
	}
}
/**
 * 动态代理类
 * @project DynamicProxy    
 * @author 史帅
 * @date 2017-1-11
 * @since jdk1.6
 * @version
 */
class DynamicProxy<T> {
	public static <T> T newProxyInstance(ClassLoader loader, 
			Class<?>[] interfaces, InvocationHandler h){
		//寻找JoinPoint连接点，AOP框架使用元数据定义
		if(true){
			//执行一个前置通知
			(new BeforeAdvice()).exec();
		}
		//执行目标，并返回结果
		return (T)java.lang.reflect.Proxy.newProxyInstance(loader,interfaces, h);
	}
}

/**
 * 动态代理测试方法
 * @project Client    
 * @author 史帅
 * @date 2017-1-11
 * @since jdk1.6
 * @version
 */
class Client {
	public static void main(String[] args) {
		/*
		 * 1.定义一个主题
		 * 2.定义一个带主题Handler
		 * 3.定义主题的代理
		 * 4.代理的行为
		 */
		//定义一个主题
		Subject subject = new RealSubject();
		//定义一个Handler
		InvocationHandler handler = new MyInvocationHandler(subject);
		//定义主题的代理
		Subject proxy = DynamicProxy.newProxyInstance(subject.getClass().getClassLoader(),
				subject.getClass().getInterfaces(),handler);
		//代理的行为
		proxy.doSomething("Finish");
	}
}