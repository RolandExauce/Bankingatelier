package com.thymeleaf.bankingatelier.repos;
import com.thymeleaf.bankingatelier.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;


// repository is a component that handles data access and persistence to the database
// JpaRepository is an interface from the spring data jpa (Java persistence api) library, provides
// methods for performing CRUD operations on entities
@Repository
public interface UserRepository  extends JpaRepository<UserModel, Long> {

    // method signature follows the Spring data naming convention,
    //will automatically generate the database query

    //or use query annotation
    //@Query("SELECT u FROM UserModel u WHERE u.username = :username")
    Optional<UserModel> findByUsername(String username);

    @Modifying
    @Transactional
    @Query("UPDATE UserModel u SET u.balance = u.balance + ?2 WHERE u.id = ?1")
    void deposit(Long userId, double amount);

    @Modifying
    @Transactional
    @Query("UPDATE UserModel u SET u.balance = u.balance - ?2 WHERE u.id = ?1 AND u.balance >= ?2")
    int withdraw(Long userId, double amount);

}
