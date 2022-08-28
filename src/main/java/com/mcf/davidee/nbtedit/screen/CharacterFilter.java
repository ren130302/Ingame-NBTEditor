package com.mcf.davidee.nbtedit.screen;

import com.mcf.davidee.nbtedit.NBTStringHelper;

import net.minecraft.SharedConstants;

public class CharacterFilter {
	public static String filterText(String str, boolean section) {
		StringBuilder sb = new StringBuilder();
		for (char c : str.toCharArray()) {
			if (SharedConstants.isAllowedChatCharacter(c)
					|| (section && (c == NBTStringHelper.SECTION_SIGN || c == '\n'))) {
				sb.append(c);
			}
		}
		return sb.toString();
	}
}
