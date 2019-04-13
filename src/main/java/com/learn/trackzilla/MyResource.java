package com.learn.trackzilla;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.learn.trackzilla.model.Application;
import com.learn.trackzilla.model.Ticket;
import com.learn.trackzilla.utils.DBUtil;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("v1")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "application/json" media type.
     *
     * @return ticket that will be returned as a application/json response.
     */
	@GET
	@Path("/ticket/{id}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response getTicket(@PathParam("id") int id) {
		
		Ticket ticket = null;
		ResultSet rs = null;
		Connection conn=null;
		PreparedStatement preparedStatement=null;
    	try {
    		conn=DBUtil.getDBConnection();
    		String sql = "select * from tza_ticket where id=?";
    		preparedStatement = conn.prepareStatement(sql);
    		preparedStatement.setInt(1, id);
    		rs =preparedStatement.executeQuery();
    		while(rs.next()) {
    			ticket=new Ticket(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5));
    		}
    		return Response.status(200).entity(ticket).build(); 
    	}catch(Exception e) {
    		e.printStackTrace();
    		System.out.println("errored out .."+e.getMessage());
    		return Response.status(500).build();
    	}finally {
    		try {
    			rs.close();
    			preparedStatement.close();
				conn.close();
				System.out.println("db connection closed.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
		
	}
	
	/**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "application/json" media type.
     *
     * @return application that will be returned as a application/json response.
     */
    @GET
    @Path("/application/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response getApplication(@PathParam("id") int id) {
    	Application application = new Application();
    	Connection conn=null;
    	ResultSet rs = null;
    	PreparedStatement preparedStatement=null;
    	try {
    		conn=DBUtil.getDBConnection();
    		String sql = "select * from tza_application where id=?";
    		preparedStatement = conn.prepareStatement(sql);
    		preparedStatement.setInt(1, id);
    		rs =preparedStatement.executeQuery();
    		while(rs.next()) {
    			application.setId(rs.getInt(1));
    			application.setName(rs.getString(2));
    			application.setDescription(rs.getString(3));
    		}
    		return Response.status(200).entity(application).build();
    	}catch(Exception e) {
    		e.printStackTrace();
    		System.out.println("errored out .."+e.getMessage());
    		return Response.status(500).build();
    	}finally {
    		try {
    			rs.close();
    			preparedStatement.close();
				conn.close();
				System.out.println("db connection closed.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
}
