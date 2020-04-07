package hacovid2020.back.dao;

import java.util.Calendar;

import org.junit.Test;
import org.springframework.util.Assert;

import hackovid2020.back.dao.User;

public class UserTest {
	
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

}
