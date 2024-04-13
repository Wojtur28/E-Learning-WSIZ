package org.example.elearningcourses;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@EqualsAndHashCode
@MappedSuperclass
public abstract class EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @CreatedDate
    @NotNull
    @Column(name = "createdon")
    private LocalDateTime createdOn = LocalDateTime.now();

    @LastModifiedDate
    @NotNull
    @Column(name = "modifiedon")
    private LocalDateTime modifiedOn = LocalDateTime.now();

}
