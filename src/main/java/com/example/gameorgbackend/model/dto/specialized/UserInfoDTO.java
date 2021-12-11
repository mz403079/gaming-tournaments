package com.example.gameorgbackend.model.dto.specialized;

import com.example.gameorgbackend.model.dto.basic.ContactDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDTO {

  String name;

  String surname;

  String email;

  ContactDTO contact;
}
