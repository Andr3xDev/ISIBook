package edu.eci.cvds.reserves.service;

import edu.eci.cvds.reserves.model.Reserve;
import edu.eci.cvds.reserves.repository.ReserveRepository;
import edu.eci.cvds.reserves.service.ReserveService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ReserveServiceTest {

    @Mock
    private ReserveRepository reserveRepository;

    @InjectMocks
    private ReserveService reserveService;

    private Reserve testReserve;

    @BeforeEach
    void setUp() {
        testReserve = new Reserve("1", "Pedro", "LabIco", LocalDateTime.now(), LocalDateTime.now().plusHours(2), "Pending", false, "Study", "None");
    }

    @Test
    void testCreateReserve() {
        when(reserveRepository.save(any(Reserve.class))).thenReturn(testReserve);
        Reserve created = reserveService.createReserve(testReserve);
        assertNotNull(created);
        assertEquals("Pedro", created.getUserId());
        assertEquals("LabIco", created.getClassroomId());
    }

    @Test
    void testGetReserveById() {
        when(reserveRepository.findById("1")).thenReturn(Optional.of(testReserve));
        Optional<Reserve> found = reserveService.getReserveById("1");
        assertTrue(found.isPresent());
        assertEquals("Pedro", found.get().getUserId());
    }

    @Test
    void testGetReservesByUser() {
        when(reserveRepository.findByUserId("Pedro")).thenReturn(List.of(testReserve));
        List<Reserve> reserves = reserveService.getReservesByUser("Pedro");
        assertFalse(reserves.isEmpty());
        assertEquals(1, reserves.size());
        assertEquals("LabIco", reserves.get(0).getClassroomId());
    }

    @Test
    void testGetReservesByClassroom() {
        when(reserveRepository.findByClassroomId("LabIco")).thenReturn(List.of(testReserve));
        List<Reserve> reserves = reserveService.getReservesByClassroom("LabIco");
        assertFalse(reserves.isEmpty());
        assertEquals(1, reserves.size());
        assertEquals("Pedro", reserves.get(0).getUserId());
    }

    @Test
    void testDeleteReserve_Success() {
        when(reserveRepository.existsById("1")).thenReturn(true);
        doNothing().when(reserveRepository).deleteById("1");

        boolean deleted = reserveService.deleteReserve("1");

        assertTrue(deleted);
        verify(reserveRepository, times(1)).deleteById("1");
    }

    @Test
    void testDeleteReserve_Failure() {
        when(reserveRepository.existsById("2")).thenReturn(false);

        boolean deleted = reserveService.deleteReserve("2");

        assertFalse(deleted);
        verify(reserveRepository, never()).deleteById("2");
    }
}
