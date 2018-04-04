package com.example.mustafa.jumboboss;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by BERK on 3/19/2018.
 */

public class ServiceManager {
    private static final String NAMESPACE=""; //servisteki namespace de yazılan yeri buraya yazılır
    private static final String URL=""; //Servisin urlsi ve ornegin http://192.168.1.102:80/Service.asmx



    SoapObject soapObject;
    SoapSerializationEnvelope soapSerializationEnvelope;
    HttpTransportSE httpTransportSE;



  /*private static final String METHOD_NAME = "";
   private static final String SOAP_ACTION=""; //namespace+methodname 
        */  //her metodun içine yazılacak



}
