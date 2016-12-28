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
import com.github.hexocraftapi.nms.packet.NmsPacketPlayOutMapChunk;
import com.github.hexocraftapi.reflection.resolver.MethodResolver;
import com.github.hexocraftapi.reflection.resolver.ResolverQuery;
import com.github.hexocraftapi.reflection.resolver.minecraft.NMSClassResolver;
import com.github.hexocraftapi.reflection.util.AccessUtil;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class NmsChunk extends Nms
{
	static class Reflection {
		private static final Class<?>       nmsChunk               = new NMSClassResolver().resolveSilent("Chunk");
		private static final MethodResolver nmsChunkMethodResolver = new MethodResolver(nmsChunk);
	}

	private Chunk chunk;


	public NmsChunk(Chunk chunk)
	{
		this.chunk = chunk;
		this.nms = CraftResolver.getHandle(chunk);
	}

	public NmsChunk(Location location)
	{
		this.chunk = location.getChunk();
		this.nms = CraftResolver.getHandle(chunk);
	}

	// EnumSkyBlock : BLOCK (0), SKY (15)
	public int getBrightness(int enumSkyBlockValue, double x, double y, double z)
	{
		try {
			return (int) Reflection.nmsChunkMethodResolver
				.resolve("getBrightness")
				.invoke(nms, new NmsEnumSkyBlock(enumSkyBlockValue).nms(), new NmsBlockPosition(x, y, z).nms());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Check if Chunk is modified
	public boolean isModified()
	{
		try {
			Field isModified = AccessUtil.setAccessible(nms.getClass().getDeclaredField("s"));
			return isModified.getBoolean(nms);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// Set Chunk as modified
	public void setModified(boolean modified)
	{
		try {
			Reflection.nmsChunkMethodResolver
				.resolve(new ResolverQuery("f", boolean.class))
				.invoke(nms, modified);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}


	public void initLighting()
	{
		try {
			Reflection.nmsChunkMethodResolver
				.resolve( new ResolverQuery("initLighting"))
				.invoke(nms);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	// Send player Chunk update
	public void sendUpdate(Player player)
	{
		setModified(false);
		NmsPacketPlayOutMapChunk.send(player, nms);
	}
}
