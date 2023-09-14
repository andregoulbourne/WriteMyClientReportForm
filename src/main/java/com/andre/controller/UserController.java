package com.andre.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andre.dao.IUser;
import com.andre.model.User;
import com.andre.model.UserDTO;
import com.andre.service.UserService;


@RestController
//@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("users")
public class UserController {
	
    @Autowired
    IUser repo;
    
    @Autowired
    UserService service;
    
    private static Logger logger = LogManager.getLogger(UserController.class);
    
    /**
     * Creates a new User in the database
     * @param User new User being created
     * @return the representation of the User with its newly generated primary key.
     */
    @PostMapping
    public User postUser(@RequestBody UserDTO userDto) {
        return repo.save(service.userDtoToUser(userDto));
    }


    /**
     * Retrieves all User stored in the database
     * @return List of all User in the database in JSON format
     */
    @GetMapping
    public List<User> getAll() {
        return repo.findAll();
    }
   
    /**
     * Retrieves an User based on the given ID
     * @param id id of the User
     * @return Single User found
     */
    @GetMapping("/{id}")
    public User getUser(@PathVariable(name = "id") int id) {
    	User result = null;
    	
        Optional<User> user = repo.findById(id);
        if(user.isPresent())
        	result = user.get();
        
        return result;
    }
    
    /**
     * Retrieves an User based on the given ID
     * @param username of the User
     * @return Single User found
     */
    @PostMapping("/login")
    public User getUser(@RequestBody UserDTO userDto) {
        User userRetrieved = repo.findByEmailId(userDto.getEmailId());
        if(userRetrieved != null && userRetrieved.getPwd().equals(userDto.getPwd())) {
        	logger.info("Login success ...");
        } else {
        	logger.info("Login failure ...");
        	userRetrieved = null;
        }
        
        return userRetrieved;
    }
    
	/**
	 * 
	 * @param id     of already existing user
	 * @param user with changes to update
	 * @return the newly changed user
	 */
	@PutMapping
	public User putUser(@RequestBody UserDTO userDTO) {
		User user = null;
		try {
			user = new User(userDTO.getId(), userDTO.getUsername(), userDTO.getPwd(), userDTO.getFirstName(),
					userDTO.getLastName(), userDTO.getMiddleName(), userDTO.getEmailId(), userDTO.getPhone(), userDTO.isAdmin(), userDTO.isVendor());
			
			Optional<User> update = repo.findById(user.getId());
			if (update.isPresent()) {
				User newUser = update.get();
				repo.saveAndFlush(newUser);
			} else {
				user = repo.save(user);
			}
			
		} catch (Exception e) {
			logger.error("Exception occurred Updating the user ...");
			user = null;
		}
		
		return user;
	}


    /**
     * Deletes the associated user
     * @param UserId ID of the about me being deleted
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(name = "id") int userId) {
	    repo.deleteById(userId);
    }	

}