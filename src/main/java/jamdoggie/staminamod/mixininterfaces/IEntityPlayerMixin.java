package jamdoggie.staminamod.mixininterfaces;

public interface IEntityPlayerMixin
{
	float getStamina();
	void setStamina(float stamina);
	boolean isExhausted();
	void setExhausted(boolean exhausted);
	float getPrevStamina();
}
