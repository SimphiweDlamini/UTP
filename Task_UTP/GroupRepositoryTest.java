
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class GroupRepositoryTest extends RepositoryTestBase<GroupDTO, IGroupRepository> {

	private IGroupRepository groupRepository;

	@Test
	public void add() {

		IGroupRepository groupRepository = getRepository();
		GroupDTO group1 = new GroupDTO(3, "Group1", "Johnny");
		groupRepository.add(group1);
		int count = groupRepository.getCount();

		GroupDTO group = new GroupDTO(4, "Group2", "Black");
		groupRepository.add(group);
		int expectedCount = count +1;
		int actualCount = groupRepository.getCount();

		Assert.assertEquals(expectedCount, actualCount);
	}

	@Test
	public void update() {

		IGroupRepository groupRepository = getRepository();

		GroupDTO groupBefore = new GroupDTO(21, "before" , "Whoopie");
		groupRepository.add(groupBefore);
		String before = groupRepository.findById(21).getName();

		GroupDTO groupAfter = new GroupDTO(21, "after", "Goldberg");
		groupRepository.update(groupAfter);

		String after = groupRepository.findById(21).getName();

		Assert.assertNotEquals(before, after);
	}
	
	@Test
	public void addOrUpdate() {

		IGroupRepository groupRepository = getRepository();

		int counterBeforeAdd = groupRepository.getCount();

		GroupDTO group1 = new GroupDTO(1, "Chem", "before");
		GroupDTO group2 = new GroupDTO(1, "Chem", "after");

		groupRepository.addOrUpdate(group1);

		int counterAfterAdd = groupRepository.getCount();
		Assert.assertEquals(counterBeforeAdd, counterAfterAdd - 1 );

		groupRepository.addOrUpdate(group2);

		GroupDTO groupFounded = getRepository().findById(1);
		String test = groupFounded.getDescription();
		counterAfterAdd = groupRepository.getCount();

		Assert.assertEquals(counterBeforeAdd, counterAfterAdd - 1 );
		Assert.assertEquals("after", test);
	}

	@Test
	public void delete() {


		IGroupRepository groupRepository = getRepository();
		GroupDTO group1 = new GroupDTO(29, "Groupf", "JOHNCENA");
		groupRepository.add(group1);

		int before = groupRepository.getCount();
		groupRepository.delete(group1);

		int after  = groupRepository.getCount() + 1;

		Assert.assertEquals(before, after);
	}

	@Test
	public void findById() {


		IGroupRepository groupRepository = getRepository();
		int idToFind = 10;
		GroupDTO groupNew = new GroupDTO(idToFind, "Groupg", "BradPitt");
		groupRepository.add(groupNew);

		GroupDTO test = groupRepository.findById(idToFind);

		Assert.assertEquals(groupNew.getId(), test.getId());
		Assert.assertEquals(groupNew.getName(), test.getName());
		Assert.assertEquals(groupNew.getDescription(), test.getDescription());

	}
	
	@Test
	public void findByName() {


		IGroupRepository groupRepository = getRepository();
		String group = "Group";
		GroupDTO groupNew1 = new GroupDTO(28, group, "gdescription");
		GroupDTO groupNew2 = new GroupDTO(29, group, "gdescription");
		groupRepository.add(groupNew1);
		groupRepository.add(groupNew2);
		List<GroupDTO> newGroupsList = new ArrayList<>();
		newGroupsList.add(groupNew1);
		newGroupsList.add(groupNew2);

		List<GroupDTO> listGroupsFounded =  groupRepository.findByName(group);


		Assert.assertEquals(newGroupsList.toString(),listGroupsFounded.toString());
	}

	protected IGroupRepository getRepositoryFromContextClass(){
		 groupRepository = getContext().getGroupRepository();
		return groupRepository;
	}

	@Override
	protected IGroupRepository Create() {
		return null;
	}
}