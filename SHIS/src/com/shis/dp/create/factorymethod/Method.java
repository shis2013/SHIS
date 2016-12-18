package com.shis.dp.create.factorymethod;
/**
 * 工厂模式
 * 工厂模式在一定程度上符合开闭原则
 * 实现了对产品角色创建的封装，避免了工厂角色内部违反开闭原则
 * 在工厂方法模式中，要么将判断逻辑留在抽象工厂角色中，要么在客户程序中将具体工厂角色写死
 * 而且产品对象创建条件的改变必然会引起工厂角色的修改
 * 
 * 
 * 简单工厂模式和工厂模式
 * 简单工厂在工厂类中进行判断，如果新增了类型，需要修改工厂类
 * 工厂模式在一定程度上符合开闭原则
 * @project Method    
 * @author 史帅
 * @date 2016-12-18
 * @since jdk1.6
 * @version
 */
public class Method {
	/**
	 * 客户端
	 * @Title main 
	 * @author 史帅
	 * @date 2016-12-18
	 * @return void 
	 * @param args
	 */
	public static void main(String[] args) {
		jianfaCalculate jianfa = new jianfaCalculate();
		Calculate ca=jianfa.getMethod();
		System.out.println(ca.counter(3, 2));
	}
}
/**
 * 计算类
 * @project Calculate    
 * @author 史帅
 * @date 2016-12-18
 * @since jdk1.6
 * @version
 */
abstract class Calculate{
	public Calculate(String type){
		System.out.println("计算类："+type);
	}
	public abstract int counter(int a,int b);
}
/**
 * 计算减法类
 * @project jianfaMethod    
 * @author 史帅
 * @date 2016-12-18
 * @since jdk1.6
 * @version
 */
class jianfaMethod extends Calculate{

	public jianfaMethod(String type) {
		super(type);
	}

	@Override
	public int counter(int a, int b) {
		// TODO Auto-generated method stub
		return a-b;
	}
}
/**
 * 计算工场
 * @project CalculateMethod    
 * @author 史帅
 * @date 2016-12-18
 * @since jdk1.6
 * @version
 */
interface CalculateMethod{
	public Calculate getMethod();
}
/**
 * 减法计算
 * @project jianfaCalculate    
 * @author 史帅
 * @date 2016-12-18
 * @since jdk1.6
 * @version
 */
class jianfaCalculate implements CalculateMethod{

	@Override
	public Calculate getMethod() {
		// TODO Auto-generated method stub
		return new jianfaMethod("减法");
	}
}