package br.com.alura.school.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.alura.school.support.validation.Unique;


public class NewUserRequest {

    @Unique(entity = User.class, field = "username")
    @Size(max=20)
    @NotBlank
    @JsonProperty
    private final String username;

    @Unique(entity = User.class, field = "email")
    @NotBlank
    @Email
    @JsonProperty
    private final String email;
    
	private  Integer quantityCourses;
    
   

    NewUserRequest(String username, String email) {
        this.username = username;
        this.email = email;
      
    }
    
    NewUserRequest(String username) {
        this.username = username;
		this.email = this.getUsername();
	     this.quantityCourses=this.getQuantityCourses();
      
    }
    
    public NewUserRequest(User user) {
      this.username=user.getUsername();
      this.email=user.getEmail();
      //this.quantityCourses=user.getEnrolls();
      
    }
    
    



    public String getUsername() {
        return username;
    }

    User toEntity() {
        return new User(username, email);
    }

	public Integer getQuantityCourses() {
		return quantityCourses;
	}

	


	
  
}
