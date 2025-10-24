package musicfy.dto;

public class AuthResponse {
	
    private String token;
    private String email;
    private String role;
    
    
	public AuthResponse(String token, String email, String role) {
		super();
		this.token = token;
		this.email = email;
		this.role = role;
	}


	public AuthResponse() {
		super();
	}


	@Override
	public String toString() {
		return "AuthResponse [token=" + token + ", email=" + email + ", role=" + role + "]";
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}
 
}
