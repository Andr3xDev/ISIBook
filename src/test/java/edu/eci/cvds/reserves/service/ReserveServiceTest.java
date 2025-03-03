package edu.eci.cvds.reserves.service;

import edu.eci.cvds.reserves.model.Reserve;
import edu.eci.cvds.reserves.repository.ReserveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReserveServiceTest {

    @Mock
    private ReserveRepository reserveRepository;

    @InjectMocks
    private ReserveService reserveService;

    private Reserve sampleReserve;

    @BeforeEach
    void setUp() {
        sampleReserve = new Reserve("Pedro", "LabIco",
                LocalDateTime.of(2025, 3, 5, 10, 0),
                LocalDateTime.of(2025, 3, 5, 12, 0));
    }

    @Test
    void shouldReturnAllReserves() {
        when(reserveRepository.findAll()).thenReturn(List.of(sampleReserve));

        List<Reserve> reserves = reserveService.getAllReserves();

        assertEquals(1, reserves.size());
        assertEquals("Pedro", reserves.get(0).getUserId());
        verify(reserveRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnReserveById() {
        when(reserveRepository.findById("1")).thenReturn(Optional.of(sampleReserve));

        Optional<Reserve> foundReserve = reserveService.getReserveById("1");

        assertTrue(foundReserve.isPresent());
        assertEquals("Pedro", foundReserve.get().getUserId());
        verify(reserveRepository, times(1)).findById("1");
    }

    @Test
    void shouldReturnEmptyWhenReserveNotFound() {
        when(reserveRepository.findById("999")).thenReturn(Optional.empty());

        Optional<Reserve> foundReserve = reserveService.getReserveById("999");

        assertFalse(foundReserve.isPresent());
        verify(reserveRepository, times(1)).findById("999");
    }

    @Test
    void shouldReturnReservesByUserId() {
        when(reserveRepository.findByUserId("Pedro")).thenReturn(List.of(sampleReserve));

        List<Reserve> reserves = reserveService.getReservesByUser("Pedro");

        assertEquals(1, reserves.size());
        assertEquals("Pedro", reserves.get(0).getUserId());
        verify(reserveRepository, times(1)).findByUserId("Pedro");
    }

    @Test
    void shouldReturnReservesByClassroomId() {
        when(reserveRepository.findByClassroomId("LabIco")).thenReturn(List.of(sampleReserve));

        List<Reserve> reserves = reserveService.getReservesByClassroom("LabIco");

        assertEquals(1, reserves.size());
        assertEquals("LabIco", reserves.get(0).getClassroomId());
        verify(reserveRepository, times(1)).findByClassroomId("LabIco");
    }

    @Test
    void shouldCreateReserve() {
        when(reserveRepository.save(sampleReserve)).thenReturn(sampleReserve);

        Reserve createdReserve = reserveService.createReserve(sampleReserve);

        assertNotNull(createdReserve);
        assertEquals("Pedro", createdReserve.getUserId());
        verify(reserveRepository, times(1)).save(sampleReserve);
    }

    @Test
    void shouldDeleteReserveWhenExists() {
        when(reserveRepository.existsById("1")).thenReturn(true);
        doNothing().when(reserveRepository).deleteById("1");

        boolean deleted = reserveService.deleteReserve("1");

        assertTrue(deleted);
        verify(reserveRepository, times(1)).existsById("1");
        verify(reserveRepository, times(1)).deleteById("1");
    }

    @Test
    void shouldNotDeleteReserveWhenNotExists() {
        when(reserveRepository.existsById("999")).thenReturn(false);

        boolean deleted = reserveService.deleteReserve("999");

        assertFalse(deleted);
        verify(reserveRepository, times(1)).existsById("999");
        verify(reserveRepository, never()).deleteById("999");
    }
}
