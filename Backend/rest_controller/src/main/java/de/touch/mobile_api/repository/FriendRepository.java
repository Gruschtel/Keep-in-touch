package de.touch.mobile_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import de.touch.mobile_api.model.account.Account;
import de.touch.mobile_api.model.account.Friends;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface FriendRepository extends CrudRepository<Friends, Long> {

    @Query("select u from Friends u where u.idaccount = :idaccount")
    List<Friends> findByIdaccount(@Param("idaccount") int idaccount);

}