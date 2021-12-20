package br.com.alura.school.course;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.alura.school.user.NewUserRequest;
import br.com.alura.school.user.User;
import br.com.alura.school.user.UserRepository;

@RestController
public class CourseController {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    
 

    CourseController(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/courses")
    Page<CourseResponse>allCourses(Pageable pageable){
    	
    	Page<Course> list= courseRepository.findAll(pageable);
    	
    	return list.map(x-> new CourseResponse(x));
	
 }

    @GetMapping("/courses/{code}")
    ResponseEntity<CourseResponse> courseByCode(@PathVariable("code") String code) {
        Course course = courseRepository.findByCode(code).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("Course with code %s not found", code)));
        return ResponseEntity.ok(new CourseResponse(course));
    }

    @PostMapping("/courses")
    ResponseEntity<Void> newCourse(@RequestBody @Valid NewCourseRequest newCourseRequest) {
        courseRepository.save(newCourseRequest.toEntity());
        URI location = URI.create(format("/courses/%s", newCourseRequest.getCode()));
        return ResponseEntity.created(location).build();
    }
   
    @PostMapping("/courses/{courseCode}/enroll")
    ResponseEntity<Void> enroll(@PathVariable("courseCode") String courseCode, @RequestBody NewUserRequest newUserRequest){
    	Course course =  courseRepository.findByCode(courseCode).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("Course with code %s not found", courseCode)));
    	System.out.println(courseCode);
    	
    
    	
    	String userName = newUserRequest.getUsername();
    	
    	User user = userRepository.findByUsername(userName).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("User with username %s not found", courseCode)));
    	
    	 List<User> users =  userRepository.findAll();
    
    	
    	
    	if(user!=null&&!course.getUsers().contains(user)) {
    		course.getUsers().add(user);
    		 
    		courseRepository.save(course);
        	
    	}else {
    		return ResponseEntity.badRequest().build();
    	}
    	

		  
		
    
 	   URI location = URI.create(format("/courses/%s", course.getCode()));
    	
    	  return ResponseEntity.created(location).build();
    	
    	
    	
    }
  @GetMapping("/courses/enroll/report")
    ResponseEntity<List<CourseEnrollResponse>> enrollReport(){
	 
    	
    	List<CourseEnrollResponse> courseEnrollResponses = new ArrayList<>();
    	List<User>   users=  userRepository.findAll();
    	
    	
    	for (User user : users) {
    		
    		
    		
    		
    		if(user.getCourses().size()>0) {
    			courseEnrollResponses.add(new CourseEnrollResponse(user.getEmail(),user.getCourses().size()));
    		}

    		
		}
    	
    	if(courseEnrollResponses.size()==0) {
    		 return  ResponseEntity.noContent().build();
    	}

   
  return  ResponseEntity.ok().body(courseEnrollResponses);
    	
    	
    }
     
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
