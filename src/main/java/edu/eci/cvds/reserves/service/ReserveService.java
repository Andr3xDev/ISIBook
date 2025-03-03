package edu.eci.cvds.reserves.service;

import edu.eci.cvds.reserves.model.Reserve;
import edu.eci.cvds.reserves.repository.ReserveRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReserveService {
    private final ReserveRepository reserveRepository;

    public ReserveService(ReserveRepository reserveRepository) {
        this.reserveRepository = reserveRepository;
    }

    public List<Reserve> getAllReserves() {
        return reserveRepository.findAll();
    }

    public Optional<Reserve> getReserveById(String id) {
        return reserveRepository.findById(id);
    }

    public List<Reserve> getReservesByUser(String userId) {
        return reserveRepository.findByUserId(userId);
    }

    public List<Reserve> getReservesByClassroom(String classroomId) {
        return reserveRepository.findByClassroomId(classroomId);
    }

    public Reserve createReserve(Reserve reserve) {
        return reserveRepository.save(reserve);
    }

    public boolean deleteReserve(String id) {
        if (reserveRepository.existsById(id)) {
            reserveRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
