package Data;

import java.io.File;

import Pojo.Fields;
import Pojo.IssueType;
import Pojo.ProjectDetails;
import Pojo.Root;


public class Payload {
	static Root root = new Root();
	static Fields payload = new Fields();
	static IssueType type = new IssueType();
	static ProjectDetails details = new ProjectDetails();
	
	public static Root issuePayload() {
		type.setName("Bug");
		details.setKey("RJ");
		payload.setSummary("Testing the create issue api");
		payload.setIssuetype(type);
		payload.setProject(details);
		root.setFields(payload);
		return root;
	}
	
	public static File attachmentFile() {
		File file = new File(System.getProperty("user.dir")+"\\src\\Data\\Attachment.png");
		return file;
	}
}

