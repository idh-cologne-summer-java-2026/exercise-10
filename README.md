Programmierung 2

# Übung: Queue und Set

Klonen Sie dieses Repository direkt in Eclipse und importieren Sie das Projekt. Bearbeiten Sie beide Aufgaben in Ihrem eigenen Branch.

## Aufgabe 1

In der Klasse `idh.java.Hangman` finden Sie ein Gerüst für eine einfache Hangman-Implementierung. Der Konstruktor bekommt das geheime Wort übergeben, die Methode `play()` steuert das eigentliche Spiel und ist bereits fertig implementiert.

Ergänzen Sie dort die drei mit `TODO` markierten Methoden:

- `alreadyGuessed(char letter)` soll prüfen, ob ein Buchstabe bereits geraten wurde.
- `buildDisplayWord()` soll das Wort mit bereits geratenen Buchstaben und Unterstrichen für den Rest zusammensetzen.
- `isWordFullyGuessed()` soll prüfen, ob alle Buchstaben des Wortes schon geraten wurden.

Die geratenen Buchstaben werden in einem `Set<Character>` gespeichert. Überlegen Sie sich, warum sich dafür ein `Set` und keine `List` anbietet.

Testen Sie Ihre Implementierung, indem Sie das Programm mit einem Wort als Kommandozeilenargument starten, z.B. `java idh.java.Hangman COMPUTER`.

Committen und pushen Sie wie üblich.

## Aufgabe 2

In der Klasse `idh.java.RPSTournament` finden Sie ein Turnier-Gerüst für Schere-Stein-Papier. Alle Spieler:innen stehen zu Beginn in einer `Queue`. Es spielen immer die ersten beiden aus der Queue gegeneinander; die/der Gewinner:in geht ans Ende der Queue zurück, die/der Verlierer:in scheidet aus. Bei einem Unentschieden kommen beide zurück in die Queue.

Ergänzen Sie die mit `TODO` markierten Methoden:

- `runTournament(Queue<String> players)` steuert den Turnierablauf, bis nur noch eine Person übrig ist.
- `playRound(String player1, String player2)` lässt zwei Spieler:innen gegeneinander antreten und bestimmt die/den Gewinner:in.
- `randomMove()` liefert einen zufälligen Zug.
- `beats(String move1, String move2)` prüft, ob ein Zug einen anderen schlägt.

Überlegen Sie sich beim Implementieren von `runTournament`, warum hier eine `Queue` und kein `Stack` das passende Datenstruktur ist.

Testen Sie Ihre Implementierung, indem Sie das Programm einfach ausführen (die Spielerliste ist in `main` schon vorgegeben).

Committen und pushen Sie wie üblich.