## Easy Tweak

Just two recipes...

and some optional feature

**Only server needs to install**

**Restart when you modify configuration file: ``easytweak.json``**

> If you have any other features you would like to implement, please contact me. Only server side.

## Recipes

1. 8 × **Block of Golden** + 1 × **Apple**  ==>  **Enchanted Golden Apple**
2. 4 × **Block of Amethyst**  ==> **Budding Amethyst**

## Optional

### Spawn Zombified Piglin

Will nether portal spawn zombified piglin

``` yaml
NETHER_PORTAL_NEVER_SPAWN_ZOMBIE_PIGLIN = true
```

### Ender Man Pick Up

Can ender man pick up block

``` yaml
ENDER_MAN_PICK_UP = true
```

### Creeper Destroy Block

Can creeper destroy block

``` yaml
CREEPER_DESTROY_BLOCK = true
```

### Spawn Bat

Can bat spawn in the world

``` yaml
SPAWN_BAT = true
```

### Spawn Ghast

Can ghast spawn above bedrock(y >= 128) in nether

``` yaml
SPAWN_GHAST_WHEN_Y_GREATER_128 = true
```

### Ghast Destroy Block

Can ghast destroy block

``` yaml
GHAST_DESTROY_BLOCK = true
```

### No Trample

Don't trample farmland

``` yaml
NO_TRAMPLE = false
```

### Better Spawner

There some features bellow:

1. Spawners can be mined, but they no longer drop XP. Instead, they drop the mob egg inside.

2. Right-clicking a spawner with a mob egg will replace the original egg with the one in your hand, and the original mob egg will drop.

3. Placing a torch or soul torch on the spawner will disable it.

4. When a spawner receive redstone power, it will continuously spawn mobs regardless of player proximity. If mobs are not spawning for a long time, please check if the area around the spawner meets the spawning requirements.

5. There is a chance for mobs to drop its egg when player kill it. The initial chance is **1%**. "Fortune" enchantment can increase the chance to **5%**, **10%**, **15%**. *(5% × level)*

``` yaml
BETTER_MOB_SPAWNER = false
```

### Spawner Player Range (Needs: Better Spawner)

Only player entry the range can the spawner working

**When spawner receive redstone power, it will ignore the range**

``` yaml
SPAWNER_PLAYER_RANGE = 16
```

### Spawner Max Nearby (Needs: Better Spawner)

If the number of mob exceeds the limit, spawner will stop working

``` yaml
SPAWNER_MAX_NEARBY = 6
```

### Min Spawn Delay (Needs: Better Spawner)

Min time between two spawns

``` yaml
SPAWNER_MIN_DELAY = 200
```

### Max Spawn Delay (Needs: Better Spawner)

Max time between two spawns

``` yaml
SPAWNER_MAX_DELAY = 800
```

### Spawner Range (Needs: Better Spawner)

The mob will spawn in this range

``` yaml
SPAWNER_RANGE = 8
```

### Spawner Height (Developing)

The mob will spawn in this height

``` yaml
SPAWNER_HEIGHT = 3
```

### Spawner Count (Needs: Better Spawner)

The number of mob attempted to spawn each time

``` yaml
SPAWNER_COUNT = 4
```

### Server Tick

Using ```/tick``` command, to check the system info, avg tick time and memory used

When you can't see GUI screen, this is a better way to see the avg tick

``` yaml
TICK_LISTENER = false
```

### Player Head

Getting player head by killing him/her

``` yaml
PLAYER_HEAD = false
```

### Fast Budding Amethyst

Increase the growth speed by 10 times

``` yaml
FAST_BUDDING_AMETHYST = false
```

### Superb Medical Skills

Only needs **4 ~ 10** second to save a zombie villager

``` yaml
SUPERB_MEDICAL_SKILLS = false
```

### Stackable Potions

Modify the max stack count of potions to **64** on the server side.

If player does not have this MOD installed, there may be data inconsistency, but it will be immediately corrected to the server data and will not affect the actual gameplay.

``` yaml
STACKABLE_POTIONS = false
```
