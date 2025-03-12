package edu.eci.cvds.reserves.controller;

import edu.eci.cvds.reserves.model.Reserve;
import edu.eci.cvds.reserves.service.ReserveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reserves")
public class ReserveController {
    private final ReserveService reserveService;

    public ReserveController(ReserveService reserveService) {
        this.reserveService = reserveService;
    }

    @GetMapping
    public ResponseEntity<?> getAllReserves() {
        List<Reserve> reserves = reserveService.getAllReserves();
        if (reserves == null || reserves.isEmpty()) {
            return ResponseEntity.badRequest().body("There are no reserves available");
        }
        return ResponseEntity.ok(reserves);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReserveById(@PathVariable String id) {
        Optional<Reserve> reserve = reserveService.getReserveById(id);
        if (reserve.isEmpty()) {
            return ResponseEntity.badRequest().body("There are no reserves with this id");
        }
        return ResponseEntity.ok(reserve);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getReservesByUser(@PathVariable String userId) {
        List<Reserve> reserves = reserveService.getReservesByUser(userId);
        if (reserves == null || reserves.isEmpty()) {
            return ResponseEntity.badRequest().body("There are no reserves for this user");
        }
        return ResponseEntity.ok(reserves);
    }

    @GetMapping("/classroom/{classroomId}")
    public ResponseEntity<?> getReservesByClassroom(@PathVariable String classroomId) {
        List<Reserve> reserves = reserveService.getReservesByClassroom(classroomId);
        if (reserves == null || reserves.isEmpty()) {
            return ResponseEntity.badRequest().body("There are no reserves for this classroom");
        }
        return ResponseEntity.ok(reserves);
    }

    @PostMapping
    public Reserve createReserve(@RequestBody Reserve reserve) {
        return reserveService.createReserve(reserve);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReserve(@PathVariable String id) {
        reserveService.deleteReserve(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserve> updateReserve(@PathVariable String id, @RequestBody Reserve updatedReserve) {
        return reserveService.updateReserve(id, updatedReserve)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/week")
    public ResponseEntity<?> getReservesByWeek(@RequestParam("startOfWeek") String startOfWeek) {
        LocalDateTime start = LocalDateTime.parse(startOfWeek);
        List<Reserve> reserves = reserveService.getReservesByWeek(start);
        if (reserves == null || reserves.isEmpty()) {
            return ResponseEntity.badRequest().body("There are no reserves for this week");
        }
        return ResponseEntity.ok(reserves);
    }

    @GetMapping("/hour")
    public ResponseEntity<?> getReservesByHour(@RequestParam("startOfHour") String startOfHour) {
        LocalDateTime start = LocalDateTime.parse(startOfHour);
        List<Reserve> reserves = reserveService.getReservesByHour(start);
        if (reserves == null || reserves.isEmpty()) {
            return ResponseEntity.badRequest().body("There are no reserves for this hour");
        }
        return ResponseEntity.ok(reserves);
    }
}