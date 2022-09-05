package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class D_DAO {

	// DAO : Data Access Object
	// 데이터베이스의 data에 접근을 위한 객체! (접근 로직)

	// 객체 생성

	Type_DTO td = new Type_DTO();
	Type_DTO tde = new Type_DTO();

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	int cnt = 0; // executeUpdate의 결과값을 담아주는 변수

	// 데이터베이스 접속을 위한 연결 메소드
	public void getCon() {
		// 1. Class.forName()
		// 2. 데이터베이스의 url, id, pw 연결

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String db_id = "campus_k_0830_5";
			String db_pw = "smhrd5";

			conn = DriverManager.getConnection(url, db_id, db_pw);

//			if (conn != null) {
//				System.out.println("접속 성공!");
//			} else {
//				System.out.println("접속 실패...");
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 사용된 객체를 닫아주는 메소드

	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// [1] 회원가입
	// - DB 접속 -> 메소드로 로직을 정리하여 따로 뽑아내기
	// - sql문장 실행
	// - 연결 종료
	public int insert(String id, String pw) {
		getCon();

		try {
			String sql = "insert into USER_INFO values(?,?)";

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, id);
			psmt.setString(2, pw);

			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			close();
		}

		return cnt;

	}

	// [2] 로그인
	// - DB 접속
	// - sql문장 실행
	// - 연결 종료

	public String login(String id, String pw) {
		getCon();

		String nick = null; // 결과값 리턴을 위한 변수

		try {
			String sql = "select nick from dama where clean > 0 and feed > 0 and likeness > 0 and id = ( select user_id from USER_INFO where user_id = ? and user_pw = ? )";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);

			rs = psmt.executeQuery();

			if (rs.next()) {
				nick = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return nick;
	}

//	public String memlogin(String id, String pw) {
//		String logid = null;
//
//		getCon();
//
//		try {
//			String sql = "select id from USER_INFO where user_id = ? and user_pw = ?";
//			psmt = conn.prepareStatement(sql);
//			psmt.setString(1, id);
//			psmt.setString(2, pw);
//
//			rs = psmt.executeQuery();
//
//			if (rs.next()) {
//				logid = rs.getString(1);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close();
//		}
//
//		return logid;
//	}

	// 유저로그인
	public String memlogin(String id, String pw) {
		String logid = null; // 결과값 리턴을 위한 변수

		getCon();

		try {
			String sql = "select user_id from user_info where user_id = ? and user_pw = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);

			rs = psmt.executeQuery();

			if (rs.next()) {
				logid = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return logid;
	}

	// [3] 조회 -> 전체 회원의 아이디와 닉네임만 출력!
	public ArrayList<D_DTO> select() {

		// 전체 회원의 정보를 담을 수 있는 ArrayList 만들기
		ArrayList<D_DTO> totalList = new ArrayList<>();

		getCon();

		try {
			String sql = "select id, nick from memberInfo";
			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();

//			while (rs.next()) {
//				String id = rs.getString(1); // 1 대신 "id"도 가능
//				String nick = rs.getString(2); // 2 대신 "nick"도 가능
//
//				// System.out.println(id+" / "+nick);
//				D_DTO dto = new D_DTO(id, nick);
//				totalList.add(dto);
//				// totalList.add(new MemberDTO(id, nick));
//
//			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			close();
		}

		return totalList;
	}

	// 캐릭터 생성
	public int create(String loginid, String nick) {
		getCon();

		try {
			String sql = "insert into dama values(?, ?, ?, ?, ?,sysdate,?,?,?)";

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, nick);
			psmt.setString(2, tde.getType());
			psmt.setInt(3, tde.getExp());
			psmt.setInt(4, tde.getLevel());
			psmt.setString(5, loginid);
			psmt.setInt(6, tde.getFeed());
			psmt.setInt(7, tde.getClean());
			psmt.setInt(8, tde.getLikeness());

			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			close();
		}

		return cnt;

	}

	// - DB 접속
	// - sql문장 실행
	// - 연결 종료

	// [4] 탈퇴
	// - DB 접속
	// - sql문장 실행
	// - 연결 종료

	public void selec(String id, String nick) {
		getCon();

		try {
			String sql = "select * from dama where id = ? and nick = ? ";

			psmt = conn.prepareStatement(sql);

			// sql 문장 전송 작업필요!
			// ResultSet -> select sql문 실행시 돌아오는 테이블 형태를
			// 담을 수 있는 객체
			psmt.setString(1, id);
			psmt.setString(2, nick);

			rs = psmt.executeQuery();

			if (rs.next()) {

				System.out.println("이름 : " + rs.getString(1));
				System.out.println("타입 : " + rs.getString(2));
				System.out.println("경험치 : " + rs.getString(3));
				System.out.println("레벨 : " + rs.getString(4));
				System.out.println("포만감 : " + rs.getString(7));
				System.out.println("청결도 : " + rs.getString(8));
				System.out.println("호감도 : " + rs.getString(9));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			close();
		}

	}

	public ArrayList<D_DTO> rank() {

		// 전체 회원의 정보를 담을 수 있는 ArrayList 만들기
		ArrayList<D_DTO> totalList = new ArrayList<>();

		getCon();

		try {
			String sql = "SELECT row_number() OVER (ORDER BY lev DESC) as 등수, nick, lev, id, dama_date FROM dama";
			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();

			while (rs.next()) {
				int rankin = rs.getInt(1);
				String nick = rs.getString(2);
				int lev = rs.getInt(3);// 1 대신 "id"도 가능
				String id = rs.getString(4); // 2 대신 "nick"도 가능
				String date = rs.getString(5); // 2 대신 "nick"도 가능

				// System.out.println(id+" / "+nick);
				D_DTO dto = new D_DTO(rankin, nick, lev, id, date);
				totalList.add(dto);
				// totalList.add(new MemberDTO(id, nick));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			close();
		}

		return totalList;
	}

}
