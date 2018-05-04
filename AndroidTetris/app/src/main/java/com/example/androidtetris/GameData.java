package com.example.androidtetris;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by duanyecong on 17-10-23.
 */

    public class GameData implements Parcelable
    {
        private String ShapeFactory;
        private String Ground;
        private int publishDate;

        public GameData()
        {

        }

        public String getShapeFactory()
        {
            return ShapeFactory;
        }

        public void setShapeFactory(String ShapeFactory )
        {
            this.ShapeFactory = ShapeFactory;
        }

        public String getGround()
        {
            return Ground;
        }

        public void setGround(String Ground)
        {
            this.Ground = Ground;
        }
        public int getPublishDate()
        {
            return publishDate;
        }
        public void setPublishDate(int publishDate)
        {
            this.publishDate = publishDate;
        }

        @Override
        public int describeContents()
        {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel out, int flags)
        {
            out.writeString(ShapeFactory);
            out.writeString(Ground);
            out.writeInt(publishDate);
        }

        public static final Parcelable.Creator<GameData> CREATOR = new Creator<GameData>()
        {
            @Override
            public GameData[] newArray(int size)
            {
                return new GameData[size];
            }

            @Override
            public GameData createFromParcel(Parcel in)
            {
                return new GameData(in);
            }
        };

        public GameData(Parcel in)
        {
            ShapeFactory = in.readString();
            Ground = in.readString();
            publishDate = in.readInt();
        }
    }
