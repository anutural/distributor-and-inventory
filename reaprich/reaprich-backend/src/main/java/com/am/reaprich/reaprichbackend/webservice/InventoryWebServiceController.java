package com.am.reaprich.reaprichbackend.webservice;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1/inventory")
public class InventoryWebServiceController {

    @RequestMapping(path = "/demo", method = RequestMethod.GET)
    public String DemoEndPoint() {
        return "Welcome to inventory rest apis";
    }
}
