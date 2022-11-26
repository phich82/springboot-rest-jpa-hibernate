package com.maison.demo.services;

import com.maison.demo.constants.Constant;
import com.maison.demo.models.Contact;
import com.maison.demo.repositories.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
//@RequestScope // new instance per each request
//@SessionScope // only initialize instance once time
//@ApplicationScope // only initialize instance once time & data shared with another screens
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public ContactService() {
        log.info("Contact Service Bean initialized");
    }

    public List<Contact> searchWithOpenStatus() {
        return contactRepository.findByStatus(Constant.OPEN);
    }

    public boolean create(Contact contact) {
        contact.setStatus(Constant.OPEN);
        // contact.setCreatedBy(Constant.ANONYMOUS);
        // contact.setCreatedAt(LocalDateTime.now());

        Log.info(this, "" + contact);
        log.info("Slf4j: " + contact);

        return contactRepository.save(contact).getContactId() > 0;
    }

    // public boolean updateStatus(Integer id, String updatedBy) throws Exception {
    public boolean updateStatus(Integer id) throws Exception {
        var contact = contactRepository.findById(id);
        if (contact.isEmpty()) {
            throw new Exception(String.format("ID [%s] Not Found", id));
        }
        contact.ifPresent(contactEntity -> {
            contactEntity.setStatus(Constant.CLOSE);
            // contactEntity.setUpdatedBy(updatedBy);
            // contactEntity.setUpdatedAt(LocalDateTime.now());

        });
        return contactRepository.save(contact.get()).getUpdatedBy() != null;
    }

}
