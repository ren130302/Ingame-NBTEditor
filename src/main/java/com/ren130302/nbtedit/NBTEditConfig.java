package com.ren130302.nbtedit;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.fml.config.ModConfig;

public class NBTEditConfig {

	public static boolean opOnly;
	public static boolean editOtherPlayers;

	public static void init(ModConfig config) {
		editOtherPlayers = Holder.CONFIG.editOtherPlayers.get();
		opOnly = Holder.CONFIG.opOnly.get();
	}

	public static class Holder {
		public final BooleanValue opOnly;
		public final BooleanValue editOtherPlayers;

		public Holder(ForgeConfigSpec.Builder builder) {
			builder.push("general");
			this.opOnly = builder
					.comment("true if only Ops can NBTEdit; false allows users in creative mode to NBTEdit")
					.define("opOnly", true);
			this.editOtherPlayers = builder.comment(
					"true if editing players other then your self is allowed. false by default. USE AT YOUR OWN RISK")
					.define("editOtherPlayers", false);
		}

		public static final ForgeConfigSpec SPEC;
		public static final Holder CONFIG;

		static {
			Pair<Holder, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(Holder::new);
			CONFIG = pair.getLeft();
			SPEC = pair.getRight();
		}
	}
}
