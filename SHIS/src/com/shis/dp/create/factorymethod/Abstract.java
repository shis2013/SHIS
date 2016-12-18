package com.shis.dp.create.factorymethod;
/**
 * 抽象方式实现简单工厂模式
 * 简单工厂在工厂类中进行判断，如果新增了类型，需要修改工厂类
 * @project Abstract    
 * @author 史帅
 * @date 2016-12-18
 * @since jdk1.6
 * @version
 */
public class Abstract {
	private FunctionAbstract z=null;
	/**
	 * 工厂 
	 * @Title getFunction 
	 * @author 史帅
	 * @date 2016-12-18
	 * @return FunctionAbstract 
	 * @param t
	 * @return
	 */
	public FunctionAbstract getFunction(int t){
		switch (t) {
		case 1:
			z=new FujianAbstract("减法");
			break;
		}
		return z;
	}
	public static void main(String[] args) {
		//抽象类实现简单工厂
		Abstract f = new Abstract();
		FunctionAbstract fa=f.getFunction(1);
		System.out.println("减法："+fa.getResult(4, 2));
	}
}
/**
 * 抽象函数
 * @project FunctionAbstract    
 * @author 史帅
 * @date 2016-12-18
 * @since jdk1.6
 * @version
 */
abstract class FunctionAbstract{
	FunctionAbstract(String name){
		System.out.println("使用："+name);
	}
	public abstract int getResult(int a,int b);
	
}
/**
* 减法类
* @project fujian    
* @author 史帅
* @date 2016-12-18
* @since jdk1.6
* @version
*/
class FujianAbstract extends FunctionAbstract{
	FujianAbstract(String name) {
		super(name);
	}

	@Override
	public int getResult(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return arg0 - arg1;
	}
}
