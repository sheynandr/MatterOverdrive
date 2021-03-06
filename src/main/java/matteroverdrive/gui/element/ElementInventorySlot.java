/*
 * This file is part of Matter Overdrive
 * Copyright (c) 2015., Simeon Radivoev, All rights reserved.
 *
 * Matter Overdrive is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Matter Overdrive is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Matter Overdrive.  If not, see <http://www.gnu.org/licenses>.
 */

package matteroverdrive.gui.element;

import matteroverdrive.client.render.HoloIcon;
import matteroverdrive.container.slot.MOSlot;
import matteroverdrive.gui.MOGuiBase;
import matteroverdrive.util.MOStringHelper;

import java.util.List;

/**
 * Created by Simeon on 4/8/2015.
 */
public class ElementInventorySlot extends ElementSlot
{
    MOSlot slot;

    public ElementInventorySlot(MOGuiBase gui, MOSlot slot, int posX, int posY, int width, int height, String type, HoloIcon icon)
    {
        super(gui, posX, posY,width,height, type,icon);
        this.slot = slot;

    }

    public ElementInventorySlot(MOGuiBase gui,MOSlot slot, int posX, int posY,int width,int height, String type)
    {
        this(gui,slot, posX, posY,width,height, type,slot.getHoloIcon());
    }

    public ElementInventorySlot(MOGuiBase gui,MOSlot slot,int width,int height, String type,HoloIcon icon) {
        this(gui,slot, slot.xDisplayPosition, slot.yDisplayPosition, width,height,type,icon);
    }

    public ElementInventorySlot(MOGuiBase gui,MOSlot slot,int width,int height, String type) {
        this(gui,slot, slot.xDisplayPosition, slot.yDisplayPosition, width,height,type,slot.getHoloIcon());
    }

    @Override
    public void addTooltip(List<String> list,int mouseX,int mouseY)
    {
        if (slot.getUnlocalizedTooltip() != null && !slot.getUnlocalizedTooltip().isEmpty() && !slot.getHasStack())
        {
            list.add(MOStringHelper.translateToLocal(slot.getUnlocalizedTooltip()));
        }
    }

    @Override
    public void updateInfo()
    {
        boolean isVisible = isVisible() && (parent == null || parent.isVisible());

        if (!isVisible)
        {
            slot.xDisplayPosition = Integer.MIN_VALUE+10;
            slot.yDisplayPosition = Integer.MIN_VALUE+10;
        }
        else {
            slot.xDisplayPosition = getGlobalX() + iconOffsetX;
            slot.yDisplayPosition = getGlobalY() + iconOffsetY;
        }

        slot.setVisible(isVisible);
    }

    @Override
    protected boolean canDrawIcon(HoloIcon icon)
    {
        return !slot.getHasStack();
    }

    public MOSlot getSlot() {
        return slot;
    }

    public void setSlot(MOSlot slot) {
        this.slot = slot;
    }
}
