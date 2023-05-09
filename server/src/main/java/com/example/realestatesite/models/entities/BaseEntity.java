package com.example.realestatesite.models.entities;

import com.example.realestatesite.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date created;
    @Column(name = "updated")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date updated;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @PrePersist
    protected void onCreate() {
        created=new Date();
        updated=new Date();
        status = Status.ACTIVE;
    }
    @PreUpdate
    protected void onUpdate(){
        updated = new Date();
    }
}
