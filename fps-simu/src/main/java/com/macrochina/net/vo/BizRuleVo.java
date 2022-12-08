package com.macrochina.net.vo;

import com.macrochina.net.entity.BizRuleData;
import com.macrochina.net.entity.BizRuleSet;
import lombok.Data;

import java.util.List;

@Data
public class BizRuleVo {
    private BizRuleSet bizRuleSet;
    private List<BizRuleData> bizRuleDataList;
}
