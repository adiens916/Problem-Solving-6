package view;

import model.dataClass.ResultStateDataClass;
import model.dataClass.ReturnDataClass;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ReturnView extends JFrame {

	public JButton returnButton;
	private JTextField campingCarIdTextField;
	private JTextField frontTextField;
	private JTextField rightTextField;
	private JTextField backTextField;
	private JTextField leftTextField;
	private JTextField fixTextField;

	public ReturnView() {
		addComponent();
	}

	public void refreshInput() {
		campingCarIdTextField.setText("");
		frontTextField.setText("");
		rightTextField.setText("");
		backTextField.setText("");
		leftTextField.setText("");
		fixTextField.setText("");
	}

	public ReturnDataClass getCarStateInput() {
		ReturnDataClass state = new ReturnDataClass();
		state.front = frontTextField.getText();
		state.right = rightTextField.getText();
		state.left = leftTextField.getText();
		state.back = backTextField.getText();
		state.fix = fixTextField.getText();
		state.campingCarId = campingCarIdTextField.getText();
		return state;
	}

	/*반환 버튼 클릭후 데이터 베이스에 업데이트 결과 나타내는 함수*/
	public void showReturnResult(ResultStateDataClass result) {
		if (result == ResultStateDataClass.SUCCESS) {
			JOptionPane.showMessageDialog(null, "반환 완료");
		} else {
			JOptionPane.showMessageDialog(null, "차의 모든 상태 및 캠핑카 ID를 확인해주세요.");
		}
	}

	private void addComponent() {
		setTitle("캠핑카프로젝트 리팩토링");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 369);
		JPanel contentPane = new JPanel();
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

		frontTextField = new JTextField();
		frontTextField.setBounds(35, 106, 45, 21);
		contentPane.add(frontTextField);
		frontTextField.setColumns(10);

		rightTextField = new JTextField();
		rightTextField.setColumns(10);
		rightTextField.setBounds(217, 22, 45, 21);
		contentPane.add(rightTextField);

		backTextField = new JTextField();
		backTextField.setColumns(10);
		backTextField.setBounds(358, 107, 45, 21);
		contentPane.add(backTextField);

		leftTextField = new JTextField();
		leftTextField.setColumns(10);
		leftTextField.setBounds(210, 193, 45, 21);
		contentPane.add(leftTextField);

		JLabel Label5 = new JLabel("수리여부(1:수리O, 0:수리x)");
		Label5.setFont(new Font("굴림", Font.BOLD, 15));
		Label5.setBounds(52, 239, 198, 21);
		contentPane.add(Label5);

		fixTextField = new JTextField();
		fixTextField.setBounds(247, 237, 55, 21);
		contentPane.add(fixTextField);
		fixTextField.setColumns(10);

		JLabel Label7 = new JLabel("캠핑카ID");
		Label7.setFont(new Font("굴림", Font.BOLD, 15));
		Label7.setBounds(174, 268, 67, 21);
		contentPane.add(Label7);

		campingCarIdTextField = new JTextField();
		campingCarIdTextField.setColumns(10);
		campingCarIdTextField.setBounds(247, 266, 55, 21);
		contentPane.add(campingCarIdTextField);
//반환 버튼 기능----------------------------------------------------------------------------
		returnButton = new JButton("반환");
		returnButton.setFont(new Font("양재튼튼체B", Font.BOLD, 15));
		returnButton.setBounds(309, 237, 74, 52);
		contentPane.add(returnButton);

//가운데 자동차 이미지-------------------------------------------------------------------------
		JLabel Label6 = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/car.png")).getImage();
		Label6.setIcon(new ImageIcon(img));
		Label6.setBounds(95, 36, 331, 161);
		contentPane.add(Label6);
	}
}
