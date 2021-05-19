package it.prova.personajaxrest2.web.listener.rest.resources;



import com.fasterxml.jackson.databind.SerializationFeature;
import it.prova.personajaxrest2.model.Persona;
import it.prova.personajaxrest2.service.MyServiceFactory;
import it.prova.personajaxrest2.service.persona.PersonaService;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;


@Path("/persona")
public class PersonaResource {

	private static final Logger LOGGER = Logger.getLogger(PersonaResource.class.getName());

	@Context
	HttpServletRequest request;

	private ObjectMapper objectMapper = new ObjectMapper();

	private PersonaService personaServiceInstance = MyServiceFactory.getPersonaServiceInstance();

	@GET
	@Path("{id : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPersona(@PathParam("id") Long id) throws Exception {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		Persona personaDaCaricare = personaServiceInstance.caricaSingoloElemento(id);
		return Response.status(200).entity(convertDate(personaDaCaricare)).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertNuovaPersona(Persona personaInput) throws Exception {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		personaServiceInstance.inserisciNuovo(personaInput);
		return Response.status(200).entity(personaInput).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll() throws Exception {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		List<Persona> result = personaServiceInstance.listAllElements();

		result.forEach(System.out::println);
		return Response.status(200).entity(convertDateList(result)).build();
	}

	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchPersona(Persona example) throws Exception {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		List<Persona> result = personaServiceInstance.findByExample(example);
		return Response.status(200).entity(convertDateList(result)).build();
	}
	
	@DELETE
	@Path("{id : \\d+}")
	public Response deletePersona(@PathParam("id") Long id) throws Exception {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		Persona personaInstance = personaServiceInstance.caricaSingoloElemento(id);

		try {
			personaServiceInstance.rimuovi(personaInstance);

			return Response.status(200).entity("Rimossa Automobile con id: "+id).build();
		} catch (Exception e) {

			return Response.status(Response.Status.NOT_FOUND).entity("not found").build();
		}

	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response aggiornaPersona(Persona personaInput) throws Exception {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		personaServiceInstance.aggiorna(personaInput);
		return Response.status(200).entity(convertDate(personaInput)).build();
	}



	private String convertDate(Persona persona) throws IOException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
		objectMapper.setDateFormat(df);
		String json = objectMapper.writeValueAsString(persona);
		return json;
	}

	private String convertDateList(List<Persona> persona) throws IOException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
		objectMapper.setDateFormat(df);
		String json = objectMapper.writeValueAsString(persona);
		return json;
	}



}
