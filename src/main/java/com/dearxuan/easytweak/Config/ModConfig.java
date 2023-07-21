package com.dearxuan.easytweak.Config;

import com.dearxuan.easytweak.Config.ModMenu.BaseConfig;
import com.dearxuan.easytweak.Config.ModMenu.EasyConfig;

public class ModConfig extends BaseConfig {

    public static ModConfig INSTANCE;

    public MOB_GRIEFING MobGriefing = new MOB_GRIEFING();

    public GAME_RULE GameRule = new GAME_RULE();

    public BETTER_SPAWNER BetterSpawner = new BETTER_SPAWNER();

    public COMMAND_REG CommandReg = new COMMAND_REG();

    public ENCHANTMENT Enchantment = new ENCHANTMENT();

    public STACKABLE_ITEM StackableItem = new STACKABLE_ITEM();

    public LOOTTABLE LootTable = new LOOTTABLE();

    public MOBACTIVITY MobActivity = new MOBACTIVITY();

    public RECIPES Recipes = new RECIPES();

    /**
     * 生物破坏类
     */
    public static class MOB_GRIEFING extends BaseConfig {

        /**
         * 禁止末影人搬动物品
         */
        @EasyConfig(mixin = "EndermanEntityMixin")
        public boolean Disable_Enderman_Pick_Up = false;

        /**
         * 禁止古力怕破坏方块
         */
        @EasyConfig(mixin = "CreeperEntityMixin")
        public boolean Disable_Creeper_Griefing = false;

        /**
         * 禁止恶魂破坏方块
         */
        @EasyConfig(mixin = "FireBallEntityMixin")
        public boolean Disable_Ghast_Griefing = false;
    }

    /**
     * 游戏规则类
     */
    public static class GAME_RULE extends BaseConfig {

        /**
         * 禁止地狱门生成僵尸猪人
         */
        @EasyConfig(mixin = "NetherPortalBlock")
        public boolean Disable_Nether_Portal_Spawning_Zombified_Piglin = false;

        /**
         * 禁止生成蝙蝠
         */
        @EasyConfig(mixin = "BatEntityMixin")
        public boolean Disable_Bat_Spawning = false;

        /**
         * 禁止在地狱的基岩上层生成恶魂
         */
        @EasyConfig(mixin = "GhastEntityMixin")
        public boolean Disable_Ghast_Spawning_Above_Bedrock = false;

        /**
         * 不再踩踏耕地
         */
        @EasyConfig(mixin = "FarmlandBlockMixin")
        public boolean Disable_Trample = false;

        /**
         * 玩家被另一名玩家击杀时, 掉落头颅
         */
        @EasyConfig(mixin = "ServerPlayerEntityMixin")
        public boolean Drop_Player_Head = false;

        /**
         *  加快紫水晶母岩的生成速度
         */
        @EasyConfig(mixin = "BuddingAmethystBlockMixin")
        public boolean Fast_Budding_Amethyst = false;


        /**
         * 医术高超, 仅需 4~10 秒便可救回村民
         */
        @EasyConfig(mixin = "ZombieVillagerEntityMixin")
        public boolean Superb_Medical_Skill = false;
    }

    /**
     * 刷怪笼强化类
     */
    public static class BETTER_SPAWNER extends BaseConfig {

        /**
         * 刷怪笼强化
         */
        @EasyConfig(mixinPackage = "BetterSpawner")
        public boolean Enable = false;

        /**
         * 刷怪笼激活距离
         */
        public int Player_Range = 16;

        /**
         * 刷怪笼附近最大实体数量
         */
        public int Max_Nearby = 6;

        /**
         * 下次刷怪的最小等待时间
         */
        public int Min_Delay = 200;

        /**
         * 下次刷怪的最大等待时间
         */
        public int Max_Delay = 800;

        /**
         * 刷怪方形区域边长, 范围是正方形
         */
        public int Spawner_Range = 8;

        /**
         * 刷怪高度
         */
        public int Spawner_Height = 3;

        /**
         * 每次生成的怪物数量
         */
        public int Spawner_Count = 4;

        /**
         * 取消光照限制
         */
        @EasyConfig(mixin = {"HostileEntityMixin"})
        public boolean Disable_Light_Restriction = false;

        /**
         * 取消流体限制
         */
        public boolean Disable_Fluid_Restriction = false;
    }

    /**
     * 命令类
     */
    public static class COMMAND_REG extends BaseConfig {

        /**
         * 统计服务器平均tick, 可使用命令查看
         */
        public boolean Command_Tick = false;
    }

    /**
     * 附魔类
     */
    public static class ENCHANTMENT extends BaseConfig {

        /**
         * 禁用附魔冲突检测
         */
        @EasyConfig(mixinPackage = "Enchantment.Conflict")
        public boolean Disable_Enchantments_Conflict = false;

        /**
         * 弓的附魔对弩有效
         */
        @EasyConfig(mixinPackage = "Enchantment.SharedEnchanting")
        public boolean Share_Enchantments_To_Crossbow = false;

        /**
         * 附魔无限的弓和弩不再需要背包中拥有箭, 药水箭也不会减少
         */
        @EasyConfig(mixinPackage = "Enchantment.RealInfinity")
        public boolean Real_Infinity = false;

        /**
         * 不再出现过于昂贵提示, 附魔最大经验值为39
         */
        @EasyConfig(mixin = "AnvilScreenHandlerMixin")
        public boolean Never_Expensive = false;

        /**
         * 经验修补直接使用玩家经验对工具修复
         */
        @EasyConfig(mixin = "ItemStackMixin")
        public boolean Better_Mending = false;
    }

    /**
     * 可堆叠物品类
     */
    public static class STACKABLE_ITEM extends BaseConfig {

        /**
         * 药水可堆叠
         */
        @EasyConfig(mixin = "PotionMixin")
        public int Potions = 1;

        /**
         * 雪球
         */
        @EasyConfig(mixin = "SnowballMixin")
        public int Snowball = 16;

        /**
         * 床可堆叠
         */
        @EasyConfig(mixin = "BedMixin")
        public int Bed = 1;
    }

    /**
     * 战利品类
     */
    public static class LOOTTABLE extends BaseConfig {

        /**
         * 烈焰人在受到任何来源伤害死亡时总会掉落烈焰棒
         */
        public boolean Always_Drop_Blaze_Rod = false;

        /**
         * 凋零骷髅死亡时总会凋零凋零骷髅头
         */
        public boolean Wither_Skeleton_Drop_Skull = false;

        /**
         * 紫水晶母岩是否可掉落
         */
        public boolean Mineable_Budding_Amethyst = false;
    }

    /**
     * 生物行为类
     */
    public static class MOBACTIVITY extends BaseConfig {

        /**
         * 禁止猪灵在主世界变为僵尸猪人
         */
        @EasyConfig(mixin = "AbstractPiglinEntityMixin")
        public boolean Disable_Piglin_Zombify = false;

        /**
         * 苦力怕主动攻击雪傀儡
         */
        @EasyConfig(mixin = "CreeperEntityMixin")
        public boolean Creeper_Attack_Snow_Golem = false;

        /**
         * 禁用僵尸破门
         */
        @EasyConfig(mixin = "")
        public boolean Disable_Zombie_Break_Door = false;
    }

    public static class RECIPES extends BaseConfig {

        /**
         * 启用附魔金苹果合成配方
         */
        @EasyConfig(mixinPackage = "Recipe")
        public boolean Enchanted_Golden_Apple = false;

        /**
         * 启用紫水晶母岩合成配方
         */
        @EasyConfig(mixinPackage = "Recipe")
        public boolean Budding_Amethyst = false;
    }
}
