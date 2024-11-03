package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateFirstUser() {
        Role adminRole = entityManager.find(Role.class, 1);
        assertThat(adminRole).isNotNull();
        User adminUser = new User("andt@gmail.com", "admin", "an", "dt");
        adminUser.addRole(adminRole);
        User savedUser = userRepository.save(adminUser);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateUserWithTwoRoles() {
        Role editorRole = new Role(3);
        Role assistantRole = new Role(5);
        User userNavi = new User("navi@gmail.com", "navi", "na", "vi");
        userNavi.addRole(editorRole);
        userNavi.addRole(assistantRole);
        User savedUser = userRepository.save(userNavi);
        assertThat(savedUser.getId()).isGreaterThan(1);
        assertThat(savedUser.getRoles().size()).isEqualTo(2);
    }

    @Test
    public void testListAllUsers() {
        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void testFindUserById() {
        System.out.println(userRepository.findById(1));
        System.out.println(userRepository.findById(2));
    }

    @Test
    public void testUpdateUser() {
        User userNavi = userRepository.findById(2).get();
        String newEmail = "naviIsTheBest@gmail.com";
        userNavi.setEmail(newEmail);
        User updatedUserNavi = userRepository.save(userNavi);
        assertThat(updatedUserNavi.getEmail()).isEqualTo(newEmail);
    }

    @Test
    public void testDeleteUser() {
        userRepository.deleteById(2);
        assertThat(userRepository.count()).isEqualTo(1);
    }

    @Test
    public void testFindUserByEmail() {
        String email = "navi@gmail.com";
        User user = userRepository.getUserByEmail(email);
        assertThat(user).isNotNull();
        assertThat(userRepository.getUserByEmail("fakeEmail@gmail.com")).isNull();
    }
}
