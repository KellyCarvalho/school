package br.com.alura.school.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.alura.school.course.Course;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity

@Table(name="User")
public class User implements Serializable{

   
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = IDENTITY)

    private Long id;

    @Size(max=20)
    @NotBlank
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "enrolls")
    private Set<Course> courses = new HashSet<>();
    
    private Integer quantityCourses;
    
    
    





	@Deprecated
    protected User() {}

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
    
    public User(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

	public Set<Course> getCourses() {
		return courses;
	}

	@Override
	public int hashCode() {
		return Objects.hash(courses);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(courses, other.courses);
	}

	@Override
	public String toString() {
		return "User [courses=" + courses + "]";
	}

	public Integer getEnrolls() {
		return this.quantityCourses;
		}

	/*public void setEnrolls(Set<Course> courses) {
		this.quantityCourses = courses.size();
	}*/
	
	

	public Integer getQuantityCourses() {
		return quantityCourses;
	}

	public void setQuantityCourses(Integer quantityCourses) {
		this.quantityCourses = quantityCourses;
	}

    

    


}
