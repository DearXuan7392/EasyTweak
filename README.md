## Easy Tweak

There some optional features.

**Only server needs to install**

**Restart when you modify configuration file: ``easytweak.yml``**

> If you need any other features, please contact me. Only server-side.

## Configuration & Explain

``` yaml
MobGriefing:
  Disable_Enderman_Pick_Up: false
  Disable_Creeper_Griefing: false
  Disable_Ghast_Griefing: false
  Disable_Zombie_Break_Door: false
GameRule:
  Disable_Nether_Portal_Spawning_Zombified_Piglin: false
  Disable_Bat_Spawning: false
  # Disable ghast spawning above bedrock(y >= 128) in nether
  Disable_Ghast_Spawning_Above_Bedrock: false
  # Don't trample farmland
  Disable_Trample: false
  # Getting player head by killing him/her
  Drop_Player_Head: false
  # Increase the growth speed by 10 times
  Fast_Budding_Amethyst: false
  # Only needs **4 ~ 10** second to save a zombie villager
  Superb_Medical_Skill: false
# The following features will take effect immediately:
#     1. Spawners can be mined, but they no longer drop XP. 
#        Instead, they drop the spawn egg inside;
#     2. Right-clicking a spawner with a spawn egg will replace the original spawn egg with the one in your hand, 
#and the spawn egg inside will drop;
#     3. Placing a torch or soul torch on the spawner will disable it;
#     4. When a spawner receive redstone power, it will continuously spawn mobs regardless of player proximity;
#     5. There is a chance for mobs to drop their spawn egg when player kill him. The initial chance is 1%. 
#        "Looting" enchantment can increase the chance to 5%, 10%, 15%. (5% × level);
BetterSpawner:
  # Only when this item is set to true, will the other modifications take effect
  Enable: false
  # Only players entry the range can the spawner working
  Player_Range: 16
  # If the number of mobs exceeds the limit, spawner will stop working
  Max_Nearby: 6
  # Min delay between two spawns
  Min_Delay: 200
  # Max delay between two spawns
  Max_Delay: 800
  Spawner_Range: 8
  Spawner_Height: 3
  Spawner_Count: 4
  # Spawner can spawn zombies in each light level
  Disable_Light_Restriction: false
  # Spawner can spawn zombies in water
  Disable_Fluid_Restriction: false
CommandReg:
  # Check the system info, avg tick time and memory used
  Command_Tick: false
Enchantment:
  # Silk touch still conflicts with Fortune.
  Disable_Enchantments_Conflict: false
  # Bow or crossbow with Infinite do not need arrows in the backpack to be used.
  Real_Infinity: false
  # Enchantments of the bow are still work on crossbow.
  Better_Crossbow: false
  # The max cost in anvil is 39.
  Never_Expensive: false
  # The tool with Mending will directly deduct XP instead of durability.
  Better_Mending: false
# It will be better if client has this mod
StackableItem:
  Potions: 1
  Snowball: 16
  Bed: 1
LootTable:
  # Blaze can drop blaze rod although damage source is not player
  Always_Drop_Blaze_Rod: false
  # Wither skeleton can drop it's skull although damage source is not player
  Wither_Skeleton_Drop_Skull: false
  Mineable_Budding_Amethyst: false
MobActivity:
  Disable_Piglin_Zombify: false
  Creeper_Attack_Snow_Golem: false
Recipes:
  # 8 x Gold block + 1 x Apple = Enchanted Golden Apple
  Enchanted_Golden_Apple: false
  # 4 x Amethyst Block = Budding Amethyst
  Budding_Amethyst: false
```