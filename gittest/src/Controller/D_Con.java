package Controller;

import java.util.ArrayList;

import Model.D_DAO;
import Model.D_DTO;
import Model.Type_DTO;

public class D_Con {
	
	D_DAO dao = new D_DAO();
	
	Type_DTO tdt = new Type_DTO();

	public void printMenu() {
		System.out.print("[1]회원가입 [2]로그인 [3]랭킹 [4]종료 >>");
	}
	
	public void printMenu2() {
		System.out.print("[1]내 캐릭터 생성 [2]내 캐릭터 키우기 [3]내 캐릭터 확인 [4]뒤로 가기 >>");
	}
	
	int cnt = 0;

	public int conInsert(String id, String pw) {
		// view가 넘겨준 회원가입에 대한 정보들을 DAO로 연결해주는 메소드
		cnt = dao.insert(id, pw);
		return cnt;
	}

	public ArrayList<D_DTO> conSelect() {
		ArrayList<D_DTO> totalList = new ArrayList<>();
		totalList = dao.select();
		return totalList;
	}

	// 로그인을 진행하기 위한 controller 요청

	public String conLogin(String id, String pw) {
		
		String nick = dao.login(id,pw);
		
		return nick;
	}
	
	public String conmemLogin(String id, String pw) {
		
		String logresult = dao.memlogin(id,pw);
		
		return logresult;
	}
	
	
	// 캐릭터 생성
	public int conCreate(String loginid, String nick) {
		// view가 넘겨준 회원가입에 대한 정보들을 DAO로 연결해주는 메소드
		cnt = dao.create(loginid, nick);
		return cnt;
	}
	
	public void conSele(String id, String nick) {
		dao.selec(id, nick);
	}
	
	
	public ArrayList<D_DTO> conRank() {
		ArrayList<D_DTO> totalList = new ArrayList<>();
		totalList = dao.rank();
		return totalList;
	}
	
}
