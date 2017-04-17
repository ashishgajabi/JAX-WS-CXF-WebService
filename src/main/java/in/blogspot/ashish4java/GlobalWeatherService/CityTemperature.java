package in.blogspot.ashish4java.GlobalWeatherService;

import javax.jws.WebMethod;
import javax.jws.WebService;

import in.blogspot.ashish4java.GlobalWeatherService.exception.CityNotFoundException;


/**
 * @author admin
 * Service Interface defines contract for getting particular city temperature and rain details. * 
 *
 */
@WebService
public interface CityTemperature {

	
    /**
     * Service operation to get temperature of particular city.
     * @param cityName - the name of city
     * @return String - temperature of city.
     * @throws CityNotFoundException - the exception if city name not found.
     */	
	@WebMethod(operationName = "getTemperatureOfCity")
	String getCityTemperature(String cityName) throws CityNotFoundException;

	/**
	 * @param cityName
	 * @return
	 * @throws CityNotFoundException
	 */
  	String getRainDetails(String cityName) throws CityNotFoundException;

}
