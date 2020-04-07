package hacovid2020.back.dao;

import java.util.Calendar;

import javax.net.ssl.SSLEngineResult.Status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import hackovid2020.back.dao.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
@Transactional
@Commit
public class UserTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void WeWantToCreateANewUserEntity() {
		// Arrange
		String name = "Peter";
		String surname = "Parker";
		String mail = name + surname + "@gmail.com";
		String password = "123456";
		
		// Act
		User user = User.createUser(name, surname, mail, password,
				Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
		
		// Assert
		Assert.isTrue(user.getFirstName().equals(name), "Wrong mapping of the FirstName");
		Assert.isTrue(user.getLastName().equals(surname), "Wrong mapping of the LastName");
		Assert.isTrue(user.getMail().equals(mail), "Wrong mapping of the Mail");
		Assert.isTrue(user.getPassword().equals(password), "Wrong mapping of the Password");
	}
	
	@Test
	public void WeWantToCreatAUserWithRequest() throws Exception {
		// Arrange
		String name = "Peter";
		String surname = "Parker";
		String mail = name + surname + "@gmail.com";
		String password = "123456";
		String content = "{\"firstName\":" + name + " , \"lastName\":" + surname + ", \"mail\":" + mail +
				", \"password\":" + password + "}";
		
		// Act
		mvc.perform(MockMvcRequestBuilders.post("/api/user")
				.content(content)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		// Assert
		System.out.println("");
	}

}
