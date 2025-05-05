# SlowCompat

Ein Minecraft-Plugin, das benutzerdefinierte Funktionen wie Gebietsteleportation, Werbenachrichten und Rechteverwaltung bietet. Dieses Plugin bietet eine einfache und konfigurierbare Möglichkeit, interaktive GUIs und Features auf deinem Server bereitzustellen.

## Features

- **Gebiete-Teleportation**: Spieler können sich zu vordefinierten Orten teleportieren lassen.
- **Werbung**: Spieler können Werbenachrichten an andere senden.
- **Rechteverwaltung**: Spieler können ihre Berechtigungen über ein GUI anzeigen lassen.

## Installation

1. Lade die neueste Version von `SlowCompat.jar` von der [Releases-Seite](https://github.com/Kiritosky/SlowCompat/releases) herunter.
2. Kopiere die Datei `SlowCompat.jar` in den `plugins`-Ordner deines Minecraft-Servers.
3. Starte den Server neu, damit das Plugin geladen wird.

## Befehle und Berechtigungen

### Befehle

| Befehl          | Beschreibung                                                  | Verwendung                 | Benötigte Berechtigung       |
|------------------|--------------------------------------------------------------|----------------------------|------------------------------|
| `/gebiete`      | Öffnet ein GUI zum Teleportieren zu verschiedenen Gebieten.   | `/gebiete`                 | `gebiete.open`              |
| `/werbung`      | Sendet eine Werbenachricht an alle Spieler.                   | `/werbung <Nachricht>`     | `werbung.command`           |
| `/rechte`       | Zeigt die Rechte des Spielers an.                             | `/rechte`                  | `slowrights.rechte`         |

### Berechtigungen

| Berechtigung         | Beschreibung                                                             | Standardwert |
|-----------------------|-------------------------------------------------------------------------|--------------|
| `gebiete.open`        | Erlaubt es dem Spieler, das Gebiets-GUI zu nutzen.                     | `true`       |
| `werbung.command`     | Erlaubt es dem Spieler, Werbenachrichten zu senden.                    | `op`         |
| `werbung.bypass`      | Erlaubt es dem Spieler, die Cooldown-Zeit für Werbenachrichten zu umgehen. | `op`         |
| `slowrights.rechte`   | Erlaubt es dem Spieler, die Rechte-GUI zu öffnen.                      | `true`       |

## Konfiguration

Die `config.yml`-Datei bietet Anpassungsmöglichkeiten für Gebiete und Items. Hier ein Auszug der Konfiguration:

### Gebiete

```yaml
# This is the main configuration file for the plugin.

#Gebiete
gebiete:
  gebiet1:
    slot: 0
    item: DIAMOND_SWORD
    name: "&bTest"
    lore:
      - "&7Testtest"
      - "&7Klicke zum Teleportieren"
    x: -19
    y: 50
    z: 31
    world: world

  gebiet2:
    slot: 1
    item: APPLE
    name: "&cApfelland"
    lore:
      - "&7Ein Land voller Äpfel"
      - "&7Klicke zum Teleportieren"
    x: 100
    y: 70
    z: 100
    world: world


#Items
items:
  gold_rank:
    slot: 0
    item: GOLD_BLOCK
    name: "&e&lGOLD RANG"
    lore:
      - "§cDieser Kostet 101010$"
  fly_permission:
    slot: 1
    item: GREEN_DYE
    name: "&7/fly"
    lore:
      - "§cDamit kannst du Fliegen."
