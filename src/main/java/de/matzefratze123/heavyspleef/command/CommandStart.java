/*
 *   HeavySpleef - Advanced spleef plugin for bukkit
 *   
 *   Copyright (C) 2013-2014 matzefratze123
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
package de.matzefratze123.heavyspleef.command;

import static de.matzefratze123.heavyspleef.util.I18N._;

import org.bukkit.entity.Player;

import de.matzefratze123.api.command.Command;
import de.matzefratze123.api.command.CommandHelp;
import de.matzefratze123.api.command.CommandListener;
import de.matzefratze123.api.command.CommandPermissions;
import de.matzefratze123.heavyspleef.HeavySpleef;
import de.matzefratze123.heavyspleef.command.handler.UserType;
import de.matzefratze123.heavyspleef.command.handler.UserType.Type;
import de.matzefratze123.heavyspleef.core.Game;
import de.matzefratze123.heavyspleef.core.GameState;
import de.matzefratze123.heavyspleef.core.Team;
import de.matzefratze123.heavyspleef.core.flag.FlagType;
import de.matzefratze123.heavyspleef.objects.SpleefPlayer;
import de.matzefratze123.heavyspleef.util.Permissions;

@UserType(Type.PLAYER)
public class CommandStart implements CommandListener {

	@Command(value = "start", onlyIngame = true)
	@CommandPermissions(value = {Permissions.START_GAME})
	@CommandHelp(usage = "/spleef start <game>", description = "Starts a game")
	public void execute(Player bukkitPlayer, Game game) {
		SpleefPlayer player = HeavySpleef.getInstance().getSpleefPlayer(bukkitPlayer);
		
		if (game == null) {
			if (!player.isActive()) {
				player.sendMessage(_("notIngame"));
				return;
			}

			game = player.getGame();
		} else {
			permissionsCheck: {
				Game playerGame = player.getGame();

				if (playerGame != null && playerGame.getName().equalsIgnoreCase(game.getName()))
					break permissionsCheck;
				if (player.getBukkitPlayer().hasPermission(Permissions.START_OTHER_GAME.getPerm()))
					break permissionsCheck;

				player.sendMessage(_("noPermission"));
				return;
			}
		}

		start(player, game);
	}

	public static void start(SpleefPlayer player, Game game) {
		if (game.getGameState() == GameState.DISABLED) {
			player.sendMessage(_("gameIsDisabled"));
			return;
		}
		if (game.getGameState() == GameState.COUNTING || game.getGameState() == GameState.INGAME) {
			player.sendMessage(_("cantStartGameWhileRunning"));
			return;
		}

		int minplayers = game.getFlag(FlagType.MINPLAYERS);

		if (game.getIngamePlayers().size() < minplayers || game.getIngamePlayers().size() < 2) {
			player.sendMessage(_("notEnoughPlayers", String.valueOf(minplayers)));
			return;
		}
		if (game.getFlag(FlagType.TEAM)) {
			for (Team team : game.getComponents().getTeams()) {
				if (team.getPlayers().size() < team.getMinPlayers()) {
					player.sendMessage(_("teamNeedMorePlayers", team.getColor()
							.toMessageColorString(),
							String.valueOf(team.getMinPlayers())));

				}
			}

			// Check if there is only one team
			if (game.getComponents().getActiveTeams().size() < 2) {
				player.sendMessage(_("minimumTwoTeams"));
				return;
			}
		}

		game.countdown();
		player.sendMessage(_("gameStarted"));
	}

}
