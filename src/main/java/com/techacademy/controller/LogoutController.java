package com.techacademy.controller;

import org.springframework.web.bind.annotation.PostMapping;

public class LogoutController {
	/**ログアウト処理をする*/
	@PostMapping("/logout")
	public String postLogout() {
		//ログイン画面へリダイレクトする
		return "redirect:/login";
	}

}
