package cpm.revature.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.dao.ReimbursementImpl;
import com.revature.dao.UserImpl;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.service.FinanceManagerService;

public class ManagerServiceTest {

	private FinanceManagerService mserv;
	private UserImpl mockdao;
	private ReimbursementImpl rmockdao;
	
	@Before
	public void setup() {
		mockdao = mock(UserImpl.class);
		rmockdao = mock(ReimbursementImpl.class);
		mserv = new FinanceManagerService(mockdao, rmockdao);
	}
	
	@After
	public void teardown() {
		mockdao = null;
		rmockdao = null;
		mserv = null;
	}
	
	@Test
	public void testConfirmLogin_success() {
		UserRole role = new UserRole(1, "Employee");
		User u1 = new User(5, "kt", "money", "kyle", "t", "email@yahoo", role);
		
		when(mockdao.findUserByUsername("kt")).thenReturn(u1);
		User actual = mserv.confirmLogin("kt", "money");
		User expected = u1;
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testConfirmLogin_fail() {
		UserRole role = new UserRole(1, "Employee");
		User u1 = new User(5, "kt", "money", "kyle", "t", "email@yahoo", role);
		
		when(mockdao.findUserByUsername("kt")).thenReturn(u1);
		User actual = mserv.confirmLogin("kt", "monii");
		User expected = new User();
		assertEquals(expected, actual);
	}
	
	@Test
	public void findReimbursementById() {
		ReimbursementType type = new ReimbursementType(1, "lodging");
		ReimbursementStatus status = new ReimbursementStatus(1, "pending");
		UserRole role = new UserRole(1, "Employee");
		User empl = new User(5, "kt", "money", "kyle", "t", "email@yahoo", role);
		Reimbursement reim = new Reimbursement(2, type, status, 220.52, null, null, "Testing", empl, null);
		
		when(rmockdao.findReimbursementById(2)).thenReturn(reim);
		Reimbursement actual = mserv.findReimbursementById(2);
		Reimbursement expected = reim;
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void denyReimbursement() {
		ReimbursementType type = new ReimbursementType(1, "lodging");
		ReimbursementStatus status = new ReimbursementStatus(1, "pending");
		UserRole role = new UserRole(1, "Employee");
		User man = new User(1, "manager", "test", "guy", "girl", "email", new UserRole(2, "Manager"));
		User empl = new User(5, "kt", "money", "kyle", "t", "email@yahoo", role);
		Reimbursement reim = new Reimbursement(2, type, status, 220.52, null, null, "Testing", empl, null);
		
		when(rmockdao.update(reim)).thenReturn(true);
		boolean actual = mserv.denyReimbursement(reim, man);
		boolean expected = true;
		assertEquals(expected, actual);
	}
}
