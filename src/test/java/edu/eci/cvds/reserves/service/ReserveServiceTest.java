package edu.eci.cvds.reserves.service;

import edu.eci.cvds.reserves.controller.ReserveController;
import edu.eci.cvds.reserves.model.Reserve;
import edu.eci.cvds.reserves.repository.ReserveRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReserveServiceTest {

    @Mock
    private ReserveRepository reserveRepository;

    @InjectMocks
    private ReserveService reserveService;

    private ReserveController reserveController;

    private Reserve testReserve;

    private Reserve updatedReserve;
    private Reserve overlappingReserve;
    private Reserve sameTimeReserve;
    private Reserve differentTimeReserve;

    @BeforeEach
    void setUp() {
        testReserve = new Reserve("Pedro", "LabIco", LocalDateTime.now(), LocalDateTime.now().plusHours(2), "Pending",
                false, "Study", "None");
        updatedReserve = new Reserve("Maria", "LabIco", LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(1).plusHours(2), "Confirmed", true, "Research", "Weekly");
        reserveController = new ReserveController(reserveService);
        overlappingReserve = new Reserve("Maria", "LabIco", LocalDateTime.now().plusMinutes(30),
                LocalDateTime.now().plusHours(2).plusMinutes(30), "Confirmed", true, "Research", "Weekly");
        sameTimeReserve = new Reserve("Juan", "LabIco", LocalDateTime.now(), LocalDateTime.now().plusHours(2),
                "Pending", false, "Study", "None");
        differentTimeReserve = new Reserve("Ana", "LabIco", LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(1).plusHours(2), "Pending", false, "Study", "None");

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
        doNothing().when(reserveRepository).deleteById("1");

        reserveService.deleteReserve("1");

        verify(reserveRepository, times(1)).deleteById("1");
    }

    // @Test
    // void testDeleteReserve_Failure() {
    // reserveService.deleteReserve("2");
    // verify(reserveRepository, never()).deleteById("2");
    // }

    @Test
    void testUpdateReserve_Success() {
        when(reserveRepository.findById("1")).thenReturn(Optional.of(testReserve));
        when(reserveRepository.save(any(Reserve.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Optional<Reserve> result = reserveService.updateReserve("1", updatedReserve);

        assertTrue(result.isPresent());
        assertEquals("Maria", result.get().getUserId());
        assertEquals("LabIco", result.get().getClassroomId());
        assertEquals("Confirmed", result.get().getStatus());
        assertTrue(result.get().isRepetitive());
        assertEquals("Research", result.get().getPurpose());
        assertEquals("Weekly", result.get().getRepetitiveTime());

        verify(reserveRepository, times(1)).findById("1");
        verify(reserveRepository, times(1)).save(any(Reserve.class));
    }

    @Test
    void testUpdateReserve_NotFound() {
        when(reserveRepository.findById("2")).thenReturn(Optional.empty());

        Optional<Reserve> result = reserveService.updateReserve("2", updatedReserve);

        assertFalse(result.isPresent());

        verify(reserveRepository, times(1)).findById("2");
        verify(reserveRepository, never()).save(any(Reserve.class));
    }

    @Test
    void testGetReservesByWeek() {
        LocalDateTime startOfWeek = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
        LocalDateTime endOfWeek = startOfWeek.plus(1, ChronoUnit.WEEKS);
        when(reserveRepository.findByStartDateBetween(startOfWeek, endOfWeek)).thenReturn(List.of(testReserve));

        List<Reserve> reserves = reserveService.getReservesByWeek(startOfWeek);
        assertFalse(reserves.isEmpty());
        assertEquals(1, reserves.size());
        assertEquals("Pedro", reserves.get(0).getUserId());
    }

    @Test
    void testGetReservesByHour() {
        LocalDateTime startOfHour = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);
        LocalDateTime endOfHour = startOfHour.plus(1, ChronoUnit.HOURS);
        when(reserveRepository.findByStartDateBetween(startOfHour, endOfHour)).thenReturn(List.of(testReserve));

        List<Reserve> reserves = reserveService.getReservesByHour(startOfHour);
        assertFalse(reserves.isEmpty());
        assertEquals(1, reserves.size());
        assertEquals("Pedro", reserves.get(0).getUserId());
    }

    @Test
    void testGetAllReserves() {
        when(reserveService.getAllReserves()).thenReturn(List.of(testReserve));
        ResponseEntity<?> response = reserveController.getAllReserves();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
    }

    @Test
    void testIsReserveDuplicate_SameTime() {
        when(reserveRepository.findByClassroomIdAndStartDate(testReserve.getClassroomId(), testReserve.getStartDate()))
                .thenReturn(List.of(sameTimeReserve));
        assertTrue(reserveService.isReserveDuplicate(testReserve));
    }

    @Test
    void testIsReserveDuplicate_OverlappingTime() {
        when(reserveRepository.findByClassroomIdAndStartDate(testReserve.getClassroomId(), testReserve.getStartDate()))
                .thenReturn(List.of(overlappingReserve));
        assertTrue(reserveService.isReserveDuplicate(testReserve));
    }

    @Test
    void testIsReserveDuplicate_DifferentTime() {
        when(reserveRepository.findByClassroomIdAndStartDate(testReserve.getClassroomId(), testReserve.getStartDate()))
                .thenReturn(List.of(differentTimeReserve));
        assertFalse(reserveService.isReserveDuplicate(testReserve));
    }

    @Test
    void testIsReserveDuplicate_NoExistingReserves() {
        when(reserveRepository.findByClassroomIdAndStartDate(testReserve.getClassroomId(), testReserve.getStartDate()))
                .thenReturn(List.of());
        assertFalse(reserveService.isReserveDuplicate(testReserve));
    }

    @Test
    void testCreateReserve_DuplicateExists() {
        Reserve duplicateReserve = new Reserve("Maria", "LabIco", LocalDateTime.now(), LocalDateTime.now().plusHours(2),
                "Confirmed", true, "Research", "Weekly");
        when(reserveRepository.findByClassroomIdAndStartDate(testReserve.getClassroomId(), testReserve.getStartDate()))
                .thenReturn(List.of(duplicateReserve));

        assertThrows(IllegalArgumentException.class, () -> reserveService.createReserve(testReserve),
                "A reserve already exists for the same day and time.");
    }

    @Test
    void testIsReserveDuplicate_StartsBeforeAndEndsAfterExisting() {
        Reserve conflictingReserve = new Reserve("Maria", "LabIco",
                testReserve.getStartDate().plusMinutes(10),
                testReserve.getFinishDate().minusMinutes(10),
                "Confirmed", true, "Research", "Weekly");

        when(reserveRepository.findByClassroomIdAndStartDate(testReserve.getClassroomId(), testReserve.getStartDate()))
                .thenReturn(List.of(conflictingReserve));

        assertTrue(reserveService.isReserveDuplicate(testReserve));
    }

    @Test
    void testIsReserveDuplicate_EndsExactlyWhenExistingStarts() {
        Reserve conflictingReserve = new Reserve("Maria", "LabIco",
                testReserve.getFinishDate(),
                testReserve.getFinishDate().plusHours(1),
                "Confirmed", true, "Research", "Weekly");

        when(reserveRepository.findByClassroomIdAndStartDate(testReserve.getClassroomId(), testReserve.getStartDate()))
                .thenReturn(List.of(conflictingReserve));

        assertFalse(reserveService.isReserveDuplicate(testReserve));
    }

    @Test
    void testIsReserveDuplicate_SameStartAndFinishTime() {
        Reserve conflictingReserve = new Reserve("Maria", "LabIco",
                testReserve.getStartDate(),
                testReserve.getFinishDate(),
                "Confirmed", true, "Research", "Weekly");

        when(reserveRepository.findByClassroomIdAndStartDate(testReserve.getClassroomId(), testReserve.getStartDate()))
                .thenReturn(List.of(conflictingReserve));

        assertTrue(reserveService.isReserveDuplicate(testReserve));
    }

    @Test
    void testIsReserveDuplicate_ContainedWithinExisting() {
        Reserve existingReserve = new Reserve("Maria", "LabIco",
                testReserve.getStartDate().minusMinutes(30),
                testReserve.getFinishDate().plusMinutes(30),
                "Confirmed", true, "Research", "Weekly");

        when(reserveRepository.findByClassroomIdAndStartDate(testReserve.getClassroomId(), testReserve.getStartDate()))
                .thenReturn(List.of(existingReserve));

        assertTrue(reserveService.isReserveDuplicate(testReserve));
    }

    @Test
    void testIsReserveDuplicate_StartsAfterExistingButSameFinishTime() {
        Reserve existingReserve = new Reserve("Maria", "LabIco",
                testReserve.getStartDate().minusHours(1),
                testReserve.getFinishDate(),
                "Confirmed", true, "Research", "Weekly");

        when(reserveRepository.findByClassroomIdAndStartDate(testReserve.getClassroomId(), testReserve.getStartDate()))
                .thenReturn(List.of(existingReserve));

        assertTrue(reserveService.isReserveDuplicate(testReserve));
    }

    @Test
    void testIsReserveDuplicate_StartsAfterAndEndsAfterExisting() {
        Reserve existingReserve = new Reserve("Maria", "LabIco",
                testReserve.getStartDate().minusHours(1),
                testReserve.getFinishDate().minusMinutes(10),
                "Confirmed", true, "Research", "Weekly");

        when(reserveRepository.findByClassroomIdAndStartDate(testReserve.getClassroomId(), testReserve.getStartDate()))
                .thenReturn(List.of(existingReserve));

        assertTrue(reserveService.isReserveDuplicate(testReserve));
    }

    @Test
    void testIsReserveDuplicate_NoOverlap() {
        Reserve existingReserve = new Reserve("Maria", "LabIco",
                testReserve.getFinishDate().plusHours(1),
                testReserve.getFinishDate().plusHours(2),
                "Confirmed", true, "Research", "Weekly");

        when(reserveRepository.findByClassroomIdAndStartDate(testReserve.getClassroomId(), testReserve.getStartDate()))
                .thenReturn(List.of(existingReserve));

        assertFalse(reserveService.isReserveDuplicate(testReserve));
    }

    @Test
    void testGetReservesByToday() {
        LocalDateTime startOfDay = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
        LocalDateTime endOfDay = startOfDay.plus(1, ChronoUnit.DAYS);
        when(reserveRepository.findByStartDateBetween(startOfDay, endOfDay)).thenReturn(List.of(testReserve));

        List<Reserve> reserves = reserveService.getReservesByToday();
        assertFalse(reserves.isEmpty());
        assertEquals(1, reserves.size());
        assertEquals("Pedro", reserves.get(0).getUserId());
    }
}
