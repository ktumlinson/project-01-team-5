package cpm.revature.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
import com.revature.service.EmployeeServices;

public class EmployeeServiceTests {

	private EmployeeServices eserv;
	private UserImpl mockdao;
	private ReimbursementImpl rmockdao;
	
	@Before
	public void setup() {
		mockdao = mock(UserImpl.class);
		rmockdao = mock(ReimbursementImpl.class);
		eserv = new EmployeeServices(mockdao, rmockdao);
	}
	
	@After
	public void teardown() {
		mockdao = null;
		rmockdao = null;
		eserv = null;
	}
	
	@Test
	public void newReimbursement() {
		ReimbursementType type = new ReimbursementType(1, "lodging");
		ReimbursementStatus status = new ReimbursementStatus(1, "pending");
		UserRole role = new UserRole(1, "Employee");
		User empl = new User(5, "kt", "money", "kyle", "t", "email@yahoo", role);
		Reimbursement reim = new Reimbursement(2, type, status, 220.52, null, null, "Testing", empl, null);
		when(rmockdao.insert(reim)).thenReturn(2);
		
		int actual = eserv.newReimbursementRequest(reim);
		int expected = 2;
		assertEquals(expected, actual);
	}
	
	@Test
	public void allRequests() {
		ReimbursementType type = new ReimbursementType(1, "lodging");
		ReimbursementStatus status = new ReimbursementStatus(1, "pending");
		UserRole role = new UserRole(1, "Employee");
		User empl = new User(5, "kt", "money", "kyle", "t", "email@yahoo", role);
		Reimbursement reim = new Reimbursement(2, type, status, 220.52, null, null, "Testing", empl, null);
		Reimbursement reim1 = new Reimbursement(3, new ReimbursementType(2, "food"), status, 25, null, null, "Testing", empl, null);
		List<Reimbursement> reims = new ArrayList<Reimbursement>();
		reims.add(reim1);
		reims.add(reim);
		
		when(rmockdao.findAllReimbersements()).thenReturn(reims);
		List<Reimbursement> expected = reims;
		List<Reimbursement> actual = eserv.allRequests(empl);
		
		assertEquals(expected, actual);
	}
}
