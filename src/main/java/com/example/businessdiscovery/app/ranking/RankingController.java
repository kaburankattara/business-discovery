package com.example.businessdiscovery.app.ranking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/ranking")
public class RankingController {

    private RankingService rankingService;

    @Autowired
    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    /**
     * メソッド呼出前処理
     *
     * @return form
     */
    @ModelAttribute("form")
    private RankingForm setUpForm() {
        return new RankingForm();
    }

    @RequestMapping
    public String index(@ModelAttribute("form") RankingForm form, Model model) {
//        form.map = rankingService.init();
        rankingService.init2();
        Map<String, Object> tmp = new HashMap<>();
        tmp.put("key", "value");
        form.map = tmp;
        model.addAttribute("form", form);
        return "ranking/index";
    }

}
