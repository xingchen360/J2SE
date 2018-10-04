package com.noteshare.designPatterns.transferObject.demo1;

/**
 * 这模式没看懂，不像有啥模式就是一个实体类包装了下，不知道是不是我找的例子有问题
 * 该模式是作为网络中一种传输数据的方式，它利用简单的POJO对象作为传输对象，相当于一种网络传输格式的约定。
 */
public class TransferObjectPatternDemo {
	public static void main(String[] args) {
		StudentBO studentBusinessObject = new StudentBO();

		// 输出所有的学生
		for (StudentVO student : studentBusinessObject.getAllStudents()) {
			System.out.println("Student: [RollNo : " + student.getRollNo() + ", Name : " + student.getName() + " ]");
		}

		// 更新学生
		StudentVO student = studentBusinessObject.getAllStudents().get(0);
		student.setName("Michael");
		studentBusinessObject.updateStudent(student);

		// 获取学生
		studentBusinessObject.getStudent(0);
		System.out.println("Student: [RollNo : " + student.getRollNo() + ", Name : " + student.getName() + " ]");
	}
}
