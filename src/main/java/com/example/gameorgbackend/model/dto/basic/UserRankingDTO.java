package com.example.gameorgbackend.model.dto.basic;

import com.example.gameorgbackend.model.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRankingDTO {
    private Long userId;

    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    @JsonIgnore
    private String email;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String surname;

    private ContactDTO contact;

    private Integer crowns;

}
