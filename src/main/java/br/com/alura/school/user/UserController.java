package br.com.alura.school.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.server.ResponseStatusException;

import br.com.alura.school.exceptions.BadRequestException;
import br.com.alura.school.exceptions.DatabaseException;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class UserController {

    private final UserRepository userRepository;

    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users/{username}")
    ResponseEntity<UserResponse> userByUsername(@PathVariable("username") String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("User %s not found", username)));
    	
    	
    
        
        return ResponseEntity.ok(new UserResponse(user));
    }

    @PostMapping("/users")
    ResponseEntity<Void> newUser(@RequestBody @Valid NewUserRequest newUserRequest) {
    	
    	
    		 boolean user =userRepository.findByUsername(newUserRequest.getUsername()).isPresent(); 
    		
    	
    	
    	 
    	   if(user) {
    		   throw new BadRequestException("User Alredy Exist");
    	
    	   }
    	   
        userRepository.save(newUserRequest.toEntity());
        
        URI location = URI.create(format("/users/%s", newUserRequest.getUsername()));
        return ResponseEntity.created(location).build();
    }
    
    

}
