package com.RESTapp.restapp.rest;

import com.RESTapp.restapp.entity.Session;
import com.RESTapp.restapp.service.SessionService;
import com.RESTapp.restapp.util.AppLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/sessions")
public class SessionRestController {

    private SessionService sessionService;
    private ObjectMapper objectMapper;

    @Autowired
    public SessionRestController(SessionService theSessionService, ObjectMapper theObjectMapper){
        sessionService = theSessionService;
        objectMapper = theObjectMapper;
    }

    @GetMapping("/fetchAll")
    public List<Session> findAll(){
        AppLogger.info("GET /fetchAll called");
        return sessionService.findAll();
    }

    @GetMapping("/fetch/{sessionId}")
    public Session getSession(@PathVariable UUID sessionId){
        Session theSession = sessionService.findById(sessionId);

        if (theSession == null){
            throw new RuntimeException("Session id not found - " + sessionId);
        }

        AppLogger.info("GET /fetch/" + sessionId + " called");
        return theSession;
    }

    @PostMapping("/create")
    public Session addSession(@RequestBody Session theSession){

        // also just in case they pass an id in JSON ... set id to null
        // this is to force a save of new item ... instead of update
        theSession.setId(null);

        Session dbSession = sessionService.save(theSession);

        AppLogger.info("/create session created");
        return dbSession;
    }

    @PatchMapping("/patch/{sessionId}")
    public Session patchSession(@PathVariable UUID sessionId, @RequestBody Map<String, Object> patchPayload){

        Session tempSession = sessionService.findById(sessionId);

        //throw exception if null
        if (tempSession == null ){
            throw new RuntimeException("Session id not found - " + sessionId);
        }

        // throw exception if request body contains "id" key
        if (patchPayload.containsKey("id")) {
            throw new RuntimeException("Session id not allowed in request body - " + sessionId);
        }

        Session patchedSession = apply(patchPayload, tempSession);

        Session dbSession = sessionService.save(patchedSession);

        AppLogger.info("PATCH /patch/" + sessionId);
        return dbSession;
    }

    private Session apply(Map<String, Object> patchPayload, Session tempSession) {

        ObjectNode sessionNode = objectMapper.convertValue(tempSession, ObjectNode.class);

        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        sessionNode.setAll(patchNode);

        return objectMapper.convertValue(sessionNode, Session.class);
    }

    @DeleteMapping("/delete/{sessionId}")
    public String deleteSession(@PathVariable UUID sessionId){

        Session tempSession = sessionService.findById(sessionId);

        // throw exception if null
        if (tempSession == null) {
            throw new RuntimeException("Session id not found - " + sessionId);
        }

        sessionService.deleteById(sessionId);

        AppLogger.info("DELETE /delete/" + sessionId);
        return "Deleted Session id - " + sessionId;
    }

}
