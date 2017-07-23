package com.ericsson.epay.test;

import java.net.URLEncoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;


public class Degist {

    public static void main(String[] args) {
        Degist degist = new Degist();
        try {
            degist.serverNotifyDegister();
            degist.webNotifyDegister();
            degist.orderDegister();
        } catch (Exception exp) {

        }
    }


    public void serverNotifyDegister() throws Exception {

        String paymentorderId = "789456321";

        String orderid = "123456789";

        String points = "0";

        String amount = "10";

        String OriginPayErrorCode = "zh234567";

        String paymentstatus = "2";


        String alg = "DESede";

        String pswd = "BBDmwTjBsF7IwTIyGWt1bmFn";


        String origin = paymentorderId + ","

            + orderid + ","
            + points + ","
            + amount + ","
            + OriginPayErrorCode + ","
            + paymentstatus + ",";
        String degist = this.getDigest(origin, pswd);

        System.out.println(degist);
    }


    public void webNotifyDegister() throws Exception {

        String paymentorderId = "789456321";
        String orderid = "20130614121234";
        String ordertime = "20130614121234";
        String merchant = "merchant";
        String points = "0";
        String amount = "10";

        String paymentstatus = "2";
        String acDate = "20130615";

        String alg = "DESede";
        String pswd = "BBDmwTjBsF7IwTIyGWt1bmFn";


        String origin = paymentorderId + ","
            + orderid + ","
            + ordertime + ","
            + merchant + ","
            + points + ","
            + amount + ","
            + paymentstatus + ","
            + acDate + ",";

        String degist = this.getDigest(origin, pswd);

        System.out.println(degist);
    }


    public void orderDegister() throws Exception {

        String c_idWeb = "01";
        String o_idWeb = "20130614121234";
        String o_timeWeb = "20130614121234";
        String merchant = "010100GD001";
        String user = "13926405877";
        String puser = null;
        int point = 0;
        int amount = 10;
        String pswdWeb = "BBDmwTjBsF7IwTIyGWt1bmFn";
        String originWeb = c_idWeb + ","
            + o_idWeb + ","
            + o_timeWeb + ","
            + merchant + ","
            + user + ","
            + ","
            + point + ","
            + amount + ",";
        String digestWeb = getDigest(originWeb, pswdWeb);
        System.out.println(digestWeb);
    }

    private String getDigest(String origin, String pswd) throws Exception {
        String alg = "DESede";
        DESedeKeySpec dks = new DESedeKeySpec(pswd.getBytes());

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(alg);

        SecretKey secretKey = keyFactory.generateSecret(dks);

        Cipher cipher = Cipher.getInstance(alg);

        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] after = cipher.doFinal(origin.getBytes());

        String digest = new String(Base64.encodeBase64(DigestUtils.md5(after)));
        return digest;
    }
}
