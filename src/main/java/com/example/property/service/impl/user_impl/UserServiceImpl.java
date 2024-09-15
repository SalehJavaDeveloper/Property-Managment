package com.example.property.service.impl.user_impl;

import com.example.property.dto.user.UserDto;
import com.example.property.dto.user.UserUpdateRequestDto;
import com.example.property.entity.user.User;
import com.example.property.exception.MethodArgumentNotValidException;
import com.example.property.mapper.user.UserMapper;
import com.example.property.repository.user.UserRepository;
import com.example.property.service.user.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    JavaMailSender javaMailSender;


    @Override
    public Page<UserDto> findAllUsers(int pageNumber, int pageSize) {
        Pageable pageAble = PageRequest.of(pageNumber, pageSize);
        return userMapper.mapPageEntityToPageResponse(userRepository.findAll(pageAble));
    }

    @Override
    public UserDto findUserByUsername(String username) throws MethodArgumentNotValidException {
        if(!userRepository.findByUsername(username).isPresent()){
            throw new MethodArgumentNotValidException("User not found with: " + username);
        }
        return userMapper.toDTO(userRepository.findByUsername(username).get());
    }

    @Override
    public UserDto findUserById(Long id) throws MethodArgumentNotValidException {
        if(!userRepository.findById(id).isPresent()){
            throw new MethodArgumentNotValidException("Specified User not found!");
        }
        return userMapper.toDTO(userRepository.findById(id).get());
    }



    @Override
    public void userUpdate(Long id, UserUpdateRequestDto userUpdateRequestDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new MethodArgumentNotValidException("Data not found"));
        userMapper.update(user,userUpdateRequestDto);
        userRepository.save(user);
    }

    @Override
    public String forgotPassword(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new RuntimeException("User not found this email: " + email);
        }

        try{
            sendSetPassword(email);
        }catch (MessagingException e){
            throw new RuntimeException("Unable to send set password email please try again");
        }


        return "Please check your email to set new password to your account";
    }

    @Override
    public String setPassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email);
        if (user==null){
            throw new RuntimeException("User not found this email: " + email);
        }
        user.setPassword(newPassword);
        userRepository.save(user);
        return "New password set succesfully login with new password!";
    }

    private void sendSetPassword(String email) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Set Password");
        mimeMessageHelper.setText("""
                <div> 
                    <a href="http://173.212.239.87:8080/set_password?email=%s" target="_blank">Click link to send password</a>
                </div>
                """.formatted(email),true);
    }
    /*
    @Override
    public void update(Long id, TaskUpdateRequestDto updateRequestDto) throws MethodArgumentNotValidException {
        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(() -> new MethodArgumentNotValidException("Data not found with this ID"));
        taskMapper.update(taskEntity, updateRequestDto);
        taskRepository.save(taskEntity);
    }
     */

}
