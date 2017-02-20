/*
 * Copyright 2014 Mike Penz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mikepenz.goodwall_typeface_library;

import android.content.Context;
import android.graphics.Typeface;

import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.iconics.typeface.ITypeface;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class GoodWall implements ITypeface {
    private static final String TTF_FILE = "goodwall-font-v1.0.ttf";
    private static Typeface typeface = null;
    private static HashMap<String, Character> mChars;

    @Override
    public IIcon getIcon(String key) {
        return Icon.valueOf(key);
    }

    @Override
    public HashMap<String, Character> getCharacters() {
        if (mChars == null) {
            HashMap<String, Character> aChars = new HashMap<String, Character>();
            for (Icon v : Icon.values()) {
                aChars.put(v.name(), v.character);
            }
            mChars = aChars;
        }
        return mChars;
    }

    @Override
    public String getMappingPrefix() {
        return "gdw";
    }

    @Override
    public String getFontName() {
        return "GoodWall";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public int getIconCount() {
        return mChars.size();
    }

    @Override
    public Collection<String> getIcons() {
        Collection<String> icons = new LinkedList<String>();
        for (Icon value : Icon.values()) {
            icons.add(value.name());
        }
        return icons;
    }

    @Override
    public String getAuthor() {
        return "Goodwall";
    }

    @Override
    public String getUrl() {
        return "http://goodwall.org";
    }

    @Override
    public String getDescription() {
        return "for the goodwall challenge";
    }

    @Override
    public String getLicense() {
        return "...";
    }

    @Override
    public String getLicenseUrl() {
        return "...";
    }

    @Override
    public Typeface getTypeface(Context context) {
        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/" + TTF_FILE);
            } catch (Exception e) {
                return null;
            }
        }
        return typeface;
    }

    public enum Icon implements IIcon {
        gdw_Trophy_02_18('\ue960'),
		gdw_Info_18_02('\ue95a'),
		gdw_Link_19('\ue95b'),
		gdw_School_04_18('\ue95c'),
		gdw_Medal_18('\ue95d'),
		gdw_Groups_03_18('\ue95f'),
		gdw_Plus_14('\ue957'),
		gdw_Connected_Circle_18('\ue961'),
		gdw_Connect_Circle_18('\ue949'),
		gdw_Connect_Pending_Circle_18('\ue943'),
		gdw_Heart_14('\ue944'),
		gdw_Congrats_08_18('\ue945'),
		gdw_Level_03_12('\ue94a'),
		gdw_Connections_12('\ue94b'),
		gdw_Flag_18('\ue94c'),
		gdw_Star_Add_20('\ue94d'),
		gdw_Video_Add_20('\ue94e'),
		gdw_Heart_12('\ue94f'),
		gdw_Eye_12('\ue950'),
		gdw_Link_12('\ue951'),
		gdw_Ambassador_Shield_18('\ue952'),
		gdw_Check_Circle_50('\ue953'),
		gdw_Pointer_24('\ue954'),
		gdw_Heart_Filled_18('\ue955'),
		gdw_Heart_18('\ue956'),
		gdw_ContactCard18('\ue941'),
		gdw_Facebook('\ue942'),
		gdw_Nearby('\ue940'),
		gdw_Globe18('\ue93a'),
		gdw_Clap22('\ue93f'),
		gdw_Filter18('\ue948'),
		gdw_Funnel18('\ue946'),
		gdw_AddPhoto40('\ue947'),
		gdw_Help18('\ue933'),
		gdw_Info18Android('\ue934'),
		gdw_Key18('\ue935'),
		gdw_Clock12('\ue939'),
		gdw_AddPhoto20('\ue918'),
		gdw_Download18('\ue919'),
		gdw_Edit12('\ue91a'),
		gdw_Edit18('\ue91c'),
		gdw_Favorite18('\ue91d'),
		gdw_Info18('\ue91e'),
		gdw_ModerationAdmin24('\ue91f'),
		gdw_Location18('\ue921'),
		gdw_Phone18('\ue922'),
		gdw_Email18('\ue923'),
		gdw_Website18('\ue924'),
		gdw_Profile18('\ue925'),
		gdw_Report18('\ue926'),
		gdw_Share18Android('\ue929'),
		gdw_Share18('\ue92b'),
		gdw_TopSubjects18('\ue92d'),
		gdw_UpArrow12('\ue92f'),
		gdw_Locked18('\ue90f'),
		gdw_Unlocked18('\ue910'),
		gdw_Locked12('\ue911'),
		gdw_Unlocked12('\ue912'),
		gdw_Play32('\ue913'),
		gdw_LeaveChannel18('\ue914'),
		gdw_Feed18('\ue916'),
		gdw_Mute18('\ue917'),
		gdw_Dots16('\ue902'),
		gdw_Search12('\ue908'),
		gdw_Create_Achievement40('\ue96e'),
		gdw_Create_Debate40('\ue95e'),
		gdw_Create_Music40('\ue965'),
		gdw_Create_Science40('\ue964'),
		gdw_Create_Sports40('\ue958'),
		gdw_Create_Volunteering40('\ue959'),
		gdw_Check20('\ue90c'),
		gdw_X20('\ue909'),
		gdw_Back12('\ue90b'),
		gdw_Next12('\ue907'),
		gdw_Back18('\ue657'),
		gdw_Next18('\ue662'),
		gdw_Camera18('\ue66e'),
		gdw_PaperClip18('\ue90d'),
		gdw_FavoriteFilled12('\ue920'),
		gdw_Connect18('\ue906'),
		gdw_SuperLikeFilled18('\ue904'),
		gdw_SuperLikeOutline18('\ue905'),
		gdw_Calendar15('\ue915'),
		gdw_X10('\ue652'),
		gdw_Up12('\ue664'),
		gdw_Down12('\ue663'),
		gdw_Map18('\ue90e'),
		gdw_School18('\ue932'),
		gdw_ConnectionPending20('\ue93c'),
		gdw_Connect20('\ue93d'),
		gdw_Connected20('\ue93e'),
		gdw_Message20('\ue903'),
		gdw_HeartFilled20('\ue927'),
		gdw_HeartOutline20('\ue928'),
		gdw_Colleges18('\ue936'),
		gdw_Achievements18('\ue938'),
		gdw_Add12('\ue90a'),
		gdw_Connections18('\ue901'),
		gdw_Edit20('\ue937'),
		gdw_Invite18('\ue900'),
		gdw_Notifications18('\ue64b'),
		gdw_Share20('\ue92e'),
		gdw_ToDo18('\ue930'),
		gdw_Settings18('\ue931'),
		gdw_Message18('\ue93b'),
		gdw_LocationActive14('\ue92a'),
		gdw_LocationInactive14('\ue92c'),
		gdw_AddPhoto('\ue91b'),
		gdw_Check12('\ue65c');

        char character;

        Icon(char character) {
            this.character = character;
        }

        public String getFormattedName() {
            return "{" + name() + "}";
        }

        public char getCharacter() {
            return character;
        }

        public String getName() {
            return name();
        }

        // remember the typeface so we can use it later
        private static ITypeface typeface;

        public ITypeface getTypeface() {
            if (typeface == null) {
                typeface = new GoodWall();
            }
            return typeface;
        }
    }
}
