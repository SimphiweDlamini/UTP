

import java.util.List;

public interface IUserRepository extends IRepository<UserDTO> {

	List<UserDTO> findByName(String username);

	void addToGroup(UserDTO dto, int group_id);
}