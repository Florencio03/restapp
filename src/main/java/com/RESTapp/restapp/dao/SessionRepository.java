package com.RESTapp.restapp.dao;

import com.RESTapp.restapp.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(path = "sessions")
public interface SessionRepository extends JpaRepository<Session, UUID> {
}
