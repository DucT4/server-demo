package com.cybersoft.uniclub.repository;


import com.cybersoft.uniclub.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Users, String> {
    //tra ra doi tuong thi de trong Optional de tranh nullpointer
    Optional<Users> findByEmail(String email);
}
