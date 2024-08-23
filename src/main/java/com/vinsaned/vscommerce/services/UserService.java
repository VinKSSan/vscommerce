package com.vinsaned.vscommerce.services;

import com.vinsaned.vscommerce.dto.ProductDTO;
import com.vinsaned.vscommerce.dto.UserDTO;
import com.vinsaned.vscommerce.entities.User;
import com.vinsaned.vscommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("Email not found");
        }
        return user;
    }

    protected User authenticated(){
        try{
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return repo.findByEmail(username);
        }catch (Exception e){
            throw  new UsernameNotFoundException("Invalid user");
        }
    }

    @Transactional
    public UserDTO getMe() {
        User entity = authenticated();
        return new UserDTO(entity);
    }
}
