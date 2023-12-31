package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.techacademy.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	private final UserService service;

	public UserController(UserService service) {
		this.service =service;
	}

	/**一覧画面を表示*/
	@GetMapping("/list")
	public String getList(Model model) {
		//全件検索結果をmodelに登録
		model.addAttribute("userList",service.getUserList());
		//User/List.htmlに画面遷移
		return "user/list";
	}

}
