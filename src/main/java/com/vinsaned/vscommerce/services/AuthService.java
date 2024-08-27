package com.vinsaned.vscommerce.services;

import com.vinsaned.vscommerce.entities.User;
import com.vinsaned.vscommerce.services.exceptions.ForBidenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    public void  validateSelfOrAdmin(long userId){
        User me = userService.authenticated();
        if(!me.hasHole("ROLE_ADMIN") && !me.getId().equals(userId)){
            throw  new ForBidenException("Access denied");
        }
    }
}
