package vn.edu.fpt;

import net.andreinc.mockneat.MockNeat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vn.edu.fpt.entity.*;
import vn.edu.fpt.repository.*;

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
//        MockNeat m = MockNeat.threadLocal();
//        Set<Role> roleSet = new HashSet<>();
//        roleSet.add( roleRepository.findById( 2 ) );
//        List<User> userList = m.reflect( User.class )
//                .field( "username", m.strings().size( m.ints().range( 5, 20 ) ) )
//                .field( "password", m.strings().size( m.ints().range( 5, 20 ) ) )
//                .field( "enable", m.bools() )
//                .field( "roles", roleSet )
//                .list( 50 )
//                .val();
//
//        userRepository.save( userList );
    }

    @Autowired
    private NotificationResponsitory notificationResponsitory;

    @Test
    public void test1() {
        Notification notification = new Notification();
        notification.setImageURL( "abc" );
        notification.setStatus( 1 );
        notification.setImageURL( "abc" );
        notificationResponsitory.save( notification );
    }

    @Autowired
    private NotificationFeedbackRepository notificationFeedbackRepository;

    @Test
    public void test2() {
        Notification notification = notificationResponsitory.findById( (long) 4 );
        System.out.println( notification.toString() );
        NotificationFeedback notificationFeedback = new NotificationFeedback();
        notificationFeedback.setDescription( "abc" );
        notificationFeedback.setNotification( notification );
        notificationFeedbackRepository.save( notificationFeedback );
    }
}
