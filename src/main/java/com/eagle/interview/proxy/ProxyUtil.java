package com.eagle.interview.proxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class ProxyUtil {
	/**
	 * @param target 目标对象
	 * @return 代理对象
	 */
	public static Object newInstance(Object target) {
		Object proxy = null;
		// 开始拼接字符串
		Class targetInf = target.getClass().getInterfaces()[0];
		Method[] methods = targetInf.getDeclaredMethods();
		String line = System.lineSeparator();
		String tab = "\t";
		String infName = targetInf.getSimpleName();
		String content = "";
		String packageContent = "package com.dmz.proxy;" + line;
		String importContent = "import " + targetInf.getName() + ";" + line;
		String clazzFirstLineContent = "public class $Proxy implements " + infName + "{" + line;
		String filedContent = tab + "private " + infName + " target;" + line;
		String constructorContent = tab + "public $Proxy (" + infName + " target){" + line
				+ tab + tab + "this.target =target;"
				+ line + tab + "}" + line;
		String methodContent = "";
		for (Method method : methods) {
			String returnTypeName = method.getReturnType().getSimpleName();
			String methodName = method.getName();
			// Sting.class String.class
			Class args[] = method.getParameterTypes();
			String argsContent = "";
			String paramsContent = "";
			int flag = 0;
			for (Class arg : args) {
				String temp = arg.getSimpleName();
				//String
				//String p0,Sting p1,
				argsContent += temp + " p" + flag + ",";
				paramsContent += "p" + flag + ",";
				flag++;
			}
			if (argsContent.length() > 0) {
				argsContent = argsContent.substring(0, argsContent.lastIndexOf(",") - 1);
				paramsContent = paramsContent.substring(0, paramsContent.lastIndexOf(",") - 1);
			}

			methodContent += tab + "public " + returnTypeName + " " + methodName + "(" + argsContent + ") {" + line
					+ tab + tab + "System.out.println(\"proxy print log for " + methodName + "\");" + line
					+ tab + tab + "target." + methodName + "(" + paramsContent + ");" + line
					+ tab + "}" + line;

		}

		content = packageContent + importContent + clazzFirstLineContent + filedContent + constructorContent + methodContent + "}";
		//字符串拼接结束


		// 开始生成.java文件
		File file = new File("e:\\com\\eagle\\proxy\\$Proxy.java");
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			fw.write(content);
			fw.flush();
			fw.close();
			// .java文件生成结束


			// 开始进行编译
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

			StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
			Iterable units = fileMgr.getJavaFileObjects(file);

			JavaCompiler.CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
			t.call();
			fileMgr.close();
			// 编译结束，生成.class文件

			// 从G盘中加载class文件
			URL[] urls = new URL[]{new URL("file:e:\\\\")};
			URLClassLoader urlClassLoader = new URLClassLoader(urls);
			// 加载
			Class clazz = urlClassLoader.loadClass("com.eagle.proxy.$Proxy");
			// 加载结束

			// 构造代理对象
			Constructor constructor = clazz.getConstructor(targetInf);
			proxy = constructor.newInstance(target);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return proxy;
	}
}
