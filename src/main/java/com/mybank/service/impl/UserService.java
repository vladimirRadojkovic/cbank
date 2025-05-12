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
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        System.out.println("DEBUG: UserService.loadUserByUsername called for: " + username);
        
        try {
            Korisnik korisnik = userDao.findByUserName(username);
            if (korisnik == null) {
                System.out.println("DEBUG: No user found with username: " + username);
                throw new UsernameNotFoundException("User not found");
            }
            
            System.out.println("DEBUG: User found: " + korisnik.getKorisnickoIme());
            System.out.println("DEBUG: Building user authorities...");
            
            List<GrantedAuthority> authoritys = buildUserAuthority(korisnik.getKorisnikRole());
            
            System.out.println("DEBUG: Created " + authoritys.size() + " authorities");
            for (GrantedAuthority auth : authoritys) {
                System.out.println("DEBUG: Authority: " + auth.getAuthority());
            }
            
            return new User(korisnik.getKorisnickoIme(), korisnik.getLozinka(), authoritys);
        } catch (Exception e) {
            System.out.println("ERROR in loadUserByUsername: " + e.getMessage());
            e.printStackTrace();
            throw new UsernameNotFoundException("Error loading user", e);
        }
    }

    private List<GrantedAuthority> buildUserAuthority(Set<KorisnikRoles> userRoles) {
        System.out.println("DEBUG: Building authorities from " + (userRoles != null ? userRoles.size() : "null") + " roles");
        List<GrantedAuthority> setAuthorities = new ArrayList<>();
        if (userRoles != null) {
            for (KorisnikRoles userRole : userRoles) {
                System.out.println("DEBUG: Adding role: " + userRole.getRola());
                setAuthorities.add(new SimpleGrantedAuthority(userRole.getRola()));
            }
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
