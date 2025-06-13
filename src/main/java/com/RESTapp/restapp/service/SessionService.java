package com.RESTapp.restapp.service;

import com.RESTapp.restapp.entity.Session;

import java.util.List;
import java.util.UUID;

public interface SessionService {
    List<Session> findAll();

    Session findById(UUID theId);

    Session save(Session theSession);

    void deleteById(UUID theId);

}
