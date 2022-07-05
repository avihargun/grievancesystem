package com.simformsolutions.grievance;

import com.simformsolutions.grievance.dto.UserDTO;
import com.simformsolutions.grievance.exception.UserAlreadyExist;
import com.simformsolutions.grievance.entity.User;
import com.simformsolutions.grievance.repository.UserRepository;
import com.simformsolutions.grievance.service.UserService;
import com.simformsolutions.grievance.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.Cookie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
class UserServiceTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil=new JwtUtil();

    @Autowired
    private ModelMapper modelMapper;

    UserDTO userDTO = new UserDTO(1L,"user","address","9212121212","mohitdavera@gmail.com","password",null);
    Cookie[] cookie= new Cookie[]{new Cookie("token",jwtUtil.generateToken("mohitdavera@gmail.com"))};

    @Test
    void saveUser_SuccessTest()
    {
        Mockito.when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(null);
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Mockito.when(userRepository.save(any(User.class))).thenReturn(modelMapper.map(userDTO,User.class));

        UserDTO actualUser = modelMapper.map(userService.saveUser(userDTO).getBody(),UserDTO.class);
        assertEquals(userDTO,actualUser);
    }

    @Test
    void saveUser_FailTest()
    {
        Mockito.when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(modelMapper.map(userDTO,User.class));
        assertThrows(UserAlreadyExist.class,() -> userService.saveUser(userDTO));
    }

    @Test
    void getComplainerId()
    {
        Mockito.when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(modelMapper.map(userDTO,User.class));
        assertEquals(userDTO.getUserId(),userService.getComplainerId(cookie));
    }

}
