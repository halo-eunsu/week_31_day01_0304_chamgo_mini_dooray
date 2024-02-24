package com.nhnacademy.springboot.taskapiserver.controller;

import com.nhnacademy.springboot.taskapiserver.domain.mileStone.MileStone;
import com.nhnacademy.springboot.taskapiserver.domain.mileStone.MileStoneRepository;
import com.nhnacademy.springboot.taskapiserver.domain.task.Task;
import com.nhnacademy.springboot.taskapiserver.domain.task.TaskRepository;
import com.nhnacademy.springboot.taskapiserver.dto.MileStoneDto;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public MileStone createMileStone(@PathVariable Long projectId,
                                     @RequestBody MileStoneDto mileStoneDto) {
        MileStone mileStone = new MileStone();
        mileStone.setMileStoneName(mileStoneDto.getMileStoneName());
        return mileStoneRepository.save(mileStone);
    }

    //todo: 2. Milestone 수정 - PUT /projects/{projectId}/milestones/{milestoneId}
    @PutMapping("milestones/{milestoneId}")
    public MileStone updateMileStone(@PathVariable Long projectId,
                                     @PathVariable Long milestoneId,
                                     @RequestBody MileStoneDto mileStoneDto) {

        MileStone mileStone = mileStoneRepository.findById(milestoneId)
                .orElse(null);
        mileStone.setMileStoneName(mileStoneDto.getMileStoneName());
        return mileStoneRepository.save(mileStone);
    }

    //todo: 3. Milestone 삭제 - DELETE /projects/{projectId}/milestones/{milestoneId}
    @DeleteMapping("/milestones/{milestoneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMileStone(@PathVariable Long projectId, @PathVariable Long milestoneId) {
        mileStoneRepository.deleteById(milestoneId);
    }

    //todo: 4. Task에 Milestone 설정 - PUT /projects/{projectId}/tasks/{taskId}/milestone
    @PutMapping("/tasks/{taskId}/milestone")
    public void setTaskMileStone(@PathVariable Long projectId, @PathVariable Long taskId, @RequestBody Long mileStoneId) {
        Task task = taskRepository.findById(taskId)
                .orElse(null);
        MileStone mileStone = mileStoneRepository.findById(mileStoneId)
                .orElse(null);
        task.setMileStone(mileStone);
        taskRepository.save(task);
    }

}