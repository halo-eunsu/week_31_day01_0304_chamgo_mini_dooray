package com.nhnacademy.springboot.taskapiserver.controller;

import com.nhnacademy.springboot.taskapiserver.domain.mileStone.MileStone;
import com.nhnacademy.springboot.taskapiserver.domain.mileStone.MileStoneRepository;
import com.nhnacademy.springboot.taskapiserver.domain.task.Task;
import com.nhnacademy.springboot.taskapiserver.domain.task.TaskRepository;
import com.nhnacademy.springboot.taskapiserver.dto.MileStoneDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects/{projectId}")
public class MileStoneController {

    private final MileStoneRepository mileStoneRepository;
    private final TaskRepository taskRepository;

    public MileStoneController(MileStoneRepository mileStoneRepository, TaskRepository taskRepository) {
        this.mileStoneRepository = mileStoneRepository;
        this.taskRepository = taskRepository;
    }

    //todo: 1. Milestone 등록 - POST /projects/{projectId}/milestones
    @PostMapping("/milestones")
    public ResponseEntity<MileStone> createMileStone(@PathVariable Long projectId,
                                                     @RequestBody MileStoneDto mileStoneDto) {
        MileStone mileStone = new MileStone();
        mileStone.setMileStoneName(mileStoneDto.getMileStoneName());
        MileStone createdMileStone = mileStoneRepository.save(mileStone);
        return new ResponseEntity<>(createdMileStone, HttpStatus.CREATED);
    }

    //todo: 2. Milestone 수정 - PUT /projects/{projectId}/milestones/{milestoneId}
    @PutMapping("milestones/{milestoneId}")
    public ResponseEntity<MileStone> updateMileStone(@PathVariable Long projectId,
                                                     @PathVariable Long milestoneId,
                                                     @RequestBody MileStoneDto mileStoneDto) {
        return mileStoneRepository.findById(milestoneId).map(mileStone -> {
            mileStone.setMileStoneName(mileStoneDto.getMileStoneName());
            MileStone updatedMileStone = mileStoneRepository.save(mileStone);
            return new ResponseEntity<>(updatedMileStone, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //todo: 3. Milestone 삭제 - DELETE /projects/{projectId}/milestones/{milestoneId}
    @DeleteMapping("/milestones/{milestoneId}")
    public ResponseEntity<Void> deleteMileStone(@PathVariable Long projectId, @PathVariable Long milestoneId) {
        if (mileStoneRepository.existsById(milestoneId)) {
            mileStoneRepository.deleteById(milestoneId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //todo: 4. Task에 Milestone 설정 - PUT /projects/{projectId}/tasks/{taskId}/milestone
    @PutMapping("/tasks/{taskId}/milestone")
    public ResponseEntity<Void> setTaskMileStone(@PathVariable Long projectId, @PathVariable Long taskId, @RequestBody Long mileStoneId) {
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task != null) {
            MileStone mileStone = mileStoneRepository.findById(mileStoneId).orElse(null);
            if (mileStone != null) {
                task.setMileStone(mileStone);
                taskRepository.save(task);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}