package br.com.alura.school.user;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.alura.school.course.Course;

public class UserResponse {

    @JsonProperty
    private final String username;

    @JsonProperty
    private final String email;
    
    @JsonProperty
    private Set<Course> courses = new HashSet<>();
    
    @JsonProperty
    private Integer quantidade_matriculas;
    

    public UserResponse(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.quantidade_matriculas=user.getEnrolls();
    }
    
   
    
    public Integer countCourses(Set<Course> courses) {
    	
    	int quantity = courses.size();
    	
		return quantity;
    	
    	
    	
    }
  
}
