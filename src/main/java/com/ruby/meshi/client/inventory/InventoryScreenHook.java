 package com.ruby.meshi.client.inventory;
 
 import com.ruby.meshi.client.inventory.InventoryScreenHook.1;
 import com.ruby.meshi.init.HiganContainerType;
 import com.ruby.meshi.util.NetworkHandler;
 import java.util.function.Supplier;
 import net.minecraft.client.gui.screen.inventory.InventoryScreen;
 import net.minecraft.client.gui.widget.button.Button.IPressable;
 import net.minecraft.util.ResourceLocation;
 import net.minecraftforge.api.distmarker.Dist;
 import net.minecraftforge.api.distmarker.OnlyIn;
 import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent.Post;
 import net.minecraftforge.eventbus.api.SubscribeEvent;
 import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
 import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
 @EventBusSubscriber(
    bus = Bus.FORGE,
    value = {Dist.CLIENT}
 )
 public class InventoryScreenHook {
    private static final ResourceLocation BUTTON_TEXTURE = new ResourceLocation("meshi", "textures/guis/meshi_button.png");
 
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void init(Post event) {
       if (event.getGui() instanceof InventoryScreen) {
          InventoryScreen screen = (InventoryScreen)event.getGui();         Supplier<Integer> left = () -> {            return screen.getGuiLeft() + 134;
          };         Supplier<Integer> top = () -> {            return screen.height / 2 - 22;
          };
          new 1((Integer)left.get(), (Integer)top.get(), 20, 18, 0, 0, 19, BUTTON_TEXTURE, (b) -> {
             NetworkHandler.openServerContainer(HiganContainerType.EXTEND_INVENTORY);
          }, left, top);
 
 
 
 
 
 
 
       }
 
    }
 }

/*
	DECOMPILATION REPORT

	Decompiled from: D:\pleiades\workspace\bamboo\run\mods\meshi-0.3.jar
	Total time: 10 ms
	
	Decompiled with FernFlower.
*/