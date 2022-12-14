package com.ren130302.nbtedit.screen;

import java.util.ArrayList;
import java.util.Objects;

import org.apache.commons.compress.utils.Lists;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.ren130302.lib.Draw;
import com.ren130302.nbtedit.NBTEditMessage;
import com.ren130302.nbtedit.NBTTags;
import com.ren130302.nbtedit.NBTTags.NBTTag;
import com.ren130302.nbtedit.nbt.NamedNBT;
import com.ren130302.nbtedit.nbt.Node;
import com.ren130302.nbtedit.screen.NBTNodeList.NBTNodeEntry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Button.OnPress;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

/**
 * Extended ImageButton
 *
 *
 * @author ren130302
 */
public class NBTButtons {

	enum T {
		END(NBTTags.END), BYTE(NBTTags.BYTE), SHORT(NBTTags.SHORT), INT(NBTTags.INT), LONG(NBTTags.LONG),
		FLOAT(NBTTags.FLOAT), DOUBLE(NBTTags.DOUBLE), BYTE_ARRAY(NBTTags.BYTE_ARRAY), STRING(NBTTags.STRING),
		LIST(NBTTags.LIST), COMPOUND(NBTTags.COUMPOUND), INT_ARRAY(NBTTags.INT_ARRAY), LONG_ARRAY(NBTTags.LONG_ARRAY),
		EDIT(), DELETE(), COPY(), CUT(), PASTE(), SECTION(), NEXTLINE(), NODE();

		private final ResourceLocation resource;
		private final NBTTag<?> tag;

		private T() {
			this(null);
		}

		private T(NBTTag<?> tag) {
			this.tag = tag;
			this.resource = new ResourceLocation("nbtedit", "textures/gui/" + this.name().toLowerCase() + ".png");
		}

		public byte getId() {
			return Objects.nonNull(this.tag) ? this.getTag().getId() : -1;
		}

		public int getY() {
			return Objects.nonNull(this.tag) ? 17 : 4;
		}

		public Tag getTag() {
			return this.tag.instance();
		}

		public ResourceLocation getResource() {
			return this.resource;
		}

		public static T value(byte id) {
			for (T value : values()) {
				if (value.getId() == id) {
					return value;
				}
			}
			return null;
		}
	}

	private final EditNBTTreeScreen screen;

	public NBTButtons(EditNBTTreeScreen screen) {
		NBTEditMessage.trace("NBTButtons - init");
		this.screen = screen;
	}

	public ArrayList<Button> editNbtTree() {
		ArrayList<Button> buttons = Lists.newArrayList();
		buttons.add(this.byteTag());
		buttons.add(this.shortTag());
		buttons.add(this.intTag());
		buttons.add(this.longTag());
		buttons.add(this.floatTag());
		buttons.add(this.doubleTag());
		buttons.add(this.byteArrayTag());
		buttons.add(this.stringTag());
		buttons.add(this.listTag());
		buttons.add(this.compoundTag());
		buttons.add(this.intArrayTag());
		buttons.add(this.longArrayTag());
		buttons.add(this.editTag());
		buttons.add(this.deleteTag());
		buttons.add(this.copyTag());
		buttons.add(this.cutTag());
		buttons.add(this.pasteTag());
		return buttons;
	}

	public CharacterButton section(int x, int y, OnPress onPress) {
		return this.characterButton(x, y, T.SECTION, onPress);
	}

	public CharacterButton newLine(int x, int y, OnPress onPress) {
		return this.characterButton(x, y, T.NEXTLINE, onPress);
	}

	public CharacterButton characterButton(int x, int y, T t, OnPress onPress) {
		return new CharacterButton(x, y, t.getResource(), onPress,
				Component.translatable("nbtedit." + t.name().toLowerCase()));
	}

	public OnPress addTag(byte id) {
		return button -> {
			this.screen.tree().addTag(id);
			NBTNodeList nodeList = this.screen.getNBTNodeList();
			NBTNodeEntry nodeEntry = nodeList.getSelected();
			nodeList.init();
			nodeList.setSelected(nodeEntry);
		};
	}

	public TagButton tag(int x, T t, OnPress onPress) {
		return new TagButton(x, t, onPress);
	}

	public TagButton tag(int x, T t) {
		return this.tag(x, t, this.addTag(t.getId()));
	}

	public TagButton byteTag() {
		return this.tag(18, T.BYTE);
	}

	public TagButton shortTag() {
		return this.tag(27, T.SHORT);
	}

	public TagButton intTag() {
		return this.tag(36, T.INT);
	}

	public TagButton longTag() {
		return this.tag(45, T.LONG);
	}

	public TagButton floatTag() {
		return this.tag(54, T.FLOAT);
	}

	public TagButton doubleTag() {
		return this.tag(63, T.DOUBLE);
	}

	public TagButton stringTag() {
		return this.tag(72, T.STRING);
	}

	public TagButton byteArrayTag() {
		return this.tag(81, T.BYTE_ARRAY);
	}

	public TagButton intArrayTag() {
		return this.tag(90, T.INT_ARRAY);
	}

	public TagButton longArrayTag() {
		return this.tag(99, T.LONG_ARRAY);
	}

	public TagButton listTag() {
		return this.tag(108, T.LIST);
	}

	public TagButton compoundTag() {
		return this.tag(117, T.COMPOUND);
	}

	public TagButton pasteTag() {
		return this.tag(48, T.PASTE, button -> {
			this.screen.tree().paste();
			this.screen.getNBTNodeList().init();
		});
	}

	public TagButton cutTag() {
		return this.tag(33, T.CUT, button -> {
			this.screen.tree().cut();
			this.screen.getNBTNodeList().init();
		});
	}

	public TagButton copyTag() {
		return this.tag(18, T.COPY, button -> {
			this.screen.tree().copy();
		});
	}

	public TagButton editTag() {
		return this.tag(78, T.EDIT, button -> {
			this.screen.tree().openEditWindow();
			this.screen.getNBTNodeList().init();
		});
	}

	public TagButton deleteTag() {
		return this.tag(93, T.DELETE, button -> {
			this.screen.tree().delete();
			this.screen.getNBTNodeList().init();
		});
	}

	public NodeButton nodeButton(Node<NamedNBT> node, OnPress onPress) {
		return new NodeButton(node, onPress);
	}

	public DeleteSaveButton deleteSave(OnPress onPress) {
		return new DeleteSaveButton(onPress);
	}

	public static class ImageButton extends Button {
		protected final ResourceLocation resourceLocation;

		protected final int texX;
		protected final int texY;
		protected final int texWidth;
		protected final int texHeight;
		protected final int picWidth;
		protected final int picHeight;

		public ImageButton(int x, int y, int width, int height, OnPress onPress, OnTooltip onTooltip, Component label,
				ResourceLocation resourceLocation, int texX, int texY, int texWidth, int texHeight, int picWidth,
				int picHeight) {
			// super(x, y, width, height, xTexStart, yTexStart, yDiffTex, resourceLocation,
			// textureWidth, textureHeight, onPress, onTooltip, component);
			super(x, y, width, height, Component.empty(), onPress, onTooltip);
			this.resourceLocation = resourceLocation;
			this.texX = texY;
			this.texY = texY;
			this.texWidth = texWidth;
			this.texHeight = texHeight;
			this.picWidth = picWidth;
			this.picHeight = picHeight;
		}

		public void playDownSound() {
			this.playDownSound(Minecraft.getInstance().getSoundManager());
		}

		@Override
		public void renderButton(PoseStack poseStack, int mouseX, int mouseY, float p_94285_) {
			RenderSystem.setShader(GameRenderer::getPositionTexShader);
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
			RenderSystem.enableBlend();
			RenderSystem.defaultBlendFunc();
			RenderSystem.enableDepthTest();
			RenderSystem.colorMask(true, true, true, true);

			float color = this.active ? (this.isHoveredOrFocused() ? 1F : 0.6F) : 0.2F;

			RenderSystem.setShaderColor(color, color, color, 1f);

			Draw.draw(poseStack, this.x, this.y, 0, this.width, this.height, this.texX, this.texY, this.texWidth,
					this.texHeight, this.picWidth, this.picHeight, this.resourceLocation);

			RenderSystem.disableTexture();

			if (this.isHoveredOrFocused()) {
				super.renderToolTip(poseStack, mouseX, mouseY);
			}
			RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
		}
	}

	public static class NBTButton extends ImageButton {

		public NBTButton(int x, int y, int size, OnPress onPress, Component label, ResourceLocation resourceLocation,
				int texX, int texY, int texSize, int picSize) {
			super(x, y, size, size, onPress, (button, poseStack, mouseX, mouseY) -> {
				if (Objects.nonNull(label)) {
					final Minecraft mc = Minecraft.getInstance();
					final Font font = mc.font;
					final int fontWidth = font.width(label);
					final int fontHeight = font.lineHeight;

					Draw.box(mouseX, mouseY, fontWidth, fontHeight, 0, 0, 0, 255);
					GuiComponent.drawString(poseStack, font, label, mouseX, mouseY, 0xFFFFFF);
				}
			}, label, resourceLocation, texX, texY, texSize, texSize, picSize, picSize);
		}

	}

	public static class CharacterButton extends NBTButton {
		public CharacterButton(int x, int y, ResourceLocation resourceLocation, OnPress onPress, Component label) {
			super(x, y, 26, onPress, label, resourceLocation, 0, 0, 26, 26);
		}
	}

	public class TagButton extends NBTButton {
		public TagButton(int x, T t, OnPress onPress) {
			// super(x, y, width, height, xTexStart, yTexStart, yDiffTex, resourceLocation,
			// textureWidth, textureHeight, onPress, onTooltip, component);
			super(x, t.getY(), NBTButtons.this.screen.size, onPress,
					Component.translatable("nbtedit." + t.name().toLowerCase()), t.getResource(), 0, 0, 16, 16);
		}
	}

	public class NodeButton extends NBTButton {

		private final Node<NamedNBT> node;

		public NodeButton(Node<NamedNBT> node, OnPress onPress) {
			// super(x, y, width, height, xTexStart, yTexStart, yDiffTex, resourceLocation,
			// textureWidth, textureHeight, onPress, onTooltip, component);
			super(0, 0, NBTButtons.this.screen.size, onPress,
					node.shouldDrawChildren() ? Component.translatable("nbtedit.node.open")
							: Component.translatable("nbtedit.node.closed"),
					T.NODE.getResource(), 0, 0, 18, 36);
			this.node = node;
		}

		@Override
		public void onPress() {
			super.playDownSound();
			super.onPress();
		}

		@Override
		public void renderButton(PoseStack poseStack, int mouseX, int mouseY, float p_94285_) {
			if (this.active) {
				RenderSystem.setShader(GameRenderer::getPositionTexShader);
				// Draw.drawBox(poseStack, 0, this.x, this.x + this.width, this.y, this.y
				// +this.height, 1, 1, 1, 1f);
				final int _texX = this.node.shouldDrawChildren() ? this.texX + this.texWidth : this.texX;
				final int _texY = this.isHoveredOrFocused() ? this.texY + this.texHeight : this.texY;

				Draw.draw(poseStack, this.x, this.y, 0, this.width, this.height, _texX, _texY, this.texWidth,
						this.texHeight, this.picWidth, this.picHeight, this.resourceLocation);

			} else {
				// Draw.drawBox(poseStack, 0, this.x, this.x + this.width, this.y, this.y +
				// this.height, 0, 0, 0, 0.5f);
			}
		}
	}

	public class DeleteSaveButton extends NBTButton {

		public DeleteSaveButton(OnPress onPress) {
			super(0, 0, NBTButtons.this.screen.size, onPress, null, T.DELETE.getResource(), 0, 0, 16, 16);
		}
	}

}