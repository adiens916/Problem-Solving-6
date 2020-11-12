package main;

import model.MainModel;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.sql.ResultSet;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Campingcar extends JFrame implements ActionListener {

	private JPanel contentPane;
	admin form;
	Customer cstform;

/* 조작할 수 있는 모델 추가 [컨트롤러 부분에 있어야 하는 내용]*/
	private MainModel model = new MainModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Campingcar frame = new Campingcar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Campingcar() {
		setTitle("17013152 최종혁 -메인페이지");
		datareset();
		cstform = new Customer();
		cstform.backbtn.addActionListener(this);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 473);
		contentPane = new JPanel();
		
		
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		 form = new admin();
		 form.backbtn.addActionListener(this);
		 
		JButton btnNewButton = new JButton("관리자");
		btnNewButton.setBackground(new Color(204, 153, 153));
		btnNewButton.setBorder(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnNewButton) {
					showReturnedList();
		        	setVisible(false);
		        	form.setVisible(true);
				}
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton.setBounds(130, 275, 116, 92);
		btnNewButton.setForeground(new Color(0, 0, 0));
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("손님");
		btnNewButton_1.setBackground(new Color(204, 153, 153));
		btnNewButton_1.setBorder(null);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cstform.returnresult();
				if(e.getSource() == btnNewButton_1) {
		        	setVisible(false);
		        	cstform.setVisible(true);
				}
			}
		});
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton_1.setBounds(403, 275, 116, 92);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(278, 47, 97, 86);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("SEJONG CAMPING CAR");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 40));
		lblNewLabel_1.setBounds(93, 176, 481, 58);
		contentPane.add(lblNewLabel_1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cstform.backbtn) {
			cstform.setVisible(false);
        	setVisible(true);
		}
		if(e.getSource() == form.backbtn) {
			form.setVisible(false);
        	setVisible(true);
		}
	}

/* 메서드 이동 필요: admin 영역에 해당 */
/* 계획: ResultSet 대신에 데이터 클래스에 담아서 가져오는 식이 더 나을 듯
 * 그러면 컨트롤러에서 복잡하게 수정 안 해주고 넘기기만 하면 된다.
 * 뷰에선 데이터 클래스에 있는 내용을 필요에 맞게 가져올 수도 있다. */
	public void showReturnedList(){
		try {
			form.returnresulttxt.setText("앞쪽 \t 오른쪽 \t 왼쪽 \t 뒤쪽 \t 수리여부 \t 캠핑카ID \t 고유대여ID \n");
			ResultSet rs = model.getReturnedList();
			while (rs.next()) {
				String str = rs.getString(1) + "\t" + rs.getString(2) + "\t" +
						rs.getString(3) + "\t" + rs.getString(4) + "\t" +
						rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getString(7) + "\n";
				form.returnresulttxt.append(str);
			}
		} catch(Exception e1) {
			System.out.println(e1);
		}
	}

/* 기존의 데이터 초기화 기능을 전부 모델에서 담당하도록 변경
* & 장황한 메서드를 각 기능 별로 분리 */
	public void datareset() {
		model.resetDatabase();
		model.dropTables();
		model.createTables();
		model.inputSampleData();
	}
}
