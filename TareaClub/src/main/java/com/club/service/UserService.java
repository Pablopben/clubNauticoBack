package com.club.service;

import com.club.dto.UserDTO;
import com.club.entities.User;
import com.club.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService{

    @Autowired
    private UserRepository userRepository;

    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    public UserDTO getByEmail(String email){
        return transformaDTO(this.userRepository.getUserByEmail(email));
    }

    private UserDTO transformaDTO (User user){
        return new UserDTO(user.getUsername(), user.getEmail(), user.getFirstName(), user.getLastName());
    }


}
