package musicfy.dto;

import java.util.Objects;

public class UserResponse {

	private String id;
	private String email;
	private Role role;
	
	public enum Role {
		USER, ADMIN
	}

	public UserResponse(String id, String email, Role role) {
		super();
		this.id = id;
		this.email = email;
		this.role = role;
	}

	public UserResponse() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserResponse [id=" + id + ", email=" + email + ", role=" + role + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserResponse other = (UserResponse) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && role == other.role;
	}
	
	
}
