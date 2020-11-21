package com.eagle.interview.IKM;

import java.util.Calendar;
import java.util.Formatter;
import java.util.GregorianCalendar;

/**
 * 常规
 *
 * B b
 * 如果参数 arg 为 null，则结果为 "false"。如果 arg 是一个 boolean 值或Boolean，则结果为 String.valueOf() 返回的字符串。否则结果为 "true"。
 * H h
 * 如果参数 arg 为 null，则结果为 "null"。否则，结果为调用Integer.toHexString(arg.hashCode()) 得到的结果。
 * S s
 * 如果参数 arg 为 null，则结果为 "null"。如果 arg 实现 Formattable，则调用arg.formatTo。否则，结果为调用 arg.toString() 得到的结果。
 *
 * 字符
 * C c
 * 结果是一个 Unicode 字符
 *
 * 整数
 * d
 * 结果被格式化为十进制整数
 * o
 * 结果被格式化为八进制整数
 * X x
 * 结果被格式化为十六进制整数
 *
 * 浮点
 * E e
 * 结果被格式化为用计算机科学记数法表示的十进制数
 * f
 * 结果被格式化为十进制数
 * G g
 * 根据精度和舍入运算后的值，使用计算机科学记数形式或十进制格式对结果进行格式化。
 * A a
 * 结果被格式化为带有效位数和指数的十六进制浮点数
 *
 * 日期，日间
 * T t
 * 日期和时间转换字符的前缀
 *
 * 百分比
 * %
 * 结果为字面值 '%'
 *
 * 行分隔符
 * n
 * 结果为特定于平台的行分隔符
 */
public class Q54Formatter {
	public static void main(String[] args) {
		String stringA = "A";
		String stringB = "B";
		String stringNull = null;

		// builder的结果会追加在格式化后的结果的后面
		StringBuilder builder = new StringBuilder("C");
		Formatter fmt = new Formatter(builder);


		fmt.format("%s%s", stringA, stringB);
		System.out.println("Line 1: "+fmt);

		fmt.format("%-2s", stringB);
		System.out.println("Line 2: "+fmt); //格式会追加上面的

		fmt.format("%b", stringNull); //b输出结果位boolean
		System.out.println("Line 3: "+fmt);

//		fmtDemo();
	}

	private static void fmtDemo(){
		//%[argument_index$][flags][width][.precision]conversion
		Formatter f1 = new Formatter(System.out);
		//格式化输出字符串和数字
		f1.format("格式化输出：%s %d", "a", 1235);
		System.out.println("\n--------");

		//日期的格式化
		Calendar c = new GregorianCalendar();
		f1.format("当前日期:%1$tY-%1$tm-%1$te", c);
		System.out.println("\n--------");

		//日期的格式化，并将格式化结果存储到一个字符串变量中
		String s = String.format("当前日期:%1$tY-%1$tm-%1$te", c);
		System.out.println(s);
		System.out.println("\n--------");

		//2$：取第二个参数
		//-: 指定为左对齐，默认右对齐
		//5：最大输出宽度为20,不够会补空格，实际若超过则全部输出
		//.2：在此表示输出参数2的最大字符数量，如果是浮点数字，则表示小数部分显示的位数
		//s ：表示输入参数是字符串
		f1.format("%2$-5.2s %1$2s", "123", "456");

		//将格式化的结果存储到字符串
		System.out.println("\n--------");
		String fs = String.format("身高体重(%.2f , %d)", 173.2, 65);
		System.out.println(fs);

		//printf()本质上也是用System.out作为输出目标构造Formatter对象
		//调用format方法，并将printf的参数传给format方法而得。
		System.out.println("\n--------");
		System.out.printf("身高体重(%.2f , %d)", 180.2, 65);
	}
}
