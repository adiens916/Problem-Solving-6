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

    final Connection connection = DatabaseConnector.connection;
    Statement statement;
    ResultSet resultSet;

    private ResultState executeCreateOrUpdate(String query, T info) {
        /* Create과 Update는 쿼리문 빼고 전부 같음
         * -> 같은 부분은 남겨놓고, 다른 부분만 빼놨음 */
        if(isNullData(info)) { // 데이터 중 빈 값이 있으면
            return ResultState.NULL; // try 거치지 않고 바로 NULL 알려줌
        }
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

    public ResultState create(T info) {
        return executeCreateOrUpdate(getCreateQuery(info), info);
    }

    public ResultState update(T info) {
        return executeCreateOrUpdate(getUpdateQuery(info), info);
    }

    /* 전체 목록 출력하는 메서드의 추상형
     * 밑의 대상 하나 출력하는 메서드와 매우 비슷함
     * -> 밑의 메서드를 위 방식으로 흡수하기 */
    public ArrayList<T> readList() {
        ArrayList<T> infoList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(getReadAllQuery());

            while(resultSet.next()) {
                T info = toInfoFromResultSet(resultSet);
                infoList.add(info);
            }
        }catch(Exception e1) {
            e1.printStackTrace();
        }
        return infoList;
    }

    public T read(String id) {
        T info = createInfo();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(getReadQuery(id));

            if(resultSet.next()) {
                info = toInfoFromResultSet(resultSet);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return info;
    }

    public ResultState delete(String id) {
        try {
            statement = connection.createStatement();
            int result = statement.executeUpdate(getDeleteQuery(id));
            if (result > 0) {
                return ResultState.SUCCESS;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return ResultState.FAILURE;
    }

    /* 쿼리문은 하위 클래스에 위임하여, 하위 클래스에서 구체화 */
    abstract String getCreateQuery(T info);

    abstract String getReadAllQuery();

    abstract String getReadQuery(String id);

    abstract String getUpdateQuery(T info);

    abstract String getDeleteQuery(String id);

    // 추상 클래스에선 데이터 클래스 참조 불가 -> 하위 클래스에 판단 넘김
    abstract boolean isNullData(T info);

    /* 추상 클래스에선 빈 데이터 클래스를 생성할 수 없으므로, 하위 클래스에서 반환 */
    abstract T createInfo();

    abstract T toInfoFromResultSet(ResultSet resultSet) throws Exception;
}
