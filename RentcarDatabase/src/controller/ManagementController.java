package controller;

import controller.dataClass.ResultState;
import model.ManagementModel;
import view.ManagementView;

import javax.swing.*;
import java.util.ArrayList;

/* 데이터 관리 컨트롤러들의 상위 클래스
 * 하위 클래스들을 나타내기 위해 제네릭 타입 활용
 * */
public abstract class ManagementController
        <M extends ManagementModel<T>, V extends ManagementView<T>, T> {

    M model;
    V view;
    public JButton quit;

    public void setVisible(boolean value) {
        view.setVisible(value);
    }

    void readList() {
        ArrayList<T> infoList = model.readList(); // 모델로부터 전체 리스트 받아옴
        view.readList(infoList); // 뷰에서 리스트 출력
    }

    void listenToRefreshInput() {
        view.btn_Refresh.addActionListener(e -> { // 뷰의 새로고침 버튼에 리스너 추가
            readList(); // 목록 새로고침
            view.refreshInput(); // 뷰의 입력란 초기화
        });
    }

    void listenToCreate() {
        view.btn_Insert.addActionListener(e -> {
            T info = view.getInput(); // 뷰에서 입력 받아옴
            ResultState result = model.create(info); // 모델에 입력 보내고, 결과 받음
            view.showCreateResult(result); // 뷰에서 결과 출력
            readList(); // 목록 새로고침
        });
    }

    void listenToRead() {
        view.btn_Search.addActionListener(e -> {
            String id = view.getId(); // 뷰에서 ID 입력 받아옴
            T info = model.read(id); // 모델에 입력 보내고, 해당 정보 가져옴
            view.read(info); // 뷰에 정보 보내서 출력
        });
    }

    void listenToUpdate() {
        view.btn_Edit.addActionListener(e -> {
            T info = view.getInput();
            ResultState result = model.update(info);
            view.showUpdateResult(result);
            readList();
        });
    }

    void listenToDelete() {
        view.btn_Delete.addActionListener(e -> {
            String id = view.getId();
            ResultState result = model.delete(id);
            view.showDeleteResult(result);
            readList();
        });
    }
}
