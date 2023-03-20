package com.venky.serviceIMP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venky.model.Users;
import com.venky.payload.UserDto;
import com.venky.repository.UserRepository;
import com.venky.service.UserService;

@Service
public class UserServiceimp implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto createUser(UserDto userDto) {

		Users users = userDtoToEntity(userDto);
		Users savedUser = userRepository.save(users);

		return entityToUserDto(savedUser);
	}

	private Users userDtoToEntity(UserDto userDto) {
		Users users = new Users();
		users.setName(userDto.getName());
		users.setEmail(userDto.getEmail());
		users.setPassword(userDto.getPassword());
		return users;
	}

	private UserDto entityToUserDto(Users users) {
		UserDto userDto = new UserDto();
		userDto.setId(users.getId());
		userDto.setName(users.getName());
		userDto.setEmail(users.getEmail());
		userDto.setPassword(userDto.getPassword());
		return userDto;
	}
	
	

}
