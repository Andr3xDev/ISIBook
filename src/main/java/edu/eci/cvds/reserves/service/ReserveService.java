package edu.eci.cvds.reserves.service;

import edu.eci.cvds.reserves.model.Reserve;
import edu.eci.cvds.reserves.repository.ReserveRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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
        if (isReserveDuplicate(reserve)) {
            throw new IllegalArgumentException("A reserve already exists for the same day and time.");
        }
        return reserveRepository.save(reserve);
    }

    public boolean isReserveDuplicate(Reserve reserve) {
        List<Reserve> existingReserves = reserveRepository.findByClassroomIdAndStartDate(reserve.getClassroomId(), reserve.getStartDate());

        return existingReserves.stream().anyMatch(existingReserve -> {
            LocalDateTime newStart = reserve.getStartDate();
            LocalDateTime newEnd = reserve.getFinishDate();
            LocalDateTime existingStart = existingReserve.getStartDate();
            LocalDateTime existingEnd = existingReserve.getFinishDate();

            // Condiciones para detectar solapamiento:
            boolean startsInside = newStart.isAfter(existingStart) && newStart.isBefore(existingEnd);
            boolean endsInside = newEnd.isAfter(existingStart) && newEnd.isBefore(existingEnd);
            boolean fullyInside = newStart.isBefore(existingStart) && newEnd.isAfter(existingEnd);
            boolean exactMatch = newStart.isEqual(existingStart) && newEnd.isEqual(existingEnd);
            boolean overlaps = startsInside || endsInside || fullyInside || exactMatch;

            return overlaps;
        });
    }


    public void deleteReserve(String id/* ,User user */) {
        // if (user instanceof Admin) {
        reserveRepository.deleteById(id);
        // }else {
        // Optional<Reserve> reserve = getReserveById(id);
        // if (reserve.isPresent()) {
        // if (reserve.get().getUserId() == user.getU)
        // }
        // }
    }

    public Optional<Reserve> updateReserve(String id, Reserve updatedReserve) {
        return reserveRepository.findById(id).map(existingReserve -> {
            existingReserve.setUserId(updatedReserve.getUserId());
            existingReserve.setClassroomId(updatedReserve.getClassroomId());
            existingReserve.setStartDate(updatedReserve.getStartDate());
            existingReserve.setFinishDate(updatedReserve.getFinishDate());
            existingReserve.setStatus(updatedReserve.getStatus());
            existingReserve.setRepetitive(updatedReserve.isRepetitive());
            existingReserve.setPurpose(updatedReserve.getPurpose());
            existingReserve.setRepetitiveTime(updatedReserve.getRepetitiveTime());

            return reserveRepository.save(existingReserve);
        });
    }
    public List<Reserve> getReservesByWeek(LocalDateTime startOfWeek) {
        LocalDateTime endOfWeek = startOfWeek.plus(1, ChronoUnit.WEEKS);
        return reserveRepository.findByStartDateBetween(startOfWeek, endOfWeek);
    }

    public List<Reserve> getReservesByHour(LocalDateTime startOfHour) {
        LocalDateTime endOfHour = startOfHour.plus(1, ChronoUnit.HOURS);
        return reserveRepository.findByStartDateBetween(startOfHour, endOfHour);
    }

}