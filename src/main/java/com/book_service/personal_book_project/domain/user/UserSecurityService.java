package com.book_service.personal_book_project.domain.user;


import com.book_service.personal_book_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> _user = this.userRepository.findByEmail(username);
        if(_user.isEmpty()){
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        Users user = _user.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if("kjk0385@naver.com".equals(username)){
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue())); //일단 관리자
        }
        else {
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue())); //일단 일반 유저 권한만...
        }
        return new User(user.getEmail(),user.getPassword(),authorities); // security 클래스
    }
}
