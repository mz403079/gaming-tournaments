package com.example.gameorgbackend.model.dto.basic;

import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDTO {

  private Long contactId;

  @Column(nullable = false, length = 20, unique = true)
  private String phoneNumber;

  @Column(nullable = false, length = 80, unique = true)
  private String discordName;
}
