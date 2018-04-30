package com.github.hexocraftapi.nms.packet;

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

import com.github.hexocraftapi.nms.NmsChunk;
import com.github.hexocraftapi.nms.utils.NmsPlayerUtil;
import com.github.hexocraftapi.reflection.minecraft.Minecraft;
import com.github.hexocraftapi.reflection.resolver.ConstructorResolver;
import com.github.hexocraftapi.reflection.resolver.minecraft.NMSClassResolver;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class NmsPacketPlayOutMapChunk
{
	static class Reflection {
		private static final Class<?>            nmsPacketPlayOutMapChunk          = new NMSClassResolver().resolveSilent("PacketPlayOutMapChunk");
		private static final ConstructorResolver nmsPacketChunkConstructorResolver = new ConstructorResolver(nmsPacketPlayOutMapChunk);
	}

	public static void send(Chunk chunk, Player player)
	{
		send(new NmsChunk(chunk), player);
	}

	public static void send(NmsChunk nmsChunk, Player player)
	{
		Player[] players = new Player[] { player };
		send(nmsChunk, players);
	}

	public static void send(NmsChunk nmsChunk, Player... players)
	{
		int bitmask = 65535;
		//int bitmask = 65280;
		//int bitmask = 255;

		try
		{
			if(Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R2))
			{
				Object nmsPacket = Reflection.nmsPacketChunkConstructorResolver
										.resolve(new Class[] {nmsChunk.nms().getClass(), boolean.class, int.class})
										.newInstance(new Object[] { nmsChunk.nms(), false, bitmask });

				for(Player player : players)
					NmsPlayerUtil.sendPacket(player, nmsPacket);
			}
			else
			{
				Object nmsPacket = Reflection.nmsPacketChunkConstructorResolver
										.resolve(new Class[] {nmsChunk.nms().getClass(), int.class})
										.newInstance(new Object[] { nmsChunk.nms(), bitmask });

				for(Player player : players)
					NmsPlayerUtil.sendPacket(player, nmsPacket);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}