package com.github.hexocraftapi.nms;

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

import com.github.hexocraftapi.nms.craft.CraftResolver;
import com.github.hexocraftapi.nms.craft.Nms;
import com.github.hexocraftapi.reflection.resolver.FieldResolver;
import com.github.hexocraftapi.reflection.resolver.MethodResolver;
import com.github.hexocraftapi.reflection.resolver.minecraft.NMSClassResolver;
import org.bukkit.entity.Player;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class NmsPlayer extends Nms
{
	static class Reflection {
		// Get fields of class EntityPlayer
		private static FieldResolver  nmsEntityPlayerFieldResolver      = new FieldResolver(new NMSClassResolver().resolveSilent("EntityPlayer"));
		// Get methods of class PlayerConnection
		private static MethodResolver nmsPlayerConnectionMethodResolver = new MethodResolver(new NMSClassResolver().resolveSilent("PlayerConnection"));
	}


	private Player player;


	public NmsPlayer(Player player)
	{
		this.player = player;
		this.nms = CraftResolver.getHandle(player);
	}


	public Object getPlayerConnection()
	{
		try  {
			return Reflection.nmsEntityPlayerFieldResolver.resolve("playerConnection").get(this.nms);
		}
		catch(Exception ignored) {}
		return false;
	}

	public void sendPacket(Object packet)
	{
		try
		{
			final Object playerConnection = getPlayerConnection();

			Reflection.nmsPlayerConnectionMethodResolver
					.resolve("sendPacket")
					.invoke(playerConnection, packet);
		}
		catch(Exception ignored) {}
	}
}
