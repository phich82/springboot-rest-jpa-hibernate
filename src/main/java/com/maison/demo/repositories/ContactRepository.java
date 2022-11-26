package com.maison.demo.repositories;

import com.maison.demo.models.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {
    /**
     * SELECT * FROM contact_message WHERE status = :status
     * @param status Status
     * @return Contact List
     */
    List<Contact> findByStatus(String status);
}
