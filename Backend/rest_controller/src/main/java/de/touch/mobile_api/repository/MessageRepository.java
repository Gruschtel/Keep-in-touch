package de.touch.mobile_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import de.touch.mobile_api.model.Message;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface MessageRepository extends CrudRepository<Message, Long>{


    @Query("select u from Message u where u.idaccount = :idaccount")
    List<Message> findByIdaccount(@Param("idaccount") int idaccount);
}