package hackovid2020.back;

import hackovid2020.back.dao.User;
import hackovid2020.back.utils.MD5Util;

import java.util.Calendar;

public class UserMother {
    private static final String firstName = "Peter";
    private static final String lastName = "Parker";
    private static final String mail = "peterparker@gmail.com";
    private static final String password = "123456";
    private static final String url = Constants.GRAVATAR + MD5Util.md5Hex(mail);

    public static User createPeterParkerUser() {
        return User.createUser(firstName, lastName, mail, password, url,
                Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
    }
}
