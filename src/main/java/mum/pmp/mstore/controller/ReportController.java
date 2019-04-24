package mum.pmp.mstore.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reports")
public class ReportController {
	@GetMapping(value="/weekly")
    public String weeklySalesReport(Model model) {
        model.addAttribute("fromDate", LocalDate.parse("04/21/2019", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        model.addAttribute("toDate", LocalDate.parse("04/27/2019", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        
        return "reports/weekly";
    }
}
