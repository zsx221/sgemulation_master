package com.macrochina.net.service;

import com.macrochina.net.dao.SamplePacs008Repository;
import com.macrochina.net.dto.SamplePace008Dto;
import com.macrochina.net.entity.SamplePacs008;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.*;
import java.util.List;

@Service
public class SamplePacs008Service {

    private Log logger = LogFactory.getLog(SamplePacs008Service.class);

    @Autowired
    private SamplePacs008Repository samplePacs008Repository;


    /**
     * 删除案例
     */
    public void deleteById(int id) {

        this.samplePacs008Repository.deleteById(id);

    }


    public SamplePacs008 findById(int id) {
        SamplePacs008 samplePacs008 = samplePacs008Repository.findById(id).get();
        return samplePacs008;
    }

    public SamplePacs008 save(SamplePacs008 samplePacs008) {
        return samplePacs008Repository.save(samplePacs008);
    }


    public Page<SamplePacs008> findAllSamplePacs008ByCdn(Integer page, Integer size, SamplePace008Dto molde) {
        Page<SamplePacs008> rtn = null;
        Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1, size == 0 ? 10 : size, Sort.by("id").descending());
        Specification<SamplePacs008> samplePacs008s = new Specification<SamplePacs008>() {


            @Override
            public Predicate toPredicate(Root<SamplePacs008> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = null;
                if (!StringUtils.isEmpty(molde.getOriginatingBankBIC())){

                    Path<String> instgagtmmbid = root.get("instgagtmmbid");
                    predicate = criteriaBuilder.like(instgagtmmbid,"%" + molde.getOriginatingBankBIC()+"%" );
                }
                if (!StringUtils.isEmpty(molde.getCategoryPurpose())){


                    Path<String> purp = root.get("purp");
                    if(predicate==null){
                        predicate = criteriaBuilder.and(criteriaBuilder.like(purp, "%" +molde.getCategoryPurpose()+"%" ));
                    }else {
                        predicate = criteriaBuilder.and(predicate,criteriaBuilder.like(purp, "%" +molde.getCategoryPurpose()+"%" ));
                    }

                }
                if (!StringUtils.isEmpty(molde.getCurrency())){

                    Path<String> grphdrccy = root.get("grphdrccy");
                    if(predicate==null){
                        predicate = criteriaBuilder.and(criteriaBuilder.like(grphdrccy, "%" + molde.getCurrency()+"%"  ));
                    }else{
                        predicate = criteriaBuilder.and(predicate,criteriaBuilder.like(grphdrccy, "%" + molde.getCurrency()+"%"  ));
                    }

                }
                if (!StringUtils.isEmpty(molde.getChargeBearer())) {

                    Path<String> chrgbr = root.get("chrgbr");

                    if(predicate==null){
                        predicate = criteriaBuilder.and(criteriaBuilder.like(chrgbr,"%" + molde.getChargeBearer()+"%" ));
                    }else{
                        predicate = criteriaBuilder.and(predicate,criteriaBuilder.like(chrgbr,"%" + molde.getChargeBearer()+"%" ));
                    }

                    
                }
                if (!StringUtils.isEmpty(molde.getCreditor())) {
                    Path<String> cdtrnm = root.get("cdtrnm");
                    if(predicate==null){
                        predicate = criteriaBuilder.and(criteriaBuilder.like(cdtrnm, "%" + molde.getCreditor() + "%"));
                    }else {
                        predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(cdtrnm, "%" + molde.getCreditor() + "%"));
                    }

                }
                if (!StringUtils.isEmpty(molde.getDebtor())) {
                    Path<String> dbtrnm = root.get("dbtrnm");
                    if(predicate==null){
                        predicate = criteriaBuilder.and(criteriaBuilder.like(dbtrnm, "%" + molde.getDebtor() + "%"));
                    }else {
                        predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(dbtrnm, "%" + molde.getDebtor() + "%"));
                    }
                }
                if(predicate!=null){
                    criteriaQuery.where(predicate);
                }
                return criteriaQuery.getRestriction();
            }
        };
        rtn = samplePacs008Repository.findAll(samplePacs008s, pageable);

        return rtn;
    }

    public Page<SamplePacs008> findAllByTxid(Integer page, Integer size, SamplePace008Dto model) {
        Page<SamplePacs008> rtn = null;
        Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1, size == 0 ? 10 : size, Sort.by("id").descending());
        Specification<SamplePacs008> samplePacs008s = new Specification<SamplePacs008>() {

            @Override
            public Predicate toPredicate(Root<SamplePacs008> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = null;

                if (!StringUtils.isEmpty(model.getIds())){
                    Path<Integer> id = root.get("id");
                    CriteriaBuilder.In<Integer> in = criteriaBuilder.in(id);
                    for (int i :model.getIds()) {
                        in.value(i);//存入值
                    }
                    predicate = criteriaBuilder.and(criteriaBuilder.not(criteriaBuilder.and(in)));

                }

                if(predicate!=null){
                    criteriaQuery.where(predicate);
                }
                return criteriaQuery.getRestriction();
            }
        };
        rtn = samplePacs008Repository.findAll(samplePacs008s,pageable);
        return rtn;
    }

    public List<SamplePacs008> findAllSamplePacs008ByCastId( int id) {
        return samplePacs008Repository.findAllSamplePacs008ByCastId(id);
    }

    public List<SamplePacs008> findAllSamplePacs008(int caseId, String s) {
        return samplePacs008Repository.findAllSamplePacs008( caseId,  s);

    }
}
