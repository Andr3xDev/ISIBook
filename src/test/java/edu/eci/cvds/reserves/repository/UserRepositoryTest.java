package edu.eci.cvds.reserves.repository;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import edu.eci.cvds.reserves.model.User;
import edu.eci.cvds.reserves.utils.factory.UserFactory;

@DataMongoTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    User userTeacher;
    User userAdmin;

    Map<String, String> teacherParams = Map.of(
            "name", "Juan Jose",
            "userName", "juan.jose-p",
            "mail", "asd@mail.com",
            "passwd", "josePass");

    Map<String, String> adminParams = Map.of(
            "name", "Super Admin",
            "userName", "adminUser",
            "passwd", "123123");

    @BeforeEach
    void setUp() {
        UserFactory userFactory = new UserFactory();
        userAdmin = userFactory.createUser("Admin", adminParams);
        userTeacher = userFactory.createUser("Teacher", teacherParams);
    }
}