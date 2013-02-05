package com.smv.test.dto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;

import com.google.gson.Gson;
import com.smv.common.Constant;
import com.smv.common.bean.ContactDTO;
import com.smv.common.bean.EventDTO;
import com.smv.common.bean.ItemDTO;
import com.smv.common.bean.UserDTO;
import com.smv.service.file.FileConstant;
import com.smv.service.iprocessor.helper.ImageConversion;
import com.smv.util.http.HttpClient;
import com.smv.util.io.StreamUtil;
import com.smv.util.number.Conversion;

public class GenerateRequestTest {
	
	//1: from FileService - 2:from FileService - 3: from Android
	private static String PATH = "img1.sharemyvision.com/" + userFolder(1) + "/" + getFolder();
	private static String FILE_NAME = "colosseum.jpg";
	private static String RESOURCE_PATH = "colosseum.jpg"; //"com/smv/test/file/colosseum.jpg";
	/*
	public static void generateEventXML() {
				
		EventDTO event = new EventDTO();
		
		event.setAction(Constant.CREATE_NEW_EVENT);
		event.setAccountId(111L);
		
		event.setEventDescription("Home party");
		event.setUserId(9001L);
		
		ItemDTO item = new ItemDTO();
		item.setFileName("filename1");
		item.setFileSize(111L);
		item.setPath("abc/cba");
		
		ItemDTO item1 = new ItemDTO();
		item1.setFileName("filename2");
		item1.setFileSize(2111L);
		item1.setPath("abc1/cba");
		
		event.setItems(new ItemDTO[] {item, item1});
		
		XStream xstream = SmvJSonXStream.getXStream(); 
        //xstream.setMode(XStream.NO_REFERENCES);

		//xstream.alias("event", com.smv.common.bean.EventDTO.class);
		String str = xstream.toXML(event);
		
		System.out.println(str);
		
		event = (EventDTO) xstream.fromXML(str);
		//System.out.println("title = " + request.getEvent().getTitle());
	}
	*/
	
	public static String generateJSonData(Object request) {
		//XStream xstream = SmvJSonXStream.getXStream(); 
		//return xstream.toXML(request);
		return new Gson().toJson(request);
	}
	
	public static Object generateObjectFromJSon(String str) {
		//return SmvJSonXStream.getXStream().fromXML(str);
		return new Gson().fromJson(str, EventDTO.class);
	}
	
	public static URL resource2URL(String fileInClasspath) {
		return new GenerateRequestTest().getClass().getResource(fileInClasspath);
	}
	
	public static File getFileFromResource(String fileInClasspath) {
		URL url = resource2URL(fileInClasspath);
		if (url == null)
			System.out.println("NULLLLLLLLLLLLL");
		
		
		return new File(url.getFile());
	}
	
	public static InputStream loadImageFile2Stream(String fileInClasspath) throws FileNotFoundException {
		return new FileInputStream(getFileFromResource(fileInClasspath));
	}
	
	private static EventDTO createSignedUploadURLRequest() {
		File file = getFileFromResource(RESOURCE_PATH);
		
		EventDTO event = new EventDTO();
		event.setAction(Constant.SIGN_UPLOAD_URL);
		event.setEventCode("1abc1");
		event.setUserId(1L);
		
		ItemDTO item = new ItemDTO();
		item.setUserId(1L);
		item.setFileName(file.getName());
		item.setFileSize(file.length());
		//item.setPath(PATH);
		item.setPath(getFolder());
	
		
		event.setItems(new ItemDTO[]{item});						
		
		return event;
	}
	
	private static EventDTO createSignedDownloadURLRequest(String path) {
		File file = getFileFromResource(RESOURCE_PATH);
		
		ItemDTO item = new ItemDTO();
		item.setFileName(file.getName());
		item.setPath(path);
		
		EventDTO event = new EventDTO();
		event.setAction(Constant.SIGN_DOWNLOAD_URL);
		event.setItems(new ItemDTO[] {item} );

							
		return event;
	}
	
	
	private static EventDTO createSignedDeleteURLRequest() {
		File file = getFileFromResource(RESOURCE_PATH);

		
		ItemDTO item = new ItemDTO();
		item.setFileName(file.getName());
		item.setPath(PATH);
		
		EventDTO event = new EventDTO();
		event.setAction(Constant.SIGN_DELETE_URL);
		event.setItems(new ItemDTO[] {item} );
	
		return event;
	}
	
	
	private static EventDTO createSignedFileDetailURLRequest() {
		File file = getFileFromResource(RESOURCE_PATH);
		
		ItemDTO item = new ItemDTO();
		item.setFileName(file.getName());
		item.setPath(PATH);
		
		EventDTO event = new EventDTO();
		event.setAction(Constant.SIGN_FILE_DETAIL_URL);
		event.setItems(new ItemDTO[] {item} );
							
		return event;
	}
	
	public static String getFolder() {
		Calendar cal = Calendar.getInstance();
		return "d" + String.valueOf(cal.get(Calendar.YEAR)) + String.valueOf(cal.get(Calendar.MONTH)) + String.valueOf(cal.get(Calendar.DATE));
	}
	
	public static String userFolder(long id) {
		
		String retVal = Conversion.toBase64String(Conversion.longToByteArray(id + System.currentTimeMillis()));
		Random ran = new Random(System.currentTimeMillis());
		retVal = retVal.replace('+', '1');
		retVal = retVal.replace('/', '5');
		retVal = retVal.replace('=', 'e');
		retVal = retVal.replace('A', Conversion.Base64Chars[ran.nextInt(62)]);
		return retVal.substring(2);
	}
	
	
	public static void uploadTest(EventDTO event) throws Exception {
		File file = getFileFromResource(RESOURCE_PATH);
		
		//Test creating upload url
		ItemDTO signedItem = event.getItems()[0];
		//signedItem.setFileName(file.getName());
		//signedItem.setFileSize(file.length());
		//signedItem.setPath(PATH);
		
		
		String putUrl = signedItem.getUrl();
		
		S3Bucket bucket = new S3Bucket(signedItem.getPath());
        S3Object object = new S3Object(bucket, signedItem.getFileName());
        object.setContentLength(signedItem.getFileSize());
        object.setDataInputStream(loadImageFile2Stream(RESOURCE_PATH));
        
        S3Object putObject = new RestS3Service(null).putObjectWithSignedUrl(putUrl, object);
        
        System.out.println("  Object has been uploaded to S3: " + putObject.getKey());
	}
	
	/*
	//public static HttpResponse sendFileRequest(EventDTO request, boolean isAsync) throws IOException, HttpException {
	public static SmvHttpResponse sendFileRequest(EventDTO request, boolean isAsync) throws IOException, HttpException {
		Map<String, String> headers = new HashMap<String, String>();
		//headers.put("SMV-REQUEST-TYPE", "InitRequest");
		headers.put("Content-Type", "application/json; charset=utf-8");
		
		if (isAsync) {
			headers.put(Constant.ASYNC_REQUEST, "true");
		}
		//headers.put("Accept", "application/json");

		//String data = generateJSonData(request);
		String data = new Gson().toJson(request);
		
		SmvHttpResponse resp = SmvHttpClient.post("http://localhost:8080/web/mo/filerequest", headers, data);
		
		System.out.println("Data : " + data);
		return resp;
		//return  HttpClient.httpPost("http://localhost:8080/web/mo/filerequest", headers, data);	
	}
	*/
	
	public static HttpResponse sendFileRequest(EventDTO request, boolean isAsync) throws IOException, HttpException {
		Map<String, String> headers = new HashMap<String, String>();
		//headers.put("SMV-REQUEST-TYPE", "InitRequest");
		headers.put("Content-Type", "application/json; charset=utf-8");
		headers.put("minh", "phuong");
		if (isAsync) {
			headers.put(Constant.ASYNC_REQUEST, "true");
		}
		//headers.put("Accept", "application/json");

		//String data = generateJSonData(request);
		String data = new Gson().toJson(request);
		
		//SmvHttpResponse resp = SmvHttpClient.post("http://localhost:8080/web/mo/filerequest", headers, data);
		
		//System.out.println("Data : " + data);
		//return resp;
		return  HttpClient.httpPost("http://sharemyvision.com/web/mo/filerequest", headers, data);	
	}
	
	public static HttpResponse sendUserRequest(UserDTO user) throws IOException, HttpException {
		Map<String, String> headers = new HashMap<String, String>();
		//headers.put("SMV-REQUEST-TYPE", "InitRequest");
		headers.put("Content-Type", "application/json; charset=utf-8");

		//headers.put("Accept", "application/json");

		String data = generateJSonData(user);
		System.out.println("data : " + data);
		
		return HttpClient.httpPost("http://localhost:8080/web/mo/userrequest", headers, data);
	}
	
	private static UserDTO createUserForRegistration() {
		UserDTO user = new UserDTO();
		
		user.setAction(Constant.REGISTER);
		user.setUserEmail("mdo2@qualys.comn");
		user.setUserPassword("123");
		
		user.setContact(new ContactDTO());
		user.getContact().setContactFirstName("Minh");
		user.getContact().setContactLastName("DO");
		
		return user;
	}
	
	private static UserDTO createUserForLogin() {
		UserDTO user = new UserDTO();
		
		user.setAction(Constant.LOGIN);
		user.setUserEmail("mdo2@qualys.comn");
		user.setUserPassword("123");

		return user;
	}
	
	public static void main1(String[] args) throws Exception  {
		//Register
		//SmvHttpResponse resp = sendUserRequest(createUserForRegistration());
		//System.out.println(resp);
		
		//Login
		sendUserRequest(createUserForLogin());
		
	}



	
    
	public static void main2(String[] args) throws Exception  {
		InputStream instream = loadImageFile2Stream(RESOURCE_PATH);
		
		ImageConversion.convertAndSaveImage(StreamUtil.inputStreamToByteArray(instream), 150, 
				                            new File("c:\\dev\\temp\\picminh" + System.currentTimeMillis() + ".jpg"));
	}
	
	public static void main(String[] args) throws Exception  {
		//generateEventXML();

		//System.out.println(generateJSonData(createSignedUploadURLRequest()));
		//System.out.println(generateJSonData(createSignedDownloadURLRequest()));
		//System.out.println(generateJSonData(createSignedDeleteURLRequest()));
		//System.out.println(generateJSonData(createSignedFileDetailURLRequest()));
				
		/////Send request to SMV for a signed url to upload
		//SmvHttpResponse resp = sendFileRequest(createSignedUploadURLRequest(), false);
		//System.out.println(resp);
		//EventDTO retVal = new Gson().fromJson(resp.body, EventDTO.class); 
		HttpResponse resp = sendFileRequest(createSignedUploadURLRequest(), false);
		String val = EntityUtils.toString(resp.getEntity());
		System.out.println(val);
		EventDTO retVal = new Gson().fromJson(val, EventDTO.class); 
		
		///////upload file to S3
		uploadTest(retVal);
		
		//send postback to update db
		
		String path = retVal.getItems()[0].getPath();
		System.out.println("Path : " + path);
		String eventCode = path.substring(path.indexOf('/') + 1);
		eventCode = eventCode.replace('/', '_');
		System.out.println("EvenCode : " + eventCode);
		
		EventDTO postbackEvent = new EventDTO();
		postbackEvent.setAction(FileConstant.UPSERT_EVENT);
		postbackEvent.setUserId(1L);
		postbackEvent.setEventCode(eventCode);
		postbackEvent.setEventTitle("a title");
		postbackEvent.setEventDescription("a descripiton");
		postbackEvent.setEventPublic(Boolean.FALSE);
		postbackEvent.setItems(new ItemDTO[] { retVal.getItems()[0]});
		
		//////send postback to SMV to update SMV its db
		resp = sendFileRequest(postbackEvent, true);
		//retVal = (EventDTO) generateObjectFromJSon(resp.body);
		System.out.println("Upsert URL Request's Response : " + resp);
		
		////Here is an example on how to get a signed download url
		resp = sendFileRequest(createSignedDownloadURLRequest(path), false);
		//retVal = (EventDTO) generateObjectFromJSon(resp.body);
		//System.out.println("Download URL Request's Response : " + retVal.getItems()[0].getUrl());
		
	}
}
