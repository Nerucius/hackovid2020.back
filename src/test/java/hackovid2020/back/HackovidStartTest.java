package hackovid2020.back;

import com.google.common.collect.Lists;
import hackovid2020.back.dao.User;
import hackovid2020.back.repository.UserRepository;
import hackovid2020.back.utils.MD5Util;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Ignore
public class HackovidStartTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void WeWantToCreateANewUserEntity() {
        // Arrange
        String name = "Peter";
        String surname = "Parker";
        String mail = name + surname + "@gmail.com";
        String password = "123456";
        String url = Constants.GRAVATAR + MD5Util.md5Hex(mail);

        // Act
        User user = User.createUser(name, surname, mail, password, url,
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
        String firstName = "Peter";
        String lastName = "Parker";
        String mail = firstName + lastName + "@gmail.com";
        String password = "123456";

        JSONObject content = new JSONObject();
        content.put("firstName", firstName);
        content.put("lastName", lastName);
        content.put("mail", mail);
        content.put("password", password);

        // Act
        JSONObject mvcResult = new JSONObject(mvc.perform(MockMvcRequestBuilders.post("/api/user")
                .content(content.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString());

        // Assert
        assertThat(mvcResult.getString("firstName"), is(firstName));
        assertThat(mvcResult.getString("lastName"), is(lastName));
        assertThat(mvcResult.getString("mail"), is(mail));
        assertThat(mvcResult.has("password"), is(false));
        assertThat(mvcResult.has("token"), is(true));
    }

    @Test
    public void loginTest() throws Exception {
        // Arrange
        String firstName = "Peter";
        String lastName = "Parker";
        String mail = firstName + lastName + "@gmail.com";
        String password = "123456";

        JSONObject content = new JSONObject();
        content.put("mail", mail);
        content.put("password", password);

        // Act
        ResultActions perform = mvc.perform(MockMvcRequestBuilders.post("/api/user/login")
                .content(content.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        MvcResult mvcResult1 = perform
                .andReturn();
        MockHttpServletResponse response = mvcResult1.getResponse();
        assertThat(response.getStatus(), is(200));
        JSONObject mvcResult = new JSONObject(response.getContentAsString());

        assertThat(mvcResult.getString("mail"), is(mail));
        assertThat(mvcResult.has("token"), is(true));
    }

}
