package itmo.p33101.gtav_territorywar.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String password;
    private String name;
    private String lastname;
    private int gang;
    private int age;
}
