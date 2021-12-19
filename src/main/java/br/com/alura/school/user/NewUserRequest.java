package br.com.alura.school.user;

import br.com.alura.school.course.Course;
import br.com.alura.school.support.validation.Unique;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


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
    
    private Set<Course> enrolls = new HashSet<>();

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

	public Set<Course> getEnrolls() {
		return enrolls;
	}
  
}
