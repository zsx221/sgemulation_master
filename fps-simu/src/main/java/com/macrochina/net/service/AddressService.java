package com.macrochina.net.service;

import address.prxy_001_001.ProxyAccountType;
import com.macrochina.net.dao.AddressRepository;
import com.macrochina.net.dto.AddressDto;
import com.macrochina.net.dto.AddrsDto;
import com.macrochina.net.entity.Address;
import com.macrochina.net.util.XmlIsoDateUtil;
import com.macrochina.net.utils.SysParamsContst;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public boolean count(String bic,String regnId,String val,String acctId){
        int num1 = addressRepository.findCountByBicAndRegnId(regnId);
        int num2 = addressRepository.findAddressByPrxy(val);
//        int num3 = addressRepository.findAddressByAcctId(acctId);
        if(num1 > 0 || num2 > 0){
            return false;
        }
        return true;
    }

    public String check2(String bic, String param, String checkType) {
        switch (checkType) {
            case "regnId":
                if (StringUtils.isNotBlank(param)) {
                    int num1 = addressRepository.findCountByBicAndRegnId(param);
                    if (num1 > 0) {
                        return "Registration Identification already exists";
                    }
                } else {
                    return "Registration Identification not null";
                }
                break;

            case "prxyId":
                if (StringUtils.isNotBlank(param)) {
                    int num2 = addressRepository.findAddressByPrxy(param);
                    if (num2 > 0) {
                        return "Proxy value already exists";
                    }
                } else {
                    return "Proxy value not null";
                }
                break;
        }
        return null;
    }

    public boolean isRegist(int id,String bic,String regnId,String val,String acctId){
        Address address = addressRepository.findById(id).get();
        if(bic.equals(address.getBic())){
            if(regnId.equals(address.getRegnId()) || val.equals(address.getVal()) || acctId.equals(address.getAcctId())){
                return true;
            }else {
               return count( bic, regnId, val, acctId);
            }
        }else {
            return count( bic, regnId, val, acctId);
        }
    }

    public void save(Address address) {
        address.setAcctPrtry(ProxyAccountType.BANK.value());
        address.setMsgDef(SysParamsContst.PRXY_001);
        address.setMsgId("");
        address.setStatus("ACTC");
        address.setCreDtTm(XmlIsoDateUtil.convertToStrFort(XmlIsoDateUtil.convertToXMLGregorianCalendar(Calendar.getInstance())));
        addressRepository.save(address);
    }

    public Address findOneById(int id) {
        return addressRepository.findById(id).get();
    }

    public void deleteById(int id) {
        addressRepository.deleteById(id);
    }

    public Page<Address> findAll(Integer page, Integer size, Address address) {
        Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1, size == 0 ? 10 : size, Sort.by("createdDate").descending());
        Specification<Address> addressSpecification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotBlank(address.getBic())) {
                Path<String> bic = root.get("bic");
                list.add(criteriaBuilder.like(bic, "%" + address.getBic() + "%"));
            }

            Path<String> status = root.get("status");
            list.add(criteriaBuilder.equal(status, "ACTC"));

//            Path<String> regnTp = root.get("regnTp");
//            list.add(criteriaBuilder.equal(regnTp, "NEWR"));

            if (StringUtils.isNotBlank(address.getAcctHldrNm())) {
                Path<String> acctHldrNm = root.get("acctHldrNm");
                list.add(criteriaBuilder.like(acctHldrNm, "%" + address.getAcctHldrNm() + "%"));
            }


            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };
        return addressRepository.findAll(addressSpecification, pageable);
    }


    public Page<Address> findAllByMsgDef(Integer page, Integer size, AddressDto model) {
        Page<Address> rtn = null;
        Pageable pageable = PageRequest.of(page == 0 ? 0 : page - 1, size == 0 ? 10 : size, Sort.by("id").descending());
        Specification<Address> addressSpecification = new Specification<Address>() {
            @Override
            public Predicate toPredicate(Root<Address> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = null;
                if (!org.springframework.util.StringUtils.isEmpty(model.getIds())) {
                    Path<Integer> id = root.get("id");
                    CriteriaBuilder.In<Integer> in = criteriaBuilder.in(id);
                    for (int i : model.getIds()) {
                        in.value(i);//存入值
                    }
                    predicate = criteriaBuilder.and(criteriaBuilder.not(criteriaBuilder.and(in)));

                }
                if (predicate != null) {
                    criteriaQuery.where(predicate);
                }
                return criteriaQuery.getRestriction();
            }
        };
        rtn = addressRepository.findAll(addressSpecification, pageable);
        return rtn;
    }


    public List<Address> findAllAddress(int caseId, String s) {
        return addressRepository.findAllAddress(caseId, s);
    }

    public List<Address> findAllByVal(String val,String msgDef) {
         return addressRepository.findByValAndMsgDef(val,msgDef);
    }

    public List<Address> findAllByBank(String bic,String msgDef) {
        return addressRepository.findAllByBank(bic,msgDef);
    }

//    public List<Address> findByValAndRegnTp(String val,String regnTp) {
//        return addressRepository.findByValAndRegnTp(val,regnTp);
//    }

    public List<Address> findAllByAcctId(String acctId,String msgDef) {
        return addressRepository.findByAcctIdAndMsgDef(acctId,msgDef);
    }

    public List<Address> findAllByRegnId(String regnId,String msgDef) {
        return addressRepository.findByRegnIdAndMsgDef(regnId,msgDef);
    }
    public List<Address> findAllByValAndAcctId(String val,String acctId,String msgDef) {
        return addressRepository.findAllByValAndAcctIdAndMsgDef(val,acctId,msgDef);
    }

    //静态查询数据
    public Page<AddrsDto> findAdrsAll(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<String> list = new ArrayList<>();
        list.add(SysParamsContst.PRXY_001);
        list.add(SysParamsContst.PRXY_002);
        list.add(SysParamsContst.PRXY_003);
        list.add(SysParamsContst.PRXY_004);
        list.add(SysParamsContst.PRXY_005);
        list.add(SysParamsContst.PRXY_006);
        list.add(SysParamsContst.PRXY_007);
        list.add(SysParamsContst.PRXY_008);
        return addressRepository.findAdrsAll(list,pageRequest);
    }
}
