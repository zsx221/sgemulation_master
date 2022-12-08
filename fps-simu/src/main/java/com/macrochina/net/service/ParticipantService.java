package com.macrochina.net.service;

import com.macrochina.net.dao.ParticipantRepository;
import com.macrochina.net.entity.Participant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    public void save(Participant participant) {
        participantRepository.save(participant);
    }

    public Participant findOneById(int id) {
        return participantRepository.findById(id).get();
    }

    public void deleteById(int id) {
        participantRepository.deleteById(id);
    }

    public Page<Participant> findAll(Integer page, Integer size, Participant participant) {
        Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1, size == 0 ? 10 : size);
        Specification<Participant> specification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotBlank(participant.getBic())) {
                Path<String> bic = root.get("bic");
                list.add(criteriaBuilder.like(bic, "%" + participant.getBic() + "%"));
            }
            if (StringUtils.isNotBlank(participant.getBankName())) {
                Path<String> bankName = root.get("bankName");
                list.add(criteriaBuilder.like(bankName, "%" + participant.getBankName() + "%"));
            }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };
        return participantRepository.findAll(specification, pageable);
    }
}
