package edu.eci.cvds.reserves.controller;

import edu.eci.cvds.reserves.model.Reserve;
import edu.eci.cvds.reserves.service.ReserveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Reserve>> getAllReserves() {
        return ResponseEntity.ok(reserveService.getAllReserves());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserve> getReserveById(@PathVariable String id) {
        Optional<Reserve> reserve = reserveService.getReserveById(id);
        return reserve.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reserve>> getReservesByUser(@PathVariable String userId) {
        return ResponseEntity.ok(reserveService.getReservesByUser(userId));
    }

    @GetMapping("/classroom/{classroomId}")
    public ResponseEntity<List<Reserve>> getReservesByClassroom(@PathVariable String classroomId) {
        return ResponseEntity.ok(reserveService.getReservesByClassroom(classroomId));
    }

    @PostMapping
    public ResponseEntity<Reserve> createReserve(@RequestBody Reserve reserve) {
        return ResponseEntity.ok(reserveService.createReserve(reserve));
    }

    @DeleteMapping("/{id}")
    public void deleteReserve(@PathVariable String id) {
        reserveService.deleteReserve(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserve> updateReserve(@PathVariable String id, @RequestBody Reserve updatedReserve) {
        return reserveService.updateReserve(id, updatedReserve)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
