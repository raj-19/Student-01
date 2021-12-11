package net.codejava.ws;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Path("/students")
public class StudentResource 
{
	private StudentDAO dao = StudentDAO.getInstance();
	
	//LIST ALL
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> list()
	{
		return dao.listAll();
	}
	
	//ADD NEW
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@PostMapping(value="student",consumes = { MediaType.APPLICATION_JSON })
	public Response add(@RequestBody Student student) throws URISyntaxException
	{
		int k = dao.add(student);
		
		URI uri = new URI("students/"+k);
		
		return Response.created(uri).build();
	}
	
	//GET BY ID
	@GET
	@Path("{id}")
	public Response get(@PathParam("id") int id)
	{
		Student student = dao.get(id);
		if(student != null)
		{
			return Response.ok(student, MediaType.APPLICATION_JSON).build();
		}
		else
		{
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	@PutMapping(value = "student" , consumes = { MediaType.APPLICATION_JSON })
	public Response update(@PathParam("id") int id,@RequestBody Student student)
	{
		student.setId(id);
		if(dao.update(student))
		{
			return Response.ok().build();
		}
		else
		{
			return Response.notModified().build();
		}
	}
	
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") int id)
	{
		if(dao.delete(id))
		{
			return Response.ok().build();
		}
		else
		{
			return Response.notModified().build();
		}
	}
}
