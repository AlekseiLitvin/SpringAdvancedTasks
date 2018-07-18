package beans.endopoint;

import beans.models.User;
import beans.services.UserService;
import generated.GetUserRequest;
import generated.GetUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndpoint {

    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private final UserService userService;

    @Autowired
    public UserEndpoint(@Qualifier("userServiceImpl") UserService userService) {
        this.userService = userService;
    }

    @PayloadRoot(localPart = "getUserRequest", namespace = NAMESPACE_URI)
    @ResponsePayload
    public GetUserResponse getUserById(@RequestPayload GetUserRequest getUserRequest) {
        GetUserResponse getUserResponse = new GetUserResponse();
        User userById = userService.getById(getUserRequest.getId());
        getUserResponse.setUser(populateUser(userById));
        return getUserResponse;
    }

    private generated.User populateUser(User user) {
        generated.User result = new generated.User();
        generated.UserAccount userAccount = new generated.UserAccount();
        userAccount.setId(user.getUserAccount().getId());
        userAccount.setPrepaidMoney(user.getUserAccount().getPrepaidMoney());
        result.setId(user.getId());
        result.setEmail(user.getEmail());
        result.setName(user.getName());
        result.setPassword(user.getPassword());
        result.setRoles(user.getRoles());
        result.setUserAccount(userAccount);
        return result;
    }

}
