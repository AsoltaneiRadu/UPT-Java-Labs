package com.example.demo.controller;

import com.example.demo.models.Carte;
import com.example.demo.repository.CarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class CarteWebController {
    @Autowired
    CarteRepository carteRepository;

    @GetMapping
    public String listaCarti(Model model) {
        List<Carte> lista = carteRepository.findAll();
        model.addAttribute("listaCarti", lista);
        model.addAttribute("mesaj", "Lista cartilor preluate prin repository");
        return "carti";
    }

    @PostMapping
    public String operatii(
            @RequestParam("isbn") String ISBN,
            @RequestParam("titlu") String titlu,
            @RequestParam("autor") String autor,
            @RequestParam(value = "Adauga", required = false) String adauga,
            @RequestParam(value = "Sterge", required = false) String sterge,
            @RequestParam(value = "Modifica", required = false) String modifica,
            @RequestParam(value = "Filtreaza dupa autor", required = false) String filtreaza,
            Model model) {
        String mesaj = "";
        List<Carte> lista = carteRepository.findAll();
        if (adauga != null) {
            if (ISBN.isEmpty() || titlu.isEmpty() || autor.isEmpty()) {
                mesaj = "Nu ati adaugat toate datele cartii";
            } else {
                if (carteRepository.existsById(ISBN))
                    mesaj = "Exista deja o carte cu acest ISBN";
                else {
                    carteRepository.save(new Carte(ISBN, titlu, autor));
                    mesaj = "Carte adaugata cu succes";
                    lista = carteRepository.findAll();

                }
            }

        } else if (modifica != null) {
            if (ISBN.isEmpty()) {
                mesaj = "Introduceti ISBN-ul cartii";
            } else {
                Optional<Carte> carteOpt = carteRepository.findById(ISBN);
                if (carteOpt.isPresent()) {
                    Carte carte = carteOpt.get();
                    if (!titlu.isEmpty())
                        carte.setTitlu(titlu);
                    if (!autor.isEmpty())
                        carte.setAutor(autor);
                    carteRepository.save(carte);
                    mesaj = "Carte modificata cu succes";
                    lista = carteRepository.findAll();
                } else {
                    mesaj = "Nu exista carte cu acest ISBN";
                }
            }

        }
        else if(sterge!=null)
        {
            if(ISBN.isEmpty())
                mesaj="Introduceti ISBN-ul cartii";
            else
            {
                if(carteRepository.existsById(ISBN))
                {
                    carteRepository.deleteById(ISBN);
                    mesaj="Carte stearsa cu succes";
                    lista=carteRepository.findAll();
        }else{
                    mesaj="Nu exista carte cu acest ISBN";
                }
            }
                }
        else if(filtreaza!=null)
        {
            if(autor.isEmpty())
                mesaj="Introduceti autorul cartii";
            else
            {
                lista=carteRepository.findByAutor(autor);
                if(lista.isEmpty())
                    mesaj="Nu exista carti cu acest autor";
            }
        }
        model.addAttribute("listaCarti", lista);
        model.addAttribute("mesaj", mesaj);
        return "carti";
    }
}
