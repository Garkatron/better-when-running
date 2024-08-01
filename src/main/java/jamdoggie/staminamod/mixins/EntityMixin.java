package jamdoggie.staminamod.mixins;

import com.mojang.nbt.CompoundTag;
import net.minecraft.core.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Entity.class, remap = false)
public class EntityMixin
{
	@Inject(method = "saveWithoutId", at = @At(value="INVOKE",
		target = "Lnet/minecraft/core/entity/Entity;addAdditionalSaveData(Lcom/mojang/nbt/CompoundTag;)V",
	shift = At.Shift.BEFORE), remap = false)
	private void saveWithoutId(CompoundTag tag, CallbackInfo ci)
	{

	}
}
