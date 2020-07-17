package com.oath2.demo.demo.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.stream.Collectors;

import com.oath2.demo.demo.dao.CarRepository;
import com.oath2.demo.demo.model.Car;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoolCarController {
    private CarRepository repository;

    public CoolCarController(CarRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cool-cars")
    @CrossOrigin(origins = "*")
    public Collection<Car> coolCars(Principal principal) {
        // OAuth2Authentication authentication = (OAuth2Authentication) principal;
        // Map<String, Object> details = (Map<String, Object>) authentication.getUserAuthentication().getDetails();

        return repository.findAll().stream()
                .filter(this::isCool)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
	public String handleCustomersRequest(Principal principal) {
			return principal.toString();
	}

// curl -v -X GET \
// -H "Accept: application/json" \
// -H "Content-Type: application/json" \
// -H "Authorization: Bearer AT.YqTYvyTw_W6O8on9LQt8cx3LpNMHLOa93yX1jNI64YU" \
// "https://dev-599893.okta.com/api/v1/users/0oaksaahzkSeDzrLa4x6/roles"

    

    private boolean isCool(Car car) {
        return !car.getName().equals("AMC Gremlin") &&
                !car.getName().equals("Triumph Stag") &&
                !car.getName().equals("Ford Pinto") &&
                !car.getName().equals("Yugo GV");
    }
}