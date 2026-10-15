# frais-de-port

Bibliothèque Java de calcul des frais de port d'une boutique en ligne.
Elle est testée par 12 tests JUnit, tous verts sur le poste de leurs auteurs.
Mais personne ne les lance avant de livrer, et le dernier bug est parti en production un vendredi.

## Ce que fait la bibliothèque

`FraisDePort.calculer(poidsGrammes, zone, montantCommande)` renvoie le montant des frais de port
d'un colis, en fonction de son poids, de sa zone de livraison (`FRANCE`, `EUROPE`, `MONDE`)
et du montant de la commande.

```java
FraisDePort fraisDePort = new FraisDePort();
Montant frais = fraisDePort.calculer(800, Zone.EUROPE, Montant.euros(45.00));
System.out.println(frais); // 12,50 €
```

### Grille tarifaire

| Poids du colis | France  | Europe  | Monde   |
|----------------|---------|---------|---------|
| jusqu'à 250 g  | 3,90 €  | 8,50 €  | 14,00 € |
| jusqu'à 1 kg   | 5,90 €  | 12,50 € | 22,00 € |
| jusqu'à 5 kg   | 8,90 €  | 19,90 € | 39,00 € |
| jusqu'à 30 kg  | 14,90 € | 34,90 € | 79,00 € |

Règles associées :

- Au-delà de 30 kg, le colis est refusé : `IllegalArgumentException("colis trop lourd")`.
- Un poids inférieur ou égal à 0 est refusé : `IllegalArgumentException("poids invalide")`.
- **Franco de port** : pour une commande de 60,00 € ou plus, le port est offert (0 €), **en France uniquement**.

## Lancer les tests

Prérequis : JDK 17 ou plus, Maven 3.9 ou plus.

```bash
mvn test
```

Résultat attendu : `Tests run: 12, Failures: 0, Errors: 0, Skipped: 0`.

## Ce qu'il manque

**Aucun pipeline d'intégration continue.** Rien ne lance les tests quand quelqu'un pousse du code
ou ouvre une pull request, et rien n'empêche de fusionner une modification qui casse un test.
Les tests ne protègent personne tant qu'ils ne tournent que si on y pense.
