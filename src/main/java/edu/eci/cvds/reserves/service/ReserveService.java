package edu.eci.cvds.reserves.service;

import edu.eci.cvds.reserves.model.Reserve;
import edu.eci.cvds.reserves.repository.ReserveRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Service class for managing reserves.
 */
@Service
public class ReserveService {

    private final ReserveRepository reserveRepository;

    /**
     * Constructor for ReserveService.
     *
     * @param reserveRepository the repository to manage reserves
     */
    public ReserveService(ReserveRepository reserveRepository) {
        this.reserveRepository = reserveRepository;
    }

    /**
     * Get all reserves.
     *
     * @return a list of all reserves
     */
    public List<Reserve> getAllReserves() {
        return reserveRepository.findAll();
    }

    /**
     * Get a reserve by its ID.
     *
     * @param id the ID of the reserve
     * @return an Optional containing the reserve if found, or empty if not found
     */
    public Optional<Reserve> getReserveById(String id) {
        return reserveRepository.findById(id);
    }

    /**
     * Get reserves by user ID.
     *
     * @param userId the ID of the user
     * @return a list of reserves for the user
     */
    public List<Reserve> getReservesByUser(String userId) {
        return reserveRepository.findByUserId(userId);
    }

    /**
     * Get reserves by classroom ID.
     *
     * @param classroomId the ID of the classroom
     * @return a list of reserves for the classroom
     */
    public List<Reserve> getReservesByClassroom(String classroomId) {
        return reserveRepository.findByClassroomId(classroomId);
    }

    /**
     * Create a new reserve.
     *
     * @param reserve the reserve to create
     * @return the created reserve
     * @throws IllegalArgumentException if a duplicate reserve exists
     */
    public Reserve createReserve(Reserve reserve) {
        if (isReserveDuplicate(reserve)) {
            throw new IllegalArgumentException("A reserve already exists for the same day and time.");
        }
        return reserveRepository.save(reserve);
    }

    /**
     * Check if a reserve is duplicate.
     *
     * @param reserve the reserve to check
     * @return true if a duplicate reserve exists, false otherwise
     */
    public boolean isReserveDuplicate(Reserve reserve) {
        List<Reserve> existingReserves = reserveRepository.findByClassroomIdAndStartDate(reserve.getClassroomId(),
                reserve.getStartDate());

        return existingReserves.stream().anyMatch(existingReserve -> {
            LocalDateTime newStart = reserve.getStartDate();
            LocalDateTime newEnd = reserve.getFinishDate();
            LocalDateTime existingStart = existingReserve.getStartDate();
            LocalDateTime existingEnd = existingReserve.getFinishDate();

            boolean startsInside = newStart.isAfter(existingStart) && newStart.isBefore(existingEnd);
            boolean endsInside = newEnd.isAfter(existingStart) && newEnd.isBefore(existingEnd);
            boolean fullyInside = newStart.isBefore(existingStart) && newEnd.isAfter(existingEnd);
            boolean exactMatch = newStart.isEqual(existingStart) && newEnd.isEqual(existingEnd);

            return startsInside || endsInside || fullyInside || exactMatch;
        });
    }

    /**
     * Delete a reserve by its ID.
     *
     * @param id the ID of the reserve to delete
     */
    public void deleteReserve(String id) {
        reserveRepository.deleteById(id);
    }

    /**
     * Update a reserve by its ID.
     *
     * @param id             the ID of the reserve to update
     * @param updatedReserve the updated reserve data
     * @return an Optional containing the updated reserve if found, or empty if not
     *         found
     */
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

    /**
     * Get reserves for a specific week.
     *
     * @param startOfWeek the start date of the week
     * @return a list of reserves for the week
     */
    public List<Reserve> getReservesByWeek(LocalDateTime startOfWeek) {
        LocalDateTime endOfWeek = startOfWeek.plus(1, ChronoUnit.WEEKS);
        return reserveRepository.findByStartDateBetween(startOfWeek, endOfWeek);
    }

    /**
     * Get reserves for a specific hour.
     *
     * @param startOfHour the start date and time of the hour
     * @return a list of reserves for the hour
     */
    public List<Reserve> getReservesByHour(LocalDateTime startOfHour) {
        LocalDateTime endOfHour = startOfHour.plusHours(1).plusMinutes(30);
        return reserveRepository.findByStartDate(startOfHour);
    }

    /**
     * Get reserves for the current day.
     *
     * @return a list of reserves for the current day
     */
    public List<Reserve> getReservesByToday() {
        LocalDateTime startOfDay = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
        LocalDateTime endOfDay = startOfDay.plus(1, ChronoUnit.DAYS);
        return reserveRepository.findByStartDateBetween(startOfDay, endOfDay);
    }

}