## Easy Tweak

提供了大量可选功能, 需在配置文件中手动开启, 无法在游戏中修改.

仅需服务端安装, 客户端无需安装即可享受所有功能.

> 如果你有其他想要实现的内容, 请联系我. 仅限服务端内容, 即客户端无需安装 mod 也可以进入.

## 配置文件

第一次运行时, 在``config``文件夹下会自动生成配置文件

``` yaml
# 生物破坏
MobGriefing:
  # 禁用末影人搬运方块
  Disable_Enderman_Pick_Up: false
  # 禁用苦力怕破坏方块
  Disable_Creeper_Griefing: false
  # 禁用恶魂破坏方块
  Disable_Ghast_Griefing: false
  # 禁用僵尸破门
  Disable_Zombie_Break_Door: false
# 游戏规则
GameRule:
  # 禁用传送门生成僵尸猪人
  Disable_Nether_Portal_Spawning_Zombified_Piglin: false
  # 禁用蝙蝠生成
  Disable_Bat_Spawning: false
  # 禁止基岩上层生成恶魂
  Disable_Ghast_Spawning_Above_Bedrock: false
  # 禁止耕地被踩踏
  Disable_Trample: false
  # 玩家被击杀(包括自杀)时掉落头颅
  Drop_Player_Head: false
  # 加快紫水晶生长
  Fast_Budding_Amethyst: false
  # 加快僵尸村民恢复
  Superb_Medical_Skill: false
# 更好的刷怪笼
# 主要功能包括: 
#     1. 刷怪笼可挖掘, 怪物被玩家击杀时有概率掉落刷怪蛋
#     2. 在刷怪笼上方放置火把或灵魂火把, 可以封印刷怪笼
#     3. 刷怪笼被红石充能时, 会持续工作,, 即使玩家不在附近
BetterSpawner:
  # 是否启用更好的刷怪笼
  Enable: false
  # 玩家激活刷怪笼所需的范围
  Player_Range: 16
  # 刷怪笼附近最大怪物数量
  Max_Nearby: 6
  # 刷怪最短间隔
  Min_Delay: 200
  # 刷怪最大间隔
  Max_Delay: 800
  # 刷怪范围
  Spawner_Range: 8
  # 刷怪高度
  Spawner_Height: 3
  # 刷怪数量
  Spawner_Count: 4
  # 禁用光照检测, 怪物将可以在白天生成
  Disable_Light_Restriction: false
  # 禁用流体检测, 怪物将可以在水中生成
  Disable_Fluid_Restriction: false
# 指令
CommandReg:
  # 检测tick耗时
  Command_Tick: false
# 附魔
Enchantment:
  # 禁用附魔冲突, 但是时运与精准采集仍然冲突
  Disable_Enchantments_Conflict: false
  # 附魔无限的武器将不再消耗任何箭矢, 同时背包中无需拥有一根箭
  Real_Infinity: false
  # 弓的附魔对弩生效
  Better_Crossbow: false
  # 限制铁砧的最大附魔花费为39
  Never_Expensive: false
  # 附魔经验修补的工具将直接使用玩家经验进行修复
  Better_Mending: false
# 可堆叠物品
StackableItem:
  # 药水
  Potions: 1
  # 雪球
  Snowball: 16
  # 床
  Bed: 1
# 战利品
LootTable:
  # 烈焰人非玩家击杀死亡时有概率掉落烈焰棒
  Always_Drop_Blaze_Rod: false
  # 凋零骷髅非玩家击杀死亡时有概率掉落凋零头颅
  Wither_Skeleton_Drop_Skull: false
  # 紫水晶母岩可挖掘
  Mineable_Budding_Amethyst: false
# 生物行为
MobActivity:
  # 禁用猪灵转化为僵尸猪灵
  Disable_Piglin_Zombify: false
  # 苦力怕主动攻击雪傀儡
  Creeper_Attack_Snow_Golem: false
# 配方
Recipes:
  # 8 x 金块 + 1 x 苹果 = 附魔金苹果
  Enchanted_Golden_Apple: false
  # 4 x 紫水晶块 = 紫水晶母岩
  Budding_Amethyst: false
```