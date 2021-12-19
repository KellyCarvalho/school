package br.com.alura.school.course;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.alura.school.user.User;

@Entity

public class Course {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Size(max=10)
    @NotBlank
    @Column(nullable = false, unique = true)
    private String code;

    @Size(max=20)
    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    private String description;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @GeneratedValue(strategy=GenerationType.TABLE, generator="tb_user_course")
    @TableGenerator(
            name="tb_user_course",
            table="GENERATOR_TABLE",
            pkColumnName = "key",
            valueColumnName = "next",
            pkColumnValue="course",
            allocationSize=30
        )
    @JoinTable(name="tb_user_course", joinColumns = @JoinColumn(name ="user_id"),inverseJoinColumns =@JoinColumn(name="course_id"))
    private Set<User> users = new HashSet<>();

    
    
    @Deprecated
    protected Course() { }

    Course(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    String getCode() {
        return code;
    }

    String getName() {
        return name;
    }

    String getDescription() {
        return description;
    }

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
