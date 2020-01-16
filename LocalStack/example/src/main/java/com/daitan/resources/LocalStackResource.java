package com.daitan.resources;

import com.daitan.core.ConsumerSQS;

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
    public Response.ResponseBuilder notifyNewArrivingMessages () {

        try {
            ConsumerSQS.consume();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR);
        }
        return Response.status(Response.Status.OK).entity("Message received");
    }
}
