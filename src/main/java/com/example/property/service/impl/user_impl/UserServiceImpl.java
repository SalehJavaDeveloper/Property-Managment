package com.example.property.service.impl.user_impl;

import com.example.property.dto.filter.RequestDto;
import com.example.property.dto.user.ResetRequest;
import com.example.property.dto.user_request.UserRequestDto;
import com.example.property.dto.user_response.UserResponseDto;
import com.example.property.entity.user.User;
import com.example.property.repository.user.UserRepository;
import com.example.property.service.impl.config_service_impl.JwtService;
import com.example.property.service.user.UserService;
import com.example.property.util.Utility;
import com.example.property.validator.ObjectValidator;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final JavaMailSender javaMailSender;

    private final JwtService jwtService;

    private final ObjectValidator<ResetRequest> requestObjectValidator;


    @Override
    public void updateUser(UserRequestDto userRequestDto) {

    }

    @Override
    public User findUserByUsername(String username) {
        return null;
    }

    @Override
    public User findUserById(Long id) {
        return null;
    }

    @Override
    public void deactivateUser(Long userId) {

    }

    @Override
    public Page<UserResponseDto> getSpecification(RequestDto requestDto) {
        return null;
    }

    @Override
    public String updateResetPasswordToken(String email, HttpServletRequest request) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);
        String token = jwtService.generateToken(user);
        if (user != null) {
            user.setResetPasswordToken(token);
            System.out.println(token);
            userRepository.save(user);
            try{
                String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
                //              String resetPasswordLink = "http://localhost:3000/" + "reset_password?token=" + token;
                sendEmail(email, resetPasswordLink);
                return "We have sent a reset password link to your email. Please check.";
            }catch (UsernameNotFoundException ex) {
                return ex.getMessage();
            } catch (UnsupportedEncodingException | MessagingException e) {
                return "Error while sending email";
            }

        } else {
            throw new UsernameNotFoundException("Could not find any user with the email" + email);
        }
    }

    @Override
    public User getByResetPasswordToken(String token) {

        List<User> users = userRepository.findByResetPasswordToken(token);
        if (users.isEmpty()) {
            throw new RuntimeException("Invalid token");
        }
        return users.get(0);
    }

    @Override
    public User updatePassword(User user, String password) {

        System.out.println("SERVISIN ICINDEYEM");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        ResetRequest build = ResetRequest.builder().newPassword(password).build();
        requestObjectValidator.validate(build);
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);

        user.setResetPasswordToken(null);
        userRepository.save(user);
        return user;
    }

    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        String subject = "Here's the link to reset your password";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom("atarverdiyev@std.beu.edu.az");
        simpleMailMessage.setTo(recipientEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);


        javaMailSender.send(simpleMailMessage);
    }



//    @Override
//    public String forgotPassword(String email) {
//        User user = userRepository.findByEmail(email);
//
//
//
//        if (user == null){
//            throw new RuntimeException("User not found this email: " + email);
//        }
//
//        try{
//            sendSetPassword(email);
//        }catch (MessagingException e){
//            throw new RuntimeException("Unable to send set password email please try again");
//        }
//
//
//        return "Please check your email to set new password to your account";
//    }
//
//    @Override
//    public String setPassword(String email, String newPassword) {
//        User user = userRepository.findByEmail(email);
//        if (user==null){
//            throw new RuntimeException("User not found this email: " + email);
//        }
//        user.setPassword(newPassword);
//        userRepository.save(user);
//        return "New password set succesfully login with new password!";
//    }
//
//    private void sendSetPassword(String email) throws MessagingException {
//
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
//        mimeMessageHelper.setTo(email);
//        mimeMessageHelper.setSubject("Set Password");
//        mimeMessageHelper.setText("""
//                <div>
//                    <a href="http://173.212.239.87:8080/set_password?email=%s" target="_blank">Click link to send password</a>
//                </div>
//                """.formatted(email),true);
//    }
    /*
    @Override
    public void update(Long id, TaskUpdateRequestDto updateRequestDto) throws MethodArgumentNotValidException {
        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(() -> new MethodArgumentNotValidException("Data not found with this ID"));
        taskMapper.update(taskEntity, updateRequestDto);
        taskRepository.save(taskEntity);
    }
     */

}