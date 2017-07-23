package com.ericsson.epay.test;

import com.chinamobile.payment.GetPaymentTDCURLReq;
import com.chinamobile.payment.GetPaymentTDCURLResp;
import com.chinamobile.payment.SubmitAuditReq;
import com.chinamobile.payment.SubmitAuditResp;

/**
 * Created by xdhua on 2017/7/23.
 */
public class EpayService {

    private static final String URL = "http://localhost:5000/epay?svc_cat=%s&svc_code=%s";
    public SubmitAuditResp submitAudit(SubmitAuditReq req ) {
        String svc_cat = "PayMgmt";
        String svc_code="SubmitAudit";
        String requstBody = JaxbUtil.beanToXml( req );

        String result = null;
        try {
           result =  HttpUtils.post(URL.format(svc_cat, svc_code), requstBody);
           return JaxbUtil.xmlToBean(result, SubmitAuditResp.class);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        SubmitAuditResp res = new SubmitAuditResp();
        res.setResult(99);
        res.setDescription("受理失败");
        return res;
    }

    public GetPaymentTDCURLResp getPaymentTDCUrl(GetPaymentTDCURLReq req ) {
        String svc_cat = "PayTx";
        String svc_code = "GetPaymentTDCURL";
        try {
            return HttpUtils.post(URL.format(svc_cat, svc_code), req, GetPaymentTDCURLResp.class);
        } catch(Exception ex ) {
            ex.printStackTrace();
        }
        return null;
    }
}
