package Model;

public class D_DTO {

	// Model -> VO(Value Object) -> DTO(Data Transfer Object)

	// 회원관리를 위한 설계도
	private String id;
	private String pw;
	private String nick;

	private int lev;

	public int getLev() {
		return lev;
	}


	


	public int getRankin() {
		return rankin;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	private int rankin;
	private String date;
	//
			
	// Select 기능 사용시 ArrayList에 담을 id, nick을 하나의 생성자로 만들기
	public D_DTO(int rankin, String nick, int lev, String id, String date) {
		this.rankin = rankin;
		this.nick = nick;
		this.lev = lev;
		this.id = id;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public String getNick() {
		return nick;
	}
}
