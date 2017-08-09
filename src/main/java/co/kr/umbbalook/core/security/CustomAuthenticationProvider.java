package co.kr.umbbalook.core.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CustomAuthenticationProvider.class);

	@Autowired
	SecurityUserDetailService userService;

	@Autowired
	ShaPasswordEncoder passwdEncode;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		String user_id = authentication.getName();
		String passwd = (String) authentication.getCredentials();


		UserDetails user;

		Collection<? extends GrantedAuthority> authorities;
		user = userService.loadUserByUsername(user_id.toLowerCase());
		if(null == user){
			user = userService.loadUserByUsername(user_id.toUpperCase());
		}

		if(null == user){
			throw new UsernameNotFoundException("등록된 사용자가 없습니다.");
		}

		authorities = user.getAuthorities();

		return new UsernamePasswordAuthenticationToken(user, passwd, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
