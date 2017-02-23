package com.mybank.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mybank.dao.UserDao;
import com.mybank.domain.Korisnik;
import com.mybank.domain.KorisnikRoles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        Korisnik korisnik = getUserDao().findByUserName(username);
        List<GrantedAuthority> authoritys = buildUserAuthority(korisnik.getKorisnikRole());
        User user = new User(korisnik.getKorisnickoIme(), korisnik.getLozinka(), authoritys);
        return user;
    }

    private List<GrantedAuthority> buildUserAuthority(Set<KorisnikRoles> userRoles) {
        List<GrantedAuthority> setAuthorities = new ArrayList<>();
        for (KorisnikRoles userRole : userRoles) {
            setAuthorities.add(new SimpleGrantedAuthority(userRole.getRola()));
        }
        return setAuthorities;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
