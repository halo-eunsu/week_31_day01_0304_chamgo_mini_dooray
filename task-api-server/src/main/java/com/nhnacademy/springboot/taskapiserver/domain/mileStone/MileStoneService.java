package com.nhnacademy.springboot.taskapiserver.domain.mileStone;

import java.util.List;
import java.util.Optional;

public interface MileStoneService {

    MileStone createMileStone(MileStone mileStone);

    Optional<MileStone> findMileStoneById(Long id);

    List<MileStone> findAllMileStones();

    MileStone updateMileStone(MileStone mileStone);

    void deleteMileStoneById(Long id);
}
