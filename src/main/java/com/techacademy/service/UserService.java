package com.techacademy.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techacademy.entity.User;
import com.techacademy.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;

	public UserService(UserRepository repository) {
		this.userRepository = repository;
	}

	/** 全件を検索して返す */
	public List<User> getUserList() {
		// リポジトリのfindAllメソッドを呼び出す
		return userRepository.findAll();
	}

	/**Userを１件検索して返す*/
	public User getUser(Integer id) {
		return userRepository.findById(id).get();
	}

	//User登録機能の追加
	/** userの登録をする*/
	@Transactional
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	//User登録機能の追記ここまで

	/**user削除をする*/
	@Transactional
	public void deleteUser(Set<Integer> idck) {
		for(Integer id : idck) {
			userRepository.deleteById(id);
		}
	}

}
