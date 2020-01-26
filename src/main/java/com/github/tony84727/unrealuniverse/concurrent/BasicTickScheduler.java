package com.github.tony84727.unrealuniverse.concurrent;

import net.minecraft.client.renderer.texture.ITickable;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class BasicTickScheduler implements ITickable {
    public static BasicTickScheduler INSTANCE = new BasicTickScheduler();
    private List<ScheduledTask> scheduledTaskList;

    public BasicTickScheduler() {
        this.scheduledTaskList = new ArrayList<>();
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent e) {
        INSTANCE.tick();
    }

    public void schedule(long after, Runnable runnable) {
        scheduledTaskList.add(new ScheduledTask(after, runnable));
    }

    public void tick() {
        scheduledTaskList.removeIf((task) -> task.after < 0);
        for (ScheduledTask task : scheduledTaskList) {
            if (task.after <= 0) {
                task.runnable.run();
            }
            task.after -= 1;
        }
    }

    private class ScheduledTask {
        private Runnable runnable;
        private long after;

        public ScheduledTask(long after, Runnable runnable) {
            this.after = after;
            this.runnable = runnable;
        }
    }
}
