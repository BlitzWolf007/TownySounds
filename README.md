# TownySounds

A lightweight plugin that lets you configure custom sounds for Towny events.

## Features

- Play different Minecraft sounds when Towny events occur.
- Fully configurable per event via `config.yml`.
- Supports volume and pitch control.

### 🔧 Command

**/townysounds reload** — Reloads the plugin configuration.  
Requires the `townysounds.reload` permission.

### ⚙️ Example Configuration

sound is one the same sounds that autocomplete in the `/playsound` command

the event key (eg NewDayEvent) is a Towny event, these can be found in the javadocs such as

[Main Events](https://townyadvanced.github.io/Towny/javadoc/prerelease/com/palmergames/bukkit/towny/event/package-summary.html)

[Town Events](https://townyadvanced.github.io/Towny/javadoc/prerelease/com/palmergames/bukkit/towny/event/town/package-summary.html)

[Nation Events](https://townyadvanced.github.io/Towny/javadoc/prerelease/com/palmergames/bukkit/towny/event/nation/package-summary.html)

the path is methods available in the event also available on the javadoc, the path is passed into the notifier. available notifiers are `nation`, `town`, and `everyone`

the sound can be set to none to not play, or the volume can be set to 0, or it can just be removed for the config

```yaml
events:
  NewDayEvent:
    sound: minecraft:entity.player.levelup
    volume: 1.0
    pitch: 1.0
    notifier: everyone
  NationKingChangeEvent:
    sound: minecraft:entity.player.levelup
    volume: 1.0
    pitch: 1.0
    notifier: nation
    path: getNation
  NationInviteTownEvent:
    sound: minecraft:entity.player.levelup
    volume: 1.0
    pitch: 1.0
    notifier: nation
    path: getInvite.getSender
  TownAddResidentEvent:
    sound: minecraft:entity.player.levelup
    volume: 1.0
    pitch: 1.0
    notifier: town
    path: getTown
```
