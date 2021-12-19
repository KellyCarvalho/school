package br.com.alura.school.course;

public class CourseEnrollResponse {
	
	private String email;
	private Integer quantityEnrolls;
	
	
	
	
	public CourseEnrollResponse(String email, Integer quantityEnrolls) {
		
		this.email = email;
		this.quantityEnrolls = quantityEnrolls;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getQuantity_enrolls() {
		return quantityEnrolls;
	}
	public void setQuantity_enrolls(Integer quantity_enrolls) {
		this.quantityEnrolls = quantity_enrolls;
	}

}
