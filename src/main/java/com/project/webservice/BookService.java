package com.project.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.entities.Book;
import com.project.model.BookDao;

@Path("/bookservice")
public class BookService {


	private BookDao bookDao = new BookDao() ;
	private Logger log = Logger.getLogger("hello");
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("status")
	public Response getStatus() {
		log.info("========== getStatus() ========");
		return Response.ok(
				"{\"status\":\"Service Book Service is running...\"}").build();
	}
	
	@GET
    @Path("/getBookByNameJSON/{name}")
    @Produces(MediaType.APPLICATION_JSON) 
    public Book getBookByNameJSON(@PathParam("name")String name){
        
        return bookDao.getBookByName(name) ;
    }
    
    /*@GET
    @Path("/getAllBooksInJSON")
    @Produces(MediaType.APPLICATION_JSON) 
    public Response getAllBooksInJSON(){
    	log.error("========== Begin getAllBooksInJSON ========");
    	String response = null;
    	List<Book> list =  bookDao.getAllBooks() ;
    	response = toJSONString(list);
    	return Response.ok(response).build();
    }*/
    
    @GET
    @Path("/getAllBooksInJSON")
    @Produces(MediaType.APPLICATION_JSON) 
    public List<Book> getAllBooksInJSON(){
    	log.info("========== Begin getAllBooksInJSON ========");
    	
    	return bookDao.getAllBooks() ;
    }
    
    public String toJSONString(Object object) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyy-MM-dd'T'HH:mm:ss.SSS'Z'"); // ISO8601 /
																	// UTC
		Gson gson = gsonBuilder.create();
		return gson.toJson(object);
	}
}
