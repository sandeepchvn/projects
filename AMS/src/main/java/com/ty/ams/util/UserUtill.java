package com.ty.ams.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ty.ams.dto.UserDto;
import com.ty.ams.entity.User;

@Component
public class UserUtill {

	public List<UserDto> getUserDto(List<User> users) {
		List<UserDto> dtos = new ArrayList<>();
		for (User user : users) {
			dtos.add(getUserDto(user));
		}
		return dtos;
	}

	public UserDto getUserDto(User user) {

		return new UserDto(user.getUserId(), user.getEmpId(), user.getName());
	}

}
