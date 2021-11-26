package tests.api;

import com.yehor.api.models.unknown.ResourceDataModel;
import com.yehor.api.models.unknown.ResourcePageModel;
import com.yehor.api.models.users.RegisterUserModel;
import com.yehor.api.models.users.RegisteredUserModel;
import com.yehor.api.models.users.UserDataModel;
import com.yehor.api.models.users.UserModel;
import com.yehor.api.models.users.UserPageModel;
import com.yehor.api.services.UnknownService;
import com.yehor.api.services.UsersService;
import com.yehor.helpers.TimeHelper;
import org.testng.annotations.Test;

import java.time.ZonedDateTime;
import java.util.Map;

import static java.util.stream.Collectors.toMap;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class UsersTest {

    @Test(description = "Users contain six users per page")
    public void testUsersWithPageParamReturnSixUsersPerPage() {
        int requestedPage = 1;
        int expectedPerPage = 6;

        UserPageModel responseBody = UsersService.getUserPage(requestedPage);

        assertEquals(responseBody.getPage(), requestedPage);
        assertEquals(responseBody.getPerPage(), expectedPerPage);
        assertNotEquals(responseBody.getTotal(), 0);
        assertEquals(responseBody.getData().size(), expectedPerPage);
    }

    @Test(description = "Get Users returns a user each containing info: id, email, first_name, last_name, avatar")
    public void testUsersWithPageParamReturnsUsersWithRequiredData() {
        int requestedPage = 2;

        UserPageModel responseBody = UsersService.getUserPage(requestedPage);

        assertNotEquals(responseBody.getTotal(), 0);
        responseBody.getData().forEach(user -> {
            assertNotEquals(user.getId(), 0);
            assertNotNull(user.getEmail());
            assertNotNull(user.getFirstName());
            assertNotNull(user.getLastName());
            assertNotNull(user.getAvatar());
        });
    }

    @Test(description = "Get Users returns a user each containing info: id, email, first_name, last_name, avatar")
    public void testUsersWithUserIdParamReturnsUserWithRequiredData() {
        UserModel responseBody = UsersService.getUserWithId(5);

        UserDataModel userData = responseBody.getData();
        assertNotEquals(userData.getId(), 0);
        assertNotNull(userData.getEmail());
        assertNotNull(userData.getFirstName());
        assertNotNull(userData.getLastName());
        assertNotNull(userData.getAvatar());
    }

    @Test(description = "Get User with not existed Id returns 404")
    public void testGetNotExistingUserReturns404() {
        int notExistingUserId = 23;

        String responseBody = UsersService.getNotExistingUserWithId(notExistingUserId);

        assertEquals(responseBody, "{}");
    }

    @Test(description = "It is possible to create a user and return new id + created at now()")
    public void testCreateNewUser() {
        RegisterUserModel request = RegisterUserModel.builder()
                .name("john")
                .job("pilot")
                .build();

        RegisteredUserModel responseBody = UsersService.registerUser(request);
        ZonedDateTime timeInResponse = responseBody.getCreatedAt();
        long differenceInSeconds = TimeHelper.getDurationBetweenNowAnd(timeInResponse).toSeconds();

        assertNotEquals(responseBody.getId(), "0");
        assertTrue(differenceInSeconds < 30);
    }

    @Test(description = "It is possible to delete a user and get 204 in response")
    public void testDeleteUserReturns204() {
        int userId = 2;

        String responseBody = UsersService.deleteUser(userId);

        assertTrue(responseBody.isEmpty());
    }

    @Test(description = "Test that colors are returns with valid hex code in get resources request")
    public void testColorsAreReturnedWithValidHexCode() {
        ResourcePageModel responseBody = UnknownService.getResources();
        Map<String, String> nameToColorMap = responseBody.getData().stream()
                .collect(toMap(ResourceDataModel::getName, ResourceDataModel::getColor));

        assertNotEquals(responseBody.getTotal(), 0);
        assertEquals(nameToColorMap.get("cerulean"), "#98B2D1");
        assertEquals(nameToColorMap.get("fuchsia rose"), "#C74375");
        assertEquals(nameToColorMap.get("true red"), "#BF1932");
        assertEquals(nameToColorMap.get("aqua sky"), "#7BC4C4");
        assertEquals(nameToColorMap.get("tigerlily"), "#E2583E");
        assertEquals(nameToColorMap.get("blue turquoise"), "#53B0AE");
    }
}