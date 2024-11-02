package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.shopme.common.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testCreateFirstRole() {
        Role role = new Role();
        role.setName("Admin");
        role.setDescription("manage everything");
        Role savedRole = roleRepository.save(role);
        assertThat(savedRole.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateRestRoles() {
        roleRepository.saveAll(List.of(
                new Role("Salesperson", "manage product price, customers, shipping, orders and sales report"),
                new Role("Editor", "manage categories, brands, products, articles and menus"),
                new Role("Shipper", "view products, view orders, and update order status"),
                new Role("Assistant", "manage question and reviews")
        ));
    }
}
