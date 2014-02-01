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
package de.matzefratze123.heavyspleef.listener;

import de.matzefratze123.heavyspleef.HeavySpleef;
import de.matzefratze123.heavyspleef.core.Game;
import de.matzefratze123.heavyspleef.core.GameManager;
import de.matzefratze123.heavyspleef.core.GameState;
import de.matzefratze123.heavyspleef.objects.SpleefPlayer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {
    
    /**
     * A flag to indicate whether the event is registered.
     */
    private static boolean isRegistered = false;
    
    /**
     * The listener singleton.
     */
    private final static PlayerMoveListener listener = new PlayerMoveListener();;

    public static void RegisterEvent() {
        if (!isRegistered) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, HeavySpleef.getInstance());
            isRegistered = true;
        }
    }
    
    /**
     * Private constructor so that only the static members of this 
     * class can instantiate it.
     */
    private PlayerMoveListener() {}
    
    /**
     * Handles events when a player moves.
     * 
     * @param e The event to handle.
     */
    @EventHandler(ignoreCancelled = true)
	public void onPlayerMove(PlayerMoveEvent e) {
        boolean countdown = false;
        
        for (Game g : GameManager.getGames()) {
            if (g.getGameState() == GameState.COUNTING) {
                countdown = true;
                break;
            }
        }
        
        // If any of the games has a countdown, do not unregister the event.
        if (!countdown) {
            // Run this outside of the event loop, in case of Concurrent Modification
            Bukkit.getServer().getScheduler().runTaskLater(HeavySpleef.getInstance(), new Runnable() {
                @Override
                public void run() {
                    PlayerMoveEvent.getHandlerList().unregister(listener);
                }
            }, 0);

            // Don't need to do any more.
            return;
        }
        
    	SpleefPlayer player = HeavySpleef.getInstance().getSpleefPlayer(e.getPlayer());
        
        // If the player is not in an active game
        if (!player.isActive()) {
			return;
		}
        
        if (player.isSpectating()) {
            return;
        }
        
        if (player.getGame().getGameState() == GameState.COUNTING) {
            e.setCancelled(true);
        }
    }
}
