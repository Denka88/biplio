package bip.online.biplio2023.dto;

public class UserDto {
    
    private String username;
    private String name;
    private String surname;
    private String password;

    public UserDto() {
    }

    public UserDto(String username, String name, String surname, String password) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
