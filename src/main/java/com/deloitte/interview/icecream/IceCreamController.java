package com.deloitte.interview.icecream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.interview.icecream.resource.ServeIceCreamRequest;
import com.deloitte.interview.icecream.resource.ServeIceCreamResponse;
import com.deloitte.interview.icecream.service.ServeIceCreamService;

import lombok.AllArgsConstructor;

/**
 * @author Prudhvi
 *
 */
@RestController
@AllArgsConstructor
public class IceCreamController{
	@Autowired
	private ServeIceCreamService serveIceCreamService;
	/**
	 * This method is used to serve the ice cream and return change
	 * @param serveIceCreamRequest
	 * @return ServeIceCreamResponse This returns served status and change 
	 */
	@PostMapping("/serveIceCream")
	public ResponseEntity<ServeIceCreamResponse> serveIceCream(@RequestBody ServeIceCreamRequest serveIceCreamRequest)
		{
		try {
		ServeIceCreamResponse response = serveIceCreamService.serveIceCream(serveIceCreamRequest);
		return buildResponseEntity(response);
		}
		catch(IceCreamServeException e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
				
		}
	
	/**
	 * This method is used to build response
	 * @param response
	 * @return This returns HTTP status as OK and response body
	 */
	private ResponseEntity<ServeIceCreamResponse>  buildResponseEntity(ServeIceCreamResponse response) {
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
}
