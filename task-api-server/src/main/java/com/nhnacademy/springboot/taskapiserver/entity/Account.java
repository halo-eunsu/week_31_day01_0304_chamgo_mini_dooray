package com.nhnacademy.springboot.taskapiserver.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Account {
    @Id
    private String id;
    private String password;
    private String email;
    private String name;
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginDate;
}
