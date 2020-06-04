package de.touch.mobile_api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import de.touch.mobile_api.model.account.Account;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    @Query("select u from Account u where u.username = :username")
    Account findByUsername(@Param("username") String username);

    @Query("select u.username from Account u where u.username = :username")
    String findUsernameByUsername(@Param("username") String username);

    @Query("select u.username from Account u where u.pid = :pid")
    String findUsernameById(@Param("pid") int pid);

    @Query("select u.email from Account u where u.email = :email")
    String findEmailByEmail(@Param("email") String email);

    
}