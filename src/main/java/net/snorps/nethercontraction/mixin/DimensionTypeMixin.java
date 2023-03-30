package net.snorps.nethercontraction.mixin;

import net.minecraft.world.level.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DimensionType.class)
public class DimensionTypeMixin {
    @Shadow
    static DimensionType DEFAULT_NETHER;

    @Inject(at=@At("RETURN"), method= "coordinateScale", cancellable=true)
    private void returnOneTimesScale(CallbackInfoReturnable<Double> ci) {
        double d = ci.getReturnValue();
        if ((DimensionType)(Object)this == DEFAULT_NETHER) {
            d = 1.0;
        }
        ci.setReturnValue(d);
    }
}
