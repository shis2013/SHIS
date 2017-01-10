package com.shis.dp.structure.proxy;
/**
 * 代理模式
 * 为其它对象提供一种代理以控制对这个对象的访问
 * 远程代理，为一个对象在不同的地址空间提供局部代表，这样可以隐藏一个对象存在于不同地址空间的事实
 * 虚拟代理，根据需要创建开销很大的对象，通过它来存放实例化需要很长时间的真实对象
 * 安全代理，用来控制真是对象访问时的权限
 * 智能指引，调用真实对象时代理处理另外一些事
 * @project Proxy    
 * @author 史帅
 * @date 2017-1-10
 * @since jdk1.6
 * @version
 */
public class Proxy {
	public static void main(String[] args) {
		//普通代理
		ProxyA proxyA=new ProxyA("访问者通过代理A");
		proxyA.model();
		//虚拟代理
		ProxyB proxyB=new ProxyB("访问者通过代理B");
		proxyB.model();
	}
}

/**
 * 模型
 * @project Model    
 * @author 史帅
 * @date 2017-1-10
 * @since jdk1.6
 * @version
 */
interface Model{
	public void model();
}
/**
 * 真实请求
 * @project Visitor    
 * @author 史帅
 * @date 2017-1-10
 * @since jdk1.6
 * @version
 */
class Visitor implements Model{
	String t="";
	public Visitor(String t){
		this.t=t;
	}
	@Override
	public void model() {
		// TODO Auto-generated method stub
		System.out.println(t+"访问模型");
	}
}
/**
 * 代理A
 * @project ProxyB    
 * @author 史帅
 * @date 2017-1-10
 * @since jdk1.6
 * @version
 */
class ProxyA implements Model{
	Visitor visitor=null;
	public ProxyA(String t){
		this.visitor=new Visitor(t);
	}
	@Override
	public void model() {
		// TODO Auto-generated method stub
		visitor.model();
	}
}
/**
 * 虚拟代理
 * @project ProxyB    
 * @author 史帅
 * @date 2017-1-11
 * @since jdk1.6
 * @version
 */
class ProxyB implements Model {
    //要代理哪个实现类
    private Model model;
    
    private String t="";
    public ProxyB(String t){
    	this.t=t;
    }
    //实现接口中定义的方法
	@Override
	public void model() {
		//判断一下真实主题是否初始化
        if(model == null){
        	model = new Visitor(t);
        }
        model.model();
	} 
}
