package com.eagle.interview.controller;



import com.eagle.interview.security.SQLInjection;
import com.eagle.interview.security.Student;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
public class IndexController implements ApplicationContextAware {

	ApplicationContext applicationContext;

	@Autowired
	RedisTemplate<String, String> redisTemplate;


	//sql注入
	//http://localhost:8080/query
	@PostMapping("/query")
	public List query(@RequestBody Student student) throws Exception {
		System.out.println("the name is:"+student.getName());
//		String name = "zhangsan' or '1=1' UNION SELECT 1,2,3,GROUP_CONCAT(table_name) FROM information_schema.`TABLES` WHERE table_schema=DATABASE()#" ;
//		student.setName(name);
		String sql = "select id, name,deptNo, cardno from student where name='"+student.getName()+"'";
		final List<Student> result = SQLInjection.getResult(sql);

		final List<Integer> list = Arrays.asList(1, 2, 3, 4);
		return result;
	}

	//http://localhost:8080/getCardNo
	//水平越权
	@PostMapping("/getCardNo")
	public String getCardNo(@RequestBody Student student) throws Exception {
		String sql = "select id, name,deptNo, cardno from student where id="+student.getId()+"";
		List<Student> result = SQLInjection.getResult(sql);
		if(result.isEmpty()){
			return "无该用户";
		}
		String cardno = result.get(0).getCardno();
		String name = result.get(0).getName();
		return name+" 的银行卡号是："+cardno;

	}

	//http://localhost:8080/updatePsw
	//垂直越权
	@PostMapping("/updatePsw")
	public String updatePsw(@RequestBody Student student) throws Exception {
		String sql = "update student set password='"+student.getPsw()+"' where id="+student.getId();
		String result = SQLInjection.updateResult(sql);
		return result;

	}

	//http://localhost:8080/login
	//暴力破解
	@PostMapping("/login")
	public String login(@RequestBody Student student) throws Exception {
		String sql = "select id, name, password from student where name='"+student.getName()+"'";
		List<Student> students = SQLInjection.getByName(sql);
		if(students.isEmpty()){
			return "无该用户";
		}
		for (Student s : students) {
			if (s.getPsw().equals(student.getPsw())) {
				return student.getName() + " 登陆成功!!!";
			}
		}
		return "无该用户";

	}




	//http://localhost:8080/forgotpsw
	// 密码找回漏洞2
	@GetMapping("/resetpsw")
	public String resetpsw() throws Exception {

		return "重置密码成功！！";

	}


	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
