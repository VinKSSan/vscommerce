package com.vinsaned.vscommerce.dto;

import com.vinsaned.vscommerce.entities.User;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateUserDTO extends UserDTO{
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CreateUserDTO(long id, String name, String email, String phone, LocalDate birthDate, String uriPhoto, String password) {
        super(id,name,email,phone,birthDate,uriPhoto);
        this.password = password;
    }
    public CreateUserDTO(User entity) {
        super(
           entity.getId(),
           entity.getName(),
           entity.getEmail(),
           entity.getPhone(),
           entity.getBirthDate(),
           entity.getUriPhoto()
        );
       password = entity.getPassword();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getUriPhoto() {return uriPhoto;}

    public List<String> getRoles() {
        return roles;
    }
}
