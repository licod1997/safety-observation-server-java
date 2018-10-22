package vn.edu.fpt;

import net.andreinc.mockneat.MockNeat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vn.edu.fpt.entity.Feedback;
import vn.edu.fpt.repository.FeedbackRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

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
                .field("feedbackDescription", m.strings().size( m.ints().range( 10, 100 ) ))
                .list( 50 )
                .val();

        feedbackRepository.save( feedbackList );
    }
}
