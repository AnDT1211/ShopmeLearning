package com.shopme.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class UserRestController {
    @Autowired
    private UserService userService;

    @PostMapping("users/check_email_unique")
    public String checkEmailUnique(@Param("email") String email) {
        return userService.isEmailUnique(email) ? "OK" : "Duplicated";
    }
}
