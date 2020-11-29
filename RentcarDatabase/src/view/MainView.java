package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainView extends JFrame {

	public JButton goToAdminButton;
	public JButton goToUserButton;

	/* MainView 객체 하나만 생성하기: 싱글턴 패턴 */
	/* 다른 곳에서 객체 참조할 때 쓰는 메서드 */
	public static MainView getInstance() {
		/* holder 에 담긴 객체를 그대로 반환 */
		return MainViewHolder.instance;
	}

	/* inner static class: static 메서드가 로딩될 때 최초 한 번만 로딩 */
	private static class MainViewHolder {
		private static final MainView instance = new MainView();
	}

	/* 생성자 private: 다른 클래스에서 접근 불가 */
	private MainView() {
		addComponent();
	}

	private void addComponent() {
		setTitle("캠핑카프로젝트 리팩토링");
//------배경설정-------------------------------------------------------------------------------------------------
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 473);
//------버튼, 텍스트상장, 라벨과 같은 객체를 넣기 위한 팔레트 생성----------------------------------------------------------------
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
//------관리자View로 가기위한 버튼------------------------------------------------------------------------------------
		goToAdminButton = new JButton("관리자");
		goToAdminButton.setBackground(new Color(204, 153, 153));
		goToAdminButton.setBorder(null);
		goToAdminButton.setFont(new Font("굴림", Font.BOLD, 20));
		goToAdminButton.setBounds(130, 275, 116, 92);
		goToAdminButton.setForeground(new Color(0, 0, 0));
		contentPane.add(goToAdminButton);
//------손님View로 가기위한 버튼------------------------------------------------------------------------------------
		goToUserButton = new JButton("손님");
		goToUserButton.setBackground(new Color(204, 153, 153));
		goToUserButton.setBorder(null);
		goToUserButton.setForeground(Color.BLACK);
		goToUserButton.setFont(new Font("굴림", Font.BOLD, 20));
		goToUserButton.setBounds(403, 275, 116, 92);
		contentPane.add(goToUserButton);
//------로고 이미지를 넣기위한 라벨 생성-------------------------------------------------------------------------------
		JLabel imageLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("logo.png")).getImage();
		imageLabel.setIcon(new ImageIcon(img));
		imageLabel.setBounds(278, 47, 97, 86);
		contentPane.add(imageLabel);
//------텍스트를 넣기위한 라벨 생성----------------------------------------------------------------------------------
		JLabel titleLabel = new JLabel("SEJONG CAMPING CAR");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
		titleLabel.setBounds(93, 176, 481, 58);
		contentPane.add(titleLabel);
	}
}
