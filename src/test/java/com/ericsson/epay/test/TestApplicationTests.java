package com.ericsson.epay.test;

import com.chinamobile.payment.SubmitAuditReq;
import com.chinamobile.payment.SubmitAuditResp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {

	//<SubmitAuditReq xmlns="http://www.chinamobile.com/payment">
	// <ChannelID>18</ChannelID>
	// <AuditType>2</AuditType>
	// <AuditTimeType>1</AuditTimeType>
	// <StartTime>20170722000000</StartTime>
	// <EndTime>20170722235959</EndTime>
	// </SubmitAuditReq>
	@Test
	public void contextLoads() throws Exception {
		SubmitAuditReq req = new SubmitAuditReq();
		req.setAuditType(2);
		req.setChannelID("18");
		req.setStartTime("20170721000000");
		req.setEndTime("20170721235959");
		req.setAuditTimeType(1);

		EpayService service = new EpayService();
		SubmitAuditResp res = service.submitAudit(req);
		System.out.println( res.getResult());

	}

}
