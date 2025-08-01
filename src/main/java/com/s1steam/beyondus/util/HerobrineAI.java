package com.s1steam.beyondus.util;

import com.s1steam.beyondus.entity.EntityHerobrine;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;

import java.util.List;
import java.util.Random;

public class HerobrineAI {

    private final EntityHerobrine herobrine;
    private int daysPassed = 0;
    private int tickCounter = 0;
    private Random random = new Random();

    public HerobrineAI(EntityHerobrine herobrine) {
        this.herobrine = herobrine;
    }

    public void tick() {
        tickCounter++;

        // Допустим, 24000 тик = 1 день в Minecraft
        if (tickCounter % 24000 == 0) {
            daysPassed++;
            increaseDifficulty();
        }

        if (herobrine.level instanceof ServerLevel serverLevel) {
            List<Player> players = serverLevel.players();

            if (!players.isEmpty()) {
                Player player = players.get(random.nextInt(players.size()));

                // С вероятностью, зависящей от дней, пугаем или атакуем
                int chance = Math.min(daysPassed * 5, 50); // максимум 50%

                if (random.nextInt(100) < chance) {
                    if (!herobrine.isAlive()) return;

                    // Иногда пугать
                    if (random.nextBoolean()) {
                        scarePlayer(player);
                    } else {
                        attackPlayer(player);
                    }
                }
            }
        }
    }

    private void increaseDifficulty() {
        // Можно увеличить скорость, частоту действий и т.п.
        herobrine.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED)
                .setBaseValue(0.25 + daysPassed * 0.02);
    }

    private void scarePlayer(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.connection.send(new net.minecraft.network.protocol.game.ClientboundSoundPacket(
                SoundEvents.WITHER_SPAWN, net.minecraft.sounds.SoundSource.HOSTILE,
                player.getX(), player.getY(), player.getZ(), 1.0f, 1.0f));
            player.sendSystemMessage(net.minecraft.network.chat.Component.literal("Ты слышишь странные звуки..."));
        }
    }

    private void attackPlayer(Player player) {
        herobrine.getNavigation().moveTo(player, 1.2);
        if (herobrine.distanceTo(player) < 2.5) {
            player.hurt(net.minecraft.world.damagesource.DamageSource.mobAttack(herobrine), 4.0F);
            player.sendSystemMessage(net.minecraft.network.chat.Component.literal("Херобрин атакует тебя!"));
        }
    }
}
