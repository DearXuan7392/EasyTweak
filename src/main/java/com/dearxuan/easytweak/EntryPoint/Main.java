package com.dearxuan.easytweak.EntryPoint;

import com.dearxuan.easytweak.Event.CommandReg;
import com.dearxuan.easytweak.Event.LootTable;
import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer {

    public static boolean DEBUG = false;

    @Override
    public void onInitialize() {
        new LootTable();
        new CommandReg();
    }
}
