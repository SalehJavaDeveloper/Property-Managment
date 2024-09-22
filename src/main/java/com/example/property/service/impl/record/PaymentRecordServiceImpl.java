package com.example.property.service.impl.record;

import com.example.property.dto.payment.PaymentRecordDto;
import com.example.property.entity.payment.PaymentEntity;
import com.example.property.repository.payment.PaymentRepository;
import com.example.property.service.payment.PaymentRecordService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PaymentRecordServiceImpl implements PaymentRecordService {
    private final EntityManager entityManager;

    @Override
    public List<Map<String, Object>> getPaymentCounts(PaymentRecordDto paymentRecordDto) {
        try {
            String jpql = "SELECT payment_date, amount, COUNT(*) as count FROM Payment WHERE EXTRACT(MONTH FROM payment_date) = :month AND EXTRACT(YEAR FROM payment_date) = :year GROUP BY amount, payment_date;";
            Query query = entityManager.createNativeQuery(jpql);
            query.setParameter("month", paymentRecordDto.getMonth());
            query.setParameter("year", paymentRecordDto.getYear());

            List<Object[]> results = query.getResultList();
            return convertToMapList(results);
        }catch (Exception e){
            throw new RuntimeException(e.getCause());
        }

    }


    private List<Map<String, Object>> convertToMapList(List<Object[]> resultList) {
        List<Map<String, Object>> formattedList = new ArrayList<>();

        for (Object[] result : resultList) {
            Map<String, Object> formattedMap = new HashMap<>();
            formattedMap.put("payment_date", result[0]);
            formattedMap.put("payment", result[1]);
            formattedMap.put("count", result[2]);
            formattedList.add(formattedMap);
        }

        return formattedList;
    }



}
