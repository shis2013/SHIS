package com.shis.dp.structure.proxy;
/**
 * 强制代理的场景类
 * @project ForceProxy    
 * @author 史帅
 * @date 2017-1-11
 * @since jdk1.6
 * @version
 */
public class ForceProxy {
	public static void main(String[] args) {
		//定义个游戏的角色
		IForceGamePlayer player = new ForceGamePlayer("张三");
		//获得指定的代理
		IForceGamePlayer proxy = player.getProxy();
		//开始打游戏，记下时间戳
		System.out.println("开始时间是：2009-8-25 10:45");
		proxy.login("zhangSan", "password");
		//开始杀怪
		proxy.killBoss();
		//升级
		proxy.upgrade();
		//记录结束游戏时间
		System.out.println("结束时间是：2009-8-26 03:40");
	}
}
/**
 *  强制代理的接口类
 * @project IForceGamePlayer    
 * @author 史帅
 * @date 2017-1-11
 * @since jdk1.6
 * @version
 */
interface IForceGamePlayer {
	//登录游戏
	public void login(String user,String password);
	//杀怪，这是网络游戏的主要特色
	public void killBoss();
	//升级
	public void upgrade();
	//每个人都可以找一下自己的代理
	public IForceGamePlayer getProxy(); 
}
/**
 * 费用计算类接口
 * @project IForceProxyCount    
 * @author 史帅
 * @date 2017-1-11
 * @since jdk1.6
 * @version
 */
interface IForceProxyCount{
	//计算费用
	public void count();
}
/**
 * 强制代理的真实角色
 * @project ForceGamePlayer    
 * @author 史帅
 * @date 2017-1-11
 * @since jdk1.6
 * @version
 */
class ForceGamePlayer implements IForceGamePlayer {
	private String name = "";
	//我的代理是谁
	private IForceGamePlayer proxy = null;

	public ForceGamePlayer(String _name){
		this.name = _name;
	}
	//找到自己的代理
	public IForceGamePlayer getProxy(){
		this.proxy = new ForceGamePlayerProxy(this);
		return this.proxy;
	}
	//打怪，最期望的就是杀老怪
	public void killBoss() {
		if(this.isProxy()){
			System.out.println(this.name + "在打怪！");
		}else{
			System.out.println("请使用指定的代理访问");
		}
	}
	//进游戏之前你肯定要登录吧，这是一个必要条件
	public void login(String user, String password) {
		if(this.isProxy()){
			System.out.println("登录名为"+user + " 的用户 " + this.name + "登录成功！");
		}else{
			System.out.println("请使用指定的代理访问");;
		}
	}
	//升级，升级有很多方法，花钱买是一种，做任务也是一种
	public void upgrade() {
		if(this.isProxy()){
			System.out.println(this.name + " 又升了一级！");
		}else{
			System.out.println("请使用指定的代理访问");
		}
	}
	//校验是否是代理访问
	private boolean isProxy(){
		if(this.proxy == null){
			return false;
		}else{
			return true;
		}
	}
}
/**
 * 强制代理的代理类
 * @project ForceGamePlayerProxy    
 * @author 史帅
 * @date 2017-1-11
 * @since jdk1.6
 * @version
 */
class ForceGamePlayerProxy implements IForceGamePlayer,IForceProxyCount {
	private IForceGamePlayer gamePlayer = null;
	//构造函数传递用户名
	public ForceGamePlayerProxy(IForceGamePlayer _gamePlayer){
		this.gamePlayer = _gamePlayer;
	}
	//代练杀怪
	public void killBoss() {
		this.gamePlayer.killBoss();
	}
	//代练登录
	public void login(String user, String password) {
		this.gamePlayer.login(user, password);
	}
	//代练升级
	public void upgrade() {
		this.gamePlayer.upgrade();
		count();
	}
	//代理的代理暂时还没有,就是自己
	public IForceGamePlayer getProxy(){
		System.out.println("进入代理类");
		return this;
	}
	@Override
	public void count() {
		System.out.println("代理类内多功能集成（添加费用计算类）:费用150元");
	} 
}
/**
 * 直接访问真实角色
 * @project RealUser    
 * @author 史帅
 * @date 2017-1-11
 * @since jdk1.6
 * @version
 */
class RealUser{
	public static void main(String[] args) {
		//定义个游戏的角色
		IForceGamePlayer player = new ForceGamePlayer("张三");
		//开始打游戏，记下时间戳
		System.out.println("开始时间是：2009-8-25 10:45");
		player.login("zhangSan", "password");
		//开始杀怪
		player.killBoss();
		//升级
		player.upgrade();
		//记录结束游戏时间
		System.out.println("结束时间是：2009-8-26 03:40");
	} 
}
/**
 * 直接访问代理类
 * @project ProxyUser    
 * @author 史帅
 * @date 2017-1-11
 * @since jdk1.6
 * @version
 */
class ProxyUser{
	public static void main(String[] args) {
		//定义个游戏的角色
		IForceGamePlayer player = new ForceGamePlayer("张三");
		//然后再定义一个代练者
		IForceGamePlayer proxy = new ForceGamePlayerProxy(player);
		//开始打游戏，记下时间戳
		System.out.println("开始时间是：2009-8-25 10:45");
		proxy.login("zhangSan", "password");
		//开始杀怪
		proxy.killBoss();
		//升级
		proxy.upgrade();
		//记录结束游戏时间
		System.out.println("结束时间是：2009-8-26 03:40"); 
	}
}