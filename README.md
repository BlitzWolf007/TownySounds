# TownySounds

A lightweight plugin that lets you configure custom sounds for Towny events.

## Features
- Play different Minecraft sounds when Towny events occur.
- Fully configurable per event via `config.yml`.
- Supports volume and pitch control.

## 🔧 Command
**/townysounds reload** — Reloads the plugin configuration.  
Requires the `townysounds.reload` permission.

## ⚙️ Example Configuration

```yaml
NationEvents:
  NationAddEnemyEvent:
    sound: ENTITY_ENDER_DRAGON_GROWL
    volume: 1.5
    pitch: 1.0

TownEvents:
  NewTownEvent:
    sound: ENTITY_VILLAGER_CELEBRATE
    volume: 0.8
    pitch: 1.2

OtherEvents:
  NewDayEvent:
    sound: ENTITY_PLAYER_LEVELUP
    volume: 1.0
    pitch: 1.0
```
