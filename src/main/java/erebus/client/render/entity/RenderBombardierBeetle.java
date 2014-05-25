package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelBombardierBeetle;

@SideOnly(Side.CLIENT)
public class RenderBombardierBeetle extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/beetleBombardier.png");

	public RenderBombardierBeetle(ModelBombardierBeetle model, float shadowSize) {
		super(model, shadowSize);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		float f1 = 1.5F;
		shadowSize = 0.6F;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}