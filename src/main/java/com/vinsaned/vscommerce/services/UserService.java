package com.vinsaned.vscommerce.services;

import com.vinsaned.vscommerce.dto.*;
import com.vinsaned.vscommerce.entities.Product;
import com.vinsaned.vscommerce.entities.Role;
import com.vinsaned.vscommerce.entities.User;
import com.vinsaned.vscommerce.repositories.RoleRepository;
import com.vinsaned.vscommerce.repositories.UserRepository;
import com.vinsaned.vscommerce.services.exceptions.DataBaseException;
import com.vinsaned.vscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository repo;

    @Autowired
    private RoleRepository repoR;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @Transactional(readOnly = true)
    public Page<UserMinDTO> findAll(Pageable pageable){
        Page<User> products = repo.findAll(pageable);
        return  products.map(UserMinDTO::new);
    }



    @Transactional
    public CreateUserDTO insert(CreateUserDTO dto ){
        User user = new User();
        if (!dto.getUriPhoto().startsWith("http")) {
            throw new IllegalArgumentException("Invalid URI for photo");
        }

        copyDtoToEntity(dto,user);
        Role clientRole = repoR.findByAuthority("ROLE_CLIENT")
                .orElseThrow(() -> new IllegalStateException("Default role 'ROLE_CLIENT' not found"));

        user.getRoles().add(clientRole);
        user = repo.save(user);
        return new CreateUserDTO(user);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if(!repo.existsById(id)) {
            throw new ResourceNotFoundException("id não encontrado! " + id);
        }
        try{
            repo.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("não foi possivel deletar, recurso não encontrado");
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException("Integridade violado, não é possivel deletar o Cliente de id: "+id);
        }
    }

    public void copyDtoToEntity(CreateUserDTO dto ,User entity){
        entity.setName(dto.getName());
        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setBirthDate(dto.getBirthDate());
        entity.setPhone(dto.getPhone());
        entity.setUriPhoto(dto.getUriPhoto());
        entity.setPassword(passwordEncoder.encode((dto.getPassword())));
    }
}
