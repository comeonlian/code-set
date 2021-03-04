package com.leolian.javaweb.framework.chapter08;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

public interface ProductService {

    @GET
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    List<Product> retrieveAllProducts();
	
    @POST
    @Path("/product")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Product createProduct(Product product);

    @PUT
    @Path("/product/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Product updateProductById(@PathParam("id") long id, Map<String, Object> fieldMap);

    @DELETE
    @Path("/product/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Product deleteProductById(@PathParam("id") long id);
}