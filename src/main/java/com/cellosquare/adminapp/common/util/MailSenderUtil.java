package main.java.com.cellosquare.adminapp.common.util;

import com.bluewaves.lab.property.XmlPropertyManager;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class MailSenderUtil {
	private static final String HTML_ENCODING = "text/html;charset=UTF-8";
	private static final String mailUrl = "https://apiv2.cellosquare.com/common/v1/mail/sendmarketingmail";
	
	//sds mail api
	public static boolean mailSenderApi(Map<String,Object> mo) throws ClientProtocolException, IOException {
		boolean sendFlag = true;
		
		JSONObject json = null;
		JSONObject jsonP = new JSONObject();
		
		json = new JSONObject();
		//json.put("SERVERLESS_ALIAS", "dev");
		//jsonP.put("stageVariables",json);
		
		json = new JSONObject();
		json.put("receiver", mo.get("receiver"));
		json.put("title", mo.get("title"));
		json.put("contents", mo.get("contents"));
		json.put("templateName", "marketing-mail");
				
		//jsonP.put("Body JSON", json);
		
		//System.out.println(jsonP.toString());
		
		HttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(mailUrl);
		httpPost.setHeader("Content-type", "application/json");
		httpPost.setHeader("key", "x-api-key");
		httpPost.setHeader("value","hPykLykbYO2DXlx4DXJqa3iWH6jquAQX6fIv5dwp");
		
		StringEntity requestEntity = new StringEntity(json.toString(),"UTF-8");
		httpPost.setEntity(requestEntity);
		
    	HttpResponse httpResponse = httpclient.execute(httpPost);
    	if (httpResponse == null || httpResponse.getStatusLine().getStatusCode() >= 300) {
    		sendFlag = false;
    		//System.out.println(httpResponse.getStatusLine().getStatusCode());
    		//System.out.println(httpResponse.getStatusLine().getReasonPhrase());
    	} else {
        	//System.out.println("httpResponse : " + httpResponse.getStatusLine());
            HttpEntity entity = httpResponse.getEntity();
            //System.out.println("httpResponse : " + httpResponse.getEntity());
            
            if (entity != null) {
                String entityString = EntityUtils.toString(entity, "UTF-8");
                //System.out.println("entityString : "+ entityString);
            }
        }    
    	
		return sendFlag;
	}
	
	
	public static boolean mailSenderGoogle(Map<String,Object> mo) throws AddressException, MessagingException {
		
		boolean sendFlag = true;
		
		// 네이버일 경우 smtp.naver.com 을 입력합니다.
		// Google일 경우 smtp.gmail.com 을 입력합니다.
		//String host = "smtp.naver.com";
		final String host = XmlPropertyManager.getPropertyValue("mail.smtp.host");
		final String username = XmlPropertyManager.getPropertyValue("mail.smtp.username");
		final String password = XmlPropertyManager.getPropertyValue("mail.smtp.userpw");
		final String senderEmail = XmlPropertyManager.getPropertyValue("mail.smtp.senderEmail");
		final int port = Integer.parseInt(XmlPropertyManager.getPropertyValue("mail.smtp.port"));
		 
		// 메일 내용		 
		String toEmail = (String) mo.get("toEmail");	// 수신자 이메일
		String subject =  (String) mo.get("subject");	// 제목
		String htmlContents = (String) mo.get("htmlContents");			// 내용
		
		try {
			Properties props = System.getProperties(); // 정보를 담기 위한 객체 생성
			 
			// SMTP 서버 정보 설정
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.ssl.trust", host);
			
			//Session 생성
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				String un=username;
				String pw=password;
				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
					return new javax.mail.PasswordAuthentication(un, pw);
				}
			});
			
			
			Message mimeMessage = new MimeMessage(session); //MimeMessage 생성
			mimeMessage.setFrom(new InternetAddress(senderEmail)); //발신자 셋팅 , 보내는 사람의 이메일주소를 한번 더 입력합니다. 이때는 이메일 풀 주소를 다 작성해주세요.
			mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail)); //수신자셋팅 //.TO 외에 .CC(참조) .BCC(숨은참조) 도 있음
	
			mimeMessage.setSubject(subject);  //제목셋팅
			//mimeMessage.setText(body);        //내용셋팅
			
			// 메일 컨텐츠
            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(htmlContents, HTML_ENCODING);
			multipart.addBodyPart(messageBodyPart);
			mimeMessage.setContent(multipart, HTML_ENCODING);
			
			Transport.send(mimeMessage); //javax.mail.Transport.send() 이용
		} catch (Exception e) {		
			sendFlag = false;
		}
		return sendFlag;
	}
	
	
	public boolean mailSender(Map<String,Object> mo) throws AddressException, MessagingException {
		boolean sendFlag = true;
		
		final String host = XmlPropertyManager.getPropertyValue("mail.smtp.host");
		final String username = XmlPropertyManager.getPropertyValue("mail.smtp.username");
		final String password = XmlPropertyManager.getPropertyValue("mail.smtp.userpw");
		final String senderEmail = XmlPropertyManager.getPropertyValue("mail.smtp.senderEmail");
		final int port = Integer.parseInt(XmlPropertyManager.getPropertyValue("mail.smtp.port"));
		 
		// 메일 내용		 
		String toEmail = (String) mo.get("toEmail");	// 수신자 이메일
		String subject =  (String) mo.get("subject");	// 제목
		String htmlContents = (String) mo.get("htmlContents");			// 내용
		
		try {
			Properties props = System.getProperties(); // 정보를 담기 위한 객체 생성
			 
			// SMTP 서버 정보 설정
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.auth", "true");
			
			//Session 생성
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				String un=username;
				String pw=password;
				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
					return new javax.mail.PasswordAuthentication(un, pw);
				}
			});
			
			
			Message mimeMessage = new MimeMessage(session); //MimeMessage 생성
			mimeMessage.setFrom(new InternetAddress(senderEmail)); //발신자 셋팅 , 보내는 사람의 이메일주소를 한번 더 입력합니다. 이때는 이메일 풀 주소를 다 작성해주세요.
			mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail)); //수신자셋팅 //.TO 외에 .CC(참조) .BCC(숨은참조) 도 있음
	
	
			mimeMessage.setSubject(subject);  //제목셋팅
			//mimeMessage.setText(body);        //내용셋팅
			
			// 메일 컨텐츠
            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(htmlContents, HTML_ENCODING);
			multipart.addBodyPart(messageBodyPart);
			mimeMessage.setContent(multipart, HTML_ENCODING);
			
			Transport.send(mimeMessage); //javax.mail.Transport.send() 이용
		} catch (Exception e) {		
			sendFlag = false;
		}
		return sendFlag;
		
	}
}
