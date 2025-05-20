package hu.pte.mik.prog4.api.controller;
import hu.pte.mik.prog4.entity.CompanyEntity;
import hu.pte.mik.prog4.service.CompanyService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
@Path("/company")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CompanyController {
    private final CompanyService CompanyService;
    public CompanyController() {
        CompanyService = new CompanyService();
    }
    @POST
    @Path("/save")
    public Response save(CompanyEntity company) {
        CompanyEntity result=CompanyService.save(company.getId(),company.getNev(),company.getAlapitasiEv(),company.getOrszag(),company.getIsmertTermek());
        if (result != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(result)
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }
    @POST
    @Path("/find/{id}")
    public Response findById(@PathParam("id")long id){
        CompanyEntity result=CompanyService.findById(id);
        if (result != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(result)
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }
    @GET
    public Response findAll(){
        List<CompanyEntity> result=CompanyService.findAll();
        return Response
                .status(Response.Status.OK)
                .entity(result)
                .build();
    }
    @GET
    @Path("/portion/{id}")
    public Response getCompany(@PathParam("id")String id){
        return Response
                .status(Response.Status.OK)
                .entity(CompanyService.getCompany(id))
                .build();
    }
}
