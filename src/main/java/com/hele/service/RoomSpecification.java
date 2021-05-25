package com.hele.service;

import com.hele.entity.Room;
import com.hele.dto.RoomFilter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class RoomSpecification implements Specification<Room> {

    private RoomFilter criteria;

    @Override
    public Predicate toPredicate
            (Root<Room> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();

        if (criteria.getSmoking() != null && criteria.getSmoking()) {
            predicates.add(builder.equal(
                    root.get("smoking"), criteria.getSmoking()));
        }
        if (criteria.getPetFriendly() != null && criteria.getPetFriendly()) {
            predicates.add(builder.equal(
                    root.get("petFriendly"), criteria.getPetFriendly()));
        }
        if (criteria.getReserved() != null && criteria.getReserved()) {
            predicates.add(builder.equal(
                    root.get("reserved"), criteria.getReserved()));
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
