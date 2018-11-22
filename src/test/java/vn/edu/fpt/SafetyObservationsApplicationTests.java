package vn.edu.fpt;

import net.andreinc.mockneat.MockNeat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vn.edu.fpt.entity.Feedback;
import vn.edu.fpt.entity.Role;
import vn.edu.fpt.entity.User;
import vn.edu.fpt.repository.FeedbackRepository;
import vn.edu.fpt.repository.RoleRepository;
import vn.edu.fpt.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith( SpringRunner.class )
@SpringBootTest
public class SafetyObservationsApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Test
    public void generatingFeedbackData() {
        MockNeat m = MockNeat.threadLocal();
        List<Feedback> feedbackList = m.reflect( Feedback.class )
                .field( "time", new Date() )
                .field( "isReject", m.bools() )
                .field( "isRead", m.bools() )
                .field( "feedbackDescription", m.strings().size( m.ints().range( 10, 100 ) ) )
                .list( 50 )
                .val();

        feedbackRepository.save( feedbackList );
    }

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() {
        MockNeat m = MockNeat.threadLocal();
        Set<Role> roleSet = new HashSet<>();
        roleSet.add( roleRepository.findById( 2 ) );
        List<User> userList = m.reflect( User.class )
                .field( "username", m.strings().size( m.ints().range( 5, 20 ) ) )
                .field( "password", m.strings().size( m.ints().range( 5, 20 ) ) )
                .field( "enable", m.bools() )
                .field( "roles", roleSet )
                .list( 50 )
                .val();

        userRepository.save( userList );
    }
}
