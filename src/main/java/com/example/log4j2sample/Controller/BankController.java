package com.example.log4j2sample.Controller;

import com.example.log4j2sample.Entity.AccountDetails;
import com.example.log4j2sample.Log4jSampleApplication;
import com.example.log4j2sample.Service.BankService;
import com.example.log4j2sample.bean.Other;
import com.example.log4j2sample.bean.SampleResponse;
import lombok.extern.slf4j.Slf4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@Slf4j
public class BankController {

    @Autowired
    BankService bankService;

    @Autowired
    private Environment environment;

    String baseUrl = System.getenv("BASEURL");
    private static final Logger logger = LogManager.getLogger(Log4jSampleApplication.class);

    @GetMapping("/getAccountDetailsByAccountNo/{accountNo}")
    public String getAccountDetails(@PathVariable String accountNo) {
        String accountDetailsResponse = callAccountDetailsEndpointbyAccountNo(accountNo);
        return accountDetailsResponse;
    }

   private String callAccountDetailsEndpoint(String accountNo) {
        logger.info("Info log Inside Method");
        System.out.println("callAccountDetailsEndpoint"+accountNo);
        RestTemplate restTemplate = new RestTemplate();
       // String baseUrl = "http://localhost:8990/bank/accounts";
        ResponseEntity<String> response = restTemplate.exchange(
                baseUrl + "/getAccountDetails/", HttpMethod.GET, null, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            logger.error("Oops! Error calling /accountDetails/ endpoint");
            return "Error calling /getAccountDetails/ endpoint";
        }
    }


   private String callAccountDetailsEndpointbyAccountNo(String accountNo) {

        logger.info("callAccountDetailsEndpoint--" + accountNo);

        RestTemplate restTemplate = new RestTemplate();

        String baseUrl = "http://localhost:8990/bank/accounts";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + "/getAccountDetails/")
                .queryParam("accountNo", accountNo);

        ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            logger.error("Oops! Error calling /accountDetails/ endpoint");
            return "Error calling /getAccountDetails/ endpoint";
        }
    }


    @GetMapping("/accountDetails/")
    public SampleResponse accountDetails() {
        logger.warn("This is a warning for accountDetails!");
        SampleResponse response = new SampleResponse(1, "", "", new Other("", "", ""));
        return response;
    }

    //DataBase Connect
    @GetMapping(value = "/getAccountDetails")
    public ResponseEntity<List<AccountDetails>> getAllAccountDetails() {
        try {
            List<AccountDetails> accountDetails = bankService.getAllAccountDetails();
            return new ResponseEntity<List<AccountDetails>>(accountDetails, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    //DataBase Connect
    @GetMapping(value = "/getAccountDetailsByNumber/{accountNo}")
    public  ResponseEntity<?> getAllAccountDetailsbyAccountNumber(@PathVariable String accountNo) {
        logger.info("getAllAccountDetailsbyAccountNumber--" + accountNo);
        try {
            List<AccountDetails> accountDetails = bankService.getAllAccountDetailsbyAccountNumber(accountNo);
            if (accountDetails.isEmpty()) {
                logger.warn("This is a warning for accountDetails!");
                return new ResponseEntity<>("Not Found.", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<List<AccountDetails>>(accountDetails, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("This is a warning for accountDetails!");
            return new ResponseEntity<>("An error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
