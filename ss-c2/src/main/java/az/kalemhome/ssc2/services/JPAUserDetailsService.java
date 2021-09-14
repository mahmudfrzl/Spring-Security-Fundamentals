package az.kalemhome.ssc2.services;

import az.kalemhome.ssc2.dao.UserRepository;
import az.kalemhome.ssc2.models.User;
import az.kalemhome.ssc2.security.MyUserDetails;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;



public class JPAUserDetailsService implements UserDetailsService {
        private UserRepository userRepository;

    public JPAUserDetailsService() {
    }

    public JPAUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);

        User u = user.orElseThrow(()-> new UsernameNotFoundException("ERROR!"));
        return new MyUserDetails(u);
    }
}
