package com.nhnacademy.springboot.taskapiserver.domain.comment;

import com.nhnacademy.springboot.taskapiserver.domain.member.Member;
import com.nhnacademy.springboot.taskapiserver.domain.task.Task;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String content;

    @ManyToOne
    @JoinColumn(name = "taskId")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @Builder
    public Comment(Long commentId, String content, Task task, Member member) {
        this.commentId = commentId;
        this.content = content;
        this.task = task;
        this.member = member;
    }

}
