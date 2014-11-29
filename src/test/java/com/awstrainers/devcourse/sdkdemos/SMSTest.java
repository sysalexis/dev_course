/*
	Copyright 2014 AWS
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	    http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/

package com.awstrainers.devcourse.sdkdemos;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.simpleemail.AWSJavaMailTransport;

public class SMSTest {
	public static final AWSCredentials cred = 
			new ClasspathPropertiesFileCredentialsProvider("AwsCredentials.properties").getCredentials();

	// Check the next url to know more about verifying email addresses in AWS.
	// http://docs.aws.amazon.com/ses/latest/DeveloperGuide/verify-email-addresses.html
	private static final String FROM_ADDRESS = System.getProperty("sms_test_from_address");
	private static final String TO_ADDRESS = System.getProperty("sms_test_to_address");;
	
	@BeforeClass
	public static void checkSourcePreconditions() throws Exception {
		if (FROM_ADDRESS == null || TO_ADDRESS== null) {
			throw new /*Precondition*/Exception("Please run this test indicating valid and verifyied email addresses.");
		}
	}
	
	@Test
	public void checkSendMail() throws Exception {
		
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "aws");
		props.setProperty("mail.aws.user", cred.getAWSAccessKeyId());
		props.setProperty("mail.aws.password", cred.getAWSSecretKey());
		 
		Session session = Session.getInstance(props);
		 
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(FROM_ADDRESS));
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(TO_ADDRESS));
		msg.setSubject("Test");
		msg.setText("Hi!");
		msg.saveChanges();
		 
		Transport t = new AWSJavaMailTransport(session, null);
		t.connect();
		t.sendMessage(msg, null);
		t.close();		
	}
	
}
