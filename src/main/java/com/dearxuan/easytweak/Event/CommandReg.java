package com.dearxuan.easytweak.Event;

import com.dearxuan.easytweak.Config.ModConfig;
import com.dearxuan.easytweak.Config.ModMenu.ModInfo;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.TextContent;
import net.minecraft.util.Util;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class CommandReg {

    private final DecimalFormat NumberFormat = Util.make(new DecimalFormat("#.##"), (decimalFormat) -> {
        decimalFormat.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ROOT));
    });

    public CommandReg() {

        if (ModConfig.INSTANCE.CommandReg.Command_Tick) {
            // 注册 /tick 命令
            CommandRegistrationCallback.EVENT.register(
                    (dispatcher, registryAccess, environment) -> {
                        dispatcher.register(CommandManager.literal("tick").executes(this::OnTickCommand));
                    });
        }
    }

    private int OnTickCommand(CommandContext<ServerCommandSource> context) {
        try {
            // CPU 信息
            SystemInfo systemInfo = new SystemInfo();
            String systemName = System.getProperty("os.name"); // 系统信息
            CentralProcessor cpuInfo = systemInfo.getHardware().getProcessor();
            int cpuLogicCore = cpuInfo.getLogicalProcessorCount(); // CPU 核心数
            double _cpuFreq = cpuInfo.getMaxFreq(); // CPU 主频(Hz)
            String cpuFreq;
            if (_cpuFreq <= 0) {
                cpuFreq = "已隐藏";
            } else if (_cpuFreq > 1024L * 1024L * 1024L * 2L) {
                cpuFreq = NumberFormat.format(_cpuFreq / 1024L / 1024L / 1024L) + " GHz";
            } else if (_cpuFreq > 1024L * 1024L) {
                cpuFreq = NumberFormat.format(_cpuFreq / 1024L / 1024L) + " MHz";
            } else if (_cpuFreq > 1024L * 2L) {
                cpuFreq = NumberFormat.format(_cpuFreq / 1024L) + " KHz";
            } else {
                cpuFreq = NumberFormat.format(_cpuFreq) + " Hz";
            }

            // 计算 tick 时间
            long total = 0L;
            long[] ticks = context.getSource().getServer().lastTickLengths;
            int length = ticks.length;
            for (long tick : ticks) {
                total += tick;
            }
            double avgTick_ms = ((double) total / length) * 1.0E-6;
            int tickColor;
            if (avgTick_ms <= 40) {
                tickColor = 0x00FF00; // 绿色
            } else if (avgTick_ms <= 50) {
                tickColor = 0xFFA500; // 橙色
            } else {
                tickColor = 0xFF0000; // 红色
            }

            // 计算内存使用
            long memoryMax = Runtime.getRuntime().maxMemory();
            long memoryUsed = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();


            MutableText mutableText = MutableText.of(TextContent.EMPTY);
            mutableText
                    .append("操作系统: " + systemName)
                    .append("\nCPU核心数: " + cpuLogicCore)
                    .append("\nCPU主频: " + cpuFreq)
                    // 平均 tick
                    .append("\n平均 tick: ")
                    .append(MutableText.of(TextContent.EMPTY).append(NumberFormat.format(avgTick_ms)).setStyle(Style.EMPTY.withColor(tickColor)))
                    .append(" ms\n")
                    // 内存使用情况
                    .append("内存占用: " + getMemory(memoryUsed) + " / " + getMemory(memoryMax) + " (" + memoryUsed * 100L / memoryMax + " %)");
            context.getSource().sendFeedback(() -> {
                return mutableText;
            }, false);
        } catch (Exception e) {
            ModInfo.LOGGER.error(e);
        }
        return 0;
    }

    private String getMemory(long _memory) {
        double memory = _memory; // B
        if (memory < 1024) {
            return NumberFormat.format(memory) + " B";
        }
        memory /= 1024;
        if (memory < 1024) {
            return NumberFormat.format(memory) + " KB";
        }
        memory /= 1024;
        if (memory < 1024) {
            return NumberFormat.format(memory) + " MB";
        }
        memory /= 1024;
        return NumberFormat.format(memory) + " GB";
    }
}
