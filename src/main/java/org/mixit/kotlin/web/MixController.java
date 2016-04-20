package org.mixit.kotlin.web;

import org.mixit.kotlin.domain.Hotel;
import org.mixit.kotlin.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MixController {

    @Autowired
    private HotelService hotelService;

    @RequestMapping("/")
    @Transactional(readOnly = true)
    public String showHotels(Model model) {
        List<Hotel> hotels = hotelService.getHotels();
        model.addAttribute("hotels", hotels);
        return "thymeleaf";
    }

    @RequestMapping("/angular")
    public String angular() {
        return "angular";
    }

}
