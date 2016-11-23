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

import com.github.hexocraftapi.nms.craft.CraftResolver;
import com.github.hexocraftapi.nms.utils.NmsPlayerUtil;
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

	public static void send(Player player, Chunk chunk)
	{
		send(player, CraftResolver.getHandle(chunk));
	}

	public static void send(Player player, Object nmsChunk)
	{
		try
		{
			Object nmsPacket1 = Reflection.nmsPacketChunkConstructorResolver
									.resolve(new Class[] {nmsChunk.getClass(), int.class})
									.newInstance(new Object[] { nmsChunk, 65280 });

			NmsPlayerUtil.sendPacket(player, nmsPacket1);

			Object nmsPacket2 = Reflection.nmsPacketChunkConstructorResolver
									.resolve(new Class[] {nmsChunk.getClass(), int.class})
									.newInstance(new Object[] { nmsChunk, 255 });

			NmsPlayerUtil.sendPacket(player, nmsPacket2);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
