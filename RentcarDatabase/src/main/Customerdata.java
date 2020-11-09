package main;

import model.DataModel_Customer;
import controller.dataClass.CustomerInfo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class Customerdata extends JFrame {

    private JPanel contentPane;
    JTextField lisenceid;
    JButton btnNewButton_1_3;
    JButton btnNewButton_1_3_1;
    JButton btnNewButton_1_3_2;
    JButton quit;
    JTextField name;
    JTextField address;
    JTextField number;
    JTextField emailaddress;
    JTextArea selectcp = new JTextArea();
    JButton btnNewButton_1;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1_1_2;
    private JLabel lblNewLabel_1_1_3;
    private JLabel lblNewLabel_2;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Customerdata frame = new Customerdata();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

/* 모델부 추가 [원래는 컨트롤러에 있어야 함] */
    private DataModel_Customer model = new DataModel_Customer();

    public void printdata() {
        selectcp.setText("운전면허번호 \t 이름 \t 주소 \t 번호 \t 이메일 \n");
        selectcp.append(model.getCustomerList());
    }

    public Customerdata() {
        setTitle("17013152 최종혁 -손님정보 관리페이지");
/* DB 연결부 삭제 [원래는 컨트롤러나 모델에서 연결] */
        // conDB();

        printdata();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 840, 442);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        /* 출력 영역 */
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(311, 98, 493, 287);
        panel.add(scrollPane);


        scrollPane.setViewportView(selectcp);

        lisenceid = new JTextField();
        lisenceid.setColumns(10);
        lisenceid.setBounds(158, 99, 72, 20);
        panel.add(lisenceid);


        JButton btnNewButton = new JButton("검색");
        btnNewButton.setFont(new Font("굴림", Font.PLAIN, 10));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
/* 메서드 추출 */
                searchCustomer();
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
        number.setBounds(158, 218, 141, 21);
        panel.add(number);

        emailaddress = new JTextField();
        emailaddress.setColumns(10);
        emailaddress.setBounds(158, 257, 141, 21);
        panel.add(emailaddress);
        btnNewButton_1_3 = new JButton("수정");
        btnNewButton_1_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
/* 메서드 추출 */
                updateCustomer();
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
                deleteCustomer();
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
                lisenceid.setText("");
            }
        });
        btnNewButton_1.setBounds(16, 18, 96, 20);
        panel.add(btnNewButton_1);

        btnNewButton_1_3_2 = new JButton("입력");
        btnNewButton_1_3_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
/* 메서드 추출 */
                addCustomer();
            }
        });
        btnNewButton_1_3_2.setForeground(Color.BLACK);
        btnNewButton_1_3_2.setFont(new Font("굴림", Font.BOLD, 15));
        btnNewButton_1_3_2.setBounds(34, 316, 70, 29);
        panel.add(btnNewButton_1_3_2);

        lblNewLabel = new JLabel("운전면허번호 검색후 수정,삭제하시면 더 편리합니다.");
        lblNewLabel.setBounds(265, 49, 294, 15);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("운전면허번호 입력");
        lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 15));
        lblNewLabel_1.setBounds(16, 98, 130, 21);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("이름");
        lblNewLabel_1_1.setFont(new Font("굴림", Font.BOLD, 18));
        lblNewLabel_1_1.setBounds(16, 141, 120, 20);
        panel.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_1_1 = new JLabel("주소");
        lblNewLabel_1_1_1.setFont(new Font("굴림", Font.BOLD, 18));
        lblNewLabel_1_1_1.setBounds(16, 178, 120, 20);
        panel.add(lblNewLabel_1_1_1);

        lblNewLabel_1_1_2 = new JLabel("번호");
        lblNewLabel_1_1_2.setFont(new Font("굴림", Font.BOLD, 18));
        lblNewLabel_1_1_2.setBounds(16, 221, 120, 20);
        panel.add(lblNewLabel_1_1_2);

        lblNewLabel_1_1_3 = new JLabel("이메일주소");
        lblNewLabel_1_1_3.setFont(new Font("굴림", Font.BOLD, 18));
        lblNewLabel_1_1_3.setBounds(16, 260, 120, 20);
        panel.add(lblNewLabel_1_1_3);

        lblNewLabel_2 = new JLabel("고객정보 입력 | 수정 | 삭제");
        lblNewLabel_2.setFont(new Font("양재튼튼체B", Font.BOLD, 28));
        lblNewLabel_2.setBounds(217, 4, 391, 39);
        panel.add(lblNewLabel_2);
    }

/* 이하 컨트롤러 + 뷰로 구성되어 있음 */
/* 현재 분류 부호가 숫자로 되어 있는데 알아보기 힘듦
 * 아래 4 방법 중 하나 골라 리팩토링 (교안 4 of 4 참고)
 * -> 조건문을 재정의로 전환
 * -> 분류 부호를 하위 클래스로 전환
 * -> 분류 부호를 상태/전략 패턴으로 전환
 * -> 분류 부호를 클래스로 전환
 * */
/* 입력 */
    public void addCustomer() {
/* 입력란으로부터 데이터 가져옴 */
        CustomerInfo customer = getCustomerInput();
/* 결과에 따라 상태 출력 */
        int result = model.addCustomer(customer);
        if (result == 1) {
            JOptionPane.showMessageDialog(btnNewButton_1_3_2, "입력완료!");
            printdata();
/* 빈 칸이 있던 경우 */
        } else if (result == 2) {
            JOptionPane.showMessageDialog(btnNewButton_1_3_2, "빈칸을 모두채워주세요");
            printdata();
/* 기 가입 고객인 경우 */
        } else if (result == 3) {
            JOptionPane.showMessageDialog(btnNewButton_1_3_2, "이미가입하신고객입니다.");
            printdata();
        } else {
            JOptionPane.showMessageDialog(btnNewButton_1_3_2, "다시입력해주세요!");
        }
    }

    public void searchCustomer() {
        String id = lisenceid.getText();
        selectcp.setText("");
        selectcp.setText("운전면허번호 \t 이름 \t 주소 \t 번호 \t 이메일 \n");
/* 모델에 id를 넘겨주면, 모델에서 id에 해당하는 걸 가져와 돌려 줌 */
        selectcp.append(model.searchCustomerById(id));
    }

    public void updateCustomer() {
        selectcp.setText("");
/* 입력란으로부터 데이터 가져옴 */
        CustomerInfo customer = getCustomerInput();
/* 모델에 데이터를 보내고, 반환된 결과값을 살핌 */
        int result = model.updateCustomer(customer);
/* 결과에 따라 뷰에서 상태 출력 */
        if (result == 1) {
            JOptionPane.showMessageDialog(btnNewButton_1_3, "수정완료");
            //수정하고나서출력!
            printdata();
        } else if (result == 2) {
            JOptionPane.showMessageDialog(btnNewButton_1_3, "빈칸을 모두채워주세요");
            printdata();
        } else {
            JOptionPane.showMessageDialog(btnNewButton_1_3, "다시입력하세요!");
        }
    }

    public void deleteCustomer() {
        String id = lisenceid.getText();
/* 모델에 id를 넘겨주고, 처리 결과를 가져옴 */
        int result = model.deleteCustomer(id);
/* 처리 결과에 맞게 뷰에서 상태 출력 */
        if (result == 1) {
            JOptionPane.showMessageDialog(btnNewButton_1_3_1, "삭제 완료");
            printdata();
            lisenceid.setText("");
        } else {
            JOptionPane.showMessageDialog(btnNewButton_1_3_1, "ID를 입력해주세요.");
            //System.out.println("실패");
        }
    }

/* 사용자 입력으로부터 데이터를 가져와서 데이터 클래스에 저장 */
    public CustomerInfo getCustomerInput() {
        CustomerInfo customer = new CustomerInfo();
        customer.name = name.getText();
        customer.address = address.getText();
        customer.number = number.getText();
        customer.emailAddress = emailaddress.getText();
        customer.licenseId = lisenceid.getText();
        return customer;
    }
}