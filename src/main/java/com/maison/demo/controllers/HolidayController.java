package com.maison.demo.controllers;

import com.maison.demo.models.Holiday;
import com.maison.demo.services.HolidayService;
import com.maison.demo.services.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidayController {

    @Autowired
    private HolidayService holidayService;

    // @Autowired
    // public HolidayController(HolidayService holidayService) {
    //     this.holidayService = holidayService;
    // }

    @GetMapping("/holidays/{display}")
    public String index(@PathVariable String display, Model model) {
    // public String index(Model model) {
        if (display != null && display.equals("all")) {
            model.addAttribute("festival", true);
            model.addAttribute("federal", true);
        } else if (display != null && display.equals("federal")) {
            model.addAttribute("federal", true);
        } else if (display != null && display.equals("festival")) {
            model.addAttribute("festival", true);
        }

        List<Holiday> holidays = holidayService.searchAllHolidays();
        Holiday.Type[] types = Holiday.Type.values();
        System.out.println("holidays => " + holidays);
        for (Holiday.Type type: types) {
            model.addAttribute(
                    type.toString(),
                    holidays.stream()
                            .filter(holiday -> holiday.getType()
                                                      .equals(type))
                            .collect(Collectors.toList()));
        }

        return "holiday.html";
    }

}
