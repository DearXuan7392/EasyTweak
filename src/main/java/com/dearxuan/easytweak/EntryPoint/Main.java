package com.dearxuan.easytweak.EntryPoint;

import com.dearxuan.easytweak.Event.LootTable;
import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer {

    public final static boolean DEBUG = false;

    @Override
    public void onInitialize() {
        // 检查是否有需要添加的战利品表
        new LootTable();
    }
}
