package com.example.property.service.impl.record;

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
    public List<Map<String, Object>> getPaymentCounts() {
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();

        String jpql = "SELECT last_name, COUNT(*) as count FROM Payment WHERE EXTRACT(MONTH FROM due_date) = :currentMonth AND EXTRACT(YEAR FROM due_date) = :currentYear GROUP BY last_name;";
        Query query = entityManager.createNativeQuery(jpql);
        query.setParameter("currentMonth",currentMonth);
        query.setParameter("currentYear",currentYear);

        List<Object[]> results = query.getResultList();
        return convertToMapList(results);
    }

    private List<Map<String, Object>> convertToMapList(List<Object[]> resultList) {
        List<Map<String, Object>> formattedList = new ArrayList<>();

        for (Object[] result : resultList) {
            Map<String, Object> formattedMap = new HashMap<>();
            formattedMap.put("payment", result[0]);
            formattedMap.put("count", result[1]);
            formattedList.add(formattedMap);
        }

        return formattedList;
    }



}
