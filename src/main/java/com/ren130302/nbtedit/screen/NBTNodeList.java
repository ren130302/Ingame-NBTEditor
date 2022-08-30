package com.ren130302.nbtedit.screen;

import java.util.Objects;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.ren130302.nbtedit.NBTEditMessage;
import com.ren130302.nbtedit.NBTStringHelper;
import com.ren130302.nbtedit.nbt.NamedNBT;
import com.ren130302.nbtedit.nbt.Node;
import com.ren130302.nbtedit.screen.NBTButtons.NodeButton;
import com.ren130302.nbtedit.screen.NBTButtons.T;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class NBTNodeList extends ObjectSelectionList<NBTNodeList.NBTNodeEntry> {

	private final EditNBTTreeScreen screen;

	public NBTNodeList(EditNBTTreeScreen screen, Minecraft minecraft) {
		super(minecraft, screen.width / 5 * 3, screen.height, 40, screen.height - 40, minecraft.font.lineHeight + 4);
		NBTEditMessage.trace("NBTNodeList - init");
		this.screen = screen;
		this.init();
	}

	@Override
	public void setSelected(NBTNodeEntry entry) {
		NBTNodeList.this.screen.setNodeFocused(entry);
		super.setSelected(entry);
	}

	@Override
	public int getRowLeft() {
		return this.x0;
	}

	@Override
	public int getRowWidth() {
		return 200;
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int code) {
		this.updateScrollingState(mouseX, mouseY, code);
		if (!this.isMouseOver(mouseX, mouseY)) {
			return false;
		} else {
			int i1 = Mth.floor(mouseY - this.y0) - this.headerHeight + (int) this.getScrollAmount() - 4;
			int j1 = i1 / this.itemHeight;
			boolean ex = mouseX < this.getScrollbarPosition() && j1 >= 0 && i1 >= 0 && j1 < this.getItemCount();
			NBTNodeEntry entry = ex ? this.children().get(j1) : null;

			if (Objects.nonNull(entry)) {
				if (entry.mouseClicked(mouseX, mouseY, code)) {
					return true;
				}
			} else if (code == 0) {
				this.clickedHeader((int) (mouseX - (this.x0 + this.width / 2 - this.getRowWidth() / 2)),
						(int) (mouseY - this.y0) + (int) this.getScrollAmount() - 4);
				return true;
			}
		}
		return false;
	}

	@Override
	protected void renderBackground(PoseStack poseStack) {
		Tesselator tesselator = Tesselator.getInstance();
		BufferBuilder bufferbuilder = tesselator.getBuilder();
		RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
		RenderSystem.setShaderTexture(0, GuiComponent.BACKGROUND_LOCATION);
		RenderSystem.enableDepthTest();
		RenderSystem.depthFunc(519);
		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
		bufferbuilder.vertex(this.x0, this.y0, -100.0D).uv(0.0F, this.y0 / 32.0F).color(64, 64, 64, 255).endVertex();
		bufferbuilder.vertex(this.x0 + this.width, this.y0, -100.0D).uv(this.width / 32.0F, this.y0 / 32.0F)
				.color(64, 64, 64, 255).endVertex();
		bufferbuilder.vertex(this.x0 + this.width, 0.0D, -100.0D).uv(this.width / 32.0F, 0.0F).color(64, 64, 64, 255)
				.endVertex();
		bufferbuilder.vertex(this.x0, 0.0D, -100.0D).uv(0.0F, 0.0F).color(64, 64, 64, 255).endVertex();
		bufferbuilder.vertex(this.x0, this.height, -100.0D).uv(0.0F, this.height / 32.0F).color(64, 64, 64, 255)
				.endVertex();
		bufferbuilder.vertex(this.x0 + this.width, this.height, -100.0D).uv(this.width / 32.0F, this.height / 32.0F)
				.color(64, 64, 64, 255).endVertex();
		bufferbuilder.vertex(this.x0 + this.width, this.y1, -100.0D).uv(this.width / 32.0F, this.y1 / 32.0F)
				.color(64, 64, 64, 255).endVertex();
		bufferbuilder.vertex(this.x0, this.y1, -100.0D).uv(0.0F, this.y1 / 32.0F).color(64, 64, 64, 255).endVertex();
		tesselator.end();
		RenderSystem.depthFunc(515);
		RenderSystem.disableDepthTest();
		RenderSystem.enableBlend();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
				GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ZERO,
				GlStateManager.DestFactor.ONE);
		RenderSystem.disableTexture();
		RenderSystem.setShader(GameRenderer::getPositionColorShader);
		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
		bufferbuilder.vertex(this.x0, this.y0 + 4, 0.0D).color(0, 0, 0, 0).endVertex();
		bufferbuilder.vertex(this.x1, this.y0 + 4, 0.0D).color(0, 0, 0, 0).endVertex();
		bufferbuilder.vertex(this.x1, this.y0, 0.0D).color(0, 0, 0, 255).endVertex();
		bufferbuilder.vertex(this.x0, this.y0, 0.0D).color(0, 0, 0, 255).endVertex();
		bufferbuilder.vertex(this.x0, this.y1, 0.0D).color(0, 0, 0, 255).endVertex();
		bufferbuilder.vertex(this.x1, this.y1, 0.0D).color(0, 0, 0, 255).endVertex();
		bufferbuilder.vertex(this.x1, this.y1 - 4, 0.0D).color(0, 0, 0, 0).endVertex();
		bufferbuilder.vertex(this.x0, this.y1 - 4, 0.0D).color(0, 0, 0, 0).endVertex();
		tesselator.end();
	}

	@Override
	public void render(PoseStack poseStack, int mouseX, int mouseY, float t) {

		int j1 = this.getRowLeft() + 10;
		int k = this.y0 + 4 - (int) this.getScrollAmount();

		this.renderList(poseStack, j1, k, mouseX, mouseY, t);
		this.renderBackground(poseStack);

		int k1 = this.getMaxScroll();
		if (k1 > 0) {
			int i = this.getScrollbarPosition();
			int j = i + 6;
			RenderSystem.disableTexture();
			RenderSystem.setShader(GameRenderer::getPositionColorShader);
			int l1 = (int) ((float) ((this.y1 - this.y0) * (this.y1 - this.y0)) / (float) this.getMaxPosition());
			l1 = Mth.clamp(l1, 32, this.y1 - this.y0 - 8);
			int i2 = (int) this.getScrollAmount() * (this.y1 - this.y0 - l1) / k1 + this.y0;

			if (i2 < this.y0) {
				i2 = this.y0;
			}
			Tesselator tesselator = Tesselator.getInstance();
			BufferBuilder bufferbuilder = tesselator.getBuilder();
			bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
			bufferbuilder.vertex(i, this.y1, 0.0D).color(0, 0, 0, 255).endVertex();
			bufferbuilder.vertex(j, this.y1, 0.0D).color(0, 0, 0, 255).endVertex();
			bufferbuilder.vertex(j, this.y0, 0.0D).color(0, 0, 0, 255).endVertex();
			bufferbuilder.vertex(i, this.y0, 0.0D).color(0, 0, 0, 255).endVertex();

			bufferbuilder.vertex(i, i2 + l1, 0.0D).color(128, 128, 128, 255).endVertex();
			bufferbuilder.vertex(j, i2 + l1, 0.0D).color(128, 128, 128, 255).endVertex();
			bufferbuilder.vertex(j, i2, 0.0D).color(128, 128, 128, 255).endVertex();
			bufferbuilder.vertex(i, i2, 0.0D).color(128, 128, 128, 255).endVertex();

			bufferbuilder.vertex(i, i2 + l1 - 1, 0.0D).color(192, 192, 192, 255).endVertex();
			bufferbuilder.vertex(j - 1, i2 + l1 - 1, 0.0D).color(192, 192, 192, 255).endVertex();
			bufferbuilder.vertex(j - 1, i2, 0.0D).color(192, 192, 192, 255).endVertex();
			bufferbuilder.vertex(i, i2, 0.0D).color(192, 192, 192, 255).endVertex();
			tesselator.end();
			RenderSystem.enableTexture();
			RenderSystem.disableBlend();
		}
	}

	@Override
	protected void renderList(PoseStack poseStack, int x, int y, int mouseX, int mouseY, float t) {
		int size = this.getItemCount();
		for (int index = 0; index < size; ++index) {
			int top = this.getRowTop(index);
			int bottom = this.getRowTop(index) + this.itemHeight;
			if (this.y0 <= bottom && top <= this.y1) {
				int height = this.itemHeight - 4;
				int width = this.getRowWidth();
				NBTNodeEntry e = this.getEntry(index);
				int left = x + e.margin;

				boolean hovered = Objects.equals(this.getHovered(), e);
				e.render(poseStack, index, top, left, width, height, mouseX, mouseY, hovered, t);
			}
		}
	}

	public void renderOverflowY(PoseStack poseStack, int mouseX, int mouseY) {
		int scrollbarWidth = 6;
		int scrollbarHeight = this.height;
		int scrollbarX = this.width - scrollbarWidth;
		int scrollbarY = this.y0;
		Draw.box(scrollbarX, scrollbarY, scrollbarWidth, scrollbarHeight, 0, 0, 0, 1);
	}

	public void renderOverflowX(PoseStack poseStack, int mouseX, int mouseY) {
		int scrollbarWidth = this.width;
		int scrollbarHeight = 6;
		int scrollbarX = this.x0;
		int scrollbarY = this.height - scrollbarHeight;
		Draw.box(scrollbarX, scrollbarY, scrollbarWidth, scrollbarHeight, 0, 0, 0, 1);
	}

	public void init() {
		this.setSelected(null);
		this.clearEntries();
		this.addNodes(this.screen.tree().getRoot(), 0);
	}

	private void addNodes(Node<NamedNBT> node, int step) {
		final int size = NBTNodeList.this.minecraft.font.lineHeight;
		this.addEntry(node, step);
		if (node.shouldDrawChildren()) {
			step += size;
			for (Node<NamedNBT> child : node.children()) {
				this.addNodes(child, step + 1);
			}
		}
	}

	public int addEntry(Node<NamedNBT> node, int margin) {
		return super.addEntry(new NBTNodeEntry(node, margin));
	}

	@OnlyIn(Dist.CLIENT)
	public final class NBTNodeEntry extends ObjectSelectionList.Entry<NBTNodeEntry> {

		private final Node<NamedNBT> node;
		private final NodeButton nodeButton;

		public final int margin;
		protected long lastClickTime;
		protected boolean isHovered;
		protected boolean isDoubleClicked;

		public NBTNodeEntry(Node<NamedNBT> node, int margin) {
			this.margin = margin;
			this.node = node;
			this.nodeButton = node.hasChildren() ? NBTNodeList.this.screen.buttonsDefine().nodeButton(node, button -> {
				node.toggleDrawChildren();
				NBTNodeList.this.screen.getNBTNodeList().init();
			}) : null;
		}

		@Override
		public Component getNarration() {
			return Component.literal(NBTStringHelper.getNBTNameSpecial(this.node.object()));
		}

		@Override
		public void render(PoseStack poseStack, int index, int top, int left, int width, int height, int mouseX,
				int mouseY, boolean hovered, float t) {
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
			if (NBTNodeList.this.isSelectedItem(index)) {
				Draw.outer_border(left, top, width, height, 1, 1, 255, 255, 255, 255);
			}

			this.isHovered = (left <= mouseX && mouseX <= left + width) && (top <= mouseY && mouseY <= top + height);

			if (this.isHovered) {
				Draw.outer_border(left, top, width, height, 1, 0, 255, 255, 255, 255);
			}

			Draw.box(left, top, width, height, 0, 255, 0, 50);

			// draw node icon
			if (Objects.nonNull(this.nodeButton)) {
				this.nodeButton.x = left;
				this.nodeButton.y = top;
				this.nodeButton.render(poseStack, mouseX, mouseY, t);
			}

			// draw nbt icon
			left += height + 3;
			ResourceLocation resourceLocation = T.value(this.node.object().tag().getId()).getResource();
			Draw.draw(poseStack, left, top, 0, height, height, 0, 0, 16, 16, 16, 16, resourceLocation);

			// draw namednbt key and value
			left += height + 3;
			final Font font = NBTNodeList.this.minecraft.font;
			drawString(poseStack, font, this.getNarration(), left, top, 0xFFFFFF);
		}

		@Override
		public boolean mouseClicked(double mouseX, double mouseY, int code) {
			if (this.isHovered) {
				this.isDoubleClicked = Util.getMillis() - this.lastClickTime < 250L;
				NBTNodeList.this.setSelected(this);

				if (this.isDoubleClicked) {
					if (this.node.hasChildren()) {
						this.nodeButton.onPress();
						return true;
					} else {
						NBTNodeList.this.screen.buttonsDefine().editTag().onPress();
						return true;
					}
				} else if (this.node.hasChildren()) {
					if (this.nodeButton.mouseClicked(mouseX, mouseY, code)) {
						return true;
					}
				}

				this.lastClickTime = Util.getMillis();
			}
			return false;
		}

		public Node<NamedNBT> node() {
			return this.node;
		}
	}
}
