package com.shis.dp.behavior.strategy;
/**
 * 策略模式结合简单工厂（将条件判断语句迁移到简单工厂，客户端只调用）
 * @project StrategyAndMethod    
 * @author 史帅
 * @date 2016-12-25
 * @since jdk1.6
 * @version
 */
abstract class StrategyAndMethod {
	public abstract void algorithm();
}
/**
 * 算法A
 * @project algorithmAndMethodA    
 * @author 史帅
 * @date 2016-12-25
 * @since jdk1.6
 * @version
 */
class algorithmAndMethodA extends StrategyAndMethod{

	@Override
	public void algorithm() {
		System.out.println("进入算法A");
	}
}
/**
 * 算法B
 * @project algorithmAndMethodB    
 * @author 史帅
 * @date 2016-12-25
 * @since jdk1.6
 * @version
 */
class algorithmAndMethodB extends StrategyAndMethod{

	@Override
	public void algorithm() {
		System.out.println("进入算法B");
	}
}
/**
 * 简单工厂控制器
 * @project ContextAndMethod    
 * @author 史帅
 * @date 2016-12-25
 * @since jdk1.6
 * @version
 */
class ContextAndMethod{
	StrategyAndMethod strategy;
	/**
	 * 1 方法A 2方法B
	 * @author 史帅 
	 * @date 2016-12-25 下午06:23:02  
	 * @param type
	 */
	public ContextAndMethod(int type){
		switch (type) {
		case 1:
			this.strategy=new algorithmAndMethodA();
			break;
		case 2:
			this.strategy=new algorithmAndMethodB();
			break;
		default:
			break;
		}
	}
	public void getContextAndMethod(){
		strategy.algorithm();
	}
}
/**
 * 测试
 * @project TestContextAndMethod    
 * @author 史帅
 * @date 2016-12-25
 * @since jdk1.6
 * @version
 */
class TestContextAndMethod{
	public static void main(String[] args) {
		ContextAndMethod text = new ContextAndMethod(1);
		text.getContextAndMethod();
	}
}
