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

import com.github.hexocraftapi.nms.NmsChatMessageType;
import com.github.hexocraftapi.nms.utils.NmsPlayerUtil;
import com.github.hexocraftapi.reflection.minecraft.Minecraft;
import com.github.hexocraftapi.reflection.resolver.ConstructorResolver;
import com.github.hexocraftapi.reflection.resolver.MethodResolver;
import com.github.hexocraftapi.reflection.resolver.ResolverQuery;
import com.github.hexocraftapi.reflection.resolver.minecraft.NMSClassResolver;
import org.bukkit.entity.Player;
public class NmsPacketPlayOutChat
{
	static class Reflection {

		private static final NMSClassResolver NMS_CLASS_RESOLVER = new NMSClassResolver();

		//
		private static final Class<?> PacketPlayOutChat  = NMS_CLASS_RESOLVER.resolveSilent("PacketPlayOutChat");
		private static final Class<?> IChatBaseComponent = NMS_CLASS_RESOLVER.resolveSilent("IChatBaseComponent");
		private static final Class<?> ChatMessageType    = NMS_CLASS_RESOLVER.resolveSilent("ChatMessageType");
		//
		private static final ConstructorResolver PacketChatConstructorResolver = new ConstructorResolver(PacketPlayOutChat);
		//
		private static MethodResolver ChatSerializerMethodResolver;


		protected static Object getSerializedMessage(String jsonMessage) throws ClassNotFoundException
		{
			if(ChatSerializerMethodResolver == null)
				ChatSerializerMethodResolver = new MethodResolver(NMS_CLASS_RESOLVER.resolve("ChatSerializer", "IChatBaseComponent$ChatSerializer"));

			try
			{
				return ChatSerializerMethodResolver
					.resolve(new ResolverQuery("a", String.class))
					.invoke(null, new Object[] { jsonMessage });
			}
			catch(ReflectiveOperationException e) {
				throw new RuntimeException(e);
			}
		}
	}


	public static void send(Player player, byte chatMessageType, String messages)
	{
		send(player, new NmsChatMessageType(chatMessageType), messages);
	}

	public static void send(Player player, NmsChatMessageType chatMessageType, String messages)
	{
		try
		{
			Object packet = null;

			if(Minecraft.VERSION.olderThan(Minecraft.Version.v1_12_R1))
			{
				packet = Reflection.PacketChatConstructorResolver
					.resolve(new Class[] {Reflection.IChatBaseComponent, byte.class})
					.newInstance(new Object[] { Reflection.getSerializedMessage(messages), (byte)chatMessageType.nms() });
			}
			else
			{
				packet = Reflection.PacketChatConstructorResolver
					.resolve(new Class[] {Reflection.IChatBaseComponent, Reflection.ChatMessageType})
					.newInstance(new Object[] { Reflection.getSerializedMessage(messages), chatMessageType.nms() });
			}

			NmsPlayerUtil.sendPacket(player, packet);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
