package com.example.log4j2sample.Controller;

import com.example.log4j2sample.Log4jSampleApplication;
import com.example.log4j2sample.bean.Other;
import com.example.log4j2sample.bean.SampleResponse;
import lombok.extern.slf4j.Slf4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/accounts")
@Slf4j
public class BankController {
    private static final Logger logger = LogManager.getLogger(Log4jSampleApplication.class);

    @GetMapping("/getAccountDetailsByAccountNo/{accountNo}")
    public String account(@PathVariable String accountNo) {
        String accountDetailsResponse = callAccountDetailsEndpoint(accountNo);
        return accountDetailsResponse;
    }

    private String callAccountDetailsEndpoint(String accountNo) {
        logger.info("Info log Inside Method");
        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "http://localhost:8080/accounts";
        ResponseEntity<String> response = restTemplate.exchange(
                baseUrl + "/accountDetails/", HttpMethod.GET, null, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return "Error calling /accountDetails/ endpoint";
        }
    }

    @GetMapping("/accountDetails/")
    public SampleResponse accountDetails() {
        SampleResponse response = new SampleResponse(1, "", "", new Other("", "", ""));
        return response;
    }

}
