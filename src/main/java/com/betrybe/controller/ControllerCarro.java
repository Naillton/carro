package com.betrybe.controller;

import com.betrybe.model.EntityCarro;
import com.betrybe.service.ServiceCarro;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/carro")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ControllerCarro {

    @Inject
    private ServiceCarro serviceCarro;

    @GET
    public Response listarCarros() {
        try {
            return Response.ok(serviceCarro.getAllCars()).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id){
        EntityCarro car = serviceCarro.getById(id);
        if (car == null) {
            return Response.status(404).tag("id not found").build();
        } else {
            try {
                return Response.ok(car).build();
            } catch (Exception e) {
                return Response.serverError().build();
            }
        }
    }


    @POST
    public Response salvar(EntityCarro carro){
        try {
            serviceCarro.salvar(carro);
            return Response.status(201).entity(carro).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }


    @PATCH
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, EntityCarro carro){
        EntityCarro car = serviceCarro.update(id, carro);
        if (car == null) {
            return Response.status(404).tag("id not found").build();
        } else {
            try {
                return Response.ok(car).build();
            } catch (Exception e) {
                return Response.serverError().build();
            }
        }
    }


    @DELETE
    @Path("/{id}")
    public void deletar(@PathParam("id") Long id){
        serviceCarro.delete(id);
    }
}
