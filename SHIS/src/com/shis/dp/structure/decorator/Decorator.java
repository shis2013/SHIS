package com.shis.dp.structure.decorator;
/**
 * 装饰者类
 * 装饰者模式是为己有功能动态的添加更多功能的一种方式，当系统需要新功能时向旧类添加新的代码，这些新的代码通常装饰了原有类的核心职责或主要行为
 * 装饰者模式的优点，把类中的装饰功能从类中搬移出去，这样可以简化原有类，有效的把类的核心职责和装饰功能区分开,而且可以去除相关类中的重复装饰逻辑
 * @project Decorator    
 * @author 史帅
 * @date 2017-1-9
 * @since jdk1.6
 * @version
 */
abstract class Decorator {
	public abstract void Operation(); 
}
/**
 * 装饰对象
 * @project ConcreteComponent    
 * @author 史帅
 * @date 2017-1-9
 * @since jdk1.6
 * @version
 */
class ConcreteComponent extends Decorator{
	
	@Override
	public void Operation() {
		System.out.println("具体对象！");
	}
	
}
/**
 * 装饰工类
 * @project Component    
 * @author 史帅
 * @date 2017-1-9
 * @since jdk1.6
 * @version
 */
abstract class Component extends Decorator{
	protected Decorator decorator;
	public void SetDecorator(Decorator decorator){
		this.decorator=decorator;
	}
	/*
	 * 重写装饰类方法
	 */
	public void Operation(){
		if(decorator!=null)
			decorator.Operation();
	}
}
/**
 * 装饰A类
 * @project ConcreteA    
 * @author 史帅
 * @date 2017-1-9
 * @since jdk1.6
 * @version
 */
class ConcreteA extends Component{
	
	private String addedState;
	/*
	 * 重写装饰类方法
	 */
	public void Operation(){
		decorator.Operation();
		AddedAehavior();
		System.out.println("具体装饰对象A！");
	}
	/*
	 * 私有装饰方法
	 */
	private void AddedAehavior(){
		addedState="New State";
		System.out.println("私有装饰："+addedState);
	}
}
/**
 * 装饰B类
 * @project ConcreteB    
 * @author 史帅
 * @date 2017-1-9
 * @since jdk1.6
 * @version
 */
class ConcreteB extends Component{
	/*
	 * 重写装饰类方法
	 */
	public void Operation(){
		decorator.Operation();
		AddedBehavior();
		System.out.println("具体装饰对象B！");
	}
	/*
	 * 私有装饰方法
	 */
	private void AddedBehavior(){
		System.out.println("私有装饰对象！");
	}
}
/**
 * 装饰者模式测试类
 * @project DecoratorText    
 * @author 史帅
 * @date 2017-1-9
 * @since jdk1.6
 * @version
 */
class DecoratorText{
	public static void main(String[] args) {
		ConcreteComponent c = new ConcreteComponent();//装饰对象
		ConcreteA ca = new ConcreteA();//A类装饰
		ConcreteB cb = new ConcreteB();//B类装饰
		ca.SetDecorator(c);//通过A类装饰对象
		cb.SetDecorator(ca);//通过B类装饰A类对象
		cb.Operation();//输出装饰后对象
	}
}