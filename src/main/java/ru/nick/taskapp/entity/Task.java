package ru.nick.taskapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author NikolayPirozhkov
 * @project task-app
 */
@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String definition;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

}
