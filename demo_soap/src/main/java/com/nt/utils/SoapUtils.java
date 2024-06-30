package com.nt.utils;

import javax.xml.soap.SOAPMessage;

public class SoapUtils {

   /* private static final String ENVELOPE_NS = "http://www.finacle.com/fixml";
    private static final String WEB_LINK = "http://webservice.fiusb.ci.infosys.com/";
    private static final Logger LOGGER = LoggerFactory.getLogger(SoapUtils.class);

    private SoapUtils() {
    }
    public static SOAPMessage getGenericRequest(IOBCMSRequest request) throws SOAPException, IOException {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
        soapEnvelope.addAttribute(new QName("xmlns:web"), WEB_LINK);
        SOAPBody soapBody = soapEnvelope.getBody();
        SOAPElement executeService = soapBody.addChildElement("web:executeService");
        SOAPElement arg0 = executeService.addChildElement("arg0");
        arg0.addAttribute(new QName("xmlns"), ENVELOPE_NS);
        SOAPElement fixml = arg0.addChildElement("FIXML");
        setHeader(request, fixml);
        if (request.getBody().getAccessTokenRequest != null)
            setTokenBody(request, fixml);
        else
            setBody(request, fixml);
        String requestAsstr = getSoapMessageAsStr(soapMessage);
        LOGGER.info("Generated Soap Message \n {}", requestAsstr);
        return getSpecificRequest(request, requestAsstr);
    }

    public static SOAPMessage getSpecificRequest(IOBCMSRequest request, String reqString)
            throws SOAPException, IOException {
        String node = getSoapWithReplacedTags(reqString);
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
        soapEnvelope.addAttribute(new QName("xmlns:web"), WEB_LINK);
        SOAPBody soapBody = soapEnvelope.getBody();
        SOAPElement executeService = soapBody.addChildElement("web:executeService");
        SOAPElement arg0 = executeService.addChildElement("arg0");
        arg0.addTextNode(node);
        String send = getSoapMessageAsStr(soapMessage);
        String finalMessageString = send.replace("amp;", "");
        LOGGER.info("Converted Soap Message \n {}", finalMessageString);
        return getStrAsSoapMessage(finalMessageString);
    }

    private static void setBody(IOBCMSRequest request, SOAPElement soapBody) throws SOAPException {
        SOAPElement body = soapBody.addChildElement("Body");
        SOAPElement executeFinacleScriptRequest = body.addChildElement("executeFinacleScriptRequest");
        SOAPElement finacleScriptInputVO = executeFinacleScriptRequest.addChildElement("ExecuteFinacleScriptInputVO");
        SOAPElement requestId = finacleScriptInputVO.addChildElement("requestId");
        addCustomData(request, executeFinacleScriptRequest, requestId);
    }

    private static void setTokenBody(IOBCMSRequest request, SOAPElement soapBody)
            throws SOAPException {
        SOAPElement body = soapBody.addChildElement("Body");
        SOAPElement getAccesTokenRequest = body.addChildElement("GetAccessTokenRequest");
        SOAPElement getAccessToken = getAccesTokenRequest.addChildElement("GetAccessToken");
        SOAPElement clientId = getAccessToken.addChildElement("ClientID");
        clientId.addTextNode(request.getBody().getAccessTokenRequest.getAccessToken.getClientID());
        SOAPElement clientSecret = getAccessToken.addChildElement("ClientSecret");
        clientSecret.addTextNode(request.getBody().getAccessTokenRequest.getAccessToken.getClientSecret());
    }

    private static void addCustomData(IOBCMSRequest request, SOAPElement executeFinacleScriptRequest, SOAPElement requestId) throws SOAPException {
        requestId.addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptInputVO().getRequestId());
        SOAPElement executeFinacleScript_CustomData = executeFinacleScriptRequest.addChildElement("executeFinacleScript_CustomData");
        SOAPElement requestID = executeFinacleScript_CustomData.addChildElement("requestID");
        requestID.addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getChannelRequestId());
        SOAPElement userID = executeFinacleScript_CustomData.addChildElement("userID");
        userID.addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getUserID());
        switch (request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getRequestId()) {
            case "dealerinfofetch.scr":
                executeFinacleScript_CustomData.addChildElement("pan").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getPan());
                executeFinacleScript_CustomData.addChildElement("glcode").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getGlcode());
                break;
            case "deDupeService.scr":
                executeFinacleScript_CustomData.addChildElement("pan").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getPan());
                break;
            case "inquireCIF_los.scr":
            case "fetchDepDetails.scr":
            case "inquireCIF.scr":
                SOAPElement customerId = executeFinacleScript_CustomData.addChildElement("customerId");
                customerId.addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getCustomerId());
                break;
            case "limitdetailsfetch.scr":
                executeFinacleScript_CustomData.addChildElement("cif").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getCif());
//                    executeFinacleScript_CustomData.addChildElement("loanac").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getLoanAccountNumber());
                break;
            case "scf_meob.scr":
                executeFinacleScript_CustomData.addChildElement("makerID").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getMakerID());
                executeFinacleScript_CustomData.addChildElement("checkerID").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getCheckerID());
                executeFinacleScript_CustomData.addChildElement("cifId").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getCif());
                executeFinacleScript_CustomData.addChildElement("solId").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getSolId());
                executeFinacleScript_CustomData.addChildElement("bensol").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getBensol());
                executeFinacleScript_CustomData.addChildElement("amount").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getAmount().toString());
                executeFinacleScript_CustomData.addChildElement("Invoiceno").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getInvoiceno());
                executeFinacleScript_CustomData.addChildElement("invoicedt").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getInvoicedt().toString());
                executeFinacleScript_CustomData.addChildElement("docno").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getDocno());
                executeFinacleScript_CustomData.addChildElement("Tenor").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getTenor());
                executeFinacleScript_CustomData.addChildElement("billpurpose").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getBillpurpose());
                executeFinacleScript_CustomData.addChildElement("dueDate").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getDueDate().toString());
                executeFinacleScript_CustomData.addChildElement("operAcctId").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getOperAcctId());
                executeFinacleScript_CustomData.addChildElement("operChrgAcctId").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getOperChrgAcctId());
                executeFinacleScript_CustomData.addChildElement("partycifId").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getPartycifId());
                if (request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getPartyname() != null)
                    executeFinacleScript_CustomData.addChildElement("partyname").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getPartyname());
                if (request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getPartyAddress() != null)
                    executeFinacleScript_CustomData.addChildElement("partyAddress").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getPartyAddress());
                break;
            case "scf_purchase.scr":
                executeFinacleScript_CustomData.addChildElement("makerID").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getMakerID());
                executeFinacleScript_CustomData.addChildElement("checkerID").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getCheckerID());
                executeFinacleScript_CustomData.addChildElement("solId").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getSolId());
                executeFinacleScript_CustomData.addChildElement("billId").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getBillId());
                executeFinacleScript_CustomData.addChildElement("eventacno").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getEventacno());
                executeFinacleScript_CustomData.addChildElement("amount").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getAmount().toString());
                executeFinacleScript_CustomData.addChildElement("operAcctId").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getOperAcctId());
                executeFinacleScript_CustomData.addChildElement("operChrgAcctId").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getOperChrgAcctId());
                break;
            case "kccstp_amt_trnsfer.scr":
                executeFinacleScript_CustomData.addChildElement("loanac").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getLoanac());
                executeFinacleScript_CustomData.addChildElement("sbac").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getSbac());
                executeFinacleScript_CustomData.addChildElement("amt").addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getAmt().toString());
                break;
            case "scf_fba_inq.scr":
                SOAPElement cif = executeFinacleScript_CustomData.addChildElement("cif");
                cif.addTextNode(request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getCif());
                break;


            default:
                throw new IllegalStateException("Unexpected value: " + request.getBody().getExecuteFinacleScriptRequest().getExecuteFinacleScriptCustomData().getRequestId());
        }
    }

    private static void setHeader(IOBCMSRequest request, SOAPElement element) throws SOAPException {
        SOAPElement header = element.addChildElement("Header");
        SOAPElement requestHeader = header.addChildElement("RequestHeader");
        setMessageKey(request, requestHeader);
        setRequestMessageInfo(request, requestHeader);
        setSecurity(request, requestHeader);
    }

    private static void setSecurity(IOBCMSRequest request, SOAPElement requestHeader) throws SOAPException {
        SOAPElement security = requestHeader.addChildElement("Security");
        SOAPElement fiCertToken = security.addChildElement("FICertToken");
        fiCertToken.addTextNode(request.getHeader().getRequestHeader().getSecurity().getFiCertToken());
        SOAPElement realUserLoginSessionId = security.addChildElement("RealUserLoginSessionId");
        realUserLoginSessionId.addTextNode(request.getHeader().getRequestHeader().getSecurity().getRealUserLoginSessionId());
        SOAPElement realUser = security.addChildElement("RealUser");
        realUser.addTextNode(request.getHeader().getRequestHeader().getSecurity().getRealUser());
        SOAPElement realUserPwd = security.addChildElement("RealUserPwd");
        realUserPwd.addTextNode(request.getHeader().getRequestHeader().getSecurity().getRealUserPwd());
        SOAPElement ssoTransferToken = security.addChildElement("SSOTransferToken");
        ssoTransferToken.addTextNode(request.getHeader().getRequestHeader().getSecurity().getSsoTransferToken());
    }

    private static void setRequestMessageInfo(IOBCMSRequest request, SOAPElement requestHeader) throws SOAPException {
        SOAPElement requestMessageInfo = requestHeader.addChildElement("RequestMessageInfo");
        SOAPElement bankId = requestMessageInfo.addChildElement("BankId");
        bankId.addTextNode(request.getHeader().getRequestHeader().getRequestMessageInfo().getBankId());
        SOAPElement timeZone = requestMessageInfo.addChildElement("TimeZone");
        timeZone.addTextNode(request.getHeader().getRequestHeader().getRequestMessageInfo().getTimeZone());
        SOAPElement entityId = requestMessageInfo.addChildElement("EntityId");
        entityId.addTextNode(request.getHeader().getRequestHeader().getRequestMessageInfo().getEntityId());
        SOAPElement entityType = requestMessageInfo.addChildElement("EntityType");
        entityType.addTextNode(request.getHeader().getRequestHeader().getRequestMessageInfo().getEntityType());
        SOAPElement armCorrelationId = requestMessageInfo.addChildElement("ArmCorrelationId");
        armCorrelationId.addTextNode(request.getHeader().getRequestHeader().getRequestMessageInfo().getArmCorrelationId());
        SOAPElement messageDateTime = requestMessageInfo.addChildElement("MessageDateTime");
        messageDateTime.addTextNode(LocalDateTime.now().toString());
    }

    private static void setMessageKey(IOBCMSRequest request, SOAPElement requestHeader) throws SOAPException {
        SOAPElement messageKey = requestHeader.addChildElement("MessageKey");
        SOAPElement requestUUID = messageKey.addChildElement("RequestUUID");
        requestUUID.addTextNode(request.getHeader().getRequestHeader().getMessageKey().getRequestUUID());
        SOAPElement serviceRequestId = messageKey.addChildElement("ServiceRequestId");
        serviceRequestId.addTextNode(request.getHeader().getRequestHeader().getMessageKey().getServiceRequestId());
        SOAPElement serviceRequestVersion = messageKey.addChildElement("ServiceRequestVersion");
        serviceRequestVersion.addTextNode(request.getHeader().getRequestHeader().getMessageKey().getServiceRequestVersion());
        SOAPElement channelId = messageKey.addChildElement("ChannelId");
        channelId.addTextNode(request.getHeader().getRequestHeader().getMessageKey().getChannelId());
        if (request.getBody().getExecuteFinacleScriptRequest() != null) {
            SOAPElement accessToken = messageKey.addChildElement("AccessToken");
            accessToken.addTextNode(request.getHeader().getRequestHeader().getMessageKey().getAccessToken());
        }
    }


    public static FIXML getResponseBody(RestTemplate restTemplate, SOAPMessage message, URL endpoint) {
        try {
            SOAPMessage response = postNoSsl(restTemplate, message, endpoint);
            String strmessage = getSoapMessageAsStr(response);
            String res = getSoapWithReplacedTagsForresponse(strmessage);
            LOGGER.info("Complete Response \n {}", response);
            XmlMapper xmlMapper = new XmlMapper();
            FIXML fixml = xmlMapper.readValue(res, FIXML.class);
            LOGGER.info("FIXML {}", fixml);
            return fixml;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static RestTemplate getNoSslRestTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial((chain, authType) -> true).build();
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf)
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        return new RestTemplate(requestFactory);
    }

    private static <T> SOAPMessage post(final SOAPMessage message, final URL endpoint) {
        final Callable<SOAPMessage> responseCallable = () -> {
            try {
                SOAPConnection connection = Try.of(SOAPConnectionFactory::newInstance)
                        .mapTry(SOAPConnectionFactory::createConnection).getOrElseThrow(
                                e -> new IOBCMSException(
                                        "Unable to create connection to SOAP connection factory", e));
                //SOAPMessage msg=connection.call(message, endpoint);
                return connection.call(message, endpoint);
            } catch (SOAPExceptionImpl e) {
                throw new BadResponseException("Bad Response or Invalid Url. Reason: " + e.getMessage());
            } catch (Exception e) {
                throw new IOBCMSException("Error during connection. Reason: " + e.getMessage());
            }
        };
        return invokeResponseCallable(responseCallable);
    }

    private static SOAPMessage postNoSsl(RestTemplate restTemplate, final SOAPMessage message, final URL endpoint) {
        final Callable<SOAPMessage> responseCallable = () -> {
            String soapPayload = getSoapMessageAsStr(message);
            HttpEntity<String> requestEntity = new HttpEntity<>(soapPayload, null);
            ResponseEntity<String> response = restTemplate.exchange(endpoint.toURI(), HttpMethod.POST, requestEntity, String.class);
            if (!response.getStatusCode().is2xxSuccessful()) {
                LOGGER.info("Got Error Response in POST. Status Code: {}", response.getStatusCodeValue());
                return MessageFactory.newInstance().createMessage();
            }
            return getStrAsSoapMessage(response.getBody());
        };
        return invokeResponseCallable(responseCallable);
    }


    private static SOAPMessage invokeResponseCallable(final Callable<SOAPMessage> responseCallable) {
        final Retryer<SOAPMessage> retryer = buildResponseRetyer();
        try {
            return retryer.call(responseCallable);
        } catch (ExecutionException | RetryException e) {
            throw new CommonException(Constants.RETRY_FAILED_MESSAGE, e);
        }
    }

    private static Retryer<SOAPMessage> buildResponseRetyer() {
        return RetryerBuilder.<SOAPMessage>newBuilder()
                .retryIfResult(RetryUtil::shouldRetrySoapResponse)
                .retryIfException()
                .withWaitStrategy(
                        WaitStrategies.randomWait(
                                Constants.DEFAULT_MINIMUM_REQUEST_RETRY_RANDOM_DELAY_IN_MILLISECONDS,
                                TimeUnit.MILLISECONDS,
                                Constants.DEFAULT_MAXIMUM_REQUEST_RETRY_RANDOM_DELAY_IN_MILLISECONDS,
                                TimeUnit.MILLISECONDS))
                .withStopStrategy(
                        StopStrategies.stopAfterDelay(
                                Constants.DEFAULT_MAXIMUM_REQUEST_RETRY_TIMEOUT_IN_MILLISECONDS,
                                TimeUnit.MILLISECONDS))
                .withRetryListener(new SoapRetryListener())
                .build();
    }

    public static String getSoapMessageAsStr(SOAPMessage message) {
        ByteArrayOutputStream outstream = new ByteArrayOutputStream();
        Try.run(() -> message.writeTo(outstream)).onFailure(e -> {
            throw new IOBCMSException("Unable to write SOAPMessage to outStream");
        });
        return outstream.toString();
    }

    public static SOAPMessage getStrAsSoapMessage(@Nullable String message) {
        if(!StringUtils.hasText(message))
            throw new CommonException("String to convert to SOAPMessage can not be null");
        InputStream is = new ByteArrayInputStream(message.getBytes());
        return Try.of(() -> MessageFactory.newInstance().createMessage(null, is))
                .getOrElseThrow(CommonException::new);
    }

    public static String getSoapWithReplacedTags(String xmlRequest) {
        String pattern = "<arg0 xmlns=\"\">(.*?)</arg0>";
        Pattern regex = Pattern.compile(pattern, Pattern.DOTALL);
        Matcher matcher = regex.matcher(xmlRequest);
        if (matcher.find()) {
            String extractedSubstring = matcher.group(1);
            return extractedSubstring.replace("<", "&lt;").replace(">", "&gt;");
        }

        return "Invalid SOAP Message";
    }

    public static String getSoapWithReplacedTagsForresponse(String xmlRequest) {
        String pattern = "<return>(.*?)</return>";
        Pattern regex = Pattern.compile(pattern, Pattern.DOTALL);
        Matcher matcher = regex.matcher(xmlRequest);
        if (matcher.find()) {
            String extractedSubstring = matcher.group(1);
            return extractedSubstring.replace("&lt;", "<").replace("&gt;", ">");
        }

        return "Invalid SOAP Message";
    }
*/
}
