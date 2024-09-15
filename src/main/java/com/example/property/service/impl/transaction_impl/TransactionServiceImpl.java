//package com.example.property.service.impl.transaction_impl;
//
//import com.example.property.dto.payment.AuthResponsePaymentAPI;
//import com.example.property.dto.payment.TransactionDto;
//import com.example.property.entity.payment.PaymentEntity;
//import com.example.property.entity.payment.TransactionEntity;
//import com.example.property.entity.user.User;
//import com.example.property.mapper.payment.TransactionMapper;
//import com.example.property.repository.payment.PaymentRepository;
//import com.example.property.repository.payment.TransactionRepository;
//import com.example.property.repository.user.UserRepository;
//import com.example.property.service.payment.TransactionService;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Random;
//import java.util.UUID;
//
//@Service("TransactionServiceImpl")
//@RequiredArgsConstructor
//public class TransactionServiceImpl implements TransactionService {
//    private final TransactionRepository transactionRepository;
//    private final TransactionMapper transactionMapper;
//    private final PaymentRepository paymentRepository;
//    private final UserRepository userRepository;
//
//
//    private String authToken;
//
//    @PostConstruct
//    public void authenticate(){
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders authHeaders = new HttpHeaders();
//
//        authHeaders.setContentType(MediaType.APPLICATION_JSON);
//
//        String email = "support@birlesikodeme.com";
//        String password = "Testmerchant2!";
//
//        String authRequestJson = "{" +
//                "\"email\": \"" + email + "\", " +
//                "\"password\": \"" + password + "\"" +
//                "}";
//
//        HttpEntity<String> authRequest = new HttpEntity<>(authRequestJson, authHeaders);
//        String authEndpoint = "https://test-vpos.unitedpayment.az/api/auth/";
//        AuthResponsePaymentAPI authResponse = restTemplate.postForObject(authEndpoint, authRequest, AuthResponsePaymentAPI.class);
//
//        if (authResponse != null) {
//            authToken = authResponse.getToken();
//        } else {
//            throw new RuntimeException("Authentication failed. Unable to obtain token.");
//        }
//    }
//
//    @Override
//    public String makePayment(long paymentId, long userId) {
//        HttpHeaders headers = new HttpHeaders();
//        RestTemplate restTemplate = new RestTemplate();
//
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Authorization", "Bearer " + authToken);
//
//        PaymentEntity byId = paymentRepository.getReferenceById(paymentId);
//        String sAmount = String.valueOf(byId.getAmount());
//        int amount = Integer.parseInt(sAmount);
//
//        String description = byId.getDescription();
//
//
//        User referenceById = userRepository.getReferenceById(paymentId);
//        String email = referenceById.getEmail();
//        String phoneNumber1 = referenceById.getPhoneNumber1();
//
//        String memberID = generateMemberId();
//        String orderId = generateOrderId();
//
//        TransactionDto transactionDto = new TransactionDto();
//        transactionDto.setAmount(amount);
//        transactionDto.setInstallment(1);
//        transactionDto.setDescription(description);
//        transactionDto.setEmail(email);
//        transactionDto.setTelephone(phoneNumber1);
//        transactionDto.setMemberId(memberID);
//        transactionDto.setOrderId(orderId);
//
//
//
//
//        String requestJson = "{" +
//                "\"amount\": " + transactionDto.getAmount() + ", " +
//                "\"installment\": " + transactionDto.getInstallment() + ", " +
//                "\"description\": \"" + transactionDto.getDescription() + "\", " +
//                "\"email\": \"" + transactionDto.getEmail() + "\", " +
//                "\"telephone\": \"" + transactionDto.getTelephone() + "\", " +
//                "\"memberId\": \"" + transactionDto.getMemberId() + "\", " +
//                "\"orderId\": \"" + transactionDto.getOrderId() + "\"" +
//                "}";
//
//        HttpEntity<String> request = new HttpEntity<>(requestJson, headers);
//
//        String paymentLink = restTemplate.postForObject("https://test-vpos.unitedpayment.az/api/transactions/create-pay-link", request, String.class);
//        TransactionEntity transactionEntity = transactionMapper.fromDto(transactionDto);
//        transactionRepository.save(transactionEntity);
//        return paymentLink;
//    }
//
//    private String generateMemberId() {
//        UUID uuid = UUID.randomUUID();
//        long mostSignificantBits = uuid.getMostSignificantBits();
//        long leastSignificantBits = uuid.getLeastSignificantBits();
//
//        if (mostSignificantBits < 0) {
//            mostSignificantBits = -mostSignificantBits;
//        }
//        if (leastSignificantBits < 0) {
//            leastSignificantBits = -leastSignificantBits;
//        }
//
//
//        String memberId = String.format("%d%d", mostSignificantBits, leastSignificantBits);
//        return memberId.substring(0, 8);
//    }
//
//    private String generateOrderId() {
//        UUID uuid = UUID.randomUUID();
//
//        String shortCode = uuid.toString().replace("-", "").substring(0, 15);
//
//        return String.format("%05d-%s-%05d-%s",
//                new Random().nextInt(100000),
//                shortCode.substring(0, 5),
//                new Random().nextInt(100000),
//                shortCode.substring(5, 15));
//    }
//
//
//}
