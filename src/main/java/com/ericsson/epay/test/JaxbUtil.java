package com.ericsson.epay.test;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by xdhua on 2017/7/23.
 */
public class JaxbUtil {

    public static String DATE_FORMAT_16 = "yyyyMMddHHmmss";
    public static String generateOrderId() {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_16);
        String dateStr = format.format( new Date());
        System.out.println( dateStr);
        return null;
    }


    public static String beanToXml(Object bean) {
        JAXBContext jaxbContext =  null;
        StringWriter writer = new StringWriter();
        try{
            jaxbContext = JAXBContext.newInstance(bean.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(bean, writer);

        } catch( JAXBException ex) {
            ex.printStackTrace();
        }
        return writer.toString();
    }

    public static <T> T xmlToBean( String xml, Class<T> clazz) {
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return(T) unmarshaller.unmarshal( new ByteArrayInputStream(xml.getBytes("UTF-8")));
        } catch(JAXBException ex) {
            ex.printStackTrace();
        } catch(UnsupportedEncodingException ex ) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        JaxbUtil.generateOrderId();
    }
}
