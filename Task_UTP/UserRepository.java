
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends RepositoryBase<UserDTO> implements IUserRepository {

    public UserRepository(ContextClass context){
        super(context);
    }

    @Override
    public List<UserDTO> findByName(String username) {
        ResultSet resultSet = null;
        UserDTO user = new UserDTO();
        ArrayList<UserDTO> usersList = new ArrayList<UserDTO>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT USERID,USER_LOGIN, USER_PASSWORD FROM USERS WHERE USER_LOGIN LIKE ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int userid = resultSet.getInt(1);
                String user_login = resultSet.getString(2);
                String user_password = resultSet.getString(3);
                user = new UserDTO(userid,user_login, user_password);
                usersList.add(user);
            }
            return usersList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void add(UserDTO dto) {
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (userid, user_login, user_password) VALUES (?, ? ,?)");
            preparedStatement.setInt(1,dto.getId());
            preparedStatement.setString(2, dto.getLogin());
            preparedStatement.setString(3, dto.getPassword());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void addToGroup(UserDTO dto, int group_id) {
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO GROUPS_USERS (gu_id,USERID, GROUPID) VALUES (?, ? , ?)");
            preparedStatement.setInt(1,dto.getId()*2);
            preparedStatement.setInt(2, dto.getId());
            preparedStatement.setInt(3, group_id);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(UserDTO dto) {
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE USERS SET " +
                    "USERID = ?,USER_LOGIN = ?, USER_PASSWORD = ? WHERE USER_LOGIN = ?");
            preparedStatement.setInt(1,dto.getId());
            preparedStatement.setString(2, dto.getLogin());
            preparedStatement.setString(3, dto.getPassword());
            preparedStatement.setString(4, dto.getLogin());
            preparedStatement.execute();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void addOrUpdate(UserDTO dto) {
        try {

            List<UserDTO> usersList =  findByName(dto.getLogin());
            if(!usersList.toString().contains(dto.getLogin())){
                System.out.println("New User will be added");
                add(dto);
            }
            else {
                System.out.println("User Updated");
                update(dto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(UserDTO dto) {
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM USERS WHERE USER_LOGIN = ?");
            preparedStatement.setString(1, dto.getLogin());
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public int getCount() {
        int counter = 0;
        try{
            Connection connection = getConnection();
            Statement s = connection.createStatement();
            ResultSet resultSet = s.executeQuery("SELECT COUNT(*) AS ROWCOUNT FROM USERS");
            resultSet.next();
            counter = resultSet.getInt("ROWCOUNT");
            resultSet.close();
            return counter;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

}
