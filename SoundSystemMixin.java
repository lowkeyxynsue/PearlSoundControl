package com.xynsue.mixin;

import com.xynsue.XynsueConfig;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundSystem;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SoundSystem.class)
public abstract class SoundSystemMixin {
    @Inject(method = "getAdjustedVolume(Lnet/minecraft/client/sound/SoundInstance;)F", at = @At("RETURN"), cancellable = true)
    private void xynsue$adjustPearlSounds(SoundInstance sound, CallbackInfoReturnable<Float> cir) {
        Identifier id = sound.getId();
        String path = id.getPath();
        float multiplier = 1.0F;

        if (path.equals("ender_pearl.throw")) {
            multiplier = XynsueConfig.pearlThrow / 100.0F;
        } else if (path.equals("enderman.teleport")) {
            multiplier = XynsueConfig.pearlLanding / 100.0F;
        }

        cir.setReturnValue(cir.getReturnValue() * multiplier);
    }
}
