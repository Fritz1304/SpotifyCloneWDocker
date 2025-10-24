package musicfy.model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
@Builder
@Document(collection = "users")
public class User {
	@Id
	private String id;
	@Indexed(unique = true)
	private String email;
	private String password;
	private Role rol = Role.USER;
	
	public enum Role {
		USER, ADMIN
	}

	public User(String id, String email, String password, Role rol) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.rol = rol;
	}

	public User() {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRol() {
		return rol;
	}

	public void setRol(Role rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", rol=" + rol + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, password, rol);
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
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(password, other.password) && rol == other.rol;
	}
	
	

}
