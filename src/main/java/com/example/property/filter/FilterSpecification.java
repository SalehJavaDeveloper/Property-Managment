package com.example.property.filter;

import com.example.property.dto.filter.SearchRequestDto;
import com.example.property.enumuration.GlobalOperator;
import com.example.property.exception.MethodArgumentNotValidException;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FilterSpecification<T> {

    public Specification<T> getSearchSpecification(List<SearchRequestDto> searchRequestDtos, GlobalOperator globalOperator){
        return (root, query, criteriaBuilder) -> {
             List<Predicate> predicates = new ArrayList<>();
        for (SearchRequestDto searchRequestDto : searchRequestDtos){

            Boolean value = "true".equalsIgnoreCase(searchRequestDto.getValue()) ? Boolean.TRUE :
                    "false".equalsIgnoreCase(searchRequestDto.getValue()) ? Boolean.FALSE : null;

            switch (searchRequestDto.getFilterOperator()){
                case EQUAL:
                    if(value != null){
                        Predicate equalBoolean = criteriaBuilder.equal(root.get(searchRequestDto.getColumn()), value);
                        predicates.add(equalBoolean);
                    }else {
                        Predicate equal = criteriaBuilder.equal(root.get(searchRequestDto.getColumn()), searchRequestDto.getValue());
                        predicates.add(equal);
                    }
                    break;
                case LIKE:
                    Predicate like = criteriaBuilder.like(criteriaBuilder.upper(root.get(searchRequestDto.getColumn())), "%" + searchRequestDto.getValue().toUpperCase() + "%");
                    predicates.add(like);
                    break;
                case IN:
                    String[] split = searchRequestDto.getValue().split(",");
                    Predicate in = root.get(searchRequestDto.getColumn()).in(Arrays.asList(split));
                    predicates.add(in);
                    break;
                case GREATER_THAN:
                    Predicate greaterThan = criteriaBuilder.greaterThan(root.get(searchRequestDto.getColumn()), searchRequestDto.getValue());
                    predicates.add(greaterThan);
                    break;
                case LESS_THAN:
                    Predicate lessThan = criteriaBuilder.lessThan(root.get(searchRequestDto.getColumn()), searchRequestDto.getValue());
                    predicates.add(lessThan);
                    break;
                case BETWEEN:
                    String[] splitBetween = searchRequestDto.getValue().split(",");
                    Predicate between = criteriaBuilder.between(root.get(searchRequestDto.getColumn()), Long.parseLong(splitBetween[0]), Long.parseLong(splitBetween[1]));
                    predicates.add(between);
                    break;
                case JOIN:
                    if(value != null){
                        Predicate join = criteriaBuilder.like(root.join(searchRequestDto.getFirstJoinTable()).get(searchRequestDto.getColumn()), "%" + value + "%");
                        predicates.add(join);
                    }else {
                        Predicate join = criteriaBuilder.like(criteriaBuilder.upper(root.join(searchRequestDto.getFirstJoinTable()).get(searchRequestDto.getColumn())), "%" + searchRequestDto.getValue().toUpperCase() + "%");
                        predicates.add(join);
                    }
                     break;
                case TWO_JOIN:
                    Join firstJoin = root.join(searchRequestDto.getFirstJoinTable());
                    Join secondJoin = firstJoin.join(searchRequestDto.getSecondJoinTable());
                    Predicate twoJoin = criteriaBuilder.like(criteriaBuilder.upper(secondJoin.get(searchRequestDto.getColumn())), "%" + searchRequestDto.getValue().toUpperCase() + "%");
                    predicates.add(twoJoin);
                    break;
                case THREE_JOIN:
                    Join firstJoin1 = root.join(searchRequestDto.getFirstJoinTable());
                    Join secondJoin2 = firstJoin1.join(searchRequestDto.getSecondJoinTable());
                    Join thirdJoin = secondJoin2.join(searchRequestDto.getThirdJoinTable());
                    Predicate treeJoin = criteriaBuilder.like(criteriaBuilder.upper(thirdJoin.get(searchRequestDto.getColumn())),"%" + searchRequestDto.getValue().toUpperCase() + "%");
                    predicates.add(treeJoin);
                    break;
                case FOUR_JOIN:
                    Join firstJoining = root.join(searchRequestDto.getFirstJoinTable());
                    Join secondJoining = firstJoining.join(searchRequestDto.getSecondJoinTable());
                    Join thirdJoining = secondJoining.join(searchRequestDto.getThirdJoinTable());
                    Join fourthJoin = thirdJoining.join(searchRequestDto.getFourthJoinTable());
                    Predicate fourJoin = criteriaBuilder.like(criteriaBuilder.upper(fourthJoin.get(searchRequestDto.getColumn())),"%" + searchRequestDto.getValue().toUpperCase() + "%");
                    predicates.add(fourJoin);
                    break;
                default:
                    throw new MethodArgumentNotValidException("Unexpected value inside Filter!");
            }
        }
        if(globalOperator.equals(GlobalOperator.AND)) {
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }else {
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        }
        };
    }
}
