package com.study.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.study.bean.Person;

@Controller
public class Thymleaf {
	
	@RequestMapping("/thymleaf")
	public String thymleaf(ModelMap map) {
		map.put("thName", "张三");
		map.put("thAge", 12);
		map.put("thSex", 12);
		map.put("thLove", List.of("排球", "足球"));
		map.put("thObject", new Person("韩信", 99, '男'));
		return "thymleaf";
	}

}
