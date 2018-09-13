package vn.edu.fpt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.entity.Demo;
import vn.edu.fpt.repository.DemoRepository;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private DemoRepository demoRepository;

    @Override
    public boolean login( String username, String password ) {
        Demo user = demoRepository.findDemoByUsername( username );

        if ( user != null ) {
            if ( user.getPasssword().equals( password ) ) return true;
        }
        return false;
    }
}
