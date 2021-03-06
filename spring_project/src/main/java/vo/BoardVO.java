package vo;

import java.sql.Date;

public class BoardVO {
	private int idx;
	private String title;
	private String id;
	private Date regdate;
	private String write_pw;
	private String file_path;
	private int hit;
	private String context;
	private int comments;
	
	public BoardVO() {

	}

	public BoardVO(int idx, String title, String id, Date regdate, String write_pw, String file_path, int hit, String context,
			int comments) {
		super();
		this.idx = idx;
		this.title = title;
		this.id = id;
		this.regdate = regdate;
		this.write_pw = write_pw;
		this.setFile_path(file_path);
		this.hit = hit;
		this.context = context;
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "BoardVO [idx=" + idx + ", title=" + title + ", id=" + id + ", regdate=" + regdate + ", write_pw="
				+ write_pw + ", file_path=" + file_path + ", hit=" + hit + ", context=" + context + ", comments=" + comments + "]";
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public String getWrite_pw() {
		return write_pw;
	}

	public void setWrite_pw(String write_pw) {
		this.write_pw = write_pw;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
}
