package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        user.getAuthorities().stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList());

        if (user == null) {
            throw new UsernameNotFoundException(String.format("Пользователя с именем '%s' не найдено", username));
        }
        return user;
    }

    //берет пачку ролей и делает такую же пачку для авторитиес


}
