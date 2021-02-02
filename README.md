# Inhoud Opdracht  

Voor het eindproject heb je carte blanche over je onderwerp. Je mag zelf een onderwerp verzinnen waarvoor je gegevens wenst bij te houden of van een bestaande api data binnentrekken. De stad Brussel heeft bv. een hele lijst aan open data sets die je vrij mag aanspreken. https://opendata.brussel.be/page/home/

De app moet volgende zaken bevatten/kunnen uitvoeren:

Data in een lokale database plaatsen om alles ook offline beschikbaar te maken
Een Google map weergeven met alle data OF De data op een alternatieve manier weergeven bv. In een recyclerview met searchbar/zoekfilters
Detail schermen weergeven bij de belangrijkste data

# AppTodoPlanning

## Omschrijving  
In kader van de opdracht Android development heb ik deze Applicatie gemaakt waar je makkelijk een overzicht krijgt van alle taken die je moet doen. 
Via de eerste view kan heb je overzicht wat je kan doen in de applicatie: 
   * Items toevoegen 
   * Naar de Calendar view 
   * Naar de list view 

### items toevoegen 
Er moet een titel ingevoegd worden, een korte beschrijving van de taak die er moet uitgevoerd worden. 
Voor wie de taak is kan je de naam toevoegen van de persoon. 
vervolgens ook de start datum en deadline van de taak kan je kiezen uit een calendar ( datepicker) . 
Deze view wordt ook gebruikt voor het update van een item. 

### Calendar view 
De kalender wordt gebruikt als filter voor de recycle view onderaan. Zo wordt er gefilterd en gezocht naar items die gelinkt zijn met het geselecteerde datum. 
Er wordt gekeken naar de begin datum en eind datum maar ook de datums er tussen.
Je kan via de 3 puntjes die naast de taak te zien zijn klikken om dit item te editeren. 

### List view 
is een recycle view , een platte lijst van alle items die in de datebase staan. 
Je kan deze via de list deleten (op het kruisje) en editeren (via de 3 puntjes) 

### ToDo 
* Status aanpassen na het aanvinken van de taak. ( Dit werkt nog niet omdat ik nog niet de juiste manier heb gevonden om deze op te laten slagen in datebase) 
* Zoek filter
