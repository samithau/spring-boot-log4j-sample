package com.example.log4j2sample.Controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/accountDetails")
@Slf4j
public class BankController {


    @GetMapping("/accountDetails/{accountNo}")
    public String account(@PathVariable String accountNo) {
        String accountDetailsResponse = callAccountDetailsEndpoint(accountNo);
        return accountDetailsResponse;
    }

    private String callAccountDetailsEndpoint(String accountNo) {
        System.out.println("inside callAccountDetailsEndpoint");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                "/accountDetails/", HttpMethod.GET, null, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Inside");
            return response.getBody();
        } else {
            System.out.println("else");
            return "Error calling /accountDetails/ endpoint";
        }
    }


    @GetMapping("/accountDetails/")
    public String accountDetails() {
        return "00051521522";
    }


}
