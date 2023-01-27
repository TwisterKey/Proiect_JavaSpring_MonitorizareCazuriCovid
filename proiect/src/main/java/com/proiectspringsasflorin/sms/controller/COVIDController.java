package com.proiectspringsasflorin.sms.controller;

import com.proiectspringsasflorin.sms.entity.Patient;
import com.proiectspringsasflorin.sms.models.LocationStats;

import com.proiectspringsasflorin.sms.service.impl.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;



@Controller
public class COVIDController {


    @Autowired //aceasta anotare este utilizată pentru a inițializa o dependență de un alt obiect sau serviciu.
    // În acest caz, se inițializează o instanță a clasei CoronaVirusDataService.
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")//aceasta anotare indică faptul că această metodă va răspunde la cereri GET cu URL-ul principal "/".
    public String home(Model model) {
        // primește ca parametru un obiect Model, care este utilizat pentru a adăuga atribute care pot fi
        // utilizate în vederea afișate în pagina web.
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        // obține toate statisticile de la serviciul de date despre COVID-19.
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        // calculează numărul total de cazuri raportate utilizând fluxurile de date.
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        //ia toate statisticile din lista "allStats" si foloseste metoda "mapToInt" pentru a transforma lista
        // in stream de int-uri, care reprezinta numarul de cazuri noi raportate in fiecare locatie
        //Apoi se foloseste metoda "sum()" pentru a calcula totalul cazurilor noi raportate la nivel global
        model.addAttribute("locationStats", allStats); //are poate fi accesat de catre vederea care va fi returnata
        //"locationStats" este o lista de obiecte "LocationStats" care contine informatii despre fiecare tara
        model.addAttribute("totalReportedCases", totalReportedCases);
        //"totalReportedCases" este numarul total de cazuri raportate din toate tarile
        model.addAttribute("totalNewCases", totalNewCases);
        //"totalNewCases" este numarul total de noi cazuri raportate din toate tarile

        return "home";
    }
}