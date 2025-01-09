package Pojo;

public class Fields {
	private ProjectDetails project;
	private IssueType issuetype;
	private String summary;
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public ProjectDetails getProject() {
		return project;
	}
	public void setProject(ProjectDetails project) {
		this.project = project;
	}
	public IssueType getIssuetype() {
		return issuetype;
	}
	public void setIssuetype(IssueType issuetype) {
		this.issuetype = issuetype;
	}


}
