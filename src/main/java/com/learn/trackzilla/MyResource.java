package com.learn.trackzilla;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.learn.trackzilla.model.Application;
import com.learn.trackzilla.utils.DBUtil;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("v1")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Path("/application/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApplication(@PathParam("id") int id) {
    	Application application = new Application();
    	Connection conn=DBUtil.getDBConnection();
    	try {
    		String sql = "select * from tza_application where id="+id;
    		ResultSet rs =conn.createStatement().executeQuery(sql);
    		while(rs.next()) {
    			application.setId(rs.getInt(1));
    			application.setName(rs.getString(2));
    			application.setDescription(rs.getString(3));
    		}
    		System.out.println(application.getName());
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		System.out.println("errored out .."+e.getMessage());
    		return null;
    	}finally {
    		try {
				conn.close();
				System.out.println("db connection closed.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return Response.status(200).entity(application).build();
    	//return application.getId()+","+application.getName()+","+application.getDescription();
    }
}
