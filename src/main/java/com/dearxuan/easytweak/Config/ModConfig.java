package com.dearxuan.easytweak.Config;

import com.dearxuan.easytweak.Config.ModMenu.EasyConfig;

public class ModConfig{

    public static ModConfig INSTANCE;

    /**
     * 地狱门生成僵尸猪人
     */
    @EasyConfig(mixin = "NetherPortalBlock")
    public boolean NETHER_PORTAL_NEVER_SPAWN_ZOMBIE_PIGLIN = true;

    /**
     * 末影人搬动物品
     */
    @EasyConfig(mixin = "EndermanEntityMixin")
    public boolean ENDER_MAN_PICK_UP = true;

    /**
     * 古力怕破坏方块
     */
    @EasyConfig(mixin = "CreeperEntityMixin")
    public boolean CREEPER_DESTROY_BLOCK = true;

    /**
     * 生成蝙蝠
     */
    @EasyConfig(mixin = "BatEntityMixin")
    public boolean SPAWN_BAT = true;

    /**
     * 在地狱的基岩上层生成恶魂
     */
    @EasyConfig(mixin = "GhastEntityMixin")
    public boolean SPAWN_GHAST_WHEN_Y_GREATER_128 = true;

    /**
     * 恶魂破坏方块
     */
    @EasyConfig(mixin = "FireBallEntityMixin")
    public boolean GHAST_DESTROY_BLOCK = true;

    /**
     * 不再踩踏耕地
     */
    @EasyConfig(mixin = "FarmlandBlockMixin")
    public boolean NO_TRAMPLE = false;


    /**
     * 刷怪笼强化
     */
    @EasyConfig(mixin = {
            "MobEntityMixin",
            "MobSpawnerBlockEntityMixin",
            "MobSpawnerLogicMixin",
            "SpawnEggItemMixin",
            "SpawnerBlockMixin"
    })
    public boolean BETTER_MOB_SPAWNER = false;

    /**
     * 刷怪笼激活距离
     */
    public int SPAWNER_PLAYER_RANGE = 16;

    /**
     * 刷怪笼附近最大实体数量
     */
    public int SPAWNER_MAX_NEARBY = 6;

    /**
     * 下次刷怪的最小等待时间
     */
    public int SPAWNER_MIN_DELAY = 200;

    /**
     * 下次刷怪的最大等待时间
     */
    public int SPAWNER_MAX_DELAY = 800;

    /**
     * 刷怪方形区域边长, 范围是正方形
     */
    public int SPAWNER_RANGE = 8;

    /**
     * 刷怪高度
     */
    public int SPAWNER_HEIGHT = 3;

    /**
     * 每次生成的怪物数量
     */
    public int SPAWNER_COUNT = 4;

    /**
     * 统计服务器平均tick, 可使用命令查看
     */
    public boolean TICK_LISTENER = false;

    /**
     * 玩家被另一名玩家击杀时, 掉落头颅
     */
    @EasyConfig(mixin = "ServerPlayerEntityMixin")
    public boolean PLAYER_HEAD = false;

    /**
     *  加快紫水晶母岩的生成速度
     */
    @EasyConfig(mixin = "BuddingAmethystBlockMixin")
    public boolean FAST_BUDDING_AMETHYST = false;


    /**
     * 医术高超, 仅需 4~10 秒便可救回村民
     */
    @EasyConfig(mixin = "ZombieVillagerEntityMixin")
    public boolean SUPERB_MEDICAL_SKILLS = false;

    /**
     * 药水可堆叠
     */
    @EasyConfig(mixin = "StackablePotionMixin")
    public boolean STACKABLE_POTIONS = false;

    public ModConfig() {

    }

    public void OnUpdate(){

    }
}
