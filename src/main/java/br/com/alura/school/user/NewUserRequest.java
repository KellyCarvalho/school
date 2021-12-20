package br.com.alura.school.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.alura.school.support.validation.Unique;


public class NewUserRequest {

    @Unique(entity = User.class, field = "username")
    @Size(max=20,message="Maximum characters is 20")
    @NotBlank(message="Username is Required!")
    @JsonProperty
    private final String username;

    @Unique(entity = User.class, field = "email")
    @NotBlank(message="Email is Required!")
    @Email(message="Email should be valid")
    @JsonProperty
    private final String email;
    

    
   




	NewUserRequest(String username, String email) {
        this.username = username;
        this.email = email;
      
    }
    
    NewUserRequest(String username) {
        this.username = username;
		this.email = this.getUsername();
	    
      
    }
    
    public NewUserRequest(User user) {
      this.username=user.getUsername();
      this.email=user.getEmail();

      
    }
    
    



    public String getUsername() {
        return username;
    }

    User toEntity() {
        return new User(username, email);
    }

	
	


	
  
}
