package com.shis.dp.create.factorymethod;
/**
 * 接口简单工厂模式
 * 简单工厂在工厂类中进行判断，如果新增了类型，需要修改工厂类
 * @project Interface    
 * @author 史帅
 * @date 2016-12-18
 * @since jdk1.6
 * @version
 */
public class Interface {
	/**
	 * 选择器
	 * @Title getFunction 
	 * @author 史帅
	 * @date 2016-12-18
	 * @return Function 
	 * @param x 1减法 2加法 3乘法 4除法
	 * @return
	 */
	public FunctionInterface getFunction(int x){
		FunctionInterface function = null;
		switch (x) {
		case 1:
			function = new JianInterface();
			break;
		}
		return function;
	}
	public static void main(String[] args) {
		//接口实现简单工厂
		Interface i = new Interface();
		FunctionInterface fu=i.getFunction(1);
		System.out.println("减法："+fu.getResult(2, 1));
	}
}
/**
 * 函数接口
 * @project Function    
 * @author 史帅
 * @date 2016-12-18
 * @since jdk1.6
 * @version
 */
interface FunctionInterface {
	public  int getResult(int a,int b);
}
/**
 * 减法
 * @project jian    
 * @author 史帅
 * @date 2016-12-18
 * @since jdk1.6
 * @version
 */
 class JianInterface implements FunctionInterface{
	public int getResult(int a,int b){
		return (a-b);
	}
}
