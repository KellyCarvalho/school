package br.com.alura.school.course;

import br.com.alura.school.support.validation.Unique;
import br.com.alura.school.user.User;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NewCourseRequest {

    @Unique(entity = Course.class, field = "code")
    @Size(max=10)
    @NotBlank
    @JsonProperty
    private final String code;

    @Unique(entity = Course.class, field = "name")
    @Size(max=20)
    @NotBlank
    @JsonProperty
    private final String name;

    @JsonProperty
    private final String description;
    

    
    private Set<User> users = new HashSet<>();

    public NewCourseRequest(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }
    
    public NewCourseRequest(Course entity) {
    	this.code=entity.getCode();
		this.name = entity.getName();
    	this.description=entity.getDescription();
    	
    }
    
    public String getCode() {
        return code;
    }

    Course toEntity() {
        return new Course(code, name, description);
    }
    
    public NewCourseRequest(Course entity, Set<User> users) {
    	this(entity);
    	users.forEach(user -> this.users.add(user));
    	
    	
    }
 

    

}
