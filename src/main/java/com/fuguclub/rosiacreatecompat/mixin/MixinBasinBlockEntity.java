package com.fuguclub.rosiacreatecompat.mixin;

import com.simibubi.create.content.processing.basin.BasinBlockEntity;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import net.dries007.tfc.common.blocks.devices.CharcoalForgeBlock;
import com.jewey.rosia.common.blocks.custom.electric_forge;
import com.jewey.rosia.common.blocks.custom.fire_box;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = {BasinBlockEntity.class}, remap = false)
public class MixinBasinBlockEntity {
    @Inject(method = {"getHeatLevelOf"}, at = {@At("HEAD")}, cancellable = true)
    private static void getHeatLevelOf(BlockState state, CallbackInfoReturnable<BlazeBurnerBlock.HeatLevel> cir) {
        if (state.getBlock() instanceof electric_forge) {
            int heat = ((Integer)state.getValue((Property)electric_forge.HEAT)).intValue();
            if (heat >= 7) {
                cir.setReturnValue(BlazeBurnerBlock.HeatLevel.SEETHING);
            } else if (heat >= 3) {
                cir.setReturnValue(BlazeBurnerBlock.HeatLevel.KINDLED);
            } else {
                cir.setReturnValue(BlazeBurnerBlock.HeatLevel.NONE);
            }
        }
        if (state.getBlock() instanceof fire_box) {
            int heat = ((Integer)state.getValue((Property)fire_box.HEAT)).intValue();
            if (heat >= 7) {
                cir.setReturnValue(BlazeBurnerBlock.HeatLevel.SEETHING);
            } else if (heat >= 3) {
                cir.setReturnValue(BlazeBurnerBlock.HeatLevel.KINDLED);
            } else {
                cir.setReturnValue(BlazeBurnerBlock.HeatLevel.NONE);
            }
        }
    }
}

