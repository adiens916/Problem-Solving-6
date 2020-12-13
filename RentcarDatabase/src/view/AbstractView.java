package view;

import model.dataClass.ResultStateDataClass;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.util.ArrayList;

/* 뷰의 공통 기능들을 포함하는 슈퍼 클래스
 * 데이터 클래스들을 제네릭 타입으로 전부 표현 가능함
 * 주로 뭔가를 가져오는 부분은 하위 클래스에서 구현하도록 함 (버튼 때문에 여기선 힘듦)
 * 대신 뭔가를 출력하는 부분은 전부 여기서 구현했음
 * */

public abstract class AbstractView<T> extends JFrame {
    /* public으로 선언 (<- 컨트롤러에서 각 버튼을 참조해 리스너를 추가해야 하므로) */
    public JButton refreshButton;
    public JButton createButton;
    public JButton readButton;
    public JButton updateButton;
    public JButton deleteButton;
    public JButton quit;
    final JTextArea textArea = new JTextArea();

    AbstractView() {
        // 뷰 생성과 동시에 뷰에 GUI 컴포넌트들 부착
        addComponent();
    }

    public abstract void refreshInput();

    public abstract T getInput();

    public abstract String getId();

    abstract String getColumnList(); // 테이블 열을 나타내는 스트링

    abstract String toStringFromInfo(T info);

    abstract void addComponent(); // UI 컴포넌트들은 하위 클래스에서 만들어 부착

    public void readList(ArrayList<T> infoList) {
        textArea.setText(getColumnList());
        for (T info : infoList) {
            textArea.append(toStringFromInfo(info));
        }
    }

    public void showCreateResult(ResultStateDataClass result) {
        if (result == ResultStateDataClass.SUCCESS) {
            JOptionPane.showMessageDialog(null, "입력 완료");
        } else if (result == ResultStateDataClass.NULL) {
            JOptionPane.showMessageDialog(null, "모든 입력 칸을 채워주세요");
        } else {
            JOptionPane.showMessageDialog(null, "입력 실패");
        }
    }

    public void showUpdateResult(ResultStateDataClass result) {
        if (result == ResultStateDataClass.SUCCESS) {
            JOptionPane.showMessageDialog(null, "수정 완료");
        } else if (result == ResultStateDataClass.NULL) {
            JOptionPane.showMessageDialog(null, "모든 입력 칸을 채워주세요");
        } else {
            JOptionPane.showMessageDialog(null, "수정 실패");
        }
    }

    public void showDeleteResult(ResultStateDataClass result) {
        if (result == ResultStateDataClass.SUCCESS) {
            JOptionPane.showMessageDialog(null, "삭제 완료");
        } else if (result == ResultStateDataClass.NULL) {
            JOptionPane.showMessageDialog(null, "ID를 입력해주세요.");
        } else {
            JOptionPane.showMessageDialog(null, "삭제 실패");
        }
    }
}
