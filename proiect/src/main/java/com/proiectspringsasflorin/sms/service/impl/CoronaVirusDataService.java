package com.proiectspringsasflorin.sms.service.impl;


import com.proiectspringsasflorin.sms.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


@Service //Aceasta annotare indica faptul ca clasa este un serviciu si va fi gestionată de catre Spring Framework.
public class CoronaVirusDataService {

    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    // Aceasta variabilă conține o adresă URL care arata catre un API care returneaza un fisier CSV
    // cu date despre cazurile confirmate de COVID-19 la nivel global.
    private List<LocationStats> allStats = new ArrayList<>();

    public List<LocationStats> getAllStats() {
        return allStats;
    } //Aceasta metodă returnează lista de obiecte LocationStats,
    // care conține informații despre cazurile confirmate de COVID-19 la nivel global.

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
        //Această metodă este marcată cu anotările @PostConstruct și @Scheduled.
        //@PostConstruct indică faptul că această metodă trebuie să fie apelată imediat după ce bean-ul serviciului a fost creat.
        //@Scheduled indică faptul că această metodă trebuie să fie apelată în mod automat la intervale regulate de timp.
        List<LocationStats> newStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        //Aceasta linie creeaza un client HTTP pentru a efectua cereri catre sursa de date.
        HttpRequest request = HttpRequest.newBuilder()
                //Aceasta linie creeaza o cerere HTTP pentru a accesa sursa de date.
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader csvBodyReader = new StringReader(httpResponse.body());
        //Aceasta linie creeaza un StringReader care va fi utilizat pentru a citi datele din string-ul obtinut anterior
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        //Aceasta linie utilizeaza biblioteca Apache Commons CSV pentru a parse-a datele din format CSV si returneaza un obiect
        // Iterable care contine inregistrarile din fisierul CSV.
        for (CSVRecord record : records) {
            // Aceasta linie parcurge fiecare inregistrare din fisierul CSV
            LocationStats locationStat = new LocationStats();//stocheaza informatiile despre fiecare tara
            locationStat.setCountry(record.get("Country/Region"));//seteaza numele tarii
            int latestCases = Integer.parseInt(record.get(record.size() - 1));
            int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
            locationStat.setLatestTotalCases(latestCases);
            locationStat.setDiffFromPrevDay(latestCases - prevDayCases);
            newStats.add(locationStat);
        }
        this.allStats = newStats;
    }

}

