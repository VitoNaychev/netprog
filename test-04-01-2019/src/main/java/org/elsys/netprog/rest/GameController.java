package org.elsys.netprog.rest;
 
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.Random;
 
 
@Path("/car_sub")
public class GameController {
    private static HashMap<String, CarSub> carSubs = new HashMap<String, CarSub>();
   
   
    @PUT
    @Path("/{color}/{car_reg}")
    @Produces(value={MediaType.APPLICATION_JSON})
    public Response guess(@PathParam("color") String zone, @PathParam("car_reg") String carReg) throws Exception{
        //TODO: Add your code here
        if(!zone.equals("blue") && !zone.equals("green")){
            return Response.status(404).build();
        }
        if(!isValidReg(carReg)) {
        	return Response.status(404).build();
        }
       
        CarSub newCar = new CarSub(carReg, zone);
       
        carSubs.put(carReg + zone, newCar);
       
        return Response.status(200).build();
    }
   
    @GET
    @Path("/{car_reg}")
    @Produces(value={MediaType.APPLICATION_JSON})
    public Response getGames(@PathParam("car_reg") String carReg) {
        //TODO: Add your code here
        CarSub curCarBlue = null;
        CarSub curCarGreen = null;
        if(carSubs.containsKey(carReg + "blue")){
            curCarBlue = carSubs.get(carReg + "blue");
            if(isDue(curCarBlue.due)) {
            	curCarBlue.active = false;
            }
        }
        if(carSubs.containsKey(carReg + "green")){
            curCarGreen = carSubs.get(carReg + "green");
            if(isDue(curCarGreen.due)) {
            	curCarGreen.active = false;
            }
        }
        if(!carSubs.containsKey(carReg + "green") && !carSubs.containsKey(carReg + "blue")){
            return Response.status(404).build();
        }
        
        if(curCarBlue == null) {
        	return Response.status(200).entity(curCarGreen).build();
        }else if(curCarGreen == null) {
        	return Response.status(200).entity(curCarBlue).build();
        }else {
        	ArrayList<CarSub> subs = new ArrayList<CarSub>();
        	subs.add(curCarBlue);
        	subs.add(curCarGreen);
        	return Response.status(200).entity(subs).build();
        }
    }
    
    private static boolean isDue(String date) {
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss z d/M/yyyy");
    	Date dueDate;
    	Date now = new Date();
		try {
			dueDate = sdf.parse(date);
	    	if(now.compareTo(dueDate) > 0) {
	    		return true;
	    	}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	return false;
    }
   
    private static boolean isValidReg(String reg) {
    	Pattern p = Pattern.compile("^[ETYOPAHKXCBM]{2}[0-9]{4}[ETYOPAHKXCBM]{2}$");
    	Matcher m = p.matcher(reg);
    	if(m.matches())
    		return true;
    	else
    		return false;
    }	
}