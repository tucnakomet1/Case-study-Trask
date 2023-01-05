Hlavní třída je `src/main/java/com/example/case_study/CaseStudy.java`

Databáze h2, bez hesla:

```text
url:       http://localhost:8989/h2-console
JDBC URL:  jdbc:h2:mem:case_study
User Name: sa
Password:  
```

## API

| GET                                                                                | PUT                                                                      | POST                                | DELETE                               |
|------------------------------------------------------------------------------------|--------------------------------------------------------------------------|-------------------------------------|--------------------------------------|
| ![vypsani vsech info](Pic/GET.png)                                                 | ![uprava uchazece](Pic/PUT.png)                                          | ![pridani uchazece](Pic/POST.png)   | ![smazani uchazece](Pic/DELETE.png)  | 
| Vypíše všechny uchazeče, včetně technologií                                        | **Přepíše** původní informace konkrétního uživatele                      | Přidá uchazeče                      | Smaže konkrétního uchazeče           |
| ![vypsani vsech info](Pic/GET_1.png)                                               | ![uprava uchazece](Pic/PUT_1_.png)                                       |                                     |                                      |
| Vypíše konkrétního uchazeče, včetně technologií                                    | **Přidá** k původním technologiím konkrétního uživatele nové technologie |                                     |                                      |
| ![vypsani vsech info](Pic/GET_1_.png)                                              |                                                                          |                                     |                                      |
| Vypíše technologie konkrétního uchazeče                                            |                                                                          |                                     |                                      |  
| ![ukazka vstupu](Pic/db_none.png) ![vypsani tech zadny kandidat](Pic/GET_none.png) |                                                                          |                                     |                                      |
| Vypíše technologie, které nemá žádný kandidát                                      |                                                                          |                                     |                                      |  

