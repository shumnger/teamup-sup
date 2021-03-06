package ru.team.up.sup.input.controllerPrivateTest;

import org.junit.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.team.up.sup.core.entity.User;
import ru.team.up.sup.core.service.UserService;
import ru.team.up.sup.input.controller.privateController.AdminController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TeamupInputAdminPrivateControllerTest {

    @Mock
    private UserService adminService;

    @Autowired
    @InjectMocks
    AdminController adminController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    User user = User.builder()
            .id(1L)
            .name("Natalya")
            .lastName("Tkachenko")
            .email("natalyatk@bk.ru")
            .password("12345")
            .lastAccountActivity(LocalDateTime.now())
            .build();

    ArrayList<User> listAdmin = new ArrayList<>();

    @Test
    public void testCreateAdmin() {
        when(adminService.saveUser(user)).thenReturn(user);
        Assert.assertEquals(201, adminController.createAdmin("admin", user).getStatusCodeValue());
    }

    @Test
    public void testGetOneById() {
        when(adminService.getOneUser(user.getId())).thenReturn(user);
        Assert.assertEquals(200, adminController.getOneAdmin(user.getId()).getStatusCodeValue());
    }

    @Test
    public void testGetAllAdmins() {
        listAdmin.add(user);
        when(adminService.getAllUsers()).thenReturn(listAdmin);
        Assert.assertEquals(200, adminController.getAllAdmins().getStatusCodeValue());
    }

    @Test
    public void testUpdateAdmin() {
        when(adminService.saveUser(user)).thenReturn(user);
        Assert.assertEquals(200, adminController.updateAdmin(user).getStatusCodeValue());
    }

    @Test
    public void testDeleteAdmin() {
        Assert.assertEquals(200, adminController.deleteAdmin(user.getId()).getStatusCodeValue());
    }

}
