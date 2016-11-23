package com.github.hexocraftapi.nms.utils;

/*
 * Copyright 2016 hexosse
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.github.hexocraftapi.nms.NmsPlayer;
import org.bukkit.entity.Player;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class NmsPlayerUtil
{
	public static Object getPlayerConnection(Player player)
	{
		return new NmsPlayer(player).getPlayerConnection();
	}

	public static void sendPacket(Player player, Object packet)
	{
		new NmsPlayer(player).sendPacket(packet);
	}
}
