package com.techacademy.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.techacademy.entity.User;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class UserControllerTest {
	private MockMvc mockMvc;

	private final WebApplicationContext webApplicationContext;

	UserControllerTest(WebApplicationContext context){
		this.webApplicationContext = context;
	}


	@BeforeEach
	void beforeEach() {
	//spring Securityを有効にする
		mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.apply(springSecurity()).build();
	}

	@Test
	@DisplayName("User更新画面")
	@WithMockUser
	void testGetUser() throws Exception{
		//Httpリクエストに対するレスポンスの検証
		MvcResult result = mockMvc.perform(get("/user/update/1/")) // URLにアクセス
	            .andExpect(status().isOk()) // ステータスを確認
	            .andExpect(model().attributeExists("user")) // Modelの内容を確認
	            .andExpect(model().hasNoErrors()) // Modelのエラー有無の確認
	            .andExpect(view().name("user/update")) // viewの確認
	            .andReturn(); // 内容の取得

	        // userの検証
	        // Modelからuserを取り出す
	        User user = (User)result.getModelAndView().getModel().get("user");
	        assertEquals(user.getId(), 1);
	        assertEquals(user.getName(), "ちたお");
	    }

	    @Test//テスト機能追加は定型文
	    @DisplayName("変更内容２")
	    @WithMockUser
	    void testGetUserList() throws Exception {//testGet＋目的のもの（テスト対象）
	        // HTTPリクエストに対するレスポンスの検証
	        MvcResult result = mockMvc.perform(get("/user/list")) // URLにアクセス　URLはControllerの＠RequestMapping＋＠GetMappingのURLのこと
	            .andExpect(status().isOk()) // ステータスを確認
	            .andExpect(model().attributeExists("userlist")) // Modelの内容を確認　ModelはControllerの＠GetMappingのModelのこと　３２行目
	            .andExpect(model().hasNoErrors()) // Modelのエラー有無の確認
	            .andExpect(view().name("user/list")) // viewの確認
	            .andReturn(); // 内容の取得

	        // userlistの検証
	        // Modelからuserlistを取り出す
	        List<User> userlist = (List<User>)result.getModelAndView().getModel().get("userlist");//複数の情報をもらうときはList<user>で<>の中が複数ほしい情報
	        assertEquals(userlist.get(0).getId(), 1);//複数の場合、始まりは１じゃなく０
	        assertEquals(userlist.get(0).getName(), "ちたお");
	        assertEquals(userlist.get(1).getId(), 2);
	        assertEquals(userlist.get(1).getName(), "たねぞう");
	        assertEquals(userlist.get(2).getId(), 3);
	        assertEquals(userlist.get(2).getName(), "花子");
	    }
	}

