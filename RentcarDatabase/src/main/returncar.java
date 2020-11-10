package main;

import model.ReturnModel;
import controller.dataClass.ReturnInfo;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class returncar extends JFrame {

	 JPanel contentPane;
	 JTextField front;
	 JTextField right;
	 JTextField back;
	 JTextField left;
	 JTextField fix;
	   JTextField cpid;
	   JButton returnbtn; 

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					returncar frame = new returncar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public returncar() {
		setTitle("17013152 최종혁 -랜트카반환페이지");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("앞");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1.setBounds(12, 106, 27, 22);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("오른쪽");
		lblNewLabel_1_1.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(156, 21, 55, 22);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("뒤");
		lblNewLabel_1_2.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(334, 105, 27, 22);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("왼쪽");
		lblNewLabel_1_3.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1_3.setBounds(167, 193, 45, 22);
		contentPane.add(lblNewLabel_1_3);
		
		front = new JTextField();
		front.setBounds(35, 106, 45, 21);
		contentPane.add(front);
		front.setColumns(10);
		
		right = new JTextField();
		right.setColumns(10);
		right.setBounds(217, 22, 45, 21);
		contentPane.add(right);
		
		back = new JTextField();
		back.setColumns(10);
		back.setBounds(358, 107, 45, 21);
		contentPane.add(back);
		
		left = new JTextField();
		left.setColumns(10);
		left.setBounds(210, 193, 45, 21);
		contentPane.add(left);
		
		JLabel lblNewLabel_2 = new JLabel("수리여부(1:수리O, 0:수리x)");
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_2.setBounds(52, 239, 198, 21);
		contentPane.add(lblNewLabel_2);
		
		fix = new JTextField();
		fix.setBounds(247, 237, 55, 21);
		contentPane.add(fix);
		fix.setColumns(10);
		
		returnbtn = new JButton("반환");
		returnbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* [컨트롤러] 뷰에서 사용자 입력 받아옴 */
				ReturnInfo state = getCarState();
				/* [컨트롤러] 받아온 데이터를 모델에 넘겨서 처리*/
				ReturnModel model = new ReturnModel();
				/* 처리 결과를 모델에게서 가져 옴 */
				int processingResult = model.returnCar(state);
				/* [컨트롤러] 처리 결과에 따라 뷰에서 메시지 출력 */
				printProcessingResult(processingResult);
			}
		});
		returnbtn.setFont(new Font("양재튼튼체B", Font.BOLD, 15));
		returnbtn.setBounds(309, 237, 74, 52);
		contentPane.add(returnbtn);
		
		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/car.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(95, 36, 331, 161);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2_1 = new JLabel("캠핑카ID");
		lblNewLabel_2_1.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(174, 268, 67, 21);
		contentPane.add(lblNewLabel_2_1);
		
		cpid = new JTextField();
		cpid.setColumns(10);
		cpid.setBounds(247, 266, 55, 21);
		contentPane.add(cpid);
	}

	/* [뷰] 사용자 입력란에서 데이터를 읽어와 데이터 클래스에 저장 */
	private ReturnInfo getCarState() {
		ReturnInfo state = new ReturnInfo();
		state.front = front.getText();
		state.right = right.getText();
		state.left = left.getText();
		state.back = back.getText();
		state.fix = fix.getText();
		state.carId = cpid.getText();
		return state;
	}

	/* [뷰] 결과값에 따라 상태 메시지 출력 */
	private void printProcessingResult(int processingResult) {
		if (processingResult == 1) {
			JOptionPane.showMessageDialog(returnbtn, "반환완료");
		} else {
			JOptionPane.showMessageDialog(returnbtn, "차의 모든 상태 및 캠핑카ID를 확인해주세요.");
		}
	}
}
