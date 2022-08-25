package com.mcf.davidee.nbtedit.screen;

import java.util.Collections;
import java.util.Objects;

import com.mcf.davidee.nbtedit.NBTEdit;
import com.mcf.davidee.nbtedit.NBTEditMessage;
import com.mcf.davidee.nbtedit.NBTStringHelper;
import com.mcf.davidee.nbtedit.ParseHelper;
import com.mcf.davidee.nbtedit.nbt.NamedNBT;
import com.mcf.davidee.nbtedit.nbt.Node;
import com.mcf.davidee.nbtedit.screen.NBTButtons.CharacterButton;
import com.mcf.davidee.nbtedit.screen.NBTButtons.T;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EditNBTScreen extends Screen {

	private static final ResourceLocation WINDOW = new ResourceLocation("nbtedit", "textures/gui/window.png");

	public static final int WIDTH = 176;
	public static final int HEIGHT = 90;

	private final EditNBTTreeScreen parent;
	private final Node<NamedNBT> focused;
	private final Tag nbt;
	private final boolean canEditKey;
	private final boolean canEditValue;

	private EditBox key;
	private EditBox value;
	private Button save;
	private Button cancel;
	private String kError = null;
	private String vError = null;

	private CharacterButton newLine;
	private CharacterButton section;

	public EditNBTScreen(EditNBTTreeScreen parent) {
		super(Component.literal("EditNBT - window"));
		this.parent = parent;
		this.focused = parent.getNBTNodeEntry().node();

		NBTEditMessage.trace("EditNBTScreen - " + this.focused.toString());

		Tag tagbase = this.focused.object().tag();
		Tag tagparent = this.focused.parent().object().tag();

		this.nbt = this.focused.object().tag();
		this.canEditKey = !(tagparent instanceof ListTag);
		this.canEditValue = !(tagbase instanceof CompoundTag || tagbase instanceof ListTag);
	}

	@Override
	protected void init() {
		this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
		String sKey = (this.key == null) ? this.focused.object().name() : this.key.getValue();
		String sValue = (this.value == null) ? NBTStringHelper.getValue(this.nbt) : this.value.getValue();

		this.section = this.addRenderableWidget(new CharacterButton((this.width + WIDTH) / 2,
				(this.height - HEIGHT) / 2 + 34, T.SECTION.getResource(), button -> {
					this.value.insertText("§");
				}, Component.literal("")));
		this.newLine = this.addRenderableWidget(new CharacterButton((this.width + WIDTH) / 2,
				(this.height - HEIGHT) / 2 + 50, T.NEXTLINE.getResource(), button -> {
					this.value.insertText("\n");
				}, Component.literal("")));
		this.key = this.addRenderableWidget(new EditBox(this.minecraft.font, (this.width - WIDTH) / 2 + 46,
				(this.height - HEIGHT) / 2 + 18, 115, 14, Component.literal("key")));
		this.value = this.addRenderableWidget(new EditBox(this.minecraft.font, (this.width - WIDTH) / 2 + 46,
				(this.height - HEIGHT) / 2 + 44, 115, 14, Component.literal("value")));

		this.key.insertText(sKey);
		this.key.setBordered(false);
		this.key.setEditable(this.canEditKey);
		this.value.setMaxLength(256);
		this.value.insertText(sValue);
		this.value.setBordered(false);
		this.value.setEditable(this.canEditValue);

		this.cancel = this.addRenderableWidget(new Button(this.width / 2 - 100, this.height / 4 + 144 + 5, 98, 20,
				CommonComponents.GUI_CANCEL, p_101366_ -> {
					this.onClose();
				}));
		this.save = this.addRenderableWidget(new Button(this.width / 2 + 2, this.height / 4 + 144 + 5, 98, 20,
				CommonComponents.GUI_DONE, p_101366_ -> {
					this.onSaveAndQuit();
				}));

		if (!this.key.isFocused() && !this.value.isFocused()) {
			this.key.setEditable(this.canEditKey);
			this.value.setEditable(this.canEditValue);
		}

		this.section.active = (this.value.isFocused());
		this.newLine.active = (this.value.isFocused());
		this.checkValidInput();
	}

	@Override
	public void onClose() {
		this.minecraft.setScreen(this.parent);
	}

	private void onSaveAndQuit() {

		if (this.canEditKey) {
			this.focused.object().name(this.key.getValue());
		}

		ParseHelper.setValidValue(this.focused, this.value.getValue());

		this.parent.getNBTNodeList().init();
		final Node<NamedNBT> parent = this.focused.parent();

		Collections.sort(parent.children(), NBTEdit.SORTER);
		this.onClose();
	}

	@Override
	public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
		Font font = this.minecraft.font;
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderTexture(0, WINDOW);
		RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.enableDepthTest();
		GuiComponent.blit(poseStack, (this.width - WIDTH) / 2, (this.height - HEIGHT) / 2, 0, 0, WIDTH, HEIGHT, WIDTH,
				HEIGHT);

		if (Objects.nonNull(this.kError)) {
			drawCenteredString(poseStack, font, this.kError, this.width / 2, (this.height - HEIGHT) / 2 + 4, 0xFF3333);
		}
		if (Objects.nonNull(this.vError)) {// this.height + 32
			drawCenteredString(poseStack, font, this.vError, this.width / 2, (this.height - HEIGHT) / 2 + 32, 0xFF3333);
		}

		super.render(poseStack, mouseX, mouseY, partialTick);
	}

	@Override
	public void tick() {
		this.key.tick();
		this.value.tick();
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int code) {
		if (this.key.mouseClicked(mouseX, mouseY, code)) {
			this.key.setFocus(true);
			this.value.setFocus(false);
			this.setFocused(this.value);
		} else if (this.value.mouseClicked(mouseX, mouseY, code)) {
			this.key.setFocus(false);
			this.value.setFocus(true);
			this.setFocused(this.key);
		} else {
			return super.mouseClicked(mouseX, mouseY, code);
		}

		this.section.active = this.value.isFocused();
		this.newLine.active = this.value.isFocused();
		return false;
	}

	@Override
	public boolean keyPressed(int p_96552_, int p_96553_, int p_96554_) {
		if (p_96552_ == InputConstants.KEY_ESCAPE) {
			this.cancel.onPress();
		} else if (p_96552_ == InputConstants.KEY_TAB) {
			if (this.key.isFocused() && this.canEditValue) {
				this.key.setFocus(false);
				this.value.setFocus(true);
				this.setFocused(this.value);
			} else if (this.value.isFocused() && this.canEditKey) {
				this.key.setFocus(true);
				this.value.setFocus(false);
				this.setFocused(this.key);
			}
			this.section.active = this.value.isFocused();
			this.newLine.active = this.value.isFocused();
		} else if (p_96552_ == InputConstants.KEY_RETURN) {
			this.checkValidInput();
			if (this.save.active) {
				this.save.onPress();
			}
		} else {
			if (this.key.isFocused()) {
				this.setFocused(this.key);
			} else if (this.value.isFocused()) {
				this.setFocused(this.value);
			}

			super.keyPressed(p_96552_, p_96553_, p_96554_);
			this.checkValidInput();
		}

		return false;
	}

	@Override
	public boolean charTyped(char p_94683_, int p_94684_) {
		super.charTyped(p_94683_, p_94684_);
		this.checkValidInput();
		return false;
	}

	private void checkValidInput() {
		this.kError = null;
		this.vError = null;

		for (Node<NamedNBT> node : this.focused.parent().children()) {
			Tag base = node.object().tag();
			if (base != this.nbt && node.object().name().equals(this.key.getValue())) {
				this.kError = "Duplicate Tag Name";
				break;
			}
		}

		try {
			ParseHelper.validValue(this.value.getValue(), this.nbt.getId());
		} catch (NumberFormatException e) {
			this.vError = e.getMessage();
		}

		System.out.println(this.key.getValue() + " : " + this.kError);
		System.out.println(this.value.getValue() + " : " + this.vError);
		this.save.active = Objects.isNull(this.kError) && Objects.isNull(this.vError);
	}
}
