# No Freeze

Adds a game rule that stops water from freezing, ever. In the mod settings you can remove the game rule
and make it permanent instead. Fabric mod for **Minecraft 26.3**.

## Install

1. Fabric Loader 0.19.5 or newer for 26.3 (Java 25)
2. [Fabric API](https://modrinth.com/mod/fabric-api) `0.163.0+26.3` or newer
3. Drop `nofreeze-1.0.0.jar` into your `mods` folder
4. Optional: [Mod Menu](https://modrinth.com/mod/modmenu) for the settings screen button

Needed on the server (or in singleplayer). Other players do not need it on their client.

## The game rule

```
/gamerule nofreeze:water_cannot_freeze true
/gamerule nofreeze:water_cannot_freeze false
```

It is **on by default**, in every world. It also shows up in the game rule screens (world creation and the
in-game one), under Updates, as "Water Cannot Freeze". You set it per world, like any other game rule.

While it is on, water never turns into ice:

- cold weather freezing lakes, rivers and oceans (the usual way)
- the freezing pass of **world generation**, so chunks generated while it is on get no ice sheets on frozen
  oceans, and no frozen lake surfaces
- **Frost Walker** boots (they turn water into frosted ice, which counts) — this one has its own switch, below

Ice that already exists is left alone, and icebergs are not touched (they are ice structures, not frozen water).

## Settings

Open them from Mod Menu (Config button on No Freeze), or bind a key under *Controls* → *No Freeze*.

| Setting | What it does |
| --- | --- |
| **Permanent Mode** | On: water can never freeze, in every world, and the game rule is **removed**: it disappears from `/gamerule` and from the game rule screens. Off: the game rule is back and you choose per world. Default off. |
| **Block Frost Walker** | While water cannot freeze, Frost Walker boots cannot make frosted ice either. Turn it off to let them work. Default on. |

Changes to Permanent Mode apply **the next time you open a world** (the game builds a world's list of game rules
when it loads). No game restart is needed. Switching it off mid-session keeps water from freezing until you
reopen the world, because the rule's default is on.

A world forgets its saved value for the rule while Permanent Mode is on. If you turn it off again, the rule
comes back with its default (on). Nothing else is touched: the world's other game rules are kept.

## Config file and servers

Settings are saved in `config/nofreeze.json`:

```json
{
  "permanent": false,
  "blockFrostWalker": true
}
```

The settings screen edits this file for singleplayer and LAN worlds. A **dedicated server** has no screen: edit
its own `config/nofreeze.json` and restart it.

## Build

JDK 25 is required (`JAVA_HOME`), then:

```
./gradlew build
```

The jar is written to `build/libs/`.
