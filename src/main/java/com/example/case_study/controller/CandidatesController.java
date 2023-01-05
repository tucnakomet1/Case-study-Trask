package com.example.case_study.controller;

import com.example.case_study.Util.Utils;
import com.example.case_study.entity.Candidates;
import com.example.case_study.repository.CandidatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class CandidatesController {

    Logger logger = Logger.getLogger(CandidatesController.class.getName());

    @Autowired
    CandidatesRepository candidatesRepository;

    // vypise obsah cele databaze, hledame podle jmena uchazece
    @GetMapping("/candidates")
    public ResponseEntity<List<Candidates>> getAllCandidates(@RequestParam(required = false) String candidate) {
        try {
            List<Candidates> candidates = new ArrayList<>();

            if (candidate == null) {
                candidates.addAll(candidatesRepository.findAll());
            } else {
                candidates.addAll(candidatesRepository.findByCandidateContaining(candidate));
            }

            if (candidates.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(candidates, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // vypise obsah databaze, hledame podle technologie
    @GetMapping("/candidates/{id}/technologies")
    public ResponseEntity<ArrayList<String>> getAllTechnologies(@PathVariable("id") long id) {
        Optional<Candidates> candidatesData = candidatesRepository.findById(id);

        if (candidatesData.isPresent()) {
            Candidates got = candidatesData.get();
            String tech = got.getTechnologies();
            String know = got.getKnowledge();
            String note = got.getNote();

            ArrayList<String> all_technologies = Utils.returnTechnologies(tech, know, note);
            logger.log(Level.INFO, String.valueOf(all_technologies));

            return new ResponseEntity<>(all_technologies, HttpStatus.OK);
        } else {
            logger.log(Level.WARNING, "NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // operace pro získani vsech technologii, ktere nema zadny kandidat
    @GetMapping("/candidates/technologies/none")
    public ResponseEntity<ArrayList<String>> getAllTechnologiesThatNoOneHas() {
        try {
            List<Candidates> candid_data = new ArrayList<>(candidatesRepository.findAll());
            ArrayList<String> allTech = new ArrayList<>(), noneTech = new ArrayList<>();
            
            for (Candidates candidate : candid_data) {
                String cand = candidate.getCandidate(), tech = candidate.getTechnologies();
                String[] split_tech = tech.split(", ");

                for (String one_tech : split_tech) {
                    // zadny kandidat --> null
                    if (cand == null || cand.equals("")) {
                        if (!noneTech.contains(one_tech) && !allTech.contains(one_tech)) {
                            noneTech.add(one_tech);
                        }
                    } else {
                        if (!allTech.contains(one_tech)) {
                            allTech.add(one_tech);

                            // jednodussi nez prunik dvou ArrayListu
                            if (noneTech.contains(one_tech)) {
                                noneTech.remove(one_tech);
                            }
                        }
                    }
                }
            }

            logger.log(Level.WARNING, "Technologie navic:   " + noneTech);
            logger.log(Level.WARNING, "Technologie pouzite: " + allTech);

            return new ResponseEntity<>(noneTech, HttpStatus.OK);
        } catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // prida kandidata do databaze - potreba vsechny hodnoty
    @PostMapping("/candidates")
    public ResponseEntity<Candidates> addCandidate(@RequestBody Candidates candidates) {
        try {
            Candidates candidates1 = candidatesRepository.save(new Candidates(
                    candidates.getCandidate(), candidates.getTechnologies(),
                    candidates.getKnowledge(), candidates.getNote()));
            return new ResponseEntity<>(candidates1, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.log(Level.WARNING, "INTERNAL SERVER ERROR");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // vypise obsah konkretniho (podle ID) kandidata
    @GetMapping("/candidates/{id}")
    public ResponseEntity<Candidates> getCandidatesDataById(@PathVariable("id") long id) {
        Optional<Candidates> candidatesData = candidatesRepository.findById(id);

        if (candidatesData.isPresent()) {
            return new ResponseEntity<>(candidatesData.get(), HttpStatus.OK);
        } else {
            logger.log(Level.WARNING, "NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // prida technologie ke konkretnimu kandidatovi
    @PutMapping("/candidates/{id}")
    public ResponseEntity<Candidates> editCandidatesDataById(@PathVariable("id") long id, @RequestBody Candidates candidates) {
        Optional<Candidates> candidatesData = candidatesRepository.findById(id);

        if (candidatesData.isPresent()) {
            Candidates candidates1 = candidatesData.get();
            candidates1.setCandidate(candidates.getCandidate());
            candidates1.setTechnologies(candidates.getTechnologies());
            candidates1.setKnowledge(candidates.getKnowledge());
            candidates1.setNote(candidates.getNote());

            return new ResponseEntity<>(candidatesRepository.save(candidates1), HttpStatus.OK);
        } else {
            logger.log(Level.WARNING, "NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // upravi obsah konkretniho (podle ID) kandidata
    @PutMapping("/candidates/{id}/technologies")
    public ResponseEntity<Candidates> addTechnologiesById(@PathVariable("id") long id, @RequestBody Candidates candidates) {
        Optional<Candidates> candidatesData = candidatesRepository.findById(id);

        String separator = ", ";

        if (candidatesData.isPresent()) {
            Candidates got = candidatesData.get();
            String old_technologies = got.getTechnologies();
            String old_knowledge = got.getKnowledge();
            String old_note = got.getNote();

            String new_technologies = candidates.getTechnologies();
            String new_knowledge = candidates.getKnowledge();
            String new_note = candidates.getNote();

            Candidates candidates1 = candidatesData.get();
            candidates1.setTechnologies(old_technologies + separator + new_technologies);
            candidates1.setKnowledge(old_knowledge + separator + new_knowledge);
            candidates1.setNote(old_note + separator + new_note);

            return new ResponseEntity<>(candidatesRepository.save(candidates1), HttpStatus.OK);

        } else {
            logger.log(Level.WARNING, "NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // vymaze konkretniho (podle ID) kandidata
    @DeleteMapping("/candidates/{id}")
    public ResponseEntity<HttpStatus> deleteCandidatesDataById(@PathVariable("id") long id) {
        try {
            candidatesRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.log(Level.WARNING, "INTERNAL SERVER ERROR");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // vymaze kompletne celou databazi
    @DeleteMapping("/candidates")
    public ResponseEntity<HttpStatus> deleteAllCandidates() {
        try {
            candidatesRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.log(Level.WARNING, "INTERNAL SERVER ERROR");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}