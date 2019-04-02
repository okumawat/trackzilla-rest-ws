package com.learn.trackzilla;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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

	@Path("/ticket/create")
	@Consumes(MediaType.APPLICATION_XML)
	@POST
	public Response createTicket(Ticket ticket) {
		Connection conn=null;
		PreparedStatement preparedStatement=null;
		try {
			conn = DBUtil.getDBConnection();
			preparedStatement = conn.prepareStatement("insert into tza_ticket(title,description,status,application_id,id) values(?,?,?,?,?)");
			preparedStatement.setString(1, ticket.getTitle());
			preparedStatement.setString(2, ticket.getDescription());
			preparedStatement.setString(3, ticket.getStatus());
			preparedStatement.setInt(4, ticket.getApplicationId());
			preparedStatement.setInt(5, ticket.getId());
			preparedStatement.execute();
			
			return Response.status(201).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(500).build();
		}finally{
			try {
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
     * @return ticket that will be returned as a application/json response.
     */
	@GET
	@Path("/ticket/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response getTicket(@PathParam("id") int id,@QueryParam("format") String format) {
		
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
    		return Response.ok("application/"+format).status(200).entity(ticket).build();
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
    @Produces(MediaType.APPLICATION_JSON)
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
