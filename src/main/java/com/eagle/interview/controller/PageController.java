package com.eagle.interview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

@Controller
public class PageController {
	@Autowired
	RedisTemplate<String, String> redisTemplate;

	//http://localhost:8080/forgotpsw/zhangsan
	// 密码找回漏洞
	@RequestMapping(value = "/forgotpsw/{name}")
	public String forgotpsw(@PathVariable("name") String name, Model model) throws Exception {
		Random random = new Random(100000);
		int randomNum = random.nextInt(100000);


		redisTemplate.opsForValue().set(name+"-verify",String.valueOf(randomNum));

		model.addAttribute("verifycode",randomNum);
		model.addAttribute("message","登陆邮箱后，点击如下链接http://localhost:8080/resetpsw?verifycode="+randomNum+"重置密码");
		return "send";
//		return "redirect:/send.html?verifycode="+randomNum;

	}

	@PostMapping(value = "/success")
	public String done() throws Exception {
		return "redirect:/success.html";

	}
}
