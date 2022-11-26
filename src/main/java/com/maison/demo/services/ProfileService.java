package com.maison.demo.services;

import com.maison.demo.constants.Constant;
import com.maison.demo.models.Contact;
import com.maison.demo.models.Profile;
import com.maison.demo.repositories.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public ProfileService() {
        log.info("Contact Service Bean initialized");
    }

    public boolean create(Profile profile) {
        Log.info(this, "" + profile);
        log.info("Slf4j: " + profile);

        return profileRepository.save(profile).getProfileId() > 0;
    }

    public boolean updateStatus(Integer id) throws Exception {
        var profile = profileRepository.findById(id);
        if (profile.isEmpty()) {
            throw new Exception(String.format("ID [%s] Not Found", id));
        }
        profile.ifPresent(profileEntity -> {

        });
        return profileRepository.save(profile.get()).getUpdatedBy() != null;
    }

}
