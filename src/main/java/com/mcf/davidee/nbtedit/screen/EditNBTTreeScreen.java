package com.mcf.davidee.nbtedit.screen;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

import javax.annotation.Nonnull;

import com.mcf.davidee.nbtedit.NBTEdit;
import com.mcf.davidee.nbtedit.NBTEditMessage;
import com.mcf.davidee.nbtedit.nbt.NBTTarget;
import com.mcf.davidee.nbtedit.nbt.NBTTree;
import com.mcf.davidee.nbtedit.nbt.NamedNBT;
import com.mcf.davidee.nbtedit.nbt.Node;
import com.mcf.davidee.nbtedit.packets.EntityNBTPacket;
import com.mcf.davidee.nbtedit.packets.PacketHandler;
import com.mcf.davidee.nbtedit.packets.TileNBTPacket;
import com.mcf.davidee.nbtedit.screen.NBTNodeList.NBTNodeEntry;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EditNBTTreeScreen extends Screen {

	private final NBTTarget target;
	private NBTNodeList nodeList;
	private SaveStateList saveList;
	private final NBTTree tree;
	private final NBTButtons buttonDefine;
	private final List<Button> buttons;
	public int size = Minecraft.getInstance().font.lineHeight;

	public EditNBTTreeScreen(NBTTarget target) {
		super(Component.literal("NBTEdit -- " + target.toString()));
		NBTEditMessage.trace("EditNBTTreeScreen - " + target.toString());
		this.target = target;
		this.tree = NBTTree.of(this, target.tag());
		this.buttonDefine = new NBTButtons(this);
		this.buttons = this.buttonDefine.editNbtTree();
	}

	@Override
	protected void init() {
		this.buttons.forEach(this::addRenderableWidget);

		this.nodeList = new NBTNodeList(this, this.minecraft);
		this.nodeList.setRenderBackground(false);
		this.saveList = new SaveStateList(this, this.minecraft);
		this.addWidget(this.nodeList);
		this.addWidget(this.saveList);

		this.addRenderableWidget(
				new Button(this.width / 2 - 100, this.height - 30, 98, 20, CommonComponents.GUI_CANCEL, p_101366_ -> {
					this.onClose();
				}));
		this.addRenderableWidget(
				new Button(this.width / 2 + 2, this.height - 30, 98, 20, CommonComponents.GUI_DONE, p_101366_ -> {
					this.onCloseAndSave();
				}));

		this.addRenderableWidget(
				new Button(this.width / 4 * 3 + 30, 20, 30, 20, Component.literal("Load"), this.saveList.loadButton()));

		this.addRenderableWidget(
				new Button(this.width / 4 * 3, 20, 30, 20, Component.literal("Save"), this.saveList.saveButton()));
	}

	private void onCloseAndSave() {
		if (this.target.hasTag()) {
			final CompoundTag tag = this.tree.toNBTTagCompound();
			if (this.target.hasEntity()) {
				PacketHandler.sendToServer(new EntityNBTPacket(this.target.entity().getId(), tag));
			} else if (this.target.hasPos()) {
				PacketHandler.sendToServer(new TileNBTPacket(this.target.pos(), tag));
			}
		} else {
			throw new NullPointerException("Cant send tag " + this.target.toString());
		}
	}

	@Override
	public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
		try {
			Font font = this.minecraft.font;

			this.nodeList.render(poseStack, mouseX, mouseY, partialTick);
			this.saveList.render(poseStack, mouseX, mouseY, partialTick);
			super.render(poseStack, mouseX, mouseY, partialTick);
			drawCenteredString(poseStack, font, this.title, this.width / 2, 8, 0xFFFFFF);
			NBTNodeEntry nodeEntry = this.getNBTNodeEntry();
			Component nodeName = Objects.nonNull(nodeEntry) ? nodeEntry.getNarration()
					: Component.literal("non Focused");
			Component nodeShouldDraw = Objects.nonNull(nodeEntry) && nodeEntry.node().shouldDrawChildren()
					? Component.literal("open")
					: Component.literal("closed");
			drawString(poseStack, font, nodeName, this.width / 4 * 3 + 2, 0, 0xFFFFFF);
			drawString(poseStack, font, nodeShouldDraw, this.width / 4 * 3 + 2, 16, 0xFFFFFF);
		} catch (Exception e) {
			e.printStackTrace();
			Minecraft.getInstance().popGuiLayer();
		}
	}

	@Override
	public boolean keyPressed(int p_96552_, int p_96553_, int p_96554_) {
		if (Screen.hasControlDown()) {
			if (p_96552_ == InputConstants.KEY_C) {
				this.tree.copy();
			}
			if (p_96552_ == InputConstants.KEY_V) {
				this.tree.paste();
			}
			if (p_96552_ == InputConstants.KEY_X) {
				this.tree.cut();
			}
		}
		return super.keyPressed(p_96552_, p_96553_, p_96554_);
	}

	public void nodeEdited(Node<NamedNBT> node) {
		Node<NamedNBT> parent = node.parent();
		Collections.sort(parent.children(), NBTEdit.SORTER);
	}

	public NBTTarget target() {
		return this.target;
	}

	public NBTTree tree() {
		return this.tree;
	}

	public NBTButtons buttonsDefine() {
		return this.buttonDefine;
	}

	public void setNodeFocused(@Nonnull NBTNodeEntry nodeEntry) {

		if (Objects.nonNull(nodeEntry)) {
			Node<NamedNBT> select_node = nodeEntry.node();
			if (select_node.object().tag() instanceof CompoundTag) {
				this.buttonActives(true, (tree, buttons) -> {
					buttons.get(12).active = (select_node.hasParent()
							&& !(select_node.parent().object().tag() instanceof ListTag));
					buttons.get(13).active = (select_node != tree.tree().getRoot());
					buttons.get(14).active = (true);
					buttons.get(15).active = (select_node != tree.tree().getRoot());
					buttons.get(16).active = (NBTTree.clipboard != null);
				});
			} else if (select_node.object().tag() instanceof ListTag) {
				if (select_node.hasChildren()) {
					this.buttonActives(false, (tree, buttons) -> {
						List<Node<NamedNBT>> children = select_node.children();
						byte type = children.get(0).object().tag().getId();
						buttons.get(type - 1).active = (true);
						buttons.get(12).active = (!(select_node.parent().object().tag() instanceof ListTag));
						buttons.get(13).active = (true);
						buttons.get(14).active = (true);
						buttons.get(15).active = (true);
						buttons.get(16).active = (NBTTree.clipboard != null && NBTTree.clipboard.tag().getId() == type);
					});
				} else {
					this.buttonActives(true, (tree, buttons) -> {
						buttons.get(12).active = (!(select_node.parent().object().tag() instanceof ListTag));
						buttons.get(13).active = (true);
						buttons.get(14).active = (true);
						buttons.get(15).active = (true);
						buttons.get(16).active = (NBTTree.clipboard != null);
					});
				}
			} else {
				this.buttonActives(false, (tree, buttons) -> {
					buttons.get(12).active = (true);
					buttons.get(13).active = (true);
					buttons.get(14).active = (true);
					buttons.get(15).active = (true);
					buttons.get(16).active = (false);
				});
			}
		} else {
			this.buttonActives(false, null);
		}
	}

	private void buttonActives(boolean active, BiConsumer<EditNBTTreeScreen, List<Button>> func) {
		this.buttons.forEach(button -> button.active = active);

		if (Objects.nonNull(func)) {
			func.accept(this, this.buttons);
		}
	}

	public NBTNodeList getNBTNodeList() {
		return this.nodeList;
	}

	public NBTNodeEntry getNBTNodeEntry() {
		return this.getNBTNodeList().getSelected();
	}

	public SaveStateList getSaveStateList() {
		return this.saveList;
	}
}
