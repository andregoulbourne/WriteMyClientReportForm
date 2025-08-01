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

/**
 * UserController is a REST controller that handles HTTP requests related to User operations.
 * It provides endpoints for creating, retrieving, updating, and deleting User entities.
 */
@RestController
@RequestMapping("users")
public class UserController {
	
    private final IUser repo;
    private final UserService service;

    @Autowired
    public UserController(IUser repo, UserService service) {
        this.repo = repo;
        this.service = service;
    }
    
    private static final Logger logger = LogManager.getLogger(UserController.class);
    
    /**
     * Creates a new User in the database
     * @param userDto new User being created
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
     * @param userDto of the User
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
	 * @param userDTO the user to be updated
	 * @return the newly changed user
	 */
	@PutMapping
	public User putUser(@RequestBody UserDTO userDTO) {
		User user;
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
     * @param userId ID of the about me being deleted
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(name = "id") int userId) {
	    repo.deleteById(userId);
    }	

}