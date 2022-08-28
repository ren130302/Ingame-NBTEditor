package com.mcf.davidee.nbtedit.screen;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

import com.mcf.davidee.nbtedit.NBTEdit;
import com.mcf.davidee.nbtedit.NBTEditMessage;
import com.mcf.davidee.nbtedit.nbt.NBTTree;
import com.mcf.davidee.nbtedit.nbt.NamedNBT;
import com.mcf.davidee.nbtedit.screen.NBTButtons.DeleteSaveButton;
import com.mcf.davidee.nbtedit.screen.NBTButtons.T;
import com.mcf.davidee.nbtedit.screen.NBTNodeList.NBTNodeEntry;
import com.mcf.davidee.nbtedit.screen.SaveStateList.SaveStateEntry;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Button.OnPress;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class SaveStateList extends ObjectSelectionList<SaveStateEntry> {

	private final EditNBTTreeScreen screen;

	public SaveStateList(EditNBTTreeScreen screen, Minecraft minecraft) {
		super(minecraft, screen.width / 5 * 2, screen.height, 40, screen.height - 40, minecraft.font.lineHeight + 4);
		NBTEditMessage.trace("SaveStateList - init");
		this.screen = screen;
		this.x0 = screen.getNBTNodeList().getWidth();
		this.x1 = screen.width;
		this.load();
	}

	@Override
	public void setSelected(SaveStateEntry entry) {
		super.setSelected(entry);
	}

	@Override
	public int getRowLeft() {
		return this.x0;
	}

	@Override
	public int getRowWidth() {
		return this.width - 20;
	}

	@Override
	public boolean charTyped(char p_94683_, int p_94684_) {
		SaveStateEntry entry = this.getSelected();
		if (Objects.nonNull(entry)) {
			if (entry.isEditable) {
				return entry.charTyped(p_94683_, p_94684_);
			}
		}
		return super.charTyped(p_94683_, p_94684_);
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int code) {
		this.updateScrollingState(mouseX, mouseY, code);
		if (!this.isMouseOver(mouseX, mouseY)) {
			return false;
		} else {
			for (SaveStateEntry entry : this.children()) {
				if (entry.mouseClicked(mouseX, mouseY, code)) {
					return true;
				}
			}

			if (code == 0) {
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
	public void render(PoseStack poseStack, int mouseX, int mouseY, float p_93450_) {
		int i = this.getScrollbarPosition();
		int j = i + 6;
		Tesselator tesselator = Tesselator.getInstance();
		BufferBuilder bufferbuilder = tesselator.getBuilder();
		RenderSystem.setShader(GameRenderer::getPositionTexColorShader);

		int j1 = this.getRowLeft() + 10;
		int k = this.y0 + 4 - (int) this.getScrollAmount();

		this.renderList(poseStack, j1, k, mouseX, mouseY, p_93450_);
		this.renderBackground(poseStack);

		int k1 = this.getMaxScroll();
		if (k1 > 0) {
			RenderSystem.disableTexture();
			RenderSystem.setShader(GameRenderer::getPositionColorShader);
			int l1 = (int) ((float) ((this.y1 - this.y0) * (this.y1 - this.y0)) / (float) this.getMaxPosition());
			l1 = Mth.clamp(l1, 32, this.y1 - this.y0 - 8);
			int i2 = (int) this.getScrollAmount() * (this.y1 - this.y0 - l1) / k1 + this.y0;

			if (i2 < this.y0) {
				i2 = this.y0;
			}

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
		}

		RenderSystem.enableTexture();
		RenderSystem.disableBlend();
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
				SaveStateEntry e = this.getEntry(index);
				int left = x;

				boolean hovered = Objects.equals(this.getHovered(), e);
				e.render(poseStack, index, top, left, width, height, mouseX, mouseY, hovered, t);
			}
		}
	}

	public OnPress saveButton() {
		return button -> {
			// SaveStateEntry save = this.getSelected();
			NBTNodeEntry node = this.screen.getNBTNodeEntry();

			NamedNBT tag = Objects.isNull(node) ? NBTTree.clipboard : node.node().object();

			this.addEntry(new SaveStateEntry(tag.name(), tag));
			this.save();
			this.load();
		};
	}

	public OnPress loadButton() {
		return button -> {
			SaveStateEntry save = this.getSelected();
			if (Objects.isNull(save)) {
				NBTEditMessage.failed("Press after selecting the slot you want to load.");
				return;
			}
			NBTTree.clipboard = save.tag;
		};
	}

	private final String key_tag = "tag";
	private final String key_name = "name";

	public void save() {
		try {
			if (!NBTEdit.NBT_DAT.exists()) {
				Files.createFile(NBTEdit.NBT_DAT.toPath());
			}

			CompoundTag root = new CompoundTag();

			for (SaveStateEntry entry : this.children()) {
				CompoundTag slot = new CompoundTag();

				NamedNBT namedNbt = entry.tag;

				slot.put(this.key_tag, namedNbt.tag());
				slot.putString(this.key_name, namedNbt.name());

				root.put(entry.label, slot);
			}

			NbtIo.write(root, NBTEdit.NBT_DAT);
			NBTEditMessage.trace("NBTEdit.dat saved successfully.");
		} catch (IOException e) {
			e.printStackTrace();
			NBTEditMessage.warn("Unable to write NBTEdit save.");
		}
	}

	public void load() {
		this.clearEntries();
		try {
			if (NBTEdit.NBT_DAT.exists() && NBTEdit.NBT_DAT.canRead()) {

				CompoundTag root = NbtIo.read(NBTEdit.NBT_DAT);

				for (String label : root.getAllKeys()) {
					CompoundTag slot = root.getCompound(label);

					Tag tag = slot.get(this.key_tag);
					String name = slot.getString(this.key_name);
					NamedNBT namedNbt = NamedNBT.of(name, tag);

					this.addEntry(new SaveStateEntry(label, namedNbt));
				}
			}
			NBTEditMessage.trace("NBTEdit.dat loaded successfully.");
		} catch (IOException e) {
			e.printStackTrace();
			NBTEditMessage.warn("Unable to read NBTEdit save.");
		}
	}

	public class SaveStateEntry extends ObjectSelectionList.Entry<SaveStateEntry> {

		public String label;
		public NamedNBT tag;

		private final EditBox editBox;
		private final DeleteSaveButton deleteButton;
		private boolean isEditable;

		protected long lastClickTime;
		protected boolean isHovered;
		protected boolean isDoubleClicked;

		public SaveStateEntry(String label, NamedNBT tag) {
			this.label = label;
			this.tag = tag;
			this.editBox = new EditBox(SaveStateList.this.minecraft.font, 0, 0, SaveStateList.this.getRowWidth(),
					SaveStateList.this.itemHeight, Component.empty());
			this.editBox.setMaxLength(32);
			this.editBox.setBordered(false);
			this.editBox.setEditable(false);
			this.editBox.setValue(label);
			this.editBox.setTextColor(0xFFFFFF);
			this.editBox.setTextColorUneditable(0xFFFFFF);
			this.editBox.visible = true;
			this.deleteButton = SaveStateList.this.screen.buttonsDefine().deleteSave(button -> {
				SaveStateList.this.removeEntry(this);
				SaveStateList.this.save();
			});
		}

		@Override
		public Component getNarration() {
			return Component.literal(this.editBox.getValue());
		}

		@Override
		public void render(PoseStack poseStack, int index, int top, int left, int width, int height, int mouseX,
				int mouseY, boolean hovered, float t) {
			RenderSystem.setShader(GameRenderer::getPositionShader);
			RenderSystem.enableBlend();
			RenderSystem.defaultBlendFunc();
			RenderSystem.enableDepthTest();

			if (SaveStateList.this.isSelectedItem(index)) {
				Draw.outer_border(left, top, width, height, 1, 1, 255, 255, 255, 255);
			}

			this.isHovered = (left <= mouseX && mouseX <= left + width) && (top <= mouseY && mouseY <= top + height);

			if (this.isHovered) {
				Draw.outer_border(left, top, width, height, 1, 0, 255, 255, 255, 255);
			}

			Draw.box(left, top, width, height, 0, 255, 0, 50);

			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

			// draw delete button
			this.deleteButton.x = left;
			this.deleteButton.y = top;
			this.deleteButton.render(poseStack, mouseX, mouseY, t);

			// draw nbt icon
			left += height + 3;
			ResourceLocation resourceLocation = T.value(this.tag.tag().getId()).getResource();
			Draw.draw(poseStack, left, top, 0, height, height, 0, 0, 16, 16, 16, 16, resourceLocation);

			// draw slotname
			left += height + 3;

			this.editBox.x = left;
			this.editBox.y = top;
			this.editBox.tick();
			this.editBox.renderButton(poseStack, mouseX, mouseY, t);
		}

		@Override
		public boolean mouseClicked(double mouseX, double mouseY, int code) {
			if (this.isHovered) {
				this.isDoubleClicked = Util.getMillis() - this.lastClickTime < 250L;
				SaveStateList.this.setSelected(this);

				if (this.isDoubleClicked) {
					this.setEdited(!this.isEditable, true, false);
					return true;
				} else if (this.deleteButton.mouseClicked(mouseX, mouseY, code)) {
					return true;
				}

				this.lastClickTime = Util.getMillis();
			} else if (this.isEditable) {
				this.setEdited(false, false, true);
				return true;
			} else {
				SaveStateList.this.setSelected(null);
				return false;
			}
			return super.mouseClicked(mouseX, mouseY, code);
		}

		private void setEdited(boolean isEditable, boolean needSave, boolean needLoad) {
			this.isEditable = isEditable;
			this.editBox.setFocus(this.isEditable);
			this.editBox.setEditable(this.isEditable);

			if (!this.isEditable && needSave) {
				this.label = this.editBox.getValue();
				SaveStateList.this.save();
			}
			if (needLoad) {
				SaveStateList.this.load();
			}
		}

		@Override
		public boolean charTyped(char p_94732_, int p_94733_) {
			return this.editBox.charTyped(p_94732_, p_94733_);
		}

		@Override
		public boolean keyPressed(int code, int p_94746_, int p_94747_) {
			return this.editBox.keyPressed(code, p_94746_, p_94747_);
		}
	}
}
