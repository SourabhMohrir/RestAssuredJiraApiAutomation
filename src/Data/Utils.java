package Data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class Utils {
	public static RequestSpecification request;
	
	public RequestSpecification requestSpecificationCreateIssue() throws IOException {
	   request = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUri")).addHeader("Content-Type","application/json")
				.addHeader("Authorization",getGlobalValue("authorization")).build();
	   return request;
	}
	
	public RequestSpecification requestSpecificationAddAttachment(String issueId) throws IOException {
		   request = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUri")).addPathParam("key",issueId)
					.addHeader("X-Atlassian-Token","no-check")
					.addHeader("Authorization",getGlobalValue("authorization")).build();
		   return request;
		}
	
	public RequestSpecification requestSpecificationDeleteIssue(String issueId) throws IOException {
		   request = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUri")).addPathParam("key",issueId)
				   .addHeader("Authorization",getGlobalValue("authorization")).build();
		   return request;
		}
	
	public static String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\Data\\Global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}

}
