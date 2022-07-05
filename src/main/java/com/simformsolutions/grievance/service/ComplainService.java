package com.simformsolutions.grievance.service;

import com.simformsolutions.grievance.dto.ComplainDTO;
import com.simformsolutions.grievance.dto.enums.Status;
import com.simformsolutions.grievance.entity.Complain;
import com.simformsolutions.grievance.entity.User;
import com.simformsolutions.grievance.repository.CategoryRepository;
import com.simformsolutions.grievance.repository.ComplainRepository;
import com.simformsolutions.grievance.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.Cookie;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ComplainService {

    @Autowired
    private ComplainRepository complainRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/webapp/complainproof";

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(2000000);
        return multipartResolver;
    }

    public String saveComplain(ComplainDTO complainDTO, long id, MultipartFile file1)
    {
        User user= userRepository.findById(id).orElseThrow(()->new NoSuchElementException("User not found with Id: "+id));
        Complain complain=new Complain();
        modelMapper.map(complainDTO,complain);
        complain.setStatus(Status.PENDING);
       // complain.setStatus(0);
        String filename=file1.getOriginalFilename();
        Path fileNameAndPath = Paths.get(uploadDirectory,filename);

        try {
            Files.write(fileNameAndPath, file1.getBytes());
        } catch (IOException e) {

            e.printStackTrace();
        }
        complain.setPhoto(filename);
        complain.setCategoryId(categoryRepository.findByCategoryName(complain.getCategory()).getCategoryId());
        user.getComplains().add(complain);
        return userRepository.save(user).toString();
    }

    public List<ComplainDTO> getUserComplains(Cookie[] cookies)
    {
        return userRepository.findById(userService.getComplainerId(cookies)).get().getComplains()
                .stream().map(complain -> modelMapper.map(complain,ComplainDTO.class)).collect(Collectors.toList());
    }
}
