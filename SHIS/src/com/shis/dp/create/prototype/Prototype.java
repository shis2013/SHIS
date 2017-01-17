package com.shis.dp.create.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



/**
 * 具体实例实现java原型接口Cloneable（添加序列化以便深复制）
 * 原型模式，用原型实例指定创建对象的种类，并通过拷贝这些原型创建新的对象（注：原型拷贝分为浅复制和深复制）
 * 1.实现java原型接口(注：Cloneable为java提供原型接口)
 * @project Resume    
 * @author 史帅
 * @date 2017-1-17
 * @since jdk1.6
 * @version
 */
class Resume implements Cloneable,Serializable{
	/** 
	 * @Fields serialVersionUID
	 * @author 史帅
	 * @date 2017-1-17
	 */ 
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String sex;
	private String age;
	private Date date;
	private ResumeInfo resumeIinfo;

	public Resume(){
		this.date=new Date();
		resumeIinfo=new ResumeInfo();
	}
	/**
	 * 浅复制（如果字段是值类型的，则对该字段执行逐位复制，如果字段是引用类型，则原始对象和副本引用同一对象）
	 * @author 史帅 
	 * @date 2017-1-17
	 * @return 
	 * @see java.lang.Object#clone()
	 */
	public Object clone(){
		Resume resume = null;
		try {
			resume = (Resume) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resume;
	}
	/**
	 * 深复制通过把对象写到流里的过程序列化(Serialization)，把对象从流中读出来的过程则反序列化(Deserialization)实现
	 * 写到流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。
	 * 在Java语言里深度克隆一个对象，常常可以先使对象实现Serializable接口，然后把对象（实际上只是对象的拷贝）写到一个流里（序列化），再从流里读回来（反序列化），便可以重建对象
	 * @Title deepClone 
	 * @author 史帅
	 * @date 2017-1-17
	 * @return Object 
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public  Object deepClone() throws IOException, ClassNotFoundException{
        //将对象写到流里
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);
        //从流里读回来
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }
	/**
	 * 信息输出
	 * @Title getInfo 
	 * @author 史帅
	 * @date 2017-1-17
	 * @return void
	 */
	public void getInfo(){
		System.out.println("name:"+name+" sex:"+sex+" age:"+age+" date:"+date);
		System.out.println("resumeIinfo1:"+resumeIinfo.getAdditional1()+" resumeIinfo2:"+resumeIinfo.getAdditional2());
	}
	
	public void setResumeInfo(String r1,String r2){
		resumeIinfo.setAdditional1(r1);
		resumeIinfo.setAdditional2(r2);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Date getDate() {
		return date;
	}

	public ResumeInfo getResumeIinfo() {
		return resumeIinfo;
	}

	public void setResumeIinfo(ResumeInfo resumeIinfo) {
		this.resumeIinfo = resumeIinfo;
	}
	
}
/**
 * 附加信息类（添加序列话，以便深复制）
 * @project ResumeInfo    
 * @author 史帅
 * @date 2017-1-17
 * @since jdk1.6
 * @version
 */
class ResumeInfo implements Serializable{
	/** 
	 * @Fields serialVersionUID
	 * @author 史帅
	 * @date 2017-1-17
	 */ 
	
	private static final long serialVersionUID = 3259230894221576402L;
	private String additional1;
	private String additional2;
	public String getAdditional1() {
		return additional1;
	}
	public void setAdditional1(String additional1) {
		this.additional1 = additional1;
	}
	public String getAdditional2() {
		return additional2;
	}
	public void setAdditional2(String additional2) {
		this.additional2 = additional2;
	}
	
}
/**
 * 通过实现java原型接口客户端
 * @project ResumeTest    
 * @author 史帅
 * @date 2017-1-17
 * @since jdk1.6
 * @version
 */
class ResumeTest{
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Resume ume1 = new Resume();
		ume1.setName("第一次");
		ume1.setAge("12");
		ume1.setSex("男");
		ume1.setResumeInfo("附加1", "附加2");
		//浅深复制
		Resume ume2 = (Resume) ume1.deepClone();
		ume2.setName("第二次");
//		ume2.setAge("15");
//		ume2.setSex("男");
		ume2.setResumeInfo("附加3", "附加4");
		//浅复制
		Resume ume3 = (Resume) ume2.clone();
		
		ume1.getInfo();
		ume2.getInfo();
		ume3.getInfo();
		
		System.out.println("ume1和ume2是否是同一对象 " + (ume1 == ume2));
		System.out.println("ume2和ume3是否是同一对象 " + (ume2 == ume3));
		System.out.println("ume1ResumeInfo和ume2ResumeInfo是否是同一对象 " + (ume1.getResumeIinfo() == ume2.getResumeIinfo()));
		System.out.println("ume2ResumeInfo和ume3ResumeInfo是否是同一对象 " + (ume2.getResumeIinfo() == ume3.getResumeIinfo()));
	}
}


/**
 * 自建抽象原型
 * 2.自建抽象原型（注：此Prototype接口为自建抽象原型）
 * @project Prototype    
 * @author 史帅
 * @date 2017-1-17
 * @since jdk1.6
 * @version
 */
public interface Prototype  {
	/**
	 * 克隆自身
	 * @Title clone 
	 * @author 史帅
	 * @date 2017-1-17
	 * @return Object 
	 * @return
	 */
	public Object clone();
    public String getName();
    public void setName(String name);
}
/**
 * 具体原型角色1
 * @project Resume1    
 * @author 史帅
 * @date 2017-1-17
 * @since jdk1.6
 * @version
 */
class Resume1 implements Prototype{
	private String name;
	private ResumeInfo info;
	public Resume1(){
		info=new ResumeInfo();
	}
	
	public Object clone(){
		return new Resume1();
	}
	public String toString(){
		String str = "具体原型角色1 , name = " + this.name + "  info1="+info.getAdditional1()+"  info2="+info.getAdditional2();
        return str;
    }
	public void setInfo(String s1,String s2){
		info.setAdditional1(s1);
		info.setAdditional2(s2);
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name=name;
	}

	public ResumeInfo getInfo() {
		return info;
	}
	
}
/**
 * 具体原型角色2
 * @project Resume2    
 * @author 史帅
 * @date 2017-1-17
 * @since jdk1.6
 * @version
 */
class Resume2 implements Prototype{
	private String name;
	
	public Resume2(){
	}
	
	
	public Object clone(){
		return new Resume2();
	}
	public String toString(){
        return "具体原型角色2 , name = " + this.name;
    }

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}


	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name=name;
	}
}
/**
 * 原型管理器
 * 原型管理器角色保持一个聚集，作为对所有原型对象的登记，这个角色提供必要的方法，供外界增加新的原型对象和取得已经登记过的原型对象。
 * @project PrototypeManager    
 * @author 史帅
 * @date 2017-1-17
 * @since jdk1.6
 * @version
 */
class PrototypeManager {
	//用来记录原型的编号和原型实例的对应关系
    private static Map<String,Prototype> map = new HashMap<String,Prototype>();
    // 私有化构造方法，避免外部创建实例
    private PrototypeManager(){}
    /**
     * 向原型管理器里面添加或是修改某个原型注册
     * @Title setPrototype 
     * @author 史帅
     * @date 2017-1-17
     * @return void 
     * @param prototypeId 原型编号
     * @param prototype 原型类型
     */
    public synchronized static void setPrototype(String prototypeId , Prototype prototype){
        map.put(prototypeId, prototype);
    }
    /**
     * 从原型管理器里面删除某个原型注册
     * @Title removePrototype 
     * @author 史帅
     * @date 2017-1-17
     * @return void 
     * @param prototypeId 原型编号
     */
    public synchronized static void removePrototype(String prototypeId){
    	System.out.println("删除原型编号："+prototypeId);
        map.remove(prototypeId);
    }
    /**
     * 获取某个原型编号对应的原型实例
     * @Title getPrototype 
     * @author 史帅
     * @date 2017-1-17
     * @return Prototype  原型类型
     * @param prototypeId 原型变化
     * @return
     * @throws Exception 如原型还没有注册或已被销毁抛出异常
     */
    public synchronized static Prototype getPrototype(String prototypeId) throws Exception{
        Prototype prototype = map.get(prototypeId);
        if(prototype == null){
            throw new Exception("您希望获取的原型还没有注册或已被销毁");
        }
        return prototype;
    }
}
/**
 * 客户端
 * @project Client    
 * @author 史帅
 * @date 2017-1-17
 * @since jdk1.6
 * @version
 */
class Client {
	/*
	 * 1.如果需要创建的原型对象数目较少而且比较固定的话，可以采取第一种形式。在这种情况下，原型对象的引用可以由客户端自己保存。
	 * 2.如果要创建的原型对象数目不固定的话，可以采取第二种形式。在这种情况下，客户端不保存对原型对象的引用，这个任务被交给管理员对象。
	 * 在复制一个原型对象之前，客户端可以查看管理员对象是否已经有一个满足要求的原型对象。如果有，可以直接从管理员类取得这个对象引用；
	 * 如果没有，客户端就需要自行复制此原型对象。
　　	 */
	/**
	 * 登记形式通过原型控制器
	 * @Title main 
	 * @author 史帅
	 * @date 2017-1-17
	 * @return void 
	 * @param args
	 */
	public static void main1(String[]args){
        try{
            Prototype p1 = new Resume1();
            PrototypeManager.setPrototype("p1", p1);
            //获取原型来创建对象
            Prototype p3 = (Prototype) PrototypeManager.getPrototype("p1").clone();
            p3.setName("张三");
            System.out.println("第一个实例：" + p3.getName());
            //有人动态的切换了实现
            Prototype p2 = new Resume2();
            PrototypeManager.setPrototype("p1", p2);
            //重新获取原型来创建对象
            Prototype p4 = (Prototype) PrototypeManager.getPrototype("p1").clone();
            p4.setName("李四");
            System.out.println("第二个实例：" + p4.getName());
            //有人注销了这个原型
            PrototypeManager.removePrototype("p1");
            //再次获取原型来创建对象
            Prototype p5 = (Prototype) PrototypeManager.getPrototype("p1").clone();
            p5.setName("王五");
            System.out.println("第三个实例：" + p5.getName());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
	/**
	 * 简单形式
	 * @Title main 
	 * @author 史帅
	 * @date 2017-1-17
	 * @return void 
	 * @param args
	 */
	public static void main(String[] args) {
		Resume1 p1 = new Resume1();
		p1.setInfo("1","2");
		p1.setName("第一实例");
		Resume1 p2 = (Resume1) p1.clone();
		p2.setInfo("3","4");
		p2.setName("第二实例");
		
		System.out.println("p1:"+p1.toString());
		System.out.println("p2:"+p2.toString());
		System.out.println("p1=p2:"+(p1==p2));
		System.out.println("p1info=p2info："+(p1.getInfo()==p2.getInfo()));
	}
}