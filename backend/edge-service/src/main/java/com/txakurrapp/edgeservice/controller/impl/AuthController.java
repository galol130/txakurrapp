package com.txakurrapp.edgeservice.controller.impl;

import com.txakurrapp.edgeservice.enums.ERole;
import com.txakurrapp.edgeservice.model.Role;
import com.txakurrapp.edgeservice.model.User;
import com.txakurrapp.edgeservice.payload.request.LoginRequest;
import com.txakurrapp.edgeservice.payload.request.SignupRequest;
import com.txakurrapp.edgeservice.payload.response.JwtResponse;
import com.txakurrapp.edgeservice.payload.response.MessageResponse;
import com.txakurrapp.edgeservice.repository.RoleRepository;
import com.txakurrapp.edgeservice.repository.UserRepository;
import com.txakurrapp.edgeservice.security.jwt.JwtUtils;
import com.txakurrapp.edgeservice.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//@CrossOrigin()
@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println("<---------------- from LOGIN --------------->");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles
        ));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        //  Check if the username is not already taken
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: username already in use"));
        }

        //  Create new user's account
        User user = new User(signupRequest.getUsername(), passwordEncoder.encode(signupRequest.getPassword()));

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null || strRoles.isEmpty()) {
            Role petownerRole = roleRepository.findByName(ERole.ROLE_PETOWNER)
                    .orElseThrow(() -> new RuntimeException("Error: Role was not found"));
            roles.add(petownerRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ROLE_PETOWNER":
                        Role petownerRole = roleRepository.findByName(ERole.ROLE_PETOWNER)
                                .orElseThrow(() -> new RuntimeException("Error: Role was not found"));
                        roles.add(petownerRole);
                        break;
                    case "ROLE_ADMIN":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role was not found"));
                        roles.add(adminRole);
                        break;
                    case "ROLE_BUSINESS":
                        Role businessRole = roleRepository.findByName(ERole.ROLE_BUSINESS)
                                .orElseThrow(() -> new RuntimeException("Error: Role was not found"));
                        roles.add(businessRole);
                        break;
                    default:
//  WARNING
                        System.out.println("------> WARNING: NO ROLES ADDED TO NEW USER <----------");
                        break;
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}
