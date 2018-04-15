package operation;

import ui.MainClass;
import java.util.*;
import java.io.*;

public class BaseOperation {
	public void showMenu()
	{
		System.out.println("欢迎进入灰太狼的图书管理系统!");
		System.out.println("图书管理:");
		System.out.println("请输入选项:");
		System.out.println("显示图书: 1");
		System.out.println("增加图书: 2");
		System.out.println("删除图书: 3");
		System.out.println("清空图书: 4");
		System.out.println("修改图书: 5");
		System.out.println(": 6");
	}

}
