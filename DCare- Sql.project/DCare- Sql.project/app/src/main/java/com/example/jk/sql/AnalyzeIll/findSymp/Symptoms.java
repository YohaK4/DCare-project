package com.example.jk.sql.AnalyzeIll.findSymp;

/**
 * Created by JK on 1/17/2015.
 */

    public class Symptoms {

        String code = null;
        String name = null;
        int ID=0;
        boolean selected = false;

        public Symptoms(int ID, String code, String name, boolean selected) {
            super();
            this.code = code;
            this.name = name;
            this.selected = selected;
            this.ID=ID;
        }

        public String getCode() {
            return code;
        }
        public void setCode(String code) {
            this.code = code;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getID() {
            return ID;
        }
        public void setName(int ID) {
            this.ID = ID;
        }
        public boolean isSelected() {
            return selected;
        }
        public void setSelected(boolean selected) {
            this.selected = selected;
        }

    }