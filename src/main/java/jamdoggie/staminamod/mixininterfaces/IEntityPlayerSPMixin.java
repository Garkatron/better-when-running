package jamdoggie.staminamod.mixininterfaces;

public interface IEntityPlayerSPMixin
{
	float getStamina();
	void setStamina(float stamina);
	boolean isExhausted();
}
