package com.mcf.davidee.nbtedit.nbt;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.compress.utils.Lists;

import com.mcf.davidee.nbtedit.NBTEdit;
import com.mcf.davidee.nbtedit.NBTStringHelper;
import com.mcf.davidee.nbtedit.screen.EditNBTScreen;
import com.mcf.davidee.nbtedit.screen.EditNBTTreeScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

public class NBTTree {

	private final Node<NamedNBT> root;

	private final EditNBTTreeScreen screen;

	@Nullable
	public static NamedNBT clipboard = null;

	private NBTTree(EditNBTTreeScreen screen, CompoundTag tag) {
		this.screen = screen;
		this.root = Node.of(NamedNBT.of("ROOT", null));
		this.root.object().tag(tag.copy());
		this.root.setDrawChildren(true);
		this.addChildrenToTree(this.root);
		this.sort(this.root);
	}

	public static NBTTree of(CompoundTag tag) {
		return of(null, tag);
	}

	public static NBTTree of(EditNBTTreeScreen screen, CompoundTag tag) {
		return new NBTTree(screen, tag);
	}

	public boolean sameAsRoot() {
		return Objects.nonNull(this.screen.getNBTNodeEntry()) && this.screen.getNBTNodeEntry().node().equals(this.root);
	}

	public boolean delete() {
		return !this.sameAsRoot() && this.deleteNode(this.screen.getNBTNodeEntry().node(), this.root);
	}

	public boolean deleteNode(Node<NamedNBT> toDelete, Node<NamedNBT> cur) {
		List<Node<NamedNBT>> children = cur.children();
		for (Node<NamedNBT> child : children) {
			if (child.equals(toDelete)) {
				children.remove(toDelete);
				return true;
			}
			boolean flag = this.deleteNode(toDelete, child);
			if (flag) {
				return true;
			}
		}
		return false;
	}

	public void sort(Node<NamedNBT> node) {
		Collections.sort(node.children(), NBTEdit.SORTER);
		for (Node<NamedNBT> c : node.children()) {
			this.sort(c);
		}
	}

	public void addChildrenToTree(Node<NamedNBT> parent) {
		Tag tag = parent.object().tag();
		if (tag instanceof CompoundTag) {
			CompoundTag compound = (CompoundTag) tag;
			for (String key : compound.getAllKeys()) {
				Tag base = compound.get(key);
				Node<NamedNBT> child = Node.of(parent, NamedNBT.of(key, base));
				parent.children(child);
				this.addChildrenToTree(child);
			}

		} else if (tag instanceof ListTag) {
			ListTag list = (ListTag) tag;
			for (int i = 0; i < list.size(); i++) {
				Node<NamedNBT> child = Node.of(parent, NamedNBT.of(list.get(i)));
				parent.children(child);
				this.addChildrenToTree(child);
			}
		}
	}

	public CompoundTag toNBTTagCompound() {
		CompoundTag tag = new CompoundTag();
		this.addChildrenToTag(this.root, tag);
		return tag;
	}

	public boolean validName(String name, List<Node<NamedNBT>> list) {
		for (Node<NamedNBT> node : list) {
			if (node.object().name().equals(name)) {
				return false;
			}
		}
		return true;
	}

	public CompoundTag addChildrenToTag(Node<NamedNBT> parent, CompoundTag tag) {
		for (Node<NamedNBT> child : parent.children()) {
			Tag base = child.object().tag();
			String name = child.object().name();
			if (base instanceof CompoundTag) {
				CompoundTag newTag = new CompoundTag();
				this.addChildrenToTag(child, newTag);
				tag.put(name, newTag);
			} else if (base instanceof ListTag) {
				ListTag list = new ListTag();
				this.addChildrenToList(child, list);
				tag.put(name, list);
			} else {
				tag.put(name, base.copy());
			}
		}
		return tag;
	}

	public ListTag addChildrenToList(Node<NamedNBT> parent, ListTag list) {
		for (Node<NamedNBT> child : parent.children()) {
			Tag base = child.object().tag();
			if (base instanceof CompoundTag) {
				CompoundTag newTag = new CompoundTag();
				this.addChildrenToTag(child, newTag);
				list.add(newTag);
			} else if (base instanceof ListTag) {
				ListTag newList = new ListTag();
				this.addChildrenToList(child, newList);
				list.add(newList);
			} else {
				list.add(base.copy());
			}
		}
		return list;
	}

	public void print() {
		this.print(this.root, 0);
	}

	private void print(Node<NamedNBT> n, int i) {
		System.out.println(this.repeat("\t", i) + NBTStringHelper.getNBTName(n.object()));
		for (Node<NamedNBT> child : n.children()) {
			this.print(child, i + 1);
		}
	}

	private List<String> toString(List<String> s, Node<NamedNBT> n, int i) {
		s.add(this.repeat("   ", i) + NBTStringHelper.getNBTName(n.object()));
		for (Node<NamedNBT> child : n.children()) {
			this.toString(s, child, i + 1);
		}
		return s;
	}

	public String repeat(String c, int i) {
		StringBuilder b = new StringBuilder(i + 1);
		for (int j = 0; j < i; ++j) {
			b.append(c);
		}
		return b.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		this.toString(Lists.newArrayList(), this.root, 0).forEach(s -> sb.append("\n\t\t\t" + s));
		return sb.toString();
	}

	private Node<NamedNBT> insert(NamedNBT nbt) {
		Node<NamedNBT> newNode = Node.of(this.screen.getNBTNodeEntry().node(), nbt);
		if (Objects.nonNull(this.screen.getNBTNodeEntry())) {

			if (this.screen.getNBTNodeEntry().node().hasChildren()) {
				List<Node<NamedNBT>> children = this.screen.getNBTNodeEntry().node().children();

				boolean added = false;
				for (int i = 0; i < children.size(); ++i) {
					if (NBTEdit.SORTER.compare(newNode, children.get(i)) < 0) {
						children.add(i, newNode);
						added = true;
						break;
					}
				}
				if (!added) {
					children.add(newNode);
				}
			} else {
				this.screen.getNBTNodeEntry().node().children(newNode);
			}
		}
		return newNode;
	}

	public Node<NamedNBT> insert(String name, byte type) {
		Tag nbt = NBTStringHelper.newTag(type);
		return Objects.nonNull(nbt) ? this.insert(NamedNBT.of(name, nbt)) : null;
	}

	private boolean canAddToParent(Tag parent, Tag child) {
		if (parent instanceof CompoundTag) {
			return true;
		}
		if (parent instanceof ListTag) {
			ListTag list = (ListTag) parent;
			return list.size() == 0 || list.getElementType() == child.getId();
		}
		return false;
	}

	public boolean canPaste(@Nonnull Node<NamedNBT> focused) {
		return clipboard != null && this.canAddToParent(focused.object().tag(), clipboard.tag());
	}

	public void paste() {
		Node<NamedNBT> focused = this.screen.getNBTNodeEntry().node();
		if (this.canPaste(focused)) {

			focused.setDrawChildren(true);

			NamedNBT namedNBT = clipboard.copy();
			if (focused.object().tag() instanceof ListTag) {
				namedNBT.name("");
				Node<NamedNBT> node = Node.of(focused, namedNBT);
				focused.children(node);
				this.addChildrenToTree(node);
				this.sort(node);
			} else {
				String name = namedNBT.name();
				List<Node<NamedNBT>> children = focused.children();
				if (!this.validName(name, children)) {
					for (int i = 1; i <= children.size() + 1; ++i) {
						String n = name + "(" + i + ")";
						if (this.validName(n, children)) {
							namedNBT.name(n);
							break;
						}
					}
				}
				Node<NamedNBT> node = this.insert(namedNBT);
				this.addChildrenToTree(node);
				this.sort(node);
			}
		}
	}

	public void copy() {
		Node<NamedNBT> focused = this.screen.getNBTNodeEntry().node();
		NamedNBT namedNBT = focused.object();
		if (namedNBT.tag() instanceof ListTag) {
			ListTag list = new ListTag();
			this.addChildrenToList(focused, list);
			clipboard = NamedNBT.of(namedNBT.name(), list);
		} else if (namedNBT.tag() instanceof CompoundTag) {
			CompoundTag compound = new CompoundTag();
			this.addChildrenToTag(focused, compound);
			clipboard = NamedNBT.of(namedNBT.name(), compound);
		} else {
			clipboard = focused.object().copy();
		}
	}

	public void cut() {
		this.copy();
		this.delete();
	}

	public void openEditWindow() {
		Minecraft.getInstance().setScreen(new EditNBTScreen(this.screen));
	}

	public Node<NamedNBT> addTag(byte id) {
		Node<NamedNBT> focused = this.screen.getNBTNodeEntry().node();
		focused.setDrawChildren(true);
		List<Node<NamedNBT>> children = focused.children();
		String type = NBTStringHelper.newTag(id).getType().getName();

		if (focused.object().tag() instanceof ListTag) {
			Tag nbt = NBTStringHelper.newTag(id);
			if (nbt != null) {
				Node<NamedNBT> newNode = Node.of(focused, NamedNBT.of("", nbt));
				children.add(newNode);
				return newNode;
			}
		} else {
			for (int i = 1; i <= children.size() + 1; ++i) {
				String name = type + i;
				if (this.validName(name, children)) {
					return this.insert(name, id);
				}
			}
		}
		return null;
	}

	public Node<NamedNBT> getRoot() {
		return this.root;
	}
}
