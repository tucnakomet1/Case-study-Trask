Hlavní třída je `src/main/java/com/example/case_study/CaseStudy.java`

Databáze h2, bez hesla:

```text
url:       http://localhost:8989/h2-console
JDBC URL:  jdbc:h2:mem:case_study
User Name: sa
Password:  
```

## API

| GET                                             | PUT                                                                      | POST                            | DELETE                              |
|-------------------------------------------------|--------------------------------------------------------------------------|---------------------------------|-------------------------------------|
| ![vypsani_vsech_info](Pic/GET.png)              | ![uprava_uchazece](Pic/PUT.png)                                          | ![pridani_uchazece](Pic/POST.png) | ![smazani_uchazece](Pic/DELETE.png) | 
| Vypíše všechny uchazeče, včetně technologií     | **Přepíše** původní informace konkrétního uživatele                      | Přidá uchazeče                  | Smaže konkrétního uchazeče          |
| ![vypsani_vsech_info](Pic/GET_1.png)            | ![uprava_uchazece](Pic/PUT_1_.png)                                       |                                 |                                     |
| Vypíše konkrétního uchazeče, včetně technologií | **Přidá** k původním technologiím konkrétního uživatele nové technologie |                                 |                                     |
| ![vypsani_vsech_info](Pic/GET_1_.png)           |                                                                          |                                 |                                     |
| Vypíše technologie konkrétního uchazeče         |                                                                          |                                 |                                     |  

