package erebus.client.render.entity;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelChameleonTick;
import erebus.entity.EntityChameleonTick;

@SideOnly(Side.CLIENT)
public class RenderChameleonTick extends RenderLiving {


	public RenderChameleonTick(ModelChameleonTick model, float scale) {
		super(model, scale);
	}

	public void renderChameleonTick(EntityChameleonTick entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		boolean alpha = Block.blocksList[entity.blockID].getRenderBlockPass() == 1;

			GL11.glPushMatrix();
			if (alpha) {
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glEnable(GL11.GL_BLEND);
			}
			GL11.glTranslatef((float) x, (float) y, (float) z);
			GL11.glTranslatef(0.0F, 0.5F, 0.0F);
			GL11.glRotatef(-entity.renderYawOffset, 0.0F, 1.0F, 0.0F);
			bindTexture(TextureMap.locationBlocksTexture);
			renderBlocks.renderBlockAsItem(Block.blocksList[entity.blockID], entity.blockMeta, 1.0F);
			if (alpha)
				GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
			super.doRenderLiving(entity, x, y, z, rotationYaw, partialTickTime);
		}

	@Override
	public void doRenderLiving(EntityLiving entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderChameleonTick((EntityChameleonTick) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderChameleonTick((EntityChameleonTick) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float scale) {
		shadowSize = 0.3F;
		GL11.glScalef(1.0F, 1.0F, 1.0F);
	}

	@Override
	protected float getDeathMaxRotation(EntityLivingBase entityLivingBase) {
		return 0F;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityChameleonTick ChameleonTick = (EntityChameleonTick) entity;
		String blockPath = Block.blocksList[ChameleonTick.blockID].getIcon(0, ChameleonTick.blockMeta).getIconName();
		String modName = "minecraft";
		if (blockPath.contains(":")) {
			modName = blockPath.split(":")[0];
			blockPath = blockPath.split(":")[1];
		}
		return new ResourceLocation(modName, "textures/blocks/" + blockPath + ".png");
	}
}