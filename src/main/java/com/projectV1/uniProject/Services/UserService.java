package com.projectV1.uniProject.Services;

import com.projectV1.uniProject.Entities.Users;
import com.projectV1.uniProject.Exceptions.RoleInvalidException;
import com.projectV1.uniProject.Repositories.UserRepository;
import com.projectV1.uniProject.Utils.JwtUtil;
import com.projectV1.uniProject.Utils.Response;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Log4j2
@AllArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;


    public Object authenticateUser(String username, String password) {

        Users user = userRepository.findByUsername(username);
        if (user == null) {
            Response response = new Response(401, "Username not found", null);
            return response;
        }

        if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
            Response response = new Response(401, "Incorrect Password", null);
            return response;
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        user.setToken(jwt);
        Response response = new Response(200, "User logged in successfully", user);
        return response;

    }

    public Response registerUser(Users user) throws RoleInvalidException {
        String role = user.getRole();
        if(!role.equalsIgnoreCase("Student")||!role.equalsIgnoreCase("Instructor")){
            throw new RoleInvalidException(role + " is not a valid role!");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        Response response = new Response(200, "User registered Successfully", user);
        return response;
    }
}