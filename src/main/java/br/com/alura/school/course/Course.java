package br.com.alura.school.course;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

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
@Table(name="Course")
public class Course {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Size(max = 10)
	@NotBlank
	@Column(nullable = false, unique = true)
	private String code;

	@Size(max = 20)
	@NotBlank
	@Column(nullable = false, unique = true)
	private String name;

	private String description;

	@ManyToMany
	@JoinTable(name = "tb_user_course", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	Set<User> enrolls = new HashSet<>();
	
	private Integer quantityEnrolls;

	@Deprecated
	protected Course() {
	}

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
		return enrolls;
	}

	public void setUsers(Set<User> users) {
		this.enrolls = users;
	}

	public Integer getQuantityEnrolls() {
		return quantityEnrolls;
	}

	public void setQuantityEnrolls(Integer quantityEnrolls) {
		this.quantityEnrolls = quantityEnrolls;
	}

}
