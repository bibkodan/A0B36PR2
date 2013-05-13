A0B36PR2
========


Semestrálna práca pre predmet A0B36PR2

SUDOKU

Zadanie: 

Program načíta novú hru / hru uloženú. Pri vytváraní novej hry si užívateľ zzvolí obtiažnosť 
Užívateľ môže zadať ťah, u ktorého program overí jeho prístupnosť (vzhľadom na pravidlá hry). 
Program môže navrhnúť ťah vedený k riešeni. Použitie grafického rozhrania.

--------------------------------------------------------------------------------------------

Pravidlá hry:

Cieľom je doplniť chýbajúce číslice od 1 do 9 podľa nasledujúcich pravidiel. Na začiatku
riešenia sú niektoré políčka vyplnené číslicami. Vyplnené číslice sú rozmiestnené tak, aby pre
dané rozmiestnenie existovalo iba jediné riešenie. Hrací plán sa skladá z 9x9 políčok
rozdelených na 9 podoblastí s 3x3 políčkami. V každej podoblasti sa každá číslica má
vyskytnúť práve raz. V každom stĺpci a v každom riadku sa má každá číslica vyskytnúť práve
raz.

--------------------------------------------------------------------------------------------

Ovládanie programu:

Program sa ovláda za pomoci myšky, kde program reaguje na kliknutie. Pri spustený program, má užívateľ na výber dve hlavné 
možnosti (nová hra / pokračovanie v rozohratej hre). Priz zvolení možnosti hry novej si užívateľ volí obtiažnosť.
Po voľbe typu hry sa otvára nové onko, ktoŕe je zároveň oknom hracím. V pravej časti okna sa nachádza uživateľské rozhranie.
Kde sú k dispozícii čísla od 1 do 9, ktoré užívateľ zadáva. Tlačitko "help", ktoré dosaí na vopred zvolenú pozíciu čislo
namiesto hráča. Tlačítko "( - )", ktoré má za úlohu z hracieho poľa vymazať čislo ak si užívateľ rozmyslel ťah. V pravom 
hornom rohu sa takisto nachádza textová oblasť, na ktorej sa zobrazujú pokyny pre hráča. Zvyšný priesto okna zaberá 
sudoku puzzle. V puzzle si užívateľ volí miesto, kde chce zadať číslo. Na dané miesto po kliknutí vyberá z tlačítok, ktoré 
má na pravej strane k dispozícii. Užívateľ nemôže zmeniť číslo, ktoré je vygenerované na začiatku hry ako prvotné zadanie.

--------------------------------------------------------------------------------------------

Zdrojový kód programu:


Trieda Sudoku_BibkoDan (main):

V tejto triede sa vytvára Objekt z triedy Start. Vytvorí sa okno ktoré zobrazí užívateľovi možnosť hri novej/uloženej.
Poprípade obtiažnosť.

..............

Trieda Sudoku:

Táto trieda zaručuje na začiatku hry vygenerovania sudoku puzzle, ktoré určite bude mať
riešenie. Následne z vygenerovaného puzzle podľa parametru obtiažnosti zakrýva určitý počet čísel. Vytvorí dve ďalšie
polia. Jedno pre pôvodné zadanie a druhé pole pre hranie. V triede sú taktiež metódy, ktoré
kontrolujú hranie hry podľa pravidiel sudoku. Táto trieda zaručuje ukladanie a načítanie uloženej hry.

..............

Trieda Start:

Na základe parametrov tejto triedy je vytvárané prvé okne pre voľbu typu a obtiažnosti hry. V triede sa nachádzajú dve
vnútorné triedy implementujúce ActionListener. Prvá "NovaHraListener" má na starosti zachytenie voľby uživateľa pre typ 
hry z dvoch volieb a to hry novej alebo pokračovanie v uloženej hre. Pri voľbe pokračovania sa vytvára objekt triedy Window
s parametrom "0". Trieda druhá "ObtiaznostListener" tá sa využíva pri voľbe hry novej. A zachytáva voľbu obtiažnosti novej
hry. Pri kliknutí na jednu z troch možností, vytvára objekt triedy Window s parametrom "25" pre obtiažnosť ľahkú, "45" 
pre obtiažnosť strednú a "58" pre obtiažnosť ťažkú.

..............

Trieda Window:

Na základe parametrov tejto triedy je vytvárané hracie okno. V tejto triede sa vytvára objekt triedy Sudoku, s parametrom 
obtiažnosti. Nachádzajú sa tu taktiež tri vnútorné triedy implementujúce ActionListener. Privátna trieda "HernePoleListener"
ma za úlohu zachytávať kliknutie na hracie pole, kde chce užívateľ zadať číslo. Umožňuje kliknúť len na políčka kde nebolo 
vygenerované čislo na začiatku hry. Privátna rieda "PomocneTlacitkaListener" slúžia na zachytenie kliknutia pre tlačítka na pomoc a 
vymazanie zadaného čísla. Posledná privátna trieda "ObsluhaCiselListener" zachytáva kliknutie na čísla od 1 do 9, ktoré 
ma užívateľ k dispozícii na pravej strane hracieho poľa a ktoré zadáva do hracieho puzzle. 

..............

Trieda Tlacitko:

Táto trieda dedí z tridey JButton. Slúži na vytváranie tlačítok. Konštruktor tejto triedy vytvára tlačítko JButton a nastavuje
mu text podľa príslušného parametru. Preťažený konštruktor danému tlačítku ešte nastavý pozíciu.

