package musicfy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import musicfy.dto.AuthRequest;
import musicfy.dto.AuthResponse;
import musicfy.dto.RegisterRequest;
import musicfy.dto.UserResponse;
import musicfy.model.User;
import musicfy.service.AppUserDetailService;
import musicfy.service.UserService;
import musicfy.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final AppUserDetailService userDetailService;
    private final JwtUtil jwtUtil;
    
    
	public AuthController(UserService userService, AuthenticationManager authenticationManager,
			AppUserDetailService userDetailService, JwtUtil jwtUtil) {
		super();
		this.userService = userService;
		this.authenticationManager = authenticationManager;
		this.userDetailService = userDetailService;
		this.jwtUtil = jwtUtil;
	}
    
	
	  @PostMapping("/login")
	    public ResponseEntity<?> Login(@RequestBody AuthRequest request) {
	        try{
	            //Authenticate the user
	            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

	            //Load the user details
	            UserDetails userDetails = userDetailService.loadUserByUsername(request.getEmail());
	            User existingUser = userService.findByEmail(request.getEmail());

	            //Generate a token jwt
	           String token = jwtUtil.generateToken(userDetails, existingUser.getRol().name());

	            return ResponseEntity.ok(new AuthResponse(token, request.getEmail(), existingUser.getRol().name()));
	        } catch (BadCredentialsException e){
	            return ResponseEntity.badRequest().body("Email or password is incorrect");
	        }catch (Exception e){
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	        }
	    }

	    @PostMapping("/register")
	    public ResponseEntity<?> Register(@RequestBody RegisterRequest request) {
	        try {
	            UserResponse response = userService.registerUser(request);
	            return ResponseEntity.ok(response);

	        } catch (RuntimeException e) {
	            return ResponseEntity.badRequest().body(e.getMessage());
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

	        }
	    }
	
	
    

}
