package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Weighted;
/**
 * highway class which implements weighted class used for testing
 * @author CSC316 
 */
public class Highway implements Weighted {
        /** name of this highway object */
		private String name;
		/** length (weight) */
        private int length;
        /**
         * constructor for highway object 
         * @param n name for this object
         * @param l weight for this path
         */
        public Highway(String n, int l) {
            setName(n);
            setLength(l);
        }
        /**
         * sets the name for this highway object
         * @param name of the object
         */
        public void setName(String name) {
            this.name = name;
        }
        public String getName() { return name; } 
        /**
         * returns the weight for the path
         * @return length of path
         */
        public int getLength() {
            return length;
        }
        /**
         * sets the weight for this object
         * @param length the weight for this path
         */
        public void setLength(int length) {
            this.length = length;
        }

        @Override
        public int getWeight() {
            return getLength();
        }
    }
