## Easy Tweak

提供了大量可选功能, 需在配置文件中手动开启, 无法在游戏中修改.

仅需服务端安装, 客户端无需安装即可享受所有功能.

> 如果你有其他想要实现的内容, 请联系我. 仅限服务端内容, 即客户端无需安装 mod 也可以进入.

## 生物破坏(MobGriefing)

### 禁止末影人搬动物品

``` yaml
Disable_Enderman_Pick_Up = false
```

### 禁止古力怕破坏方块

无论是否启用, 爆炸总会正常造成伤害

``` yaml
Disable_Creeper_Griefing = false
```

### 禁止恶魂破坏方块

``` yaml
Disable_Ghast_Griefing = false
```

## 游戏规则(GameRule)

### 禁止地狱门生成僵尸猪人

``` yaml
Disable_Nether_Portal_Spawning_Zombified_Piglin = false
```

### 禁止生成蝙蝠

``` yaml
Disable_Bat_Spawning = false
```

### 禁止在基岩上层生成恶魂

该功能主要用于防止地狱基岩上层(≥128)的刷怪塔生成恶魂

``` yaml
Disable_Ghast_Spawning_Above_Bedrock = false
```

### 禁止踩踏

耕地不再被踩坏

``` yaml
Disable_Trample = false
```

### 玩家头颅

玩家被玩家击杀时, 会掉落头颅

``` yaml
Drop_Player_Head = false
```

### 快速紫水晶母岩

移除了原版 **20%** 的概率要求, 当紫水晶母岩接收到随机刻时, 会至多执行两次选取周围方块, 并生成紫水晶

相当于让紫水晶生长速度变为原来的 10 倍

``` yaml
Fast_Budding_Amethyst = false
```

### 医术高超

仅需要 **4~10** 秒, 即可救活一名僵尸村民

``` yaml
Superb_Medical_Skill = false
```

## 刷怪笼强化(BetterSpawner)

### 启用

只有此项为``true``时, 此类下的其他修改才会生效.

以下功能无需修改配置就会立即生效

1. 刷怪笼可以被稿开采, 但不再掉落经验, 而是掉落其中的刷怪蛋;
2. 使用生物蛋右键刷怪笼, 将会用手中的刷怪蛋替换原刷怪蛋, 同时原刷怪蛋掉落;
3. 在刷怪笼上方放置火把或灵魂火把, 将会封印刷怪笼;
4. 当刷怪笼被红石充能时, 会持续运行, 无论周围是否有玩家(若长时间不生成, 请检查刷怪笼附近是否符合此生物的生成要求);
5. 玩家击杀生物时, 有概率掉落对应的刷怪蛋, 初始为 **1%**, 使用**时运**附魔的武器可以让概率提高到 **5%**, **10%**, **15%**

```yaml
Enable = false
```

### 玩家范围

当玩家进入此范围, 刷怪笼才会启动

被红石充能时, 将会无视范围

``` yaml
Player_Range = 16
```

### 最大生成数量

刷怪笼停止生成前的最大怪物数量

``` yaml
Max_Nearby = 6
```

### 刷怪笼最小等待时间

两次刷怪间的最小间隔

``` yaml
Min_Delay = 200
```

### 刷怪笼最大等待时间

两次刷怪间的最大间隔

``` yaml
Max_Delay = 800
```

### 刷怪范围-边长

刷怪区域边长

``` yaml
Spawner_Range = 8
```

### 刷怪范围-高度(未启用)

刷怪区域高度

``` yaml
Spawner_Height = 3
```

### 刷怪数量(需要启用: 更好的刷怪笼)

每次以组为单位生成的怪物数量

``` yaml
Spawner_Count = 4
```

## 命令类(CommandReg)

### 服务器 tick

游戏内输入 ```/tick``` 命令, 查看当前系统信息, tick 耗时与内存使用情况, 无需 OP 权限

本功能适用于隐藏图形界面的面板服查看 tick 耗时

``` yaml
TICK_LISTENER = false
```

## 可堆叠物品(StackableItem)

在服务器端修改物品的最大堆叠数为 **64**

若客户端未安装该 MOD, 将会出现短暂的数据不一致, 但是客户端会立即修正为服务器数据, 对实际游戏没有影响

### 药水可堆叠

无论是否启用, 酿造台的最大酿造数量不受影响

``` yaml
Potions = false
```

## 战利品表(LootTable)

### 烈焰人掉落物

无论伤害来源是什么, 烈焰人总是掉落烈焰棒

``` yaml
Always_Drop_Blaze_Rod = false
```

### 紫水晶母岩可挖掘

``` yaml
Mineable_Budding_Amethyst = false
```

## 配方(Recipes)

### 附魔金苹果

8 * 金块 + 苹果

``` yaml
Enchanted_Golden_Apple = false
```

### 紫水晶母岩

4 * 紫水晶块

``` yaml
Budding_Amethyst = false
```