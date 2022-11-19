package com.maison.demo.services;

import com.maison.demo.models.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Slf4j
@Service
//@RequestScope // new instance per each request
//@SessionScope // only initialize instance once time
@ApplicationScope // only initialize instance once time & data shared with another screens
public class ContactService {

    public ContactService() {
        log.info("Contact Service Bean initialized");
    }

    private int counter = 0;
    public boolean create(Contact contact) {
        boolean isSaved = true;
        Log.info(this, contact.toString());
        log.info("Slf4j: " + contact.toString());

        return isSaved;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter += counter;
    }
}
