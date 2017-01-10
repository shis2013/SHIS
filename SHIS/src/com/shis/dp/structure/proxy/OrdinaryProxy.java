package com.shis.dp.structure.proxy;
/**
 * 普通代理的场景类
 * @project OrdinaryProxy    
 * @author 史帅
 * @date 2017-1-11
 * @since jdk1.6
 * @version
 */
public class OrdinaryProxy {
	 public static void main(String[] args) {
	     /*
	      * 定义一个代练者
	      * 通过代练者找到游戏者
	      */
		 IOrdinaryGamePlayer proxy = new OrdinaryGamePlayerProxy("张三");
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
 * 普通代理游戏者接口
 * @project IOrdinaryGamePlayer    
 * @author 史帅
 * @date 2017-1-11
 * @since jdk1.6
 * @version
 */
interface IOrdinaryGamePlayer {
	//登录游戏
	public void login(String user,String password);
	//杀怪，网络游戏的主要特色 
	public void killBoss();
	//升级
	public void upgrade();
}
/**
 * 普通代理的游戏者
 * @project OrdinaryGamePlayer    
 * @author 史帅
 * @date 2017-1-11
 * @since jdk1.6
 * @version
 */
class OrdinaryGamePlayer implements IOrdinaryGamePlayer {
	private String name = "";
	//构造函数限制谁能创建对象，并同时传递姓名
	public OrdinaryGamePlayer(IOrdinaryGamePlayer _gamePlayer,String _name) throws Exception{
		if(_gamePlayer == null ){
			throw new Exception("不能创建真是角色！");
		}else{
			this.name = _name;
		}
	}
	//打怪，最期望的就是杀老怪
	public void killBoss() {
		System.out.println(this.name + "在打怪！");
	}
	//进游戏之前你肯定要登录吧，这是一个必要条件
	public void login(String user, String password) {
		System.out.println("登录名为"+user + " 的用户 " + this.name + "登录成功！");
	}
	//升级，升级有很多方法，花钱买是一种，做任务也是一种
	public void upgrade() {
		System.out.println(this.name + " 又升了一级！");
	}
}
/**
 * 普通代理的代理者
 * @project OrdinaryGamePlayerProxy    
 * @author 史帅
 * @date 2017-1-11
 * @since jdk1.6
 * @version
 */
class OrdinaryGamePlayerProxy implements IOrdinaryGamePlayer {
	private IOrdinaryGamePlayer gamePlayer = null; 
	//通过构造函数传递要对谁进行代练
	public OrdinaryGamePlayerProxy(String name){
		try {
			gamePlayer = new OrdinaryGamePlayer(this,name);
		} catch (Exception e) {

			// TODO 异常处理
		}
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
	}
}