package com.example.gameorgbackend.model.dto.basic;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDTO {

    private Long addressId;

    private String city;

    private String street;
}
