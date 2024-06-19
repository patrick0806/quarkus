package com.cvccorp.store.adapter.exceptionHandler;

import com.cvccorp.store.model.exceptions.*;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import java.time.LocalDateTime;

public class ExceptionHandler {

    @ServerExceptionMapper
    public Response handleInvalidParamException(InvalidParamException e) {
        final var exceptionData = new ExceptionData()
                .setStatus(400)
                .setType("https://store.com.br/invalid-param")
                .setTitle("Invalid Param")
                .setDetail(e.getMessage())
                .setTimestamp(LocalDateTime.now());

        return Response.status(400).entity(exceptionData).build();
    }

    @ServerExceptionMapper
    public Response handleAlreadyExistsException(AlreadyExistsException e) {
        final var exceptionData = new ExceptionData()
                .setStatus(409)
                .setType("https://store.com.br/already-exists")
                .setTitle("Already Exists")
                .setDetail(e.getMessage())
                .setTimestamp(LocalDateTime.now());

        return Response.status(409).entity(exceptionData).build();
    }

    @ServerExceptionMapper
    public Response handleDatabaseException(DatabaseException e) {
        final var exceptionData = new ExceptionData()
                .setStatus(500)
                .setType("https://store.com.br/database-error")
                .setTitle("Database Error")
                .setDetail(e.getMessage())
                .setTimestamp(LocalDateTime.now());

        return Response.status(500).entity(exceptionData).build();
    }

    @ServerExceptionMapper
    public Response handleNotFoundException(NotFoundException e) {
        final var exceptionData = new ExceptionData()
                .setStatus(404)
                .setType("https://store.com.br/not-found")
                .setTitle("Not found entity")
                .setDetail(e.getMessage())
                .setTimestamp(LocalDateTime.now());

        return Response.status(404).entity(exceptionData).build();
    }

    @ServerExceptionMapper
    public Response handleException(Exception e) {
        final var exceptionData = new ExceptionData()
                .setStatus(500)
                .setType("https://store.com.br/internal-server-error")
                .setTitle("Internal Server Error")
                .setDetail(e.getMessage())
                .setTimestamp(LocalDateTime.now());

        return Response.status(500).entity(exceptionData).build();
    }
}
