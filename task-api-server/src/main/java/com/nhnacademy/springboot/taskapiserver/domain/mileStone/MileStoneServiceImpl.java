package com.nhnacademy.springboot.taskapiserver.domain.mileStone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MileStoneServiceImpl implements MileStoneService {
    private final MileStoneRepository mileStoneRepository;

    @Autowired
    public MileStoneServiceImpl(MileStoneRepository mileStoneRepository) {
        this.mileStoneRepository = mileStoneRepository;
    }

    @Override
    public MileStone createMileStone(MileStone mileStone) {
        return mileStoneRepository.save(mileStone);
    }

    @Override
    public Optional<MileStone> findMileStoneById(Long id) {
        return mileStoneRepository.findById(id);
    }

    @Override
    public List<MileStone> findAllMileStones() {
        return mileStoneRepository.findAll();
    }

    @Override
    public MileStone updateMileStone(MileStone mileStone) {
        return mileStoneRepository.save(mileStone);
    }

    @Override
    public void deleteMileStoneById(Long id) {
        mileStoneRepository.deleteById(id);
    }
}