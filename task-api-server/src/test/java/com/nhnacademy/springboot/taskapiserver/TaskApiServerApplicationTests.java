package com.nhnacademy.springboot.taskapiserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.nhnacademy.springboot.taskapiserver.domain.project.ProjectService;
import com.nhnacademy.springboot.taskapiserver.domain.member.MemberService;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TaskApiServerApplicationTests {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MemberService memberService;

    @Test
    void contextLoads() {
        assertThat(projectService).isNotNull();
        assertThat(memberService).isNotNull();
    }
}
