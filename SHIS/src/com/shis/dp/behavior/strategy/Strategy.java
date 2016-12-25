package com.shis.dp.behavior.strategy;
/**
 * 策略模式（策略模式封装了变化【算法】）
 * @project Strategy    
 * @author 史帅
 * @date 2016-12-25
 * @since jdk1.6
 * @version
 */
abstract class Strategy {
	public abstract void algorithm();
}
/**
 * 算法A
 * @project algorithmA    
 * @author 史帅
 * @date 2016-12-25
 * @since jdk1.6
 * @version
 */
class algorithmA extends Strategy{

	@Override
	public void algorithm() {
		System.out.println("进入算法A");
	}
}
/**
 * 算法B
 * @project algorithmB    
 * @author 史帅
 * @date 2016-12-25
 * @since jdk1.6
 * @version
 */
class algorithmB extends Strategy{

	@Override
	public void algorithm() {
		System.out.println("进入算法B");
	}
}
/**
 * 控制器
 * @project Context    
 * @author 史帅
 * @date 2016-12-25
 * @since jdk1.6
 * @version
 */
class Context{
	Strategy strategy;
	public Context(Strategy strategy){
		this.strategy=strategy;
	}
	public void getContext(){
		strategy.algorithm();
	}
}
/**
 * 客户端
 * @project TestStrategy    
 * @author 史帅
 * @date 2016-12-25
 * @since jdk1.6
 * @version
 */
class TestStrategy{
	public static void main(String[] args) {
		Context test=new Context(new algorithmA());
		test.getContext();
	}
}