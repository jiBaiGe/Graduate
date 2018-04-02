package com.example.ye.graduate;

/**
 * Created by ye on 2017/7/19.
 */

public class City {


        private  String mName;
        private String mTmp;
        private String mDate;
        private String mCond;


        public City(String name , String cond , String tmp , String date){
            mName = name;
            mTmp = tmp;
            mDate = date;
            mCond = cond;
        }

        public String getName   () { return mName;    }
        public String getTmp () { return  mTmp; }
        public String getDate    () { return mDate;     }
        public String getCond    () { return mCond;     }

        public void setName   (String name) {  mName = name;    }
        public void setTmp (String tmp) {  mTmp = tmp; }
        public void setDate    (String date) { mDate = date;     }
        public void setCond    (String cond) { mCond = cond;     }


}
