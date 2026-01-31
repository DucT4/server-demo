package com.cybersoft.uniclub.services.imp;

import com.cybersoft.uniclub.entity.Users;
import com.cybersoft.uniclub.repository.UserRepository;
import com.cybersoft.uniclub.services.AuthenticationServices;
import com.cybersoft.uniclub.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServicesImp  implements AuthenticationServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public String checkLogin(String email, String password) {
        String token = "";
        Optional<Users> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                String roleName = user.getRoles().getName();
                token = jwtHelper.generateToken(roleName);
            }

        }
        return token;
    }
}




