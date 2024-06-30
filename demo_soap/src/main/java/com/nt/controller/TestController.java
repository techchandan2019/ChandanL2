package com.nt.controller;

import com.nt.utils.SoapUtility;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayOutputStream;

@RestController
public class TestController {

    @GetMapping("/get")
    public Object getMessage() throws Exception {

    /*    String url = "https://www.dataaccess.com/webservicesserver/numberconversion.wso?WSDL";
//        String soapAction = "http://www.example.com/soapAction";
        String xmlRequest = "<soapenv:Envelope xmlns:dat=\"http://www.dataaccess.com/webservicesserver/\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "    <soapenv:Header/>\n" +
                "    <soapenv:Body>\n" +
                "        <dat:NumberToDollars>\n" +
                "            <dat:dNum>99.0</dat:dNum>\n" +
                "        </dat:NumberToDollars>\n" +
                "    </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        RestTemplate template=new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
//        headers.add("SOAPAction", soapAction);

        HttpEntity<String> request = new HttpEntity<>(xmlRequest, headers);

        ResponseEntity<String> response = template.exchange(url, HttpMethod.POST, request, String.class);

        return response.getBody();*/

        //=========================================

        String soapEndpointUrl="https://www.dataaccess.com/webservicesserver/numberconversion.wso?WSDL";
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        SOAPMessage request=SoapUtility.createSOAPRequest();
        // convert to String
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        request.writeTo(outputStream);
        String message=new String(outputStream.toByteArray());

        RestTemplate template=new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
//        headers.add("SOAPAction", soapAction);

        HttpEntity<String> request1 = new HttpEntity<>(message, headers);

        ResponseEntity<String> response = template.exchange(soapEndpointUrl, HttpMethod.POST, request1, String.class);

        return response.getBody();

    }
    @PostMapping("/api")
    public Object getMessage1(@RequestBody String data) throws Exception {

        String url = "https://www.dataaccess.com/webservicesserver/numberconversion.wso?WSDL";
        RestTemplate template=new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
//        headers.add("SOAPAction", soapAction);

        HttpEntity<String> request = new HttpEntity<>(data, headers);

        ResponseEntity<String> response = template.exchange(url, HttpMethod.POST, request, String.class);

        return response.getBody();

    }
}
