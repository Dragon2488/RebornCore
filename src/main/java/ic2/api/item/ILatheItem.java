package ic2.api.item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface ILatheItem
{
	int getWidth(ItemStack p0);

	int[] getCurrentState(ItemStack p0);

	void setState(ItemStack p0, int p1, int p2);

	ItemStack getOutputItem(ItemStack p0, int p1);

	float getOutputChance(ItemStack p0, int p1);

	@SideOnly(Side.CLIENT)
	ResourceLocation getTexture(ItemStack p0);
}