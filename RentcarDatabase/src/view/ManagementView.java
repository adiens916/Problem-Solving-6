package view;

import controller.dataClass.ResultState;

import javax.swing.*;
import java.util.ArrayList;

/* 뷰의 공통 기능들을 포함하는 슈퍼 클래스
 * 데이터 클래스들을 제네릭 타입으로 전부 표현 가능함
 * 주로 뭔가를 가져오는 부분은 하위 클래스에서 구현하도록 함 (버튼 때문에 여기선 힘듦)
 * 대신 뭔가를 출력하는 부분은 전부 여기서 구현했음
 * */

public abstract class ManagementView<T> extends JFrame {
    /* 퍼블릭으로 선언: 컨트롤러에서 각 버튼을 참조해 리스너를 추가해야 하므로 */
    public JButton btn_Search;
    public JButton btn_Insert;
    public JButton btn_Edit;
    public JButton btn_Refresh;
    public JButton btn_Delete;
    public JButton quit;
    final JTextArea selectcp = new JTextArea();

    ManagementView() {
        // 뷰 생성과 동시에 뷰에 GUI 컴포넌트들 부착
        addComponent();
    }

    public abstract void refreshInput();

    public abstract String getId();

    public abstract T getInput();

    abstract String getColumnList(); // 테이블 열을 나타내는 스트링

    abstract String toStringFromInfo(T info);

    abstract void addComponent(); // UI 컴포넌트들은 하위 클래스에서 만들어 부착

    public void readList(ArrayList<T> infoList) {
        selectcp.setText(getColumnList());
        for (T info : infoList) {
            selectcp.append(toStringFromInfo(info));
        }
    }

    public void read(T info) {
        selectcp.setText(getColumnList());
        selectcp.append(toStringFromInfo(info));
    }

    public void showCreateResult(ResultState result) {
        if (result == ResultState.SUCCESS) {
            JOptionPane.showMessageDialog(null, "입력완료!");
        } else if (result == ResultState.NULL) {
            JOptionPane.showMessageDialog(null, "빈칸을 모두채워주세요");
        } else {
            JOptionPane.showMessageDialog(null, "다시입력해주세요!");
        }
    }

    public void showUpdateResult(ResultState result) {
        if (result == ResultState.SUCCESS) {
            JOptionPane.showMessageDialog(null, "수정완료");
        } else if (result == ResultState.NULL) {
            JOptionPane.showMessageDialog(null, "빈칸을 모두채워주세요");
        } else {
            JOptionPane.showMessageDialog(null, "다시입력하세요!");
        }
    }

    public void showDeleteResult(ResultState result) {
        if (result == ResultState.SUCCESS) {
            JOptionPane.showMessageDialog(null, "삭제 완료");
        } else {
            JOptionPane.showMessageDialog(null, "ID를 입력해주세요.");
        }
    }
}
