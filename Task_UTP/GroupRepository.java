

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupRepository extends RepositoryBase<GroupDTO> implements IGroupRepository {

    public GroupRepository(ContextClass context){ super(context); }

    @Override
    public List<GroupDTO> findByName(String name) {
        ResultSet resultSet = null;
        GroupDTO group = new GroupDTO();
        List<GroupDTO> groups = new ArrayList<GroupDTO>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT groupid, groupname, groupdescription FROM groups WHERE GROUPNAME LIKE ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int group_id = resultSet.getInt(1);
                String group_name = resultSet.getString(2);
                String group_description = resultSet.getString(3);
                group = new GroupDTO(group_id, group_name, group_description);
                groups.add(group);
            }
            return groups;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void add(GroupDTO dto) {
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO GROUPS (GROUPID, GROUPNAME, GROUPDESCRIPTION) VALUES ( ? , ?, ?)");
            preparedStatement.setInt(1, dto.getId());
            preparedStatement.setString(2, dto.getName());
            preparedStatement.setString(3, dto.getDescription());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void update(GroupDTO dto) {
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE GROUPS SET " +
                    "GROUPNAME = ?, GROUPDESCRIPTION = ? WHERE GROUPID = ?");
            preparedStatement.setString(1, dto.getName());
            preparedStatement.setString(2, dto.getDescription());
            preparedStatement.setInt(3, dto.getId());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void addOrUpdate(GroupDTO dto) {
        try {
            int idGroup = dto.getId();
            GroupDTO groupFounded = findById(idGroup);
            if(groupFounded.getId() == 0 && groupFounded.getName() == null){
                System.out.println("New Group will be added");
                add(dto);
            }
            else {
                System.out.println("Group will be updated");
                update(dto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(GroupDTO dto) {
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM GROUPS WHERE GROUPID = ? ");
            preparedStatement.setInt(1, dto.getId());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public GroupDTO findById(int id) {
        ResultSet resultSet = null;
        GroupDTO group = new GroupDTO();

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT GROUPID, GROUPNAME, GROUPDESCRIPTION FROM GROUPS WHERE GROUPID =?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                int group_id = resultSet.getInt(1);
                String group_name = resultSet.getString(2);
                String group_description = resultSet.getString(3);
                group = new GroupDTO(group_id, group_name, group_description);
                }
            return group;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getCount() {
        int counter = 0;
        try{
            Connection connection = getConnection();
            Statement s = connection.createStatement();
            ResultSet resultSet = s.executeQuery("SELECT COUNT(*) AS ROWCOUNT FROM GROUPS");
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
