package Board;

public class qBoardDTO {
	private String idx;
	private String title;
	private String uname;
	private String pass;
	private java.sql.Date postdate;
	private String qtext;
	private java.sql.Date answerdate;
	private String atext;
	
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public java.sql.Date getPostdate() {
		return postdate;
	}
	public void setPostdate(java.sql.Date postdate) {
		this.postdate = postdate;
	}
	public String getQtext() {
		return qtext;
	}
	public void setQtext(String qtext) {
		this.qtext = qtext;
	}
	public java.sql.Date getAnswerdate() {
		return answerdate;
	}
	public void setAnswerdate(java.sql.Date answerdate) {
		this.answerdate = answerdate;
	}
	public String getAtext() {
		return atext;
	}
	public void setAtext(String atext) {
		this.atext = atext;
	}
}