package co.kr.umbbalook.core.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class UserVO extends org.springframework.security.core.userdetails.User{
	/**
	 *
	 */
	private static final long serialVersionUID = 2550249307461767130L;

	public UserVO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 생성된 토큰키
	 */
	private String user_id = "";
	private String passwd = "";
}
// :)--