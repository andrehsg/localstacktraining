package com.daitan.resources;

import com.daitan.core.ConsumerSQS;
import com.daitan.model.MessageResponse;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)

public class LocalStackResource {

    @POST
    @Path("/newMessage")
    public Response notifyNewArrivingMessages () {

        try {
            ConsumerSQS.consume();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
        return Response.ok(new MessageResponse("Messages Received")).build();
    }
}
