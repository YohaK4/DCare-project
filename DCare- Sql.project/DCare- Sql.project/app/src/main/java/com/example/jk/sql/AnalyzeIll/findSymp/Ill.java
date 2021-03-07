package com.example.jk.sql.AnalyzeIll.findSymp;

/**
 * Created by JK on 1/26/2015.
 */
public class Ill {

        int _id;
        String name;
        String symps;
        String treat;
        int odds;

        // constructors
        public Ill() {
        }

        public Ill(int _id, String name, String treat, int odds) {
            this._id=_id;
            this.name = name;
            this.treat = treat;
            this.odds = odds;
        }


        // setters
        public void setId(int id) {
            this._id = id;
        }
    public void setName(String name) {
            this.name = name;
        }

    public void setTreat(String treat) {
            this.treat = treat;
        }

    public void setOdds(int odds) {
            this.odds = odds;
        }

    public void setOdds(String odds) {int od=Integer.parseInt(odds); this.odds = od;}


        // getters
           public long getId() {
            return this._id;
        }

           public String getName() {
                return this.name;
            }

           public String getTreat() {
                return this.treat;
            }

            public int getOdds() {
                return this.odds;
            }


    public String toString() {
        return ("Ill name: "+this.getName()+
                ", Ill id: "+this.getId()+
                ", Ill treat: "+ this.getTreat() +
                ", Ill odds : " + this.getOdds());
    }



    }

