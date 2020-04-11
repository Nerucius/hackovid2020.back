package hackovid2020.back;

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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class HackovidStartTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void WeWantToCreateANewUserEntity() {
        final String firstName = "Peter";
        final String lastName = "Parker";
        final String mail = "peterparker@gmail.com";
        final String password = "123456";
        final String url = Constants.GRAVATAR + MD5Util.md5Hex(mail);
        // Act
        User user = User.createUser(firstName, lastName, mail, password, url,
                Calendar.getInstance().getTime(), Calendar.getInstance().getTime());

        // Assert
        Assert.isTrue(user.getFirstName().equals(firstName), "Wrong mapping of the FirstName");
        Assert.isTrue(user.getLastName().equals(lastName), "Wrong mapping of the LastName");
        Assert.isTrue(user.getMail().equals(mail), "Wrong mapping of the Mail");
        Assert.isTrue(user.getPassword().equals(password), "Wrong mapping of the Password");
    }

    @Test
    public void WeWantToCreatAUserWithRequest() throws Exception {
        User peterParkerUser = UserMother.createPeterParkerUser();
        JSONObject content = new JSONObject();
        content.put("firstName", peterParkerUser.getFirstName());
        content.put("lastName", peterParkerUser.getLastName());
        content.put("mail", peterParkerUser.getMail());
        content.put("password", peterParkerUser.getPassword());

        // Act
        MockHttpServletResponse response = sendRequest(content, MockMvcRequestBuilders.post("/api/user"));
        assertThat(response.getStatus(), is(200));
        JSONObject mvcResult = new JSONObject(response
                .getContentAsString());

        // Assert
        assertThat(mvcResult.getString("firstName"), is(peterParkerUser.getFirstName()));
        assertThat(mvcResult.getString("lastName"), is(peterParkerUser.getLastName()));
        assertThat(mvcResult.getString("mail"), is(peterParkerUser.getMail()));
        assertThat(mvcResult.has("password"), is(false));
        assertThat(mvcResult.getString("token"), not(""));
    }

    @Test
    public void loginTest() throws Exception {
        User peterParkerUser = UserMother.createPeterParkerUser();
        userRepository.saveAndFlush(
                peterParkerUser
        );

        JSONObject content = new JSONObject();
        content.put("mail", peterParkerUser.getMail());
        content.put("password", peterParkerUser.getPassword());

        // Act
        MockHttpServletResponse response = sendRequest(content, MockMvcRequestBuilders.post("/api/user/login"));
        assertThat(response.getStatus(), is(200));
        JSONObject mvcResult = new JSONObject(response.getContentAsString());

        assertThat(mvcResult.getString("mail"), is(peterParkerUser.getMail()));
        assertThat(mvcResult.getString("token"), not(""));
    }

    @Test
    public void wrongLoginTest() throws Exception {
        User peterParkerUser = UserMother.createPeterParkerUser();
        userRepository.saveAndFlush(
                peterParkerUser
        );

        JSONObject content = new JSONObject();
        content.put("mail", peterParkerUser.getMail());
        content.put("password", "WRONG_PASSWORD");

        // Act
        MockHttpServletResponse response = sendRequest(content, MockMvcRequestBuilders.post("/api/user/login"));
        assertThat(response.getStatus(), is(403));
    }

    private MockHttpServletResponse sendRequest(JSONObject content, MockHttpServletRequestBuilder builder) throws Exception {
        return mvc.perform(builder
                .content(content.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
    }

}
