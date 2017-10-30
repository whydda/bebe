package co.kr.umbbalook.core.security;

import co.kr.umbbalook.service.mapper.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by whydda on 2017-08-03.
 */
@Service(value = "securityUserDetailService")
public class SecurityUserDetailService implements UserDetailsService {

    @Autowired
    private CommonMapper commonMapper;

    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserDetails userDetails = (UserDetails) getUser(userId);
        return userDetails;
    }

    private UserVO getUser(String userId){
//        Map<String, Object> map = commonMapper.selectTest();

        UserVO user = null;
//        if (map != null) {
            String username = "whydda";
            String password = "1234";
            //인증 부분
            user = new UserVO(username, password, AuthorityUtils.createAuthorityList("ROLE_USER"));
//        }
        return user;
    }
}
