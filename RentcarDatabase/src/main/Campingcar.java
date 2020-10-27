package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class Campingcar extends JFrame implements ActionListener {

	private JPanel contentPane;
	admin form;
	Customer cstform;
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

	// SQL 연결
		static Connection con;
		PreparedStatement psmt;
		   Statement stmt,stmt1;
		   ResultSet rs;
		   String Driver="";
		   String url="jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false"; 
		   String userid="madang";
		   String pwd="madang";
		   private JTextField torepair;
		   private JTextField tofix;
		public void conDB() { 
		     try {
		       Class.forName("com.mysql.cj.jdbc.Driver");   
		       //System.out.println("드라이버 로드 성공");
		     } catch(ClassNotFoundException e1) {
		         e1.printStackTrace();
		     }
		   try {
		       //System.out.println("데이터베이스 연결 준비...");
		       con=DriverManager.getConnection(url, userid, pwd); 
		       System.out.println("CONNECTION SUCCESFUL\nREADY TO USE PROGRAM");
		     } catch(SQLException e1) {
		         e1.printStackTrace();
		       }
		   }
	public Campingcar() {
		setTitle("17013152 최종혁 -메인페이지");
		conDB();
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
					returnresult();
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
	public void returnresult(){
		try {
			form.returnresulttxt.setText("앞쪽 \t 오른쪽 \t 왼쪽 \t 뒤쪽 \t 수리여부 \t 캠핑카ID \t 고유대여ID \n");
			stmt = con.createStatement();
			String query="SELECT * FROM campingcar_return;";
			rs = stmt.executeQuery(query);
             while(rs.next()) {
                String str = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
                +  "\t" + rs.getString(5)+ "\t" + rs.getString(6)+"\t" + rs.getString(7)+"\n";
                form.returnresulttxt.append(str);
             }
		}catch(Exception e1) {
			System.out.println(e1);
		}
	}
	
	public void datareset() {
		try {
			stmt1 = con.createStatement();
			String query="DROP SCHEMA IF EXISTS `madang` ;";
			stmt1.executeUpdate(query);
			
			
			stmt1 = con.createStatement();
			query="CREATE SCHEMA IF NOT EXISTS `madang` DEFAULT CHARACTER SET utf8 ;";
			stmt1.executeUpdate(query);
			
			stmt1 = con.createStatement();
			query="USE `madang`;";
			stmt1.executeUpdate(query);
			
			stmt1 = con.createStatement();
            query="DROP TABLE IF EXISTS `customer_rent_old_list`;";
             stmt1.executeUpdate(query);
             query="DROP TABLE IF EXISTS `rentcar_list`;";
             stmt1.executeUpdate(query);
             query="DROP TABLE IF EXISTS `campingcar_return`;";
             stmt1.executeUpdate(query);
             query="DROP TABLE IF EXISTS `repair_list`;";
             stmt1.executeUpdate(query);
             query="DROP TABLE IF EXISTS `garage`;";
             stmt1.executeUpdate(query);
             query="DROP TABLE IF EXISTS `customer_rent_list`;";
             stmt1.executeUpdate(query);
             query="DROP TABLE IF EXISTS `campingcar_list`;";
             stmt1.executeUpdate(query);
             query="DROP TABLE IF EXISTS `customer`;"; 
             stmt1.executeUpdate(query);
             query="DROP TABLE IF EXISTS `campingcar_rent_company`;";
             stmt1.executeUpdate(query);
               // System.out.println("삭제완료!");
                
           query="CREATE TABLE IF NOT EXISTS `campingcar_rent_company` (\r\n" + 
           		"  `camping_rent_company_id` INT NOT NULL AUTO_INCREMENT,\r\n" + 
           		"  `cp_name` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `cp_address` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `cp_number` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `cp_mng_email` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `cp_mng_name` VARCHAR(45) NOT NULL,\r\n" + 
           		"  PRIMARY KEY (`camping_rent_company_id`))";
           stmt1.executeUpdate(query);
           
           query="CREATE TABLE IF NOT EXISTS `customer` (\r\n" + 
           		"  `license_id` INT NOT NULL,\r\n" + 
           		"  `c_name` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `c_address` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `c_number` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `c_email` VARCHAR(100) NOT NULL,\r\n" + 
           		"  PRIMARY KEY (`license_id`))";
           stmt1.executeUpdate(query);
           
           query="CREATE TABLE IF NOT EXISTS `campingcar_list` (\r\n" + 
           		"  `campingcar_list_id` INT NOT NULL AUTO_INCREMENT,\r\n" + 
           		"  `cc_name` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `cc_number` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `cc_sits` INT NOT NULL,\r\n" + 
           		"  `cc_manufacture` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `cc_manufacture_year` INT NOT NULL,\r\n" + 
           		"  `cc_mileage` INT NOT NULL,\r\n" + 
           		"  `cc_rent_price` INT NOT NULL,\r\n" + 
           		"  `cc_regist_date` DATE NOT NULL,\r\n" + 
           		"  `campingcar_rent_company_id` INT NOT NULL,\r\n" + 
           		"  PRIMARY KEY (`campingcar_list_id`),\r\n" + 
           		"  INDEX `fk_campingcar_list_campingcar_rent_company1_idx` (`campingcar_rent_company_id` ASC) VISIBLE,\r\n" + 
           		"  CONSTRAINT `fk_campingcar_list_campingcar_rent_company1`\r\n" + 
           		"    FOREIGN KEY (`campingcar_rent_company_id`)\r\n" + 
           		"    REFERENCES `campingcar_rent_company` (`camping_rent_company_id`)\r\n" + 
           		"    ON DELETE CASCADE\r\n" + 
           		"    ON UPDATE NO ACTION)";
           stmt1.executeUpdate(query);
           
           query="CREATE TABLE IF NOT EXISTS `customer_rent_list` (\r\n" + 
           		"  `rent_id` INT NOT NULL AUTO_INCREMENT,\r\n" + 
           		"  `start_date` DATE NOT NULL,\r\n" + 
           		"  `rent_time` INT NOT NULL,\r\n" + 
           		"  `due_date` DATE NOT NULL,\r\n" + 
           		"  `otherthing` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `others_price` INT NOT NULL,\r\n" + 
           		"  `campingcar_company_id` INT NOT NULL,\r\n" + 
           		"  `c_license_id` INT NOT NULL,\r\n" + 
           		"  `campingcar_id` INT NOT NULL,\r\n" + 
           		"  `cc_price` INT NOT NULL,\r\n" + 
           		"  PRIMARY KEY (`rent_id`),\r\n" + 
           		"  INDEX `fk_customer rent list_customer1_idx` (`c_license_id` ASC) VISIBLE,\r\n" + 
           		"  INDEX `fk_customer rent list_campingcar rent company1_idx` (`campingcar_company_id` ASC) VISIBLE,\r\n" + 
           		"  INDEX `fk_customer_rent_list_campingcar_list1_idx` (`campingcar_id` ASC) VISIBLE,\r\n" + 
           		"  CONSTRAINT `fk_customer rent list_customer1`\r\n" + 
           		"    FOREIGN KEY (`c_license_id`)\r\n" + 
           		"    REFERENCES `customer` (`license_id`)\r\n" + 
           		"    ON DELETE CASCADE\r\n" + 
           		"    ON UPDATE NO ACTION,\r\n" + 
           		"  CONSTRAINT `fk_customer rent list_campingcar rent company1`\r\n" + 
           		"    FOREIGN KEY (`campingcar_company_id`)\r\n" + 
           		"    REFERENCES `campingcar_rent_company` (`camping_rent_company_id`)\r\n" + 
           		"    ON DELETE CASCADE\r\n" + 
           		"    ON UPDATE NO ACTION,\r\n" + 
           		"  CONSTRAINT `fk_customer_rent_list_campingcar_list1`\r\n" + 
           		"    FOREIGN KEY (`campingcar_id`)\r\n" + 
           		"    REFERENCES `campingcar_list` (`campingcar_list_id`)\r\n" + 
           		"    ON DELETE CASCADE\r\n" + 
           		"    ON UPDATE NO ACTION)";
           stmt1.executeUpdate(query);
           
           query="CREATE TABLE IF NOT EXISTS `garage` (" + 
                 "  `garage_id` INT NOT NULL AUTO_INCREMENT," + 
                 "  `g_name` VARCHAR(45) NOT NULL," + 
                 "  `g_address` VARCHAR(45) NOT NULL," + 
                 "  `g_number` VARCHAR(45) NOT NULL," + 
                 "  `g_manager` VARCHAR(45) NOT NULL," + 
                 "  `g_email` VARCHAR(100) NOT NULL," + 
                 "  PRIMARY KEY (`garage_id`))";
           stmt1.executeUpdate(query);
           
           query="CREATE TABLE IF NOT EXISTS `repair_list` (\r\n" + 
           		"  `repair list_id` INT NOT NULL AUTO_INCREMENT,\r\n" + 
           		"  `r_log` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `r_date` DATE NOT NULL,\r\n" + 
           		"  `r_price` INT NOT NULL,\r\n" + 
           		"  `r_due_date` DATE NOT NULL,\r\n" + 
           		"  `r_other_repair` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `customer_license_id` INT NOT NULL,\r\n" + 
           		"  `campingcar_rent_company_id` INT NOT NULL,\r\n" + 
           		"  `garage_id` INT NOT NULL,\r\n" + 
           		"  `campingcar_list_id` INT NOT NULL,\r\n" + 
           		"  PRIMARY KEY (`repair list_id`),\r\n" + 
           		"  INDEX `fk_repair list_customer1_idx` (`customer_license_id` ASC) VISIBLE,\r\n" + 
           		"  INDEX `fk_repair list_campingcar rent company1_idx` (`campingcar_rent_company_id` ASC) VISIBLE,\r\n" + 
           		"  INDEX `fk_repair list_garage1_idx` (`garage_id` ASC) VISIBLE,\r\n" + 
           		"  CONSTRAINT `fk_repair list_customer1`\r\n" + 
           		"    FOREIGN KEY (`customer_license_id`)\r\n" + 
           		"    REFERENCES `customer` (`license_id`)\r\n" + 
           		"    ON DELETE CASCADE\r\n" + 
           		"    ON UPDATE NO ACTION,\r\n" + 
           		"  CONSTRAINT `fk_repair list_campingcar rent company1`\r\n" + 
           		"    FOREIGN KEY (`campingcar_rent_company_id`)\r\n" + 
           		"    REFERENCES `campingcar_rent_company` (`camping_rent_company_id`)\r\n" + 
           		"    ON DELETE CASCADE\r\n" + 
           		"    ON UPDATE NO ACTION,\r\n" + 
           		"  CONSTRAINT `fk_repair list_garage1`\r\n" + 
           		"    FOREIGN KEY (`garage_id`)\r\n" + 
           		"    REFERENCES `garage` (`garage_id`)\r\n" + 
           		"    ON DELETE CASCADE\r\n" + 
           		"    ON UPDATE NO ACTION)";
           stmt1.executeUpdate(query);
           
           query="CREATE TABLE IF NOT EXISTS `campingcar_return` (\r\n" + 
           		"  `front` VARCHAR(100) NOT NULL,\r\n" + 
           		"  `right` VARCHAR(100) NOT NULL,\r\n" + 
           		"  `left` VARCHAR(100) NOT NULL,\r\n" + 
           		"  `back` VARCHAR(100) NOT NULL,\r\n" + 
           		"  `fix` INT NOT NULL,\r\n" + 
           		"  `campingcar_list_id` INT NOT NULL,\r\n" + 
           		"  `custom_rent_list_id` INT NOT NULL,\r\n" + 
           		"  INDEX `fk_campingcar return_campingcar list1_idx` (`campingcar_list_id` ASC) VISIBLE,\r\n" + 
           		"  UNIQUE INDEX `custom_rent_list_id_UNIQUE` (`custom_rent_list_id` ASC) VISIBLE,\r\n" + 
           		"  CONSTRAINT `fk_campingcar return_campingcar list1`\r\n" + 
           		"    FOREIGN KEY (`campingcar_list_id`)\r\n" + 
           		"    REFERENCES `campingcar_list` (`campingcar_list_id`)\r\n" + 
           		"    ON DELETE CASCADE\r\n" + 
           		"    ON UPDATE NO ACTION)";
           stmt1.executeUpdate(query);
           
           query="CREATE TABLE IF NOT EXISTS `rentcar_list` (\r\n" + 
           		"  `rent_id` INT NOT NULL,\r\n" + 
           		"  `cc_name` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `cc_number` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `cc_sits` INT NOT NULL,\r\n" + 
           		"  `cc_manufacture` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `cc_manufacture_year` INT NOT NULL,\r\n" + 
           		"  `cc_mileage` INT NOT NULL,\r\n" + 
           		"  `cc_rent_price` INT NULL,\r\n" + 
           		"  `cc_regist_date` DATE NOT NULL,\r\n" + 
           		"  `camping_rent_company_id` INT NOT NULL,\r\n" + 
           		"  PRIMARY KEY (`rent_id`),\r\n" + 
           		"  INDEX `fk_rentcar_list_campingcar_rent_company1_idx` (`camping_rent_company_id` ASC) VISIBLE,\r\n" + 
           		"  CONSTRAINT `fk_rentcar_list_campingcar_rent_company1`\r\n" + 
           		"    FOREIGN KEY (`camping_rent_company_id`)\r\n" + 
           		"    REFERENCES `campingcar_rent_company` (`camping_rent_company_id`)\r\n" + 
           		"    ON DELETE CASCADE\r\n" + 
           		"    ON UPDATE NO ACTION)";
           stmt1.executeUpdate(query);
           
           query="CREATE TABLE IF NOT EXISTS `customer_rent_old_list` (\r\n" + 
           		"  `rent_id` INT NOT NULL,\r\n" + 
           		"  `start_date` DATE NOT NULL,\r\n" + 
           		"  `retn_time` INT NOT NULL,\r\n" + 
           		"  `due_date` DATE NOT NULL,\r\n" + 
           		"  `otherthing` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `others_price` INT NOT NULL,\r\n" + 
           		"  `campingcar_company_id` INT NOT NULL,\r\n" + 
           		"  `c_license_id` INT NOT NULL,\r\n" + 
           		"  `campingcar_id` INT NOT NULL,\r\n" + 
           		"  `cc_price` INT NOT NULL,\r\n" + 
           		"  PRIMARY KEY (`rent_id`))";
           stmt1.executeUpdate(query);
        //    System.out.println("테이블 초기화 완료");
            
           query="insert into campingcar_rent_company values('1','SAMSUNG','서울시 서초구','02-345-5676','samsungcprent@naver.com','김삼성'),"+
        		 "('2','LG','서울시 강남구','02-546-5474','lgcprent@naver.com','최엘쥐'),"+
                 "('3','HYUNDAI','서울시 강서구','02-368-7374','hyundaicprent@naver.com','강현대'),"+
                 "('4','BOOGATI','서울시 강북구','02-485-9536','boogaticprent@naver.com','고부지'),"+
                 "('5','SHEVORAE','서울시 용산구','02-854-9642','shevoraecprent@naver.com','쉐보레'),"+
                 "('6','PORCHE','서울시 종로구','02-458-4736','porchecprent@naver.com','김포쉐'),"+
                 "('7','SYOUNG','서울시 마포구','02-146-9074','sangyoungcprent@naver.com','주쌍용'),"+
                 "('8','BMW','서울시 영등포구','02-136-9654','bmwcprent@naver.com','빔덤유'),"+
                 "('9','GMC','경기도 성남','02-725-0526','gmccprent@naver.com','주엠시'),"+
                 "('10','TOYOTA','서울시 동작구','02-6427-3743','toyotacprent@naver.com','토요타'),"+
                 "('11','HONDA','서울시 관악구','02-9558-1584','hondacprent@naver.com','잠혼다'),"+
                 "('12','KIA','서울시 영등포구','02-435-3023','kiacprent@naver.com','최키아'),"+
                 "('13','RAMBORG','서울시 광진구','02-435-3023','ramborgcprent@naver.com','이람보'),"+
                 "('14','SAMSUNG','서울시 종로구','02-345-5348','samsung2cprent@naver.com','최삼수'),"+
                 "('15','HYUNDAI','서울시 강동구','02-546-2346','hyndai2cprent@naver.com','김현수');";
                stmt1.executeUpdate(query);
               
               query="insert into campingcar_list values('1','TIGERJK','23하 4534','8','samsung','1945','20000','30','2017-04-20','1')," + 
                     "('2','LIONG2','45호 3453','6','LG','1975','31000','30','2019-05-20','2')," + 
                     "('3','DOG46','33호 4245','8','HYUNDAI','2021','68000','20','2021-05-20','3')," + 
                     "('4','CAT32','36하 8553','7','BOOGATI','2020','99000','50','2020-04-25','4')," + 
                     "('5','PENCILMANIA','26하 4525','9','SHEVORAE','2020','300000','30','2020-01-20','5')," + 
                     "('6','CORANDO','25하 9626','10','PORCHE','1945','294033','32','2017-04-20','6')," + 
                     "('7','CAMPFILE','95하 4216','5','SSANGYOUNG','2015','305924','54','2017-07-20','7')," + 
                     "('8','BOOMBA','73하 4791','8','HYUNDAI','2017','24924','43','2018-01-05','3')," + 
                     "('9','GARGARRI','743하 5274','8','samsung','2014','12353','65','2017-03-06','1')," + 
                     "('10','GOMAN','937하 5930','8','HYUNDAI','2015','402044','85','2017-03-10','3')," + 
                     "('11','GOODSCORE','793하 4362','5','HYUNDAI','2017','500','12','2017-07-19','3')," + 
                     "('12','DAMBOO','274하 6927','8','samsung','2012','7942','53','2017-04-13','1')," + 
                     "('13','EARLYBURN','407하 2423','4','KIA','2018','13414','84','2018-06-10','13')," + 
                     "('14','BESTY','501하 5035','8','KIA','2019','93423','894','2019-07-30','15')," + 
                     "('15','ROLEBACK','36하 6959','10','KIA','2020','19494','15','2020-05-20','11')," +
                     "('16','NONEBREAK','34하 2423','13','HYUNDAI','2020','19494','15','2020-06-09','3'),"+
                     "('17','ROLEBACK','54하 6959','5','KIA','1944','19494','15','1944-05-20','13')," + 
                     "('18','RANDY','23하 3421','6','HYUNDAI','1967','35421','45','1967-05-20','6')," + 
                     "('19','ROMA','347하 3462','7','samsung','2020','34133','46','2020-05-20','8')," + 
                     "('20','ROOMER','45하 5684','4','SHEVORAE','2020','31546','36','2020-05-20','7')," + 
                     "('21','RANAD','613하 9578','7','HYUNDAI','2020','23431','38','2020-05-20','8')," + 
                     "('22','CODEA','35하 5684','8','HYUNDAI','2020','345425','35','2020-05-20','4')," + 
                     "('23','FEDCRT','38하 3456','9','HYUNDAI','2020','34345','83','2020-05-20','1')," + 
                     "('24','NFRSV','84하 3458','4','KIA','2020','3533','76','2020-05-20','2')," + 
                     "('25','EFCGE','29하 3559','6','HYUNDAI','2020','2454','34','2020-05-20','3')," + 
                     "('26','ECC4','68하 3354','8','KIA','2020','4624','47','2020-05-20','7')," + 
                     "('27','FEVSR5','35하 3482','9','HYUNDAI','2020','34563','26','2020-05-20','9')," + 
                     "('28','NSXCT3','74하 4594','4','KIA','2020','23462','34','2020-05-20','9')," + 
                     "('29','DDDDEF','28하 9563','8','HYUNDAI','2020','24624','100','2020-05-20','4')," + 
                     "('30','EREER3','83하 2348','9','KIA','2020','134345','120','2020-05-20','7'),"+
                     "('31','BESTCAR','83하 3231','10','KIA','2020','111313','120','2020-05-20','14')";
                     
            stmt1.executeUpdate(query);
            
               query="insert into customer values('1111111','김마당','서울시 영등포구','010-3735-2396','uiurihappy@naver.com')," + 
                     "('2235235','최종혁','서울시 서초구','010-9773-5873','cjh@naver.com')," + 
                     "('3235245','이수','서울시 강남구','010-2135-1343','isu@gmail.com')," + 
                     "('4353522','싸이','서울시 강동구','010-4787-9133','psy@nate.com')," + 
                     "('5356356','양다일','경기도 일산','010-1377-6464','dail@naver.com')," + 
                     "('6235565','이지금','서울시 강동구','010-5468-1381','dlwlrma@naver.com')," + 
                     "('7245245','최원재','서울시 마포구','010-7893-5133','kidcozyboy@naver.com')," + 
                     "('8567433','문지훈','서울시 마포구','010-4861-1669','porkcutlet@naver.com')," + 
                     "('9467242','우원재','인천시 계양구','010-1384-8319','santakiller@gmail.com')," + 
                     "('1023454','염현수','서울시 강북구','010-1731-9997','yumdda@naver.com')," + 
                     "('1124635','한요한','경기도 분당구','010-3656-3961','yohans@gmail.com')," + 
                     "('1256735','구창모','서울시 영등포구','010-4881-8386','ganggang@hanmail.net')," + 
                     "('1323554','윤진영','서울시 마포구','010-0203-3080','clloud@naver.com')," + 
                     "('1473565','김민경','서울시 강서구','010-8816-8479','liik@naver.com')," + 
                     "('1484534','신동갑','서울시 영등포구','010-8403-9930','thequiet@naver.com');";
              stmt1.executeUpdate(query);
              
             query="insert into customer_rent_list values('1','2019-11-14','7','2019-11-11','캠핑의자','2','1','1023454','17','30')," + 
                   "('2','2019-01-04','4','2019-01-01','바베큐그릴','4','2','1124635','18','20')," + 
                   "('3','2019-07-12','3','2019-07-09','부탄가스','1','3','1256735','19','15')," + 
                   "('4','2019-08-31','5','2019-08-28','일회용품','1','4','1323554','20','25')," + 
                   "('5','2019-10-10','10','2019-10-07','바베큐그릴','4','5','1111111','21','42')," + 
                   "('6','2019-06-14','8','2019-06-11','부탄가스','1','6','1473565','22','35')," + 
                   "('7','2019-12-25','7','2019-12-22','숯','2','7','1484534','23','30')," + 
                   "('8','2019-05-04','15','2019-05-01','일회용품','1','8','2235235','24','65')," + 
                   "('9','2019-08-25','12','2019-08-22','캠핑의자','2','9','3235245','25','50')," + 
                   "('10','2019-07-06','9','2019-07-03','천막','3','10','4353522','26','40')," + 
                   "('11','2019-04-25','7','2019-04-22','낚시대','6','11','5356356','27','30')," + 
                   "('12','2019-02-14','6','2019-02-11','천막','3','12','6235565','28','26')," + 
                   "('13','2019-01-13','15','2019-01-10','일회용품','1','13','7245245','29','65')," + 
                   "('14','2019-05-16','11','2019-05-13','캠핑의자','2','14','8567433','30','46')," + 
                   "('15','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','31','30')";
                   
                stmt1.executeUpdate(query);
                
                query="insert into campingcar_return values('양호','양호','양호','양호','0','30','17')," + 
                      "('양호','양호','양호','양호','0','29','18')," + 
                      "('양호','양호','불량','양호','1','28','19')," + 
                      "('양호','양호','양호','양호','0','27','20')," + 
                      "('양호','양호','불량','양호','1','26','21')," + 
                      "('양호','양호','양호','양호','0','25','22')," + 
                      "('불량','양호','양호','양호','1','24','23')," + 
                      "('양호','불량','양호','양호','1','23','24')," + 
                      "('양호','양호','양호','양호','0','22','25')," + 
                      "('양호','양호','양호','양호','0','20','27')," + 
                      "('양호','양호','양호','불량','1','19','28')," + 
                      "('양호','양호','양호','양호','0','18','29')," + 
                      "('양호','양호','양호','양호','0','17','30')," +
                      "('양호','불량','양호','양호','1','16','31');";
                   stmt1.executeUpdate(query);
                   
                query="insert into garage values('1','정의로운카센터','서울시 양천구','010-0943-5493','정카터','justice@naver.com')," + 
                      "('2','잘고침카센터','서울시 금천구','010-2343-5454','잘수요','sdgse@naver.com')," + 
                      "('3','KIAPROF','서울시 구로구','010-6756-7856','이민호','sefsfsd@naver.com')," + 
                      "('4','DENTIST','서울시 강동구','010-4536-5643','원빈','asdfsadf@naver.com')," + 
                      "('5','AMZACAR','서울시 송파구','010-1233-4543','정우성','sdfsdf@naver.com')," + 
                      "('6','READYCL','서울시 광진구','010-7533-2364','강남','wefwef@naver.com')," + 
                      "('7','이지금카센터','서울시 종로구','010-4537-8543','아이유','sdvsv@naver.com')," + 
                      "('8','서초구카센터','서울시 진구','010-3424-7345','이지원','sewewe@naver.com')," + 
                      "('9','강서구카센터','서울시 용산구','010-7342-9774','강만월','dgasdd@naver.com')," + 
                      "('10','흑석동카센터','서울시 성북구','010-3425-6542','최수혁','asdfsdfe@naver.com')," + 
                      "('11','신우오카센터','서울시 덕양구','010-7934-2346','이강산','wefwefd@naver.com')," + 
                      "('12','감고구카센터','서울시 양천구','010-9357-4935','이승배','bdadvasd@naver.com')," + 
                      "('13','충하오카센터','서울시 서초구','010-4824-1293','이세종','sdfasdfae@naver.com')," + 
                      "('14','감사카센터','서울시 강남구','010-6319-0372','강군무','wefwefwq@naver.com')," + 
                      "('15','빅마마카센터','서울시 강남구','010-3589-4524','킹훈남','sdadsd@naver.com');";
                stmt1.executeUpdate(query);
                
                query= "insert into repair_list values('1','타이어','2019-11-25','4','2019-11-28','범퍼', '1111111', '1', '1', '342')," + 
                      "('2','라디에이터','2019-01-11','8','2019-01-14','배터리', '2235235', '2', '2', '342')," + 
                      "('3','클러치','2019-07-18','7','2019-07-21','운전대', '3235245', '3', '3', '45')," + 
                      "('4','기어','2019-09-08','9','2019-09-11','차축', '4353522', '4', '4', '655')," + 
                      "('5','소음기','2019-10-23','13','2019-10-26','추진축', '5356356', '5', '5', '956')," + 
                      "('6','브레이크','2019-06-25','5','2019-06-28','타이어', '6235565', '6', '6', '453')," + 
                      "('7','운전대','2020-01-04','18','2019-01-07','엑셀', '7245245', '7', '7', '457')," + 
                      "('8','라디에이터','2019-05-22','9','2019-05-25','배기관', '8567433', '8', '8', '97')," + 
                      "('9','발전기','2019-09-09','7','2019-09-12','배터리', '9467242', '9', '9', '78')," + 
                      "('10','보닛','2019-07-18','11','2019-07-21','클러치', '1023454', '10', '10', '57')," + 
                      "('11','기어','2019-05-05','13','2019-05-08','엑셀', '1124635', '11', '11', '89')," + 
                      "('12','트렁크','2019-02-23','12','2019-02-26','소음기', '1256735', '12', '12', '56')," + 
                      "('13','브레이크','2019-01-31','5','2019-02-03','냉각 펜', '1323554', '13', '13', '86')," + 
                      "('14','엔진','2019-05-30','16','2019-06-03','배기관', '1473565', '14', '14', '76')," + 
                      "('15','발전기','2019-12-05','13','2019-12-08','추진축', '1484534', '15', '15', '54');";
                 stmt1.executeUpdate(query);
                 
           query="insert into rentcar_list values('1','TIGERJK','23하 4534','8','SAMSUNG','1945','20000','30','2017-04-20','1')," + 
                     "('2','LIONG2','45호 3453','6','LG','1975','31000','30','2019-05-20','2')," + 
                     "('3','DOG46','33호 4245','8','HYUNDAI','2021','68000','20','2021-05-20','3')," + 
                     "('4','CAT32','36하 8553','7','BOOGATI','2020','99000','50','2020-04-25','4')," + 
                     "('5','PENCILMANIA','26하 4525','9','SHEVORAE','2020','300000','30','2020-01-20','5')," + 
                     "('6','CORANDO','25하 9626','10','PORCHE','1945','294033','32','2017-04-20','6')," + 
                     "('7','CAMPFILE','95하 4216','5','SYOUNG','2015','305924','54','2017-07-20','7')," + 
                     "('8','BOOMBA','73하 4791','8','HYUNDAI','2017','24924','43','2018-01-05','3')," + 
                     "('9','GARGARRI','743하 5274','8','samsung','2014','12353','65','2017-03-06','1')," + 
                     "('10','GOMAN','937하 5930','8','HYUNDAI','2015','402044','85','2017-03-10','3')," + 
                     "('11','GOODSCORE','793하 4362','5','HYUNDAI','2017','500','12','2017-07-19','3')," + 
                     "('12','DAMBOO','274하 6927','8','samsung','2012','7942','53','2017-04-13','1')," + 
                     "('13','EARLYBURN','407하 2423','4','KIA','2018','13414','84','2018-06-10','12')," + 
                     "('14','BESTY','501하 5035','8','KIA','2019','93423','894','2019-07-30','12')";

               stmt1.executeUpdate(query);
           /*query ="insert into customer_rent_old_list values"+
        		   +
                   "('17','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','16','30'),"+
                   "('18','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','16','30'),"+
                   "('19','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','16','30'),"+
                   "('20','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','16','30'),"+
                   "('21','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','16','30'),"+
                   "('22','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','16','30'),"+
                   "('23','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','16','30'),"+
                   "('24','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','16','30'),"+
                   "('25','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','16','30'),"+
                   "('26','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','16','30'),"+
                   "('27','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','16','30'),"+
                   "('28','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','16','30'),"+
                   "('29','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','16','30'),"+
                   "('30','2019-11-25','7','2019-11-22','바베큐그릴','4','15','9467242','16','30')";
           stmt1.executeUpdate(query);	    */           
         //  System.out.println("입력완료!");
        }catch(Exception Insert) {
           System.out.println("데이터 입력에 오류 발생!\n"+Insert);
        }
	}
}
