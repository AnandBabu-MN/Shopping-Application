package com.shopping.application.user.serviceimpl;

import com.shopping.application.config.ErrorCodes;
import com.shopping.application.user.entity.User;
import com.shopping.application.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException(ErrorCodes.USER_NOT_FOUND));
        return new UserDetailsGroup(user);
    }
}
