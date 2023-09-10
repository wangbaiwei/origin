package com.wbw.serviceprice.controller;


import com.wbw.internalcommon.dto.PriceRule;
import com.wbw.internalcommon.dto.ResponseResult;
import com.wbw.serviceprice.service.PriceRuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/price-rule")
public class PriceRuleController {

    @Autowired
    private PriceRuleService priceRuleService;


    @PostMapping("add")
    public ResponseResult add(@RequestBody PriceRule priceRule) {

        return priceRuleService.add(priceRule);
    }

    @PostMapping("edit")
    public ResponseResult edit(@RequestBody PriceRule priceRule) {

        return priceRuleService.edit(priceRule);
    }

    /**
     * 判断城市和对应的车型的计价规则是否存在
     *
     * @param priceRule
     * @return
     */
    @GetMapping("/price-rule/get/if-exists")
    public ResponseResult<Boolean> isExists(@RequestBody PriceRule priceRule) {
        return priceRuleService.ifExists(priceRule);
    }

}
