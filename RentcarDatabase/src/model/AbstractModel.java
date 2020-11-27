package model;

import controller.dataClass.ResultState;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/* 모델들의 공통 기능을 포함하는 슈퍼 클래스
 * 이후 새로운 모델을 만들 때마다 이 클래스를 상속하면 된다
 * 원리: 데이터 클래스들을 대신하는 제네릭 타입 활용
 * 원리 2: 쿼리문을 메서드화, 하위 클래스에서 구체화함
 * */
public abstract class AbstractModel<T> {

    final Connection connection = DatabaseConnector.getConnection();
    Statement statement;
    ResultSet resultSet;

    public ResultState create(T info) {
        if(isNullData(info)) { // 데이터 중 빈 값이 있으면
            return ResultState.NULL; // try 거치지 않고 바로 NULL 알려줌
        } else {
            return executeQuery(getCreateQuery(info));
        }
    }

    public ResultState update(T info) {
        if(isNullData(info)) { // 데이터 중 빈 값이 있으면
            return ResultState.NULL; // try 거치지 않고 바로 NULL 알려줌
        } else {
            return executeQuery(getUpdateQuery(info));
        }
    }

    public ResultState delete(String id) {
        return executeQuery(getDeleteQuery(id));
    }

    public ArrayList<T> readList() {
        return executeReadingQuery(getReadAllQuery());
    }

    /* 단일 대상 출력도 리스트에 넣는 형식으로 했음 (<- 중복 제거) */
    public ArrayList<T> read(String id) {
        return executeReadingQuery(getReadQuery(id));
    }

    ResultState executeQuery(String query) {
        /* Create & Update: 쿼리문 빼고 전부 같음
         * -> 같은 부분은 남겨놓고, 다른 부분만 빼놨음 */
        try {
            statement = connection.createStatement();
            int result = statement.executeUpdate(query);
            if (result > 0) {
                return ResultState.SUCCESS;
            }
        }catch(Exception e1) {
            e1.printStackTrace();
        }
        return ResultState.FAILURE;
    }

    /* 목록 출력하는 메서드의 추상형 */
    ArrayList<T> executeReadingQuery(String query) {
        ArrayList<T> infoList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                T info = toInfoFromResultSet(resultSet);
                infoList.add(info);
            }
        }catch(Exception e1) {
            e1.printStackTrace();
        }
        return infoList;
    }

    /* 쿼리문은 하위 클래스에 위임하여, 하위 클래스에서 구체화 */
    abstract String getCreateQuery(T info);

    abstract String getReadAllQuery();

    abstract String getReadQuery(String id);

    abstract String getUpdateQuery(T info);

    abstract String getDeleteQuery(String id);

    // 추상 클래스에선 데이터 클래스 참조 불가 -> 하위 클래스에 판단 넘김
    abstract boolean isNullData(T info);

    abstract T toInfoFromResultSet(ResultSet resultSet) throws Exception;
}
