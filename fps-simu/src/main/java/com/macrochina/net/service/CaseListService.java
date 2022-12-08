package com.macrochina.net.service;

import com.macrochina.net.dao.CaseListRepository;
import com.macrochina.net.entity.CaseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseListService {
    @Autowired
    CaseListRepository caseListRepository;
    public List<CaseList> findAllByCaseId(int caseId) {
        return  caseListRepository.findAllByCaseId(caseId);
    }

    public void deleteByCaseId(int id) {
        caseListRepository.deleteByCaseId(id);
    }

    public List<Integer> findSampleIdBySampleType(int Caseid,String s) {
        return caseListRepository.findSampleIdBySampleType(Caseid,s);
    }
}
