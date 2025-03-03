package edu.eci.cvds.reserves.repository;

import edu.eci.cvds.reserves.model.Reserve;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ReserveRepository extends MongoRepository<Reserve, String> {
    List<Reserve> findByUserId(String userId);
    List<Reserve> findByClassroomId(String classroomId);
}
