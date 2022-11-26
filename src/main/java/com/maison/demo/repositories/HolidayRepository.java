package com.maison.demo.repositories;

import com.maison.demo.models.Holiday;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
@Repository stereotype annotation is used to add a bean of this class
type to the Spring context and indicate that given Bean is used to perform
DB related operations and
* */
@Repository
public interface HolidayRepository extends CrudRepository<Holiday, Integer> {

}
