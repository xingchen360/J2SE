package com.noteshare.designPatterns.prototype.demo1;

import java.util.Arrays;

public class PrototypePatternDemo {
	public static void main(String[] args) {
		ShapeCache.loadCache();

		Shape clonedShape = (Shape) ShapeCache.getShape("1");
		clonedShape.addList("张三");
		System.out.println("Shape : " + clonedShape.getType());
		System.out.println(Arrays.toString(clonedShape.getList().toArray()));
		
		Shape clonedShape2 = (Shape) ShapeCache.getShape("2");
		clonedShape2.addList("李四");
		System.out.println("Shape : " + clonedShape2.getType());
		System.out.println(Arrays.toString(clonedShape2.getList().toArray()));
		
		Shape clonedShape3 = (Shape) ShapeCache.getShape("3");
		clonedShape3.addList("王五");
		System.out.println("Shape : " + clonedShape3.getType());
		System.out.println(Arrays.toString(clonedShape3.getList().toArray()));
		
		//测试深浅克隆的却别
		Shape clonedShape33 = clonedShape3.clone();
		clonedShape33.addList("王五王五");
		System.out.println("Shape : " + clonedShape33.getType());
		System.out.println(Arrays.toString(clonedShape33.getList().toArray()));
		//测试开启和关闭shape中的深浅克隆查看输出结果是否一致(去掉和开启Shape类中该行代码的注释：shape.list = (ArrayList<String>) this.list.clone();)
		System.out.println("Shape : " + clonedShape3.getType());
		System.out.println(Arrays.toString(clonedShape3.getList().toArray()));
		
		// 关闭结果如下：
		/*Shape : Circle
		[张三]
		Shape : Square
		[李四]
		Shape : Rectangle
		[王五]
		Shape : Rectangle
		[王五, 王五王五]
		Shape : Rectangle
		[王五, 王五王五]*/
		//开启结果如下：
		/*Shape : Circle
		[张三]
		Shape : Square
		[李四]
		Shape : Rectangle
		[王五]
		Shape : Rectangle
		[王五, 王五王五]
		Shape : Rectangle
		[王五]
*/
		
	}
}
