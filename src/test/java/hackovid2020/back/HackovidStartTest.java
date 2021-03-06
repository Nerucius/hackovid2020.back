package hackovid2020.back;

import hackovid2020.back.dao.Category;
import hackovid2020.back.dao.File;
import hackovid2020.back.dao.Location;
import hackovid2020.back.dao.Shop;
import hackovid2020.back.dao.User;
import hackovid2020.back.dao.support.EventType;
import hackovid2020.back.repository.CategoryRepository;
import hackovid2020.back.repository.FileRepository;
import hackovid2020.back.repository.LocationRepository;
import hackovid2020.back.repository.ShopRepository;
import hackovid2020.back.repository.UserRepository;
import hackovid2020.back.utils.MD5Util;
import org.json.JSONArray;
import org.json.JSONException;
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
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
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

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private ShopRepository shopRepository;
    
    @Autowired
    private LocationRepository locationRepository;

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
    public void testCors() throws Exception {
        MockHttpServletResponse response = sendRequest(new JSONObject(),
                MockMvcRequestBuilders
                        .options("/api/user")
                        .header("Origin", "http://localhost:8080")
        );
        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void testOptionsRequest() throws Exception {
        MockHttpServletResponse response = sendRequest(new JSONObject(),
                MockMvcRequestBuilders
                        .options("/api/user")
        );
        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void testHeadRequest() throws Exception {
        MockHttpServletResponse response = sendRequest(new JSONObject(),
                MockMvcRequestBuilders
                        .head("/api/user")
        );
        assertThat(response.getStatus(), is(200));
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
    public void getUserTest() throws Exception {
        User peterParkerUser = UserMother.createPeterParkerUser();
        userRepository.saveAndFlush(
                peterParkerUser
        );

        JSONObject content = new JSONObject();
        content.put("firstName", peterParkerUser.getFirstName());
        content.put("lastName", peterParkerUser.getLastName());
        content.put("mail", peterParkerUser.getMail());
        content.put("password", peterParkerUser.getPassword());

        // Act
        MockHttpServletResponse response = sendRequest(new JSONObject(),
                MockMvcRequestBuilders.get("/api/user")
                        .queryParam("id", peterParkerUser.getUserId().toString())
        );
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
    public void userWithSameEmailTest() throws Exception {
        User peterParkerUser = UserMother.createPeterParkerUser();
        userRepository.saveAndFlush(
                peterParkerUser
        );

        JSONObject content = new JSONObject();
        content.put("firstName", peterParkerUser.getFirstName());
        content.put("lastName", peterParkerUser.getLastName());
        content.put("mail", peterParkerUser.getMail());
        content.put("password", peterParkerUser.getPassword());

        // Act
        MockHttpServletResponse response = sendRequest(content, MockMvcRequestBuilders.post("/api/user"));
        assertThat(response.getStatus(), is(400));
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

        // Assert
        assertThat(mvcResult.getString("firstName"), is(peterParkerUser.getFirstName()));
        assertThat(mvcResult.getString("lastName"), is(peterParkerUser.getLastName()));
        assertThat(mvcResult.getString("mail"), is(peterParkerUser.getMail()));
        assertThat(mvcResult.has("password"), is(false));
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

    @Test
    public void createShopTest() throws Exception {
        User peterParkerUser = UserMother.createPeterParkerUser();
        userRepository.saveAndFlush(peterParkerUser);

        List<File> files = FileMother.createRandomFiles(3);
        fileRepository.saveAll(files);
        fileRepository.flush();

        List<Category> categories = CategoryMother.createRandomCategories(3);
        categoryRepository.saveAll(categories);
        categoryRepository.flush();

        JSONObject content = new JSONObject();
        content.put("ownerId", peterParkerUser.getUserId());
        File coverImage = files.get(0);
        content.put("coverImage", coverImage.getFileId());
        content.put("latitude", 20f);
        content.put("longitude", 20f);
        content.put("streetname", "Aribau");
        content.put("shopCategoryIds", new JSONArray(categories.stream().map(Category::getCategoryId).collect(Collectors.toList())));
        content.put("shopImageIds", new JSONArray(files.stream().map(File::getFileId).toArray()));

        // Act
        MockHttpServletResponse response = sendRequest(content, MockMvcRequestBuilders.post("/api/shop/"));
        assertThat(response.getStatus(), is(200));

        JSONObject mvcResult = new JSONObject(response.getContentAsString());

        assertThat(mvcResult.has("shopId"), not(false));
        JSONObject coverImageJSON = mvcResult.getJSONObject("coverImage");
        assertThat(coverImageJSON.getLong("fileId"), comparesEqualTo(coverImage.getFileId()));
        assertThat(coverImageJSON.getString("name"), is(coverImage.getName()));
        assertThat(coverImageJSON.getString("fileType"), is(coverImage.getFileType().name()));
        assertThat(coverImageJSON.getString("url"), is(coverImage.getUrl()));

        JSONObject userDetailsResponse = mvcResult.getJSONObject("userDetailsResponse");
        assertThat(userDetailsResponse.getString("firstName"), is(peterParkerUser.getFirstName()));
        assertThat(userDetailsResponse.getString("lastName"), is(peterParkerUser.getLastName()));
        assertThat(userDetailsResponse.getString("mail"), is(peterParkerUser.getMail()));
        assertThat(userDetailsResponse.has("password"), is(false));
        assertThat(userDetailsResponse.getString("token"), not(""));

        assertThat(mvcResult.has("createdAt"), not(false));
        assertThat(mvcResult.has("modifiedAt"), not(false));

        JSONArray shopImages = mvcResult.getJSONArray("shopImages");
        assertThat(shopImages.length(), is(files.size())); // TODO test better pls

        JSONArray shopCategories = mvcResult.getJSONArray("shopCategories");
        assertThat(shopCategories.length(), is(categories.size()));
        JSONObject categoryResponse = shopCategories.getJSONObject(0);
        Category category = categories.get(0); // TODO be menos gitano testeando
        assertThat(categoryResponse.getString("shopCategory"), is(category.getCategory().name()));
    }

    private MockHttpServletResponse sendRequest(JSONObject content, MockHttpServletRequestBuilder builder) throws Exception {
        return mvc.perform(builder
                .content(content.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
    }
    
    @Test
    public void createEventTest() throws Exception {
    	// Arrange
    	User peterParkerUser = UserMother.createPeterParkerUser();
        userRepository.saveAndFlush(peterParkerUser);
    	
    	File file = FileMother.createRandomFile();
    	fileRepository.saveAndFlush(file);
    	
    	List<File> files = FileMother.createRandomFiles(3);
        fileRepository.saveAll(files);
        fileRepository.flush();

        List<Category> categories = CategoryMother.createRandomCategories(3);
        categoryRepository.saveAll(categories);
        categoryRepository.flush();
        
        Location location = LocationMother.createRandomLocation();
        locationRepository.saveAndFlush(location);
    	
    	Shop someFckingShop = ShopMother.createCandyShop(peterParkerUser, file, files, categories, location);
    	shopRepository.saveAndFlush(someFckingShop);
    	
    	String eventName = "My soul is 50% off";
    	EventType eventType = EventType.DISCOUNT;
    	
    	JSONObject content = new JSONObject();
    	content.put("name", eventName);
        content.put("shopId", someFckingShop.getShopId());
        content.put("eventType", eventType);
    	
    	// Act
    	MockHttpServletResponse response = sendRequest(content, MockMvcRequestBuilders.post("/api/shop/"));
        assertThat(response.getStatus(), is(200));

        JSONObject mvcResult = new JSONObject(response.getContentAsString());
        assertThat(mvcResult.has("eventId"), not(false));
        assertThat(mvcResult.has("shopId"), not(false));
        
        assertThat(mvcResult.getString("name"), is(eventName));
        
        
        assertThat(mvcResult.has("createdAt"), not(false));
        assertThat(mvcResult.has("modifiedAt"), not(false));
        
    }
}
