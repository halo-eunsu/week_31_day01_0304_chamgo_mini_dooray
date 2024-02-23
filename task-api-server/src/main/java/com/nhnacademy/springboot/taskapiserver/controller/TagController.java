package com.nhnacademy.springboot.taskapiserver.controller;

import com.nhnacademy.springboot.taskapiserver.domain.tag.Tag;
import com.nhnacademy.springboot.taskapiserver.domain.tag.TagRepository;
import com.nhnacademy.springboot.taskapiserver.domain.tag.TagService;
import com.nhnacademy.springboot.taskapiserver.domain.tag.TagServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects/{projectId}")
public class TagController {

    private final TagService tagService;

    public TagController(TagRepository tagRepository, TagServiceImpl tagService) {
        this.tagService = tagService;
    }

//todo: 1. Tag 등록 - POST /projects/{projectId}/tags

    @PostMapping("/tags")
    @ResponseStatus(HttpStatus.CREATED)
    public Tag registerTag(@PathVariable("projectId") Long id,
                           @RequestBody Tag tag) {

    }

//todo: 2. Tag 수정 - PUT /projects/{projectId}/tags/{tagId}
    @PutMapping("/tags/{tagId}")
    public String modifyTag(@PathVariable("tagId")Long tagId) {
        return null;
    }


//todo: 3. Tag 삭제 - DELETE /projects/{projectId}/tags/{tagId}

//todo: 4. Task에 Tag 설정 - POST /projects/{projectId}/tasks/{taskId}/tags



}