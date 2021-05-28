
import org.junit.Assert;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;


public class UserRepositoryTest extends RepositoryTestBase<UserDTO, IUserRepository> {

	private IUserRepository userRepository;
	@Test
	public void add() {


		IUserRepository userRepository = getRepository();
		UserDTO user1 = new UserDTO( 12, "user", "password");
		userRepository.add(user1);
		int count = userRepository.getCount();

		UserDTO user2 = new UserDTO( 13, "user", "password");
		userRepository.add(user2);
		int expectedCount = count+1 ;
		int actualCount = userRepository.getCount();
		Assert.assertEquals(expectedCount, actualCount);
	}

	@Test
	public void update() {
		IUserRepository userRepository = getRepository();
		String passBefore = "passBEFOREUpdate";
		UserDTO userDTOBefore = new UserDTO(14, "user" , passBefore);
		userRepository.add(userDTOBefore);
		List<UserDTO> beforeUpdate = userRepository.findByName("user");

		String passAfter = "passAFTERUpdate";
		UserDTO userAfter = new UserDTO(14, "user", passAfter);
		userRepository.update(userAfter);
		List<UserDTO> afrerUpdate = userRepository.findByName("user");

		Assert.assertNotEquals(beforeUpdate.toString(),afrerUpdate.toString());
	}
	
	@Test
	public void addOrUpdate() {

		IUserRepository userRepository = getRepository();

		int counterBeforeAdd = userRepository.getCount();

		UserDTO user1 = new UserDTO(1, "user", "before");
		UserDTO user2 = new UserDTO(1, "user", "after");

		userRepository.addOrUpdate(user1);

		int counterAfterAdd = userRepository.getCount();

		Assert.assertEquals(counterBeforeAdd, counterAfterAdd - 1 );

		userRepository.addOrUpdate(user2);

		ArrayList<UserDTO> usersList = (ArrayList<UserDTO>) userRepository.findByName(user1.getLogin());
		UserDTO userFounded = usersList.get(0);

		String passwordAfter = userFounded.getPassword();
		counterAfterAdd = userRepository.getCount();

		Assert.assertEquals(counterBeforeAdd, counterAfterAdd - 1 );
		Assert.assertEquals("after", passwordAfter);
	}

	@Test
	public void delete() {

		IUserRepository userRepository = getRepository();
		UserDTO user15 = new UserDTO(15, "user", "pass");
		userRepository.add(user15);

		int before = userRepository.getCount();
		userRepository.delete(user15);

		int after  = userRepository.getCount()+1;

		Assert.assertEquals(before,after);
	}

	@Test
	public void findById() {
	}
	
	@Test
	public void findByName() {
		IUserRepository userRepository = getRepository();
		String name = "user16";
		UserDTO userNew1 = new UserDTO(16, name, "pass");
		userRepository.add(userNew1);

		List<UserDTO> newUsersList = new ArrayList<>();
		newUsersList.add(userNew1);

		List<UserDTO> listUsersFounded =  userRepository.findByName(name);

		Assert.assertEquals(newUsersList.toString(),listUsersFounded.toString());
	}

	/*@Test
	public void addUserToGroup(){
		IUserRepository userRepository = getRepository();
		UserDTO user = new UserDTO(9, "user9", "password");
		userRepository.addToGroup(user, 24);
	}*/

	protected IUserRepository getRepositoryFromContextClass() {
		 userRepository = getContext().getUserRepository();
		return userRepository;
	}

	@Override
	protected IUserRepository Create() {
		return null;
	}
}