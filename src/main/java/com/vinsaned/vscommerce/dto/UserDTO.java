package com.vinsaned.vscommerce.dto;

import com.vinsaned.vscommerce.entities.User;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    protected long id;
    protected String name;
    protected String email;
    protected String phone;
    protected LocalDate birthDate;
    protected String uriPhoto;
    protected List<String> roles = new ArrayList<>();

    public UserDTO(long id, String name, String email, String phone, LocalDate birthDate, String uriPhoto) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.uriPhoto = uriPhoto;
    }
    public UserDTO(User entity) {
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        phone = entity.getPhone();
        birthDate = entity.getBirthDate();
        uriPhoto = entity.getUriPhoto();
        for (GrantedAuthority role: entity.getAuthorities()){
            roles.add(role.getAuthority());
        }
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
