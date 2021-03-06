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
import com.github.hexocraftapi.reflection.resolver.ResolverQuery;
import com.github.hexocraftapi.reflection.resolver.minecraft.NMSClassResolver;
import org.bukkit.Chunk;
import org.bukkit.World;


/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class NmsWorld extends Nms
{
	static class Reflection {
		private static final Class<?>       nmsWorld               = new NMSClassResolver().resolveSilent("World");
		private static final MethodResolver nmsWorldMethodResolver = new MethodResolver(nmsWorld);

		private static final Class<?>       nmsWorldServer         = new NMSClassResolver().resolveSilent("WorldServer");
		private static final MethodResolver nmsWorldServerMethodResolver = new MethodResolver(nmsWorldServer);

		private static final Class<?>       nmsChunkProviderServer = new NMSClassResolver().resolveSilent("ChunkProviderServer");
		private static final MethodResolver nmsChunkProviderServerMethodResolver = new MethodResolver(nmsChunkProviderServer);

		private static final Class<?>       nmsBlockPosition       = new NMSClassResolver().resolveSilent("BlockPosition");
		private static final Class<?>       nmsChunk               = new NMSClassResolver().resolveSilent("Chunk");
		private static final Class<?>       nmsEnumSkyBlock        = new NMSClassResolver().resolveSilent("EnumSkyBlock");
	}


	private World world;


	public NmsWorld(World world)
	{
		this.world = world;
		this.nms = CraftResolver.getHandle(world);
	}

	public void setBlockLight(double x, double y, double z, int light)
	{
		try {
			Reflection.nmsWorldMethodResolver
				.resolve(new ResolverQuery("a", Reflection.nmsEnumSkyBlock, Reflection.nmsBlockPosition, int.class))
				.invoke(nms, new NmsEnumSkyBlock(0).nms(), new NmsBlockPosition(x, y, z).nms(), light);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void setSkyLight(double x, double y, double z, int light)
	{
		try {
			Reflection.nmsWorldMethodResolver
			.resolve(new ResolverQuery("a", Reflection.nmsEnumSkyBlock, Reflection.nmsBlockPosition, int.class))
			.invoke(nms, new NmsEnumSkyBlock(15).nms(), new NmsBlockPosition(x, y, z).nms(), light);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public boolean relightBlock(double x, double y, double z)
	{
		try {
			Object o = Reflection.nmsWorldMethodResolver
						.resolve( new ResolverQuery("c", Reflection.nmsEnumSkyBlock, Reflection.nmsBlockPosition))
						.invoke(nms, new NmsEnumSkyBlock(0).nms(), new NmsBlockPosition(x, y, z).nms());
			return (boolean)o;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean relightSky(double x, double y, double z)
	{
		try {
			Object o = Reflection.nmsWorldMethodResolver
							.resolve( new ResolverQuery("c", Reflection.nmsEnumSkyBlock, Reflection.nmsBlockPosition))
							.invoke(nms, new NmsEnumSkyBlock(15).nms(), new NmsBlockPosition(x, y, z).nms());
			return (boolean)o;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean relight(double x, double y, double z)
	{
		try {
			Object o = Reflection.nmsWorldMethodResolver
						.resolve( new ResolverQuery("w", Reflection.nmsBlockPosition))
						.invoke(nms, new NmsBlockPosition(x, y, z).nms());
			return (boolean)o;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void saveChunk(Chunk chunk)
	{
		try {
			Object chunkProviderServer = Reflection.nmsWorldServerMethodResolver
				.resolve( new ResolverQuery("getChunkProviderServer"))
				.invoke(nms);

			Reflection.nmsChunkProviderServerMethodResolver
				.resolve( new ResolverQuery("saveChunk", Reflection.nmsChunk))
				.invoke(chunkProviderServer, new NmsChunk(chunk).nms());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
