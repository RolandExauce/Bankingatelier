package com.thymeleaf.bankingatelier.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "users")
@Entity // mark the class as a jpa entity, instances of this class will be mapped to database records
//lombok, will generate getters and setters for methods for all fields in the class, likewise constructors for all fields
// and constructors no-args constructors
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    @Id //primary key for the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
//    private String firstname;
//    private String lastname;

    //unique constraint on email and username
    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;
    private String password;
    private double balance;
//    @Enumerated(EnumType.STRING) // Specify that this field is an enum and stored as a string
//    private Role role;

    @Override //override toString method from the Object class, to get a string representation of the UserModel object
    public String toString() {
        return "UserModel{" +
                "Id=" + Id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}




