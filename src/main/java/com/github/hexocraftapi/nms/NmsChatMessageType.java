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

import com.github.hexocraftapi.nms.craft.Nms;
import com.github.hexocraftapi.reflection.resolver.FieldResolver;
import com.github.hexocraftapi.reflection.resolver.minecraft.NMSClassResolver;

public class NmsChatMessageType extends Nms
{
	static class Reflection
	{
		private static final Class<?>      nmsChatMessageType              = new NMSClassResolver().resolveSilent("ChatMessageType");
		private static final FieldResolver nmsChatMessageTypeFieldResolver = new FieldResolver(nmsChatMessageType);
	}


	static {
		try
		{
			if(Reflection.nmsChatMessageType.isEnum())
			{
				for(Object objEnum: Reflection.nmsChatMessageType.getEnumConstants())
				{
					Object objEnumValue = Reflection.nmsChatMessageTypeFieldResolver.resolve("d").get(objEnum) ;

					if((byte)objEnumValue == 0)
						NmsChatMessageType.CHAT = objEnum;
					else if((byte)objEnumValue == 1)
						SYSTEM = objEnum;
					else if((byte)objEnumValue == 2)
						GAME_INFO = objEnum;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}



	private byte   chatMessageType;
	private static Object CHAT;			// CHAT : 0
	private static Object SYSTEM;		// SYSTEM : 1
	private static Object GAME_INFO;	// GAME_INFO : 2


	public NmsChatMessageType(byte chatMessageType)
	{
		this.chatMessageType = chatMessageType;

		if(chatMessageType == 0)
			nms = CHAT;
		else if(chatMessageType == 1)
			nms = SYSTEM;
		else if(chatMessageType == 2)
			nms = GAME_INFO;
	}
}
