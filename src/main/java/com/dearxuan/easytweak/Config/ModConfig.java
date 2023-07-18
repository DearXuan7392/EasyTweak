package com.dearxuan.easytweak.Config;

import com.dearxuan.easytweak.Config.ModMenu.BaseConfig;
import com.dearxuan.easytweak.Config.ModMenu.EasyConfig;

public class ModConfig extends BaseConfig {

    public static ModConfig INSTANCE;

    public MOB_GRIEFING MobGriefing = new MOB_GRIEFING();

    public GAME_RULE GameRule = new GAME_RULE();

    public BETTER_SPAWNER BetterSpawner = new BETTER_SPAWNER();

    public COMMAND_REG CommandReg = new COMMAND_REG();

    public STACKABLE_ITEM StackableItem = new STACKABLE_ITEM();

    public LOOTTABLE LootTable = new LOOTTABLE();

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
     * 可堆叠物品类
     */
    public static class STACKABLE_ITEM extends BaseConfig {

        /**
         * 药水可堆叠
         */
        @EasyConfig(mixin = "StackablePotionMixin")
        public int Potions = 1;
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
         * 紫水晶母岩是否可掉落
         */
        public boolean Mineable_Budding_Amethyst = false;
    }

    public static class RECIPES extends BaseConfig {

        /**
         * 启用附魔金苹果合成配方
         */
        @EasyConfig(
                mixinPackage = "Recipe"
        )
        public boolean Enchanted_Golden_Apple = false;

        /**
         * 启用紫水晶母岩合成配方
         */
        @EasyConfig(
                mixinPackage = "Recipe"
        )
        public boolean Budding_Amethyst = false;
    }
}
