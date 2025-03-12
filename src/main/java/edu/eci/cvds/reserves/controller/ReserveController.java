package edu.eci.cvds.reserves.controller;

import edu.eci.cvds.reserves.model.Reserve;
import edu.eci.cvds.reserves.service.ReserveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * REST controller for managing reserves.
 */
@RestController
@RequestMapping("/api/reserves")
public class ReserveController {
    private final ReserveService reserveService;

    /**
     * Constructor for ReserveController.
     *
     * @param reserveService the service to manage reserves
     */
    public ReserveController(ReserveService reserveService) {
        this.reserveService = reserveService;
    }

    /**
     * Get all reserves.
     *
     * @return a ResponseEntity containing the list of all reserves or an error
     *         message
     */
    @GetMapping
    public ResponseEntity<List<Reserve>> getAllReserves() {
        List<Reserve> reserves = reserveService.getAllReserves();
        if (reserves.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reserves);
    }

    /**
     * Get a reserve by its ID.
     *
     * @param id the ID of the reserve
     * @return a ResponseEntity containing the reserve or an error message
     */
    @GetMapping("/{id}")
    public ResponseEntity<Reserve> getReserveById(@PathVariable String id) {
        return reserveService.getReserveById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get reserves by user ID.
     *
     * @param userId the ID of the user
     * @return a ResponseEntity containing the list of reserves for the user or an
     *         error message
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reserve>> getReservesByUser(@PathVariable String userId) {
        List<Reserve> reserves = reserveService.getReservesByUser(userId);
        if (reserves.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reserves);
    }

    /**
     * Get reserves by classroom ID.
     *
     * @param classroomId the ID of the classroom
     * @return a ResponseEntity containing the list of reserves for the classroom or
     *         an error message
     */
    @GetMapping("/classroom/{classroomId}")
    public ResponseEntity<List<Reserve>> getReservesByClassroom(@PathVariable String classroomId) {
        List<Reserve> reserves = reserveService.getReservesByClassroom(classroomId);
        if (reserves.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reserves);
    }

    /**
     * Create a new reserve.
     *
     * @param reserve the reserve to create
     * @return a ResponseEntity containing the created reserve
     */
    @PostMapping
    public ResponseEntity<Reserve> createReserve(@RequestBody Reserve reserve) {
        Reserve createdReserve = reserveService.createReserve(reserve);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReserve);
    }

    /**
     * Delete a reserve by its ID.
     *
     * @param id the ID of the reserve to delete
     * @return a ResponseEntity indicating the result of the operation
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserve(@PathVariable String id) {
        if (reserveService.getReserveById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        reserveService.deleteReserve(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Update a reserve by its ID.
     *
     * @param id             the ID of the reserve to update
     * @param updatedReserve the updated reserve data
     * @return a ResponseEntity containing the updated reserve or a not found status
     */
    @PutMapping("/{id}")
    public ResponseEntity<Reserve> updateReserve(@PathVariable String id, @RequestBody Reserve updatedReserve) {
        return reserveService.updateReserve(id, updatedReserve)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get reserves for a specific week.
     *
     * @param startOfWeek the start date of the week in ISO-8601 format
     * @return a ResponseEntity containing the list of reserves for the week or an
     *         error message
     */
    @GetMapping("/week")
    public ResponseEntity<List<Reserve>> getReservesByWeek(@RequestParam("startOfWeek") String startOfWeek) {
        LocalDateTime start = LocalDateTime.parse(startOfWeek);
        List<Reserve> reserves = reserveService.getReservesByWeek(start);
        if (reserves.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reserves);
    }

    /**
     * Get reserves for a specific hour.
     *
     * @param startOfHour the start date and time of the hour in ISO-8601 format
     * @return a ResponseEntity containing the list of reserves for the hour or an
     *         error message
     */
    @GetMapping("/hour")
    public ResponseEntity<List<Reserve>> getReservesByHour(@RequestParam("startOfHour") String startOfHour) {
        LocalDateTime start = LocalDateTime.parse(startOfHour);
        List<Reserve> reserves = reserveService.getReservesByHour(start);
        if (reserves.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reserves);
    }

    /**
     * Get reserves for the current day.
     *
     * @return a ResponseEntity containing the list of reserves for the current day
     */
    @GetMapping("/today")
    public ResponseEntity<List<Reserve>> getReservesByToday() {
        List<Reserve> reserves = reserveService.getReservesByToday();
        if (reserves.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reserves);
    }
}
