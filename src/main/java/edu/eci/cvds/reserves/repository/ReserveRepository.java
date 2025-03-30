package edu.eci.cvds.reserves.repository;

import edu.eci.cvds.reserves.model.Reserve;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for Reserve entity.
 * Provides methods to perform CRUD operations on Reserve collection.
 */
public interface ReserveRepository extends MongoRepository<Reserve, String> {

    List<Reserve> findByUserId(String userId);

    List<Reserve> findByClassroomId(String classroomId);

    List<Reserve> findByStartDateBetween(LocalDateTime start, LocalDateTime end);

    List<Reserve> findByClassroomIdAndStartDate(String classroomId, LocalDateTime startDate);

    List<Reserve> findByStartDate(LocalDateTime startOfHour);
}
