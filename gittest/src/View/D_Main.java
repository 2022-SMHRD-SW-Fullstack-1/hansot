package View;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Controller.D_Con;
import Model.D_DTO;
import Model.Type_DTO;

public class D_Main {

	public static void main(String[] args) {

		Random rd = new Random();

		Scanner sc = new Scanner(System.in);

		Type_DTO egg = new Type_DTO();
		// main 실행하게 되면은
		// 사용자가 메뉴(캐릭터 생성, 전투, 내 캐릭터 확인, 종료)를 선택할 수 있게 함
		// -> Controller - printMenu()
		// 1. GameController 객체 생성 후 참조값 저장하고있는 레퍼런스 변수까지 선언
		D_Con dcon = new D_Con();
		// 메뉴가 반복적으로 출력 -> 사용자는 1~4 숫자 입력
		// 1-> 내 캐릭터 생성! 2-> 전투모드! 3-> 내 캐릭터 확인! 4-> 반복문 종료

		int result = 0;

		while (true) {
			dcon.printMenu();
			int menu = sc.nextInt();
			if (menu == 1) {
				System.out.println("====회원가입====");
				System.out.print("아이디 : ");
				String id = sc.next();
				System.out.print("비밀번호 : ");
				String pw = sc.next();
				result = dcon.conInsert(id, pw);

				if (result > 0) {
					System.out.println("가입 성공");
				} else {
					System.out.println("가입 실패");
				}
				// insert 작업
				// MemberDTO -> id, pw-> (DB) 회원가입
			} else if (menu == 2) {
				System.out.println("====로그인====");
				System.out.print("아이디 : ");
				String id = sc.next();

				System.out.print("비밀번호 : ");
				String pw = sc.next();

				String memlogin = dcon.conmemLogin(id, pw);
				// 로그인 연결

				// 로그인 성공시
				if (memlogin != null) {

					while (true) {
						String damalog = dcon.conLogin(id, pw);
						dcon.printMenu2();
						int menu2 = sc.nextInt();
						// 메뉴2 프린트문
						// 메뉴 선택

						// 새 캐릭터 등록
						if (menu2 == 1) {
							int charresult = 0;
							if (damalog == null) {
								// 캐릭터 생성
								System.out.println();
								System.out.print("이름 : ");
								String nick = sc.next();
								charresult = dcon.conCreate(id, nick);
								if (charresult != 0) {
									System.out.println(nick + "이/가 생성되었습니다");
								}
							} else {
								System.out.println("이미 생성된 캐릭터가 있습니다");
							}

						} else if (menu2 == 2) {// 현재 캐릭터 키우기
							if (damalog == null) {
								System.out.println("캐릭터 생성 먼저 해주세요");
							} else {
								int needsrd = rd.nextInt(3);
								String needs;
								if (needsrd == 1) {
									needs = "씻기";
								} else if (needsrd == 2) {
									needs = "놀기";
								} else {
									needs = "밥먹기";
								}
								System.out.println(damalog + "은/는 " + needs + "를 원하는 눈치이다");
								System.out.println("[1]씻기 [2]놀기 [3]밥 [4]지켜보기 >> ");
								int menu3 = sc.nextInt();
								if (menu3 == 1) {
									System.out.println("깨끗");
								} else if (menu3 == 2) {
									System.out.println("놀기놀기");

								} else if (menu3 == 3) {
									System.out.println("밥밥");
								} else if (menu3 == 4) {
									System.out.println("지켜보기");
								} else {
									System.out.println("다시 선택해주세요");
								}
							}
						} else if (menu2 == 3) {
							// 아스키아트
							// 이름 레벨 등 출력
							// 현재 캐릭터 상태 보기
							if (damalog == null) {
								System.out.println("캐릭터 생성 먼저 해주세요");
							} else {
								dcon.conSele(id, damalog);
							}
						} else if (menu2 == 4) {
							System.out.println("메인 화면으로 돌아갑니다");
							break;
						} else {
							System.out.println("다시 선택해주세요");
						}
					}
				} else {
					System.out.println("아이디와 비밀번호가 맞지 않습니다");
				}

			} else if (menu == 3) {
				ArrayList<D_DTO> resultList = new ArrayList<D_DTO>(dcon.conRank());

				System.out.println("====랭킹====");

				// ArrayList 출력!
				System.out.println("등수 / 별명 / 레벨 / 아이디/ 최근 접속날짜");
				for (int i = 0; i < resultList.size(); i++) {
					System.out.println(resultList.get(i).getRankin() + " / " + resultList.get(i).getNick() + " / "
							+ resultList.get(i).getLev() + " / " + resultList.get(i).getId() + " / "
							+ resultList.get(i).getDate());
				}

			} else if (menu == 4) {
				System.out.println("게임을 종료합니다");
				break;

			} else {
				System.out.println("다시 선택하세요");
			}

		}
	}

}
