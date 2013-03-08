/**
 *   HeavySpleef - The simple spleef plugin for bukkit
 *   
 *   Copyright (C) 2013 matzefratze123
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package me.matzefratze123.heavyspleef.command;

import me.matzefratze123.heavyspleef.core.Game;
import me.matzefratze123.heavyspleef.core.GameManager;
import me.matzefratze123.heavyspleef.core.region.Floor;
import me.matzefratze123.heavyspleef.utility.Permissions;

import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRemoveFloor extends HSCommand {

	public CommandRemoveFloor() {
		setMaxArgs(0);
		setMinArgs(0);
		setOnlyIngame(true);
		setPermission(Permissions.REMOVE_FLOOR.getPerm());
		setUsage("/spleef removefloor");
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		Player player = (Player)sender;
		Block block = player.getTargetBlock(null, 50);
		if (block == null) {
			player.sendMessage(_("notLookingAtABlock"));
			return;
		}
		for (Game game : GameManager.getGames()) {
			for (Floor floor : game.getFloors()) {
				if (floor.contains(block)) {
					if (game.isIngame() || game.isCounting()) {
						player.sendMessage(_("cantRemoveFloorWhileRunning"));
						return;
					}
					
					int id = floor.getId();
					game.removeFloor(id);
					player.sendMessage(_("floorRemoved"));
					return;
				}
			}
		}
		
		player.sendMessage(_("notLookingAtFloor"));
	}

}
