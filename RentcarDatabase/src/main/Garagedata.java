package main;

import model.GarageModel;
import controller.dataClass.Garage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class Garagedata extends JFrame{

	private JPanel contentPane;
	 JTextField garageid;
	JButton btnNewButton_1_3;
	JButton btnNewButton_1_3_1;
	JButton btnNewButton_1_3_2;
	JButton quit;
	 JTextField name;
	 JTextField address;
	 JTextField number;
	 JTextField emailaddress;
	 JTextArea selectcp = new JTextArea();;
	 JButton btnNewButton_1;
	 private JLabel lblNewLabel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Garagedata frame = new Garagedata();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// SQL 연결
		static Connection con;
		   Statement stmt,stmt2;
		   ResultSet rs,rs2;
		   String Driver="";
		   String url="jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true&useSSL=false";
		   String userid="madang";
		   String pwd="madang";
		   private JTextField gmanager;
		   private JLabel lblNewLabel_1;
		   private JLabel lblNewLabel_1_1;
		   private JLabel lblNewLabel_1_1_1;
		   private JLabel lblNewLabel_2;
		   private JLabel lblNewLabel_1_1_2;
		   private JLabel lblNewLabel_1_1_3;
		   private JLabel lblNewLabel_1_2;

/* model을 참고할 수 있게 함 [원래는 Controller 부분] */
		private GarageModel model = new GarageModel();
		   
		public void conDB() { 
		     try {
		       Class.forName("com.mysql.cj.jdbc.Driver");   
		     //  System.out.println("드라이버 로드 성공");
		     } catch(ClassNotFoundException e1) {
		         e1.printStackTrace();
		     }
		   try {
		     //  System.out.println("데이터베이스 연결 준비...");
		       con=DriverManager.getConnection(url, userid, pwd); 
		      // System.out.println("데이터베이스 연결 성공");
		     } catch(SQLException e1) {
		         e1.printStackTrace();
		       }
		   }
		public void printdata() {
			selectcp.setText("차고지ID \t 카센터이름 \t 주소 \t 번호 \t 매니저이름 \t 이메일주소\n");
			/*ArrayList<GarageInfo> garageList = model.readGarageList();
*//* 뷰에서는 처리된 리스트만 가져와서 보여줌 *//*
			for (GarageInfo garage : garageList) {
				String str = garage.id + "\t" +
						garage.name + "\t" +
						garage.address + "\t" +
						garage.number + "\t" +
						garage.manager + "\t" +
						garage.emailAddress + "\n";
				selectcp.append(str);
			}*/
		}
		
	public Garagedata() {
		setTitle("17013152 최종혁 -정비소정보 관리페이지");
		conDB();
		
		printdata();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 921, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		/* 출력 영역 */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(311, 98, 576, 287);
		panel.add(scrollPane);
		
		
		scrollPane.setViewportView(selectcp);
		
		garageid = new JTextField();
		garageid.setColumns(10);
		garageid.setBounds(158, 99, 72, 20);
		panel.add(garageid);
			
		
		JButton btnNewButton = new JButton("검색");
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
/* 메서드 추출 */
				searchGarage();
			}
		});
		btnNewButton.setBounds(242, 98, 57, 23);
		panel.add(btnNewButton);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(158, 142, 141, 21);
		panel.add(name);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(158, 179, 141, 21);
		panel.add(address);
		
		number = new JTextField();
		number.setColumns(10);
		number.setBounds(158, 216, 141, 21);
		panel.add(number);
		
		emailaddress = new JTextField();
		emailaddress.setColumns(10);
		emailaddress.setBounds(158, 285, 141, 21);
		panel.add(emailaddress);
		btnNewButton_1_3 = new JButton("수정");
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
/* 메서드 추출 */
				updateGarage();
			}
		});
		btnNewButton_1_3.setForeground(Color.BLACK);
		btnNewButton_1_3.setFont(new Font("굴림", Font.BOLD, 15));
		btnNewButton_1_3.setBounds(115, 316, 70, 29);
		panel.add(btnNewButton_1_3);

		btnNewButton_1_3_1 = new JButton("삭제");
		btnNewButton_1_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
/* 메서드 추출 */
				deleteGarage();
			}
		});
		btnNewButton_1_3_1.setForeground(Color.BLACK);
		btnNewButton_1_3_1.setFont(new Font("굴림", Font.BOLD, 15));
		btnNewButton_1_3_1.setBounds(197, 316, 70, 29);
		panel.add(btnNewButton_1_3_1);
		
		quit = new JButton("닫기");
		quit.setForeground(Color.BLACK);
		quit.setFont(new Font("굴림", Font.BOLD, 15));
		quit.setBounds(115, 355, 70, 22);
		panel.add(quit);
		
		btnNewButton_1 = new JButton("새로고침");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printdata();
				name.setText("");
				address.setText("");
				number.setText("");
				emailaddress.setText("");
				garageid.setText("");
			}
		});
		btnNewButton_1.setBounds(16, 18, 96, 20);
		panel.add(btnNewButton_1);
		
		btnNewButton_1_3_2 = new JButton("입력");
		btnNewButton_1_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
/* 메서드 추출 */
				addGarage();
			}
		});
		btnNewButton_1_3_2.setForeground(Color.BLACK);
		btnNewButton_1_3_2.setFont(new Font("굴림", Font.BOLD, 15));
		btnNewButton_1_3_2.setBounds(34, 316, 70, 29);
		panel.add(btnNewButton_1_3_2);
		
		lblNewLabel = new JLabel("차고지ID 검색후 수정,삭제하시면 더 편리합니다.");
		lblNewLabel.setBounds(329, 55, 294, 15);
		panel.add(lblNewLabel);
		
		gmanager = new JTextField();
		gmanager.setColumns(10);
		gmanager.setBounds(158, 252, 141, 21);
		panel.add(gmanager);
		
		lblNewLabel_1 = new JLabel("이름");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1.setBounds(16, 141, 120, 20);
		panel.add(lblNewLabel_1);
		
		lblNewLabel_1_1 = new JLabel("주소");
		lblNewLabel_1_1.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(16, 178, 120, 20);
		panel.add(lblNewLabel_1_1);
		
		lblNewLabel_1_1_1 = new JLabel("이메일주소");
		lblNewLabel_1_1_1.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(16, 286, 120, 20);
		panel.add(lblNewLabel_1_1_1);
		
		lblNewLabel_2 = new JLabel("정비소 정보 입력 | 수정 | 삭제");
		lblNewLabel_2.setFont(new Font("양재튼튼체B", Font.BOLD, 28));
		lblNewLabel_2.setBounds(274, 10, 432, 39);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_1_1_2 = new JLabel("매니저이름");
		lblNewLabel_1_1_2.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1_1_2.setBounds(16, 255, 120, 20);
		panel.add(lblNewLabel_1_1_2);
		
		lblNewLabel_1_1_3 = new JLabel("차고지ID입력");
		lblNewLabel_1_1_3.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1_1_3.setBounds(16, 102, 120, 20);
		panel.add(lblNewLabel_1_1_3);
		
		lblNewLabel_1_2 = new JLabel("번호");
		lblNewLabel_1_2.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(16, 216, 120, 20);
		panel.add(lblNewLabel_1_2);
	}

/* 이하 뷰 + 컨트롤러 영역이 섞여 있음 */
/* 입력 */
	public void addGarage() {
		/* 입력란으로부터 정비고 데이터 가져옴 */
		Garage garage = getGarageInput();
		/* 결과에 따라 상태 출력 */
		/*int result = model.createGarage(garage);
		if (result == 1) {
			JOptionPane.showMessageDialog(btnNewButton_1_3_2, "입력완료!");
			printdata();
		} else if (result == 2) {
			JOptionPane.showMessageDialog(btnNewButton_1_3_2, "빈칸을 모두채워주세요");
			printdata();
		} else {
			JOptionPane.showMessageDialog(btnNewButton_1_3_2, "다시입력해주세요!");
		}*/
	}
/* 검색 */
	public void searchGarage() {
		String id = garageid.getText();
		selectcp.setText("");
		selectcp.setText("차고지ID \t 카센터이름 \t 주소 \t 번호 \t 매니저이름 \n");
		/* 모델에 id를 넘겨주면, 모델에서 id에 해당하는 걸 가져와 돌려 줌 */
		/*GarageInfo garage = model.readGarage(id);
		selectcp.append(toStringFromGarageInfo(garage));*/
	}
	private String toStringFromGarageInfo(Garage garage) {
		String str = garage.id + "\t" +
				garage.name + "\t" +
				garage.address + "\t" +
				garage.number + "\t" +
				garage.manager + "\n";
		return str;
	}

/* 수정 */
	public void updateGarage() {
		selectcp.setText("");
		/* 입력란으로부터 정비고 데이터 가져옴 */
		Garage garage = getGarageInput();
		/* 모델에 데이터를 보내고, 반환된 결과값을 살핌 */
		/*int result = model.updateGarage(garage);

		if (result == 1) {
			JOptionPane.showMessageDialog(btnNewButton_1_3, "수정완료");
			//수정하고나서출력!
			printdata();
		} else if (result == 2) {
			JOptionPane.showMessageDialog(btnNewButton_1_3, "빈칸을 모두채워주세요");
			printdata();
		} else {
			JOptionPane.showMessageDialog(btnNewButton_1_3, "다시입력하세요!");
		}*/
	}
/* 삭제 */
	public void deleteGarage() {
		String garageId = garageid.getText();
		/* 모델에 id를 넘겨주고, 처리 결과를 가져옴 */
		/*int result = model.deleteGarage(garageId);
		*//* 처리 결과에 맞게 뷰에서 상태 출력 *//*
		if (result == 1) {
			JOptionPane.showMessageDialog(btnNewButton_1_3_1, "삭제 완료");
			printdata();
			garageid.setText("");
		}else {
			JOptionPane.showMessageDialog(btnNewButton_1_3_1, "ID를 입력해주세요.");
			//ystem.out.println("실패");
		}*/
	}
/* 사용자 입력으로부터 데이터를 가져와서 데이터 클래스에 저장 */
	public Garage getGarageInput() {
		Garage garage = new Garage();
		garage.name = name.getText();
		garage.address = address.getText();
		garage.number = number.getText();
		garage.manager = gmanager.getText();
		garage.emailAddress = emailaddress.getText();
		garage.id = garageid.getText();
		return garage;
	}
}