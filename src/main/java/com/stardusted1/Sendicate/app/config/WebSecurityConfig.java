package com.stardusted1.Sendicate.app.config;

import com.stardusted1.Sendicate.app.core.users.Deliveryman;
import com.stardusted1.Sendicate.app.core.users.NewUser;
import com.stardusted1.Sendicate.app.core.users.Provider;
import com.stardusted1.Sendicate.app.core.users.Receiver;
import com.stardusted1.Sendicate.app.service.System;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/","/assets/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    @Bean
    public PrincipalExtractor principalExtractor(System system){
        return map -> {
            String id = (String) map.get("sub");
            Optional<Deliveryman> deliveryman = system.deliverymanRepository.findById(id);
            if(deliveryman.isPresent()){
                return deliveryman.get();
            }
            Optional<Provider> provider = system.providerRepository.findById(id);
            if(provider.isPresent()){
                return provider.get();
            }
            Optional<Receiver> receiver = system.receiverRepository.findById(id);
            if(receiver.isPresent()){
                return receiver;
            }
            Optional<NewUser> newuser = Optional.of(system.newUserRepository.findById(id).orElseGet(() -> {
                NewUser newUser = new NewUser();
                newUser.setId(id);
                newUser.setName((String) map.get("name"));
                newUser.setEmail((String) map.get("email"));
                newUser.setPictureUrl((String) map.get("picture"));
                newUser.setLocale(system.getUserLocale((String) map.get("locale")));
                system.newUserRepository.save(newUser);
                return newUser;
            }));
            newuser.get().setUserAuthority();
            return newuser;
        };
    }
}
