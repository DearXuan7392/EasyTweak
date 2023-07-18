## Easy Tweak

There some optional features.

**Only server needs to install**

**Restart when you modify configuration file: ``easytweak.yml``**

> If you need any other features, please contact me. Only server-side.

## MobGriefing

### Disable Enderman Pick Up

``` yaml
Disable_Enderman_Pick_Up = false
```

### Disable Creeper Griefing

``` yaml
Disable_Creeper_Griefing = false
```

### Disable Ghast Griefing

``` yaml
Disable_Ghast_Griefing = false
```

## GameRule

### Disable Nether Portal Spawning Zombified Piglin

``` yaml
Disable_Nether_Portal_Spawning_Zombified_Piglin = false
```

### Disable Bat Spawning

``` yaml
Disable_Bat_Spawning = false
```

### Disable Ghast Spawning Above Bedrock

Disable ghast spawning above bedrock(y >= 128) in nether

``` yaml
Disable_Ghast_Spawning_Above_Bedrock = false
```

### Disable Trample

Don't trample farmland

``` yaml
Disable_Trample = false
```

### Player Head

Getting player head by killing him/her

``` yaml
Drop_Player_Head = false
```

### Fast Budding Amethyst

Increase the growth speed by 10 times

``` yaml
Fast_Budding_Amethyst = false
```

### Superb Medical Skills

Only needs **4 ~ 10** second to save a zombie villager

``` yaml
Superb_Medical_Skill = false
```

## BetterSpawner

### Enable

Only when this item is set to ``true``, will the other modifications take effect

The following features will take effect immediately:

1. Spawners can be mined, but they no longer drop XP. Instead, they drop the spawn egg inside;

2. Right-clicking a spawner with a spawn egg will replace the original spawn egg with the one in your hand, and the spawn egg inside will drop;

3. Placing a torch or soul torch on the spawner will disable it;

4. When a spawner receive redstone power, it will continuously spawn mobs regardless of player proximity;

5. There is a chance for mobs to drop their spawn egg when player kill him. The initial chance is **1%**. "Fortune" enchantment can increase the chance to **5%**, **10%**, **15%**. *(5% × level)*;

```yaml
Enable = false
```

### Player Range

Only players entry the range can the spawner working

**When spawner receive redstone power, it will ignore the range**

``` yaml
Player_Range = 16
```

### Max Nearby

If the number of mobs exceeds the limit, spawner will stop working

``` yaml
Max_Nearby = 6
```

### Min Delay

Min delay between two spawns

``` yaml
Min_Delay = 200
```

### Max Delay

Max delay between two spawns

``` yaml
Max_Delay = 800
```

### Spawner Range (Width)

Mobs will spawn in this range

``` yaml
Spawner_Range = 8
```

### Spawner Height (Developing)

Mobs will spawn in this height

``` yaml
Spawner_Height = 3
```

### Spawner Count

The number of mobs attempted to spawn each time

``` yaml
Spawner_Count = 4
```

## CommandReg

### Server tick

Using ```/tick``` command, to check the system info, avg tick time and memory used

When you can't see GUI screen, this is a better way to see the avg tick

``` yaml
TICK_LISTENER = false
```

## StackableItem

Modify the max stack count to **64** on the server-side.

If client does not have this MOD installed, there may be data inconsistency, but it will be immediately corrected to the server data and will not affect the gameplay.

### Potions

``` yaml
Potions = false
```

## LootTable

### Blaze Always Drop Blaze Rod

``` yaml
Always_Drop_Blaze_Rod = false
```

### Can Budding Amethyst Be Mined

``` yaml
Mineable_Budding_Amethyst = false
```

## Recipes

### Enchanted Golden Apple

8 * Gold Block + Apple

``` yaml
Enchanted_Golden_Apple = false
```

### Budding Amethyst

4 * Amethyst

``` yaml
Budding_Amethyst = false
```