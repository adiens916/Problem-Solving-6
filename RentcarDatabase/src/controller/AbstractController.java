package controller;

import model.dataClass.ResultStateDataClass;
import model.AbstractModel;
import view.AbstractView;

import javax.swing.*;
import java.util.ArrayList;

/* 데이터 관리 컨트롤러들의 상위 클래스
 * 하위 클래스들을 나타내기 위해 제네릭 타입 활용
 * */
public abstract class AbstractController
        <M extends AbstractModel<T>, V extends AbstractView<T>, T> {

    M model;
    V view;
    public JButton quit;

    public AbstractController() {
        setModelAndView();
        addListenerToRefreshInput();
        addListenerToCreate();
        addListenerToRead();
        addListenerToUpdate();
        addListenerToDelete();
        addListenerToQuit();
    }

    // AdminController에서 하위 페이지 띄울 때 쓰는 메서드 (<- view가 default라 접근 불가)
    public void setVisible(boolean value) {
        if (value) {
            readList();
        }
        view.setVisible(value);
    }

    abstract void setModelAndView();

    void readList() {
        ArrayList<T> infoList = model.readList(); // 모델로부터 전체 리스트 받아옴
        view.readList(infoList); // 뷰에서 목록 출력
    }

    void addListenerToRefreshInput() {
        // 뷰의 새로고침 버튼 동작 설정
        view.refreshButton.addActionListener(e -> { // 뷰의 새로고침 버튼에 리스너 추가
            readList(); // 현재 목록 출력
            view.refreshInput(); // 뷰의 입력란 초기화
        });
    }

    void addListenerToCreate() {
        view.createButton.addActionListener(e -> {
            T info = view.getInput(); // 뷰에서 입력 받아옴
            ResultStateDataClass result = model.create(info); // 입력을 모델에 보내고, 결과 받음
            view.showCreateResult(result); // 결과를 뷰에 보내서 출력
            readList(); // 바뀐 목록 출력
        });
    }

    void addListenerToRead() {
        view.readButton.addActionListener(e -> {
            String id = view.getId(); // 뷰에서 ID 입력 받아옴
            ArrayList<T> infoList = model.read(id); // 입력을 모델에 보내고, 해당 정보 가져옴
            view.readList(infoList); // 정보를 뷰에 보내서 출력
        });
    }

    void addListenerToUpdate() {
        view.updateButton.addActionListener(e -> {
            T info = view.getInput(); // 뷰에서 입력 가져옴 
            ResultStateDataClass result = model.update(info); // 입력을 모델에 보내고 결과 받음
            view.showUpdateResult(result); // 결과를 뷰에 보내서 출력
            readList(); // 바뀐 목록 출력
        });
    }

    void addListenerToDelete() {
        view.deleteButton.addActionListener(e -> {
            String id = view.getId();
            ResultStateDataClass result = model.delete(id);
            view.showDeleteResult(result);
            readList();
        });
    }

    void addListenerToQuit() {
        quit.addActionListener(e -> {
            setVisible(false);
            AdminController.getInstance().setVisible(true);
        });
    }
}
