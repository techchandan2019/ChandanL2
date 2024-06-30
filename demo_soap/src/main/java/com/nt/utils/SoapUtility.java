package com.nt.utils;

import javax.xml.soap.*;

public class SoapUtility {

    public static SOAPMessage createSOAPRequest() throws Exception {

        /*
         Constructed SOAP Request Message:
         <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:web="http://www.example.com/webservice">
            <soapenv:Header/>
            <soapenv:Body>
               <web:ExecuteFinacleScriptRequest>
                  <web:ExecuteFinacleScriptCustomData>
                     <web:pan>12345</web:pan>
                     <web:glcode>67890</web:glcode>
                  </web:ExecuteFinacleScriptCustomData>
               </web:ExecuteFinacleScriptRequest>
            </soapenv:Body>
         </soapenv:Envelope>
         */
      /*  MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = "http://www.example.com/webservice";


        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("web", serverURI);
        SOAPBody soapBody = envelope.getBody();
        SOAPElement executeFinacleScriptRequest = soapBody.addChildElement("ExecuteFinacleScriptRequest", "web");
        SOAPElement executeFinacleScriptCustomData = executeFinacleScriptRequest.addChildElement("ExecuteFinacleScriptCustomData", "web");

        executeFinacleScriptCustomData.addChildElement("pan", "web").addTextNode("12345");
        executeFinacleScriptCustomData.addChildElement("glcode", "web").addTextNode("67890");

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI + "/ExecuteFinacleScriptRequest");

        soapMessage.saveChanges();
        return soapMessage;*/
        //============================================================
    /*    <soapenv:Envelope xmlns:dat="http://www.dataaccess.com/webservicesserver/" xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
    <soapenv:Header/>
    <soapenv:Body>
        <dat:NumberToDollars>
            <dat:dNum>99.0</dat:dNum>
        </dat:NumberToDollars>
    </soapenv:Body>
</soapenv:Envelope>*/
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String dat = "http://www.dataaccess.com/webservicesserver/";
        String soapenv="http://schemas.xmlsoap.org/soap/envelope/";


        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("dat", dat);
        envelope.addNamespaceDeclaration("soapenv", soapenv);
        SOAPBody soapBody = envelope.getBody();
        SOAPElement executeFinacleScriptRequest = soapBody.addChildElement("NumberToDollars","dat");
//        SOAPElement executeFinacleScriptCustomData = executeFinacleScriptRequest.addChildElement("ExecuteFinacleScriptCustomData");

        executeFinacleScriptRequest.addChildElement("dNum","dat").addTextNode("99.0");

//        MimeHeaders headers = soapMessage.getMimeHeaders();
//        headers.addHeader("SOAPAction", serverURI + "/ExecuteFinacleScriptRequest");

        soapMessage.saveChanges();
        return soapMessage;


    }
}
