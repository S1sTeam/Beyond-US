package com.s1steam.beyondus;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.CommandSourceStack;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import static net.minecraft.commands.Commands.literal;

import java.util.Random;

@Mod(HerobrineMod.MODID)
public class HerobrineMod {
    public static final String MODID = "herobrinemod";

    private int ticksPlayed = 0;  // Счётчик времени игры (в тиках, 20 тиков = 1 секунда)
    private Random random = new Random();

    public HerobrineMod() {
        MinecraftForge.EVENT_BUS.register(this);
        System.out.println("Herobrine mod загружен!");
    }

    // Отслеживаем время игры
    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            ticksPlayed++;
            if (ticksPlayed % 1200 == 0) {  // Каждую минуту
                System.out.println("Игроки играют уже " + (ticksPlayed / 20 / 60) + " минут.");
                attemptHerobrineAppearance();
            }
        }
    }

    // Попытка появления Херобрина рядом с игроком
    private void attemptHerobrineAppearance() {
        // Получаем всех онлайн игроков
        for (ServerPlayer player : MinecraftForge.EVENT_BUS.getListeners(ServerPlayer.class)) {
            Level level = player.level;
            if (level.isClientSide) continue;

            // Вычисляем шанс появления Херобрина, растущий с временем (максимум 30%)
            double chance = Math.min(0.3, 0.01 + ticksPlayed / (20.0 * 60 * 60 * 2)); // максимум через 2 часа = 0.3

            if (random.nextDouble() < chance) {
                spawnHerobrineNearPlayer(player);
            }
        }
    }

    // Спавним Херобрина (здесь — зомби как заглушка)
    private void spawnHerobrineNearPlayer(ServerPlayer player) {
        Level level = player.level;
        if (level.isClientSide) return;

        BlockPos pos = player.blockPosition().offset(random.nextInt(10) - 5, 0, random.nextInt(10) - 5);

        Zombie herobrine = EntityType.ZOMBIE.create(level);
        if (herobrine != null) {
            herobrine.moveTo(pos, 0, 0);
            herobrine.setCustomNameVisible(true);
            herobrine.setCustomName(new net.minecraft.network.chat.TextComponent("Herobrine"));
            // Можно добавить уровни сложности, здоровье, урон и т.д.
            // Чем больше время игры — тем сильнее можно сделать (псевдо интеллект)
            int levelOfDanger = (int) Math.min(5, ticksPlayed / (20 * 60));  // 1 уровень на минуту, максимум 5

            herobrine.setHealth(20 + levelOfDanger * 4);  // здоровье растёт
            // Можно добавить другие эффекты, например усиление урона

            level.addFreshEntity(herobrine);
            System.out.println("Herobrine появился рядом с игроком " + player.getName().getString());
        }
    }

    // Регистрация команды /herobrinesound для пугалки
    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        dispatcher.register(literal("herobrinesound")
            .requires(cs -> cs.hasPermission(0))
            .executes(context -> {
                ServerPlayer player = context.getSource().getPlayerOrException();
                playScarySound(player);
                return 1;
            }));
    }

    // Воспроизводим страшный звук рядом с игроком
    private void playScarySound(ServerPlayer player) {
        Level level = player.level;
        if (level.isClientSide) return;

        SoundEvent sound = SoundEvents.WITHER_SPAWN;  // Можно заменить на другие страшные звуки
        level.playSound(null, player.blockPosition(), sound, player.getSoundSource(), 1.0F, 1.0F);
        System.out.println("Страшный звук проигран для " + player.getName().getString());
    }
                      }
          
