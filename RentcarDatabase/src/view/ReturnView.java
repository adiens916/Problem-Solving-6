package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import controller.dataClass.ResultState;
import controller.dataClass.ReturnInfo;

public class ReturnView extends JFrame {

	private JPanel contentPane;
	private JTextField cpid, front, right, back, left, fix;
	public JButton returnbtn;

	public ReturnView() {
		addComponent();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnView frame = new ReturnView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ReturnInfo getCarStateInput() {
		ReturnInfo state = new ReturnInfo();
		state.front = front.getText();
		state.right = right.getText();
		state.left = left.getText();
		state.back = back.getText();
		state.fix = fix.getText();
		state.carId = cpid.getText();
		return state;
	}

	/*반환 버튼 클릭후 데이터 베이스에 업데이트 결과 나타내는 함수*/
	public void showReturnResult(ResultState result) {
		if (result == ResultState.SUCCESS) {
			JOptionPane.showMessageDialog(null, "반환완료");
		} else {
			JOptionPane.showMessageDialog(null, "차의 모든 상태 및 캠핑카ID를 확인해주세요.");
		}
	}

	private void addComponent() {
		setTitle("캠핑카프로젝트 리팩토링");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

//수리부분을 체크하기 위한 텍스트 라벨과 데이터값 입력창--------------------------------------------------
		JLabel Label1 = new JLabel("앞");
		Label1.setFont(new Font("굴림", Font.BOLD, 18));
		Label1.setBounds(12, 106, 27, 22);
		contentPane.add(Label1);

		JLabel Label2 = new JLabel("오른쪽");
		Label2.setFont(new Font("굴림", Font.BOLD, 18));
		Label2.setBounds(156, 21, 55, 22);
		contentPane.add(Label2);

		JLabel Label3 = new JLabel("뒤");
		Label3.setFont(new Font("굴림", Font.BOLD, 18));
		Label3.setBounds(334, 105, 27, 22);
		contentPane.add(Label3);

		JLabel Label4 = new JLabel("왼쪽");
		Label4.setFont(new Font("굴림", Font.BOLD, 18));
		Label4.setBounds(167, 193, 45, 22);
		contentPane.add(Label4);

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

		JLabel Label5 = new JLabel("수리여부(1:수리O, 0:수리x)");
		Label5.setFont(new Font("굴림", Font.BOLD, 15));
		Label5.setBounds(52, 239, 198, 21);
		contentPane.add(Label5);

		fix = new JTextField();
		fix.setBounds(247, 237, 55, 21);
		contentPane.add(fix);
		fix.setColumns(10);

		JLabel Label7 = new JLabel("캠핑카ID");
		Label7.setFont(new Font("굴림", Font.BOLD, 15));
		Label7.setBounds(174, 268, 67, 21);
		contentPane.add(Label7);

		cpid = new JTextField();
		cpid.setColumns(10);
		cpid.setBounds(247, 266, 55, 21);
		contentPane.add(cpid);
//반환 버튼 기능----------------------------------------------------------------------------
		returnbtn = new JButton("반환");
		returnbtn.setFont(new Font("양재튼튼체B", Font.BOLD, 15));
		returnbtn.setBounds(309, 237, 74, 52);
		contentPane.add(returnbtn);

//가운데 자동차 이미지-------------------------------------------------------------------------
		JLabel Label6 = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("car.png")).getImage();
		Label6.setIcon(new ImageIcon(img));
		Label6.setBounds(95, 36, 331, 161);
		contentPane.add(Label6);
	}
}
