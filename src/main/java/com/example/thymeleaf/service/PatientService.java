package com.example.thymeleaf.service;

import com.example.thymeleaf.model.SkierowanieDoLekarza;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PatientService {
private Map<Integer, SkierowanieDoLekarza> PatientMap = new HashMap<>();

public SkierowanieDoLekarza createSkierowanie(String lekarz, String pacjent, Date termin) {
    int id = new Random().nextInt();
    SkierowanieDoLekarza skierowanieDoLekarza = new SkierowanieDoLekarza(id, lekarz, pacjent, termin);
    PatientMap.put(id,skierowanieDoLekarza);
    return skierowanieDoLekarza;
}
public Collection<SkierowanieDoLekarza> listaSkierowan() {
    return PatientMap.values();
    }

    public SkierowanieDoLekarza getSkierowanie(int id) {
    return PatientMap.get(id);
    }
}
