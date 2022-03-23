package bujo.web.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_details")
public class UserDetails {
	
	@Id
	@Column(name="username")
	private String username;

	@Column(name="email")
	private String email;

	public UserDetails(String username, String email) {
		this.username = username;
		this.email = email;
	}

	public UserDetails() {}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserDetails [username=" + username + ", email=" + email + "]";
	}
	
	
	
}
