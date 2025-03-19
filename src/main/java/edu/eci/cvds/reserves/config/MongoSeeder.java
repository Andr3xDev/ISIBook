package edu.eci.cvds.reserves.config;

import edu.eci.cvds.reserves.model.*;
import edu.eci.cvds.reserves.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Configuration class for seeding the MongoDB database with initial data.
 */
@Configuration
public class MongoSeeder {

    /**
     * Bean that initializes the database with default users, classrooms, and
     * reserves.
     *
     * @param userRepository      the repository for user entities
     * @param classroomRepository the repository for classroom entities
     * @param reserveRepository   the repository for reserve entities
     * @return a CommandLineRunner that seeds the database
     */
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, ClassroomRepository classroomRepository,
            ReserveRepository reserveRepository) {
        return args -> {
            // Populate users if the user repository is empty
            if (userRepository.count() == 0) {
                User admin = new User("Admin", "admin", "password123", "Admin", "admin@example.com");
                User alfredo = new User("Alfredo", "user123", "password123", "Teacher", "alfredo@gmail.com");
                User pedro = new User("Pedro", "user456", "password456", "Teacher", "pedro@gmail.com");
                User maria = new User("Maria", "user789", "password789", "Teacher", "maria@gmail.com");
                User juan = new User("Juan", "user101", "password101", "Teacher", "juan@gmail.com");

                userRepository.saveAll(List.of(admin, alfredo, pedro, maria, juan));
            }

            // Populate classrooms if the classroom repository is empty
            if (classroomRepository.count() == 0) {
                Classroom roomA = new Classroom(null, "Room A", "Building 1", new Specs());
                Classroom roomB = new Classroom(null, "Room B", "Building 2", new Specs());
                Classroom roomC = new Classroom(null, "Room C", "Building 3", new Specs());
                Classroom roomD = new Classroom(null, "Room D", "Building 4", new Specs());
                classroomRepository.saveAll(List.of(roomA, roomB, roomC, roomD));
            }

            // Populate reserves if the reserve repository is empty
            if (reserveRepository.count() == 0) {
                LocalDateTime Time1 = LocalDateTime.of(2025, 3, 12, 9, 0);
                LocalDateTime Time2 = LocalDateTime.of(2025, 3, 12, 10, 30);
                LocalDateTime Time3 = LocalDateTime.of(2025, 3, 12, 12, 0);
                LocalDateTime Time4 = LocalDateTime.of(2025, 3, 12, 13, 30);
                LocalDateTime Time5 = LocalDateTime.of(2025, 3, 12, 15, 0);

                Reserve reserve1 = new Reserve("user123", "roomA", Time1, Time1.plusMinutes(90), "ACTIVE", false,
                        "Meeting", "");
                Reserve reserve2 = new Reserve("user456", "roomB", Time2, Time2.plusMinutes(90), "ACTIVE", true,
                        "Lecture", "Weekly");
                Reserve reserve3 = new Reserve("user789", "roomC", Time3, Time3.plusMinutes(90), "ACTIVE", false,
                        "Workshop", "");
                Reserve reserve4 = new Reserve("user101", "roomD", Time4, Time4.plusMinutes(90), "ACTIVE", false,
                        "Seminar", "");
                Reserve reserve5 = new Reserve("user123", "roomA", Time5, Time5.plusMinutes(90), "ACTIVE", true,
                        "Training", "Biweekly");
                reserveRepository.saveAll(List.of(reserve1, reserve2, reserve3, reserve4, reserve5));
            }

            System.out.println("Database populated successfully!");
        };
    }
}