package com.nhnacademy.springboot.taskapiserver.domain.mileStone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MileStoneServiceImplTest {

    @Mock
    private MileStoneRepository mileStoneRepository;

    @InjectMocks
    private MileStoneServiceImpl mileStoneService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createMileStone() {
        MileStone mileStone = new MileStone();
        mileStone.setMileStoneName("New MileStone");

        when(mileStoneRepository.save(any(MileStone.class))).thenReturn(mileStone);

        MileStone created = mileStoneService.createMileStone(mileStone);

        assertNotNull(created);
        assertEquals("New MileStone", created.getMileStoneName());
        verify(mileStoneRepository).save(any(MileStone.class));
    }

    @Test
    void findMileStoneById() {
        MileStone mileStone = new MileStone();
        mileStone.setMileStoneName("Existing MileStone");

        when(mileStoneRepository.findById(anyLong())).thenReturn(Optional.of(mileStone));

        Optional<MileStone> found = mileStoneService.findMileStoneById(1L);

        assertTrue(found.isPresent());
        assertEquals("Existing MileStone", found.get().getMileStoneName());
        verify(mileStoneRepository).findById(anyLong());
    }

    @Test
    void findAllMileStones() {
        MileStone mileStone = new MileStone();
        mileStone.setMileStoneName("MileStone");

        when(mileStoneRepository.findAll()).thenReturn(Collections.singletonList(mileStone));

        List<MileStone> mileStones = mileStoneService.findAllMileStones();

        assertFalse(mileStones.isEmpty());
        assertEquals(1, mileStones.size());
        assertEquals("MileStone", mileStones.get(0).getMileStoneName());
        verify(mileStoneRepository).findAll();
    }

    @Test
    void updateMileStone() {
        MileStone mileStone = new MileStone();
        mileStone.setMileStoneName("Old Name");

        when(mileStoneRepository.save(any(MileStone.class))).thenReturn(mileStone);
        mileStone.setMileStoneName("Updated Name");
        MileStone updated = mileStoneService.updateMileStone(mileStone);

        assertNotNull(updated);
        assertEquals("Updated Name", updated.getMileStoneName());
        verify(mileStoneRepository).save(any(MileStone.class));
    }

    @Test
    void deleteMileStoneById() {
        doNothing().when(mileStoneRepository).deleteById(anyLong());

        mileStoneService.deleteMileStoneById(1L);

        verify(mileStoneRepository).deleteById(anyLong());
    }
}
