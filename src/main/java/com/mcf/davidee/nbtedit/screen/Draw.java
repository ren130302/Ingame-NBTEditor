package com.mcf.davidee.nbtedit.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.PoseStack.Pose;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;

import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Draw {
	public static void draw(PoseStack poseStack, int x, int y, int z, int width, int height, float srcX, float srcY,
			float srcWidth, float srcHeight, float textureWidth, float textureHeight,
			ResourceLocation resourceLocation) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderTexture(0, resourceLocation);
		RenderSystem.enableDepthTest();
		Tesselator tesselator = Tesselator.getInstance();
		BufferBuilder bufferbuilder = tesselator.getBuilder();
		Matrix4f pose = poseStack.last().pose();
		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
		bufferbuilder.vertex(pose, x, y + height, z).uv((srcX / textureWidth), (srcY + srcHeight) / textureHeight)
				.endVertex();
		bufferbuilder.vertex(pose, x + width, y + height, z)
				.uv((srcX + srcWidth) / textureWidth, (srcY + srcHeight) / textureHeight).endVertex();
		bufferbuilder.vertex(pose, x + width, y, z).uv((srcX + srcWidth) / textureWidth, srcY / textureHeight)
				.endVertex();
		bufferbuilder.vertex(pose, x, y, z).uv(srcX / textureWidth, srcY / textureHeight).endVertex();
		tesselator.end();
		RenderSystem.disableTexture();
	}

	public static void outer_border(int x, int y, int width, int height, int size, int padding, int red, int green,
			int blue, int alpha) {

		int _size0 = size + padding;
		int _size1 = _size0 * 2;
		int _width = width + _size1;
		int _height = height + _size1;
		int x0 = x - _size0;
		int x1 = x + width + padding;
		int y0 = y - _size0;
		int y1 = y + height + padding;

		Draw.box(x0, y0, size, _height, red, green, blue, alpha);// left
		Draw.box(x1, y0, size, _height, red, green, blue, alpha);// right
		Draw.box(x0, y0, _width, size, red, green, blue, alpha);// top
		Draw.box(x0, y1, _width, size, red, green, blue, alpha);// bottom
	}

	public static void inner_border(int x, int y, int width, int height, int size, int margin, int red, int green,
			int blue, int alpha) {

		int _size0 = (size + margin);
		int _size1 = (margin * 2);
		int _width = width - _size1;
		int _height = height - _size1;
		int x0 = x + margin;
		int x1 = x + width - _size0;
		int y0 = y + margin;
		int y1 = y + height - _size0;

		Draw.box(x0, y0, size, _height, red, green, blue, alpha);// left
		Draw.box(x1, y0, size, _height, red, green, blue, alpha);// right
		Draw.box(x0, y0, _width, size, red, green, blue, alpha);// top
		Draw.box(x0, y1, _width, size, red, green, blue, alpha);// bottom
	}

	public static void outerBorder(int x, int y, int width, int height, int size, int padding, int red, int green,
			int blue, int alpha) {
		int x1 = x - size + padding;
		int x2 = x;
		int x3 = x + width;
		int x4 = x + width + size - padding;
		int y1 = y - size + padding;
		int y2 = y;
		int y3 = y + height;
		int y4 = y + height + size - padding;

		RenderSystem.setShader(GameRenderer::getPositionColorShader);
		RenderSystem.disableTexture();
		RenderSystem.enableBlend();

		Tesselator tesselator = Tesselator.getInstance();
		BufferBuilder bufferbuilder = tesselator.getBuilder();

		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
		bufferbuilder.defaultColor(red, green, blue, alpha);
		// top
		bufferbuilder.vertex(x1, y1, 0).endVertex();
		bufferbuilder.vertex(x1, y2, 0).endVertex();
		bufferbuilder.vertex(x4, y2, 0).endVertex();
		bufferbuilder.vertex(x4, y1, 0).endVertex();
		// bottom
		bufferbuilder.vertex(x1, y3, 0).endVertex();
		bufferbuilder.vertex(x1, y4, 0).endVertex();
		bufferbuilder.vertex(x4, y4, 0).endVertex();
		bufferbuilder.vertex(x4, y3, 0).endVertex();
		// left
		bufferbuilder.vertex(x1, y2, 0).endVertex();
		bufferbuilder.vertex(x1, y3, 0).endVertex();
		bufferbuilder.vertex(x2, y3, 0).endVertex();
		bufferbuilder.vertex(x2, y2, 0).endVertex();
		// right
		bufferbuilder.vertex(x3, y2, 0).endVertex();
		bufferbuilder.vertex(x3, y3, 0).endVertex();
		bufferbuilder.vertex(x4, y3, 0).endVertex();
		bufferbuilder.vertex(x4, y2, 0).endVertex();

		tesselator.end();
	}

	public static void innerBorder(int x, int y, int width, int height, int size, int margin, int red, int green,
			int blue, int alpha) {
		int x1 = x;
		int x2 = x + size;
		int x3 = x + width - size;
		int x4 = x + width;
		int y1 = y;
		int y2 = y + size;
		int y3 = y + height - size;
		int y4 = y + height;

		RenderSystem.setShader(GameRenderer::getPositionColorShader);
		RenderSystem.disableTexture();
		RenderSystem.enableBlend();

		Tesselator tesselator = Tesselator.getInstance();
		BufferBuilder bufferbuilder = tesselator.getBuilder();

		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
		bufferbuilder.defaultColor(red, green, blue, alpha);
		// top
		bufferbuilder.vertex(x1, y1, 0).endVertex();
		bufferbuilder.vertex(x1, y2, 0).endVertex();
		bufferbuilder.vertex(x4, y2, 0).endVertex();
		bufferbuilder.vertex(x4, y1, 0).endVertex();
		// bottom
		bufferbuilder.vertex(x1, y3, 0).endVertex();
		bufferbuilder.vertex(x1, y4, 0).endVertex();
		bufferbuilder.vertex(x4, y4, 0).endVertex();
		bufferbuilder.vertex(x4, y3, 0).endVertex();
		// left
		bufferbuilder.vertex(x1, y2, 0).endVertex();
		bufferbuilder.vertex(x1, y3, 0).endVertex();
		bufferbuilder.vertex(x2, y3, 0).endVertex();
		bufferbuilder.vertex(x2, y2, 0).endVertex();
		// right
		bufferbuilder.vertex(x3, y2, 0).endVertex();
		bufferbuilder.vertex(x3, y3, 0).endVertex();
		bufferbuilder.vertex(x4, y3, 0).endVertex();
		bufferbuilder.vertex(x4, y2, 0).endVertex();
		tesselator.end();
	}

	public static void box(int x, int y, int width, int height, int red, int green, int blue, int alpha) {
		int x1 = x;
		int x2 = x + width;
		int y1 = y;
		int y2 = y + height;

		RenderSystem.setShader(GameRenderer::getPositionColorShader);
		RenderSystem.disableTexture();
		RenderSystem.enableBlend();
		Tesselator tesselator = Tesselator.getInstance();
		BufferBuilder bufferbuilder = tesselator.getBuilder();

		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
		bufferbuilder.defaultColor(red, green, blue, alpha);
		bufferbuilder.vertex(x1, y1, 0).endVertex();
		bufferbuilder.vertex(x1, y2, 0).endVertex();
		bufferbuilder.vertex(x2, y2, 0).endVertex();
		bufferbuilder.vertex(x2, y1, 0).endVertex();
		bufferbuilder.unsetDefaultColor();
		tesselator.end();
	}

	public static void outline(PoseStack poseStack, VertexConsumer consumer, Level level, Camera camera,
			BlockPos blockPos, float partialTick) {
		final double x = blockPos.getX();
		final double y = blockPos.getY();
		final double z = blockPos.getZ();
		final VoxelShape shape = level.getBlockState(blockPos).getShape(level, blockPos,
				CollisionContext.of(camera.getEntity()));
		final Vec3 targetPos = new Vec3(x, y, z);
		shape(poseStack, consumer, shape, camera, targetPos, 255, 0, 0, 90, partialTick);
	}

	public static void outline(PoseStack poseStack, VertexConsumer consumer, Level level, Camera camera, Entity entity,
			float partialTick) {
		final AABB aabb = entity.getBoundingBox();
		final double x = entity.getX();
		final double y = entity.getY();
		final double z = entity.getZ();
		final VoxelShape shape = Shapes.create(aabb.move(-x, -y, -z));
		final Vec3 targetPos = entity.position();
		shape(poseStack, consumer, shape, camera, targetPos, 255, 0, 0, 90, partialTick);
	}

	public static void shape(PoseStack poseStack, VertexConsumer consumer, VoxelShape shape, Camera cameraPos,
			Vec3 targetPos, int r, int g, int b, int a, float partialTick) {
		shape(poseStack, consumer, shape, cameraPos.getPosition(), targetPos, r, g, b, a, partialTick);
	}

	public static void shape(PoseStack poseStack, VertexConsumer consumer, VoxelShape shape, Vec3 cameraPos,
			Vec3 targetPos, int r, int g, int b, int a, float partialTick) {
		final Vec3 pos = targetPos.subtract(cameraPos);

		final double x = pos.x();
		final double y = pos.y();
		final double z = pos.z();

		final Pose pose = poseStack.last();
		final Matrix4f m4f = pose.pose();
		final Matrix3f m3f = pose.normal();

		shape.forAllEdges((minX, minY, minZ, maxX, maxY, maxZ) -> {
			float nx = (float) (maxX - minX);
			float ny = (float) (maxY - minY);
			float nz = (float) (maxZ - minZ);
			float f3 = Mth.sqrt(nx * nx + ny * ny + nz * nz);
			nx /= f3;
			ny /= f3;
			nz /= f3;

			float x0 = (float) (minX + x);
			float y0 = (float) (minY + y);
			float z0 = (float) (minZ + z);
			float x1 = (float) (maxX + x);
			float y1 = (float) (maxY + y);
			float z1 = (float) (maxZ + z);

			consumer.vertex(m4f, x0, y0, z0).color(r, g, b, a).normal(m3f, nx, ny, nz).endVertex();
			consumer.vertex(m4f, x1, y1, z1).color(r, g, b, a).normal(m3f, nx, ny, nz).endVertex();
		});
	}
}
