/*
 * Copyright (C) 2007 The Android Open Source Project
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

package com.androidsfuture.bodystats;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

/**
 * Demonstrates expandable lists using a custom {@link ExpandableListAdapter}
 * from {@link BaseExpandableListAdapter}.
 */
public class GymList extends ExpandableListActivity {

    ExpandableListAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gymlist);

        // Set up our adapter
        mAdapter = new MyExpandableListAdapter();
        setListAdapter(mAdapter);
        registerForContextMenu(getExpandableListView());
    }

  /*  @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Sample menu");
        menu.add(0, 0, 0, R.string.expandable_list_action);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        ExpandableListContextMenuInfo info = (ExpandableListContextMenuInfo) item.getMenuInfo();

        String title = ((TextView) info.targetView).getText().toString();
        
        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
            int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition); 
            int childPos = ExpandableListView.getPackedPositionChild(info.packedPosition); 
            Toast.makeText(this, title + ": Child " + childPos + " clicked in group " + groupPos,
                    Toast.LENGTH_SHORT).show();
           
            if (groupPos == 0  && childPos == 0){
            	
            	Intent WeightLossMotivation = new Intent(Intent.ACTION_VIEW);
    			WeightLossMotivation.setData(Uri.parse("http://www.contoursexpress.com/"));
    			startActivity(WeightLossMotivation);
            	
            }
            return true;
        } else if (type == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
            int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition); 
            Toast.makeText(this, title + ": Group " + groupPos + " clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        
        return false;
    } */
    
    /**
     * A simple adapter which maintains an ArrayList of photo resource Ids. 
     * Each photo is displayed as an image. This adapter supports clearing the
     * list of photos and adding a new photo.
     *
     */
   
    public class MyExpandableListAdapter extends BaseExpandableListAdapter {
        // Sample data set.  children[i] contains the children (String[]) for groups[i].
        private String[] groups = { "Nationwide", "Alabama", "California", "Florida",
        		"Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Maryland",
        		"Massachusetts", "Michigan", "Missouri", "New Jersey", "New Mexico",
        		"New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma",
        		"Oregon", "Pennsylvania", "Rhode Island", "Texas", "Wisconsin", "Wyoming", 
        		"United Kingdom Gyms"};
        private String[][] children = {
        		//Nationwide
                { "Contours Express Fitness", "Curves", "Lady of America Fitness for Women", "Lucille Roberts"},
                //Alabama
                { "Fitness for Women" },
                //California
                { "4 Women Only Fitness Centers", "Elan Health & Fitness Center", "Express Fitness for Women",
                  "Fitness USA", "Orange County Gym - Fitness Elite for Women", "Serenity Fitness Gym & Spa",
                  "Total Woman Gym & Day Spa", "Women's Fitness Club", "Women's Fitness World","Women's World Fitness Center" },
                //Florida
                { "Fitness for Women", "Gainesville Health & Fitness", "Liberty Fitness Women's Health Club", "Rejuvenate Fitness Center & Spa for Women", 
                	  "Shapes Total Fitness for Women" },
                //Georgia
                { "AC Fitness for Women", "Women's Premier Fitness"},
                //Hawaii
                { "Women's Wellness & Fitness Center"},
                 //Idaho
                { "Total Woman Fitness"},
                 //Illinois
                { "Great Shapes Fitness for Women", "Oasis Women's Fitness & Spa"},
                 //Indiana
                 { "Fitness USA"},
                 //Maryland
                 { "Ladies Total Fitness", "Synergy Fitness for Women"},
                 //Massachusetts
                 { "Fitness Etcetera for Women", "Fitness Unlimited Health Club for Women", "The Fitness Club for Women", 
                    "USA Fitness Centers for Women", "Women's Fitness of Boston"}, 
                 //Michigan
                 { "Fitness USA"},
                 //Missouri
                 { "Shape Express"},
                 //New Jersey
                 {"American Woman Fitness Center", "Ethos Fitness Spa for Women", "Shape Express",  
                	 "Spa Lady Fitness Centers for Women", "The Fitness Center for Women Only"},
                 //New Mexico
                 {"Fitness Plus"},
                 //New York
                 {"Sedona Fitness for Women", "Susanne's Fitness for Women"},
                 //North Carolina
                 {"For Women Only Fitness Center", "New Millennium Fitness for Women"},
                 //North Dakota
                 {"Elements"},
                 //Ohio
                 {"Bodyworks"},
                 //Oklahoma
                 {"Shape Express"},
                 //Oregon
                 {"Northwest Women's Fitness Club", "Women's Fitness Company"}, 
                 //Pennsylvania
                 { "Body & Soul Fitness Studio", "Liberty Fitness Women's Health Club", "Sedona Fitness for Women"},
                 //Rhode Island
                 {"Sedona Fitness for Women"},
                 //Texas
                 {"Bella Women's Fitness", "Sedona Fitness for Women", "Shape Express", "Pure Fitness for Women"},
                 //Wisconsin
                 {"Women's Fitness Center"},
                 //Wyoming
                 {"Sedona Fitness for Women"},
                 //United Kingdom Gyms
                 {"24HR Gym", "Bodylines Fitness", "Circuit 76", "Energie Fitness For Women",
                	 "Fitness First for Women", "Gently Does It", "Gym Heaven", "Gymophobics",
                	 "Just Women Gym", "Lady Zone", "Ladybird Fitness Club for Woman",
                	 "LTE Female Fitness", "New River Express Fitness for Women",
                	 "Paradise Walk", "Resonance", "Revolution", "Slimtone", "Sports Centre",
                	 "Style & Tone", "Sundance Ladies Health & Fitness", "Sunstone", 
                	 "TBF Women Only Gym", "The Studio", "Weight Loss Resources", 
                	 "Women In Mind", "Women's Kettlebell Gym", "Women's Workout"
                 }
        };
        
        public Object getChild(int groupPosition, int childPosition) {
            return children[groupPosition][childPosition];
        }

        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        public int getChildrenCount(int groupPosition) {
            return children[groupPosition].length;
        }

        public TextView getGenericView() {
            // Layout parameters for the ExpandableListView
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT, 64);

            TextView textView = new TextView(GymList.this);
            textView.setLayoutParams(lp);
            // Center the text vertically
            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            // Set the text starting position
            textView.setPadding(50, 0, 0, 0);
            return textView;
        }
        
		public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                View convertView, ViewGroup parent) {
            TextView textView = getGenericView();
            textView.setText(getChild(groupPosition, childPosition).toString());
            return textView;
        }

        public Object getGroup(int groupPosition) {
            return groups[groupPosition];
        }

        public int getGroupCount() {
            return groups.length;
        }

        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                ViewGroup parent) {
            TextView textView = getGenericView();
            textView.setText(getGroup(groupPosition).toString());
            return textView;
        }

        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        public boolean hasStableIds() {
            return true;
        }

    }
    
    @Override 
    public boolean onChildClick(android.widget.ExpandableListView parent, 
              View v, int groupPosition, int childPosition, long id) { 
    	
    	//National Gyms
    	 if (groupPosition == 0  && childPosition == 0){
        	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
        	OpenWebSite.setData(Uri.parse("http://www.contoursexpress.com/"));
 			startActivity(OpenWebSite);
         }
    	 
    	 if (groupPosition == 0  && childPosition == 1){
         	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
         	OpenWebSite.setData(Uri.parse("http://curvesinformation.com/"));
  			startActivity(OpenWebSite);
          }
    	 
    	 if (groupPosition == 0  && childPosition == 2){
          	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
          	OpenWebSite.setData(Uri.parse("http://ladyofamerica.com/clubInformation.asp"));
   			startActivity(OpenWebSite);
           }
    	 
    	 if (groupPosition == 0  && childPosition == 3){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://www.lucilleroberts.com/"));
    			startActivity(OpenWebSite);
            }
    	 
    	 //Alabama
    	 if (groupPosition == 1  && childPosition == 0){
            	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
            	OpenWebSite.setData(Uri.parse("http://www.fitnessforwomenauburn.com/"));
     			startActivity(OpenWebSite);
             } 
    	 
    	 //California
    	 if (groupPosition == 2  && childPosition == 0){
         	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
         	OpenWebSite.setData(Uri.parse("http://www.4womenonlyfitness.com/"));
  			startActivity(OpenWebSite);
          } 
    	 
    	 if (groupPosition == 2  && childPosition == 1){
          	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
          	OpenWebSite.setData(Uri.parse("http://elanfitness.com/"));
   			startActivity(OpenWebSite);
           } 
    	 
    	 if (groupPosition == 2  && childPosition == 2){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://www.effw.com"));
    			startActivity(OpenWebSite);
            } 
    	 
    	 if (groupPosition == 2  && childPosition == 3){
            	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
            	OpenWebSite.setData(Uri.parse("http://www.fitnessusa.com/WomensFitness.asp"));
     			startActivity(OpenWebSite);
             } 
    	 
    	 if (groupPosition == 2  && childPosition == 4){
         	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
         	OpenWebSite.setData(Uri.parse("http://fitnesselite4women.com/about_us.asp"));
  			startActivity(OpenWebSite);
          } 
    	 
    	 if (groupPosition == 2  && childPosition == 5){
          	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
          	OpenWebSite.setData(Uri.parse("http://www.serenityfitnessclub.com/"));
   			startActivity(OpenWebSite);
           } 
    	 
    	 if (groupPosition == 2  && childPosition == 6){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://totalwomanspa.com/"));
    			startActivity(OpenWebSite);
            } 
    	 
    	 if (groupPosition == 2  && childPosition == 7){
            	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
            	OpenWebSite.setData(Uri.parse("http://womensfitnessclub.com/"));
     			startActivity(OpenWebSite);
             } 
    	 
    	 if (groupPosition == 2  && childPosition == 8){
         	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
         	OpenWebSite.setData(Uri.parse("http://womensfitnessworld.com/womens_fitness_world.html"));
  			startActivity(OpenWebSite);
          } 
    	 
    	 if (groupPosition == 2  && childPosition == 9){
          	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
          	OpenWebSite.setData(Uri.parse("http://womensworldfitnesscenter.com/"));
   			startActivity(OpenWebSite);
           } 
    	 
    	 //Florida
    	 if (groupPosition == 3  && childPosition == 0){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://fitnessforwomen-boca.com/our_club.cfm"));
    			startActivity(OpenWebSite);
            } 
    	 
    	 if (groupPosition == 3  && childPosition == 1){
            	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
            	OpenWebSite.setData(Uri.parse("http://www.ghfc.com/clubs/"));
     			startActivity(OpenWebSite);
             } 
     	 
    	 if (groupPosition == 3  && childPosition == 2){
         	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
         	OpenWebSite.setData(Uri.parse("http://libertyfitness.com/our-club/environment"));
  			startActivity(OpenWebSite);
          } 
    	 
    	 if (groupPosition == 3  && childPosition == 3){
          	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
          	OpenWebSite.setData(Uri.parse("http://rejuvenatehf.com/"));
   			startActivity(OpenWebSite);
           } 
    	 
    	 if (groupPosition == 3  && childPosition == 4){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://www.shapestotalfitness.com/Club/Scripts/Home/home.asp"));
    			startActivity(OpenWebSite);
            } 
    	 
    	 //Georgia
    	 if (groupPosition == 4  && childPosition == 0){
            	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
            	OpenWebSite.setData(Uri.parse("http://acfitnessforwomen.com/"));
     			startActivity(OpenWebSite);
             } 
    	 
    	 if (groupPosition == 4  && childPosition == 1){
         	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
         	OpenWebSite.setData(Uri.parse("http://womenspremierfitness.com/fitness_programs.html"));
  			startActivity(OpenWebSite);
          } 
    	 
    	 //Hawaii
    	 if (groupPosition == 5  && childPosition == 0){
         	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
         	OpenWebSite.setData(Uri.parse("http://w2fitness.com/"));
  			startActivity(OpenWebSite);
          } 
    	 
    	 //Idaho
    	 if (groupPosition == 6  && childPosition == 0){
          	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
          	OpenWebSite.setData(Uri.parse("http://www.totalwomanfitness.net/"));
   			startActivity(OpenWebSite);
           } 
    	 
    	 //Illinois
    	 if (groupPosition == 7  && childPosition == 0){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://greatshapesfitness.com/"));
    			startActivity(OpenWebSite);
            } 
    	 	 
    	 if (groupPosition == 7  && childPosition == 1){
            	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
            	OpenWebSite.setData(Uri.parse("http://oasisforher.com/"));
     			startActivity(OpenWebSite);
             } 
    	 //Indiana
    	 if (groupPosition == 8  && childPosition == 0){
            	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
            	OpenWebSite.setData(Uri.parse("http://www.fitnessusa.com/WomensFitness.asp"));
     			startActivity(OpenWebSite);
             } 
    	 
    	 //Maryland
    	 if (groupPosition == 9  && childPosition == 0){
         	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
         	OpenWebSite.setData(Uri.parse("http://ladiestotalfitness.com/"));
  			startActivity(OpenWebSite);
          } 
 	 
    	 if (groupPosition == 9  && childPosition == 1){
          	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
          	OpenWebSite.setData(Uri.parse("http://synergywomensfitness.com/home.htm"));
   			startActivity(OpenWebSite);
           } 
    	 
    	 //Massachusetts
    	 if (groupPosition == 10  && childPosition == 0){
          	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
          	OpenWebSite.setData(Uri.parse("http://fitnessetcetera.com/"));
   			startActivity(OpenWebSite);
           } 
  	 
    	 if (groupPosition == 10  && childPosition == 1){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://fitnessunlimited.com/"));
    			startActivity(OpenWebSite);
            } 
   	 
    	 if (groupPosition == 10  && childPosition == 2){
            	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
            	OpenWebSite.setData(Uri.parse("http://fitnessclubforwomen.com"));
     			startActivity(OpenWebSite);
             } 
    	 
    	 
    	 if (groupPosition == 10  && childPosition == 3){
         	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
         	OpenWebSite.setData(Uri.parse("http://usafitnesscentersforwomen.com/"));
  			startActivity(OpenWebSite);
          } 
 	 
    	 if (groupPosition == 10  && childPosition == 4){
          	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
          	OpenWebSite.setData(Uri.parse("http://womensfitnessofboston.com/mainpage.html"));
   			startActivity(OpenWebSite);
           } 
  	 
    	 //Michigan
    	 if (groupPosition == 11  && childPosition == 0){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://www.fitnessusa.com/WomensFitness.asp"));
    			startActivity(OpenWebSite);
            } 
   	 
    	 //Missouri
    	 if (groupPosition == 12  && childPosition == 0){
            	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
            	OpenWebSite.setData(Uri.parse("http://shapexpress.com/"));
     			startActivity(OpenWebSite);
             } 
    	 
     	 //New Jersey
    	 if (groupPosition == 13  && childPosition == 0){
         	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
         	OpenWebSite.setData(Uri.parse("http://americanwomanfitness.com/"));
  			startActivity(OpenWebSite);
          } 
 	 
    	 if (groupPosition == 13  && childPosition == 1){
          	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
          	OpenWebSite.setData(Uri.parse("http://ethosfitness.com/AmenitiesatEthos.html"));
   			startActivity(OpenWebSite);
           } 
  	 
    	 if (groupPosition == 13  && childPosition == 2){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://shapexpress.com/"));
    			startActivity(OpenWebSite);
            } 
   	  
    	 if (groupPosition == 13  && childPosition == 3){
            	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
            	OpenWebSite.setData(Uri.parse("http://spaladyhealth.com/services"));
     			startActivity(OpenWebSite);
             } 
    	 
    	 if (groupPosition == 13  && childPosition == 4){
         	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
         	OpenWebSite.setData(Uri.parse("http://thefitnesscenterforwomen.com/"));
  			startActivity(OpenWebSite);
          } 
 	 
    	 //New Mexico
    	 if (groupPosition == 14  && childPosition == 0){
          	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
          	OpenWebSite.setData(Uri.parse("http://fitnessplussantafe.com/"));
   			startActivity(OpenWebSite);
           } 
    	 
    	 //New York
    	 if (groupPosition == 15  && childPosition == 0){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://sedona4women.com/"));
    			startActivity(OpenWebSite);
            } 
     	 
    	 if (groupPosition == 15  && childPosition == 1){
            	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
            	OpenWebSite.setData(Uri.parse("http://www.susannesfitness.com/"));
     			startActivity(OpenWebSite);
             } 
      	 
    	 //North Carolina
    	 if (groupPosition == 16  && childPosition == 0){
            	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
            	OpenWebSite.setData(Uri.parse("http://fwofitness.com/"));
     			startActivity(OpenWebSite);
             } 
      	 
    	 if (groupPosition == 16  && childPosition == 1){
         	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
         	OpenWebSite.setData(Uri.parse("http://nmfforwomen.com/"));
  			startActivity(OpenWebSite);
          } 
    	 
    	 //North Dakota
    	 if (groupPosition == 17  && childPosition == 0){
         	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
         	OpenWebSite.setData(Uri.parse("http://elementsforwomen.com/html/index.htm"));
  			startActivity(OpenWebSite);
          } 
    	 
    	 //Ohio
    	 if (groupPosition == 18  && childPosition == 0){
          	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
          	OpenWebSite.setData(Uri.parse("http://bodyworks.net/"));
   			startActivity(OpenWebSite);
           } 
     	 
    	 //Oklahoma
    	 if (groupPosition == 19  && childPosition == 0){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://shapexpress.com/"));
    			startActivity(OpenWebSite);
            } 
    	 
    	 //Oregon
    	 if (groupPosition == 20  && childPosition == 0){
            	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
            	OpenWebSite.setData(Uri.parse("http://www.nwwomensfitness.com/"));
     			startActivity(OpenWebSite);
             } 
     	 
    	 if (groupPosition == 20  && childPosition == 1){
         	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
         	OpenWebSite.setData(Uri.parse("http://wfcmedford.com/WFC/Home.html"));
  			startActivity(OpenWebSite);
          } 
  	 
    	 //Pennsylvania
    	 if (groupPosition == 21  && childPosition == 0){
         	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
         	OpenWebSite.setData(Uri.parse("http://bsfstudio.com/"));
  			startActivity(OpenWebSite);
          } 
  	 
    	 if (groupPosition == 21  && childPosition == 1){
          	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
          	OpenWebSite.setData(Uri.parse("http://libertyfitness.com/our-club/environment"));
   			startActivity(OpenWebSite);
           } 
   	 
    	 if (groupPosition == 21  && childPosition == 2){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://sedona4women.com/"));
    			startActivity(OpenWebSite);
            } 
    	 
    	 //Rhode Island
    	 if (groupPosition == 22  && childPosition == 0){
          	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
          	OpenWebSite.setData(Uri.parse("http://sedona4women.com/"));
   			startActivity(OpenWebSite);
           } 
   	 
    	 //Texas
    	 if (groupPosition == 23  && childPosition == 0){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://www.bellawomensfitness.com/"));
    			startActivity(OpenWebSite);
            } 
    	 
    	 if (groupPosition == 23  && childPosition == 1){
            	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
            	OpenWebSite.setData(Uri.parse("http://sedona4women.com/"));
     			startActivity(OpenWebSite);
             } 
     	 
    	 if (groupPosition == 23  && childPosition == 2){
         	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
         	OpenWebSite.setData(Uri.parse("http://shapexpress.com/"));
  			startActivity(OpenWebSite);
          } 
    	 
    	 if (groupPosition == 23  && childPosition == 3){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://purefitnessforwomen.com/"));
    			startActivity(OpenWebSite);
            } 
     	 
    	 //Wisconsin
    	 if (groupPosition == 24  && childPosition == 0){
            	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
            	OpenWebSite.setData(Uri.parse("http://womensfitnesscenter.org/"));
     			startActivity(OpenWebSite);
             } 
    	 
    	 //Wyoming
    	 if (groupPosition == 25  && childPosition == 0){
         	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
         	OpenWebSite.setData(Uri.parse("http://sedona4women.com/"));
  			startActivity(OpenWebSite);
          }  
    	 
    	 //United Kingdom
    	 if (groupPosition == 26  && childPosition == 0){
          	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
          	OpenWebSite.setData(Uri.parse("http://www.24hrgym.co.uk/24hr-gym-facilities/pink-gym.php"));
   			startActivity(OpenWebSite);
           }  
    	 
    	 if (groupPosition == 26  && childPosition == 1){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://www.bodylinesfitness.co.uk"));
    			startActivity(OpenWebSite);
            } 
    	 
    	 if (groupPosition == 26  && childPosition == 2){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://www.circuit76.co.uk"));
    			startActivity(OpenWebSite);
            } 
     	 
    	 if (groupPosition == 26  && childPosition == 3){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://www.effw.co.uk"));
    			startActivity(OpenWebSite);
            } 
    	 
    	 if (groupPosition == 26  && childPosition == 4){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://www.fitnessfirst.co.uk/fitness-first-for-women-gym/gym-for-women-fitness-for-women.aspx"));
    			startActivity(OpenWebSite);
            } 
    	 
    	 if (groupPosition == 26  && childPosition == 5){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://www.gentlydoesit.co.uk/gym.html"));
    			startActivity(OpenWebSite);
            } 
    	 
    	 if (groupPosition == 26  && childPosition == 6){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://gymheaven.co.uk"));
    			startActivity(OpenWebSite);
            } 
    	 
    	 if (groupPosition == 26  && childPosition == 7){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://www.gymophobics.co.uk"));
    			startActivity(OpenWebSite);
            } 
    	 
    	 if (groupPosition == 26  && childPosition == 8){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://www.thegym-justwomen.co.uk/women.html"));
    			startActivity(OpenWebSite);
            } 
    	 
    	 if (groupPosition == 26  && childPosition == 9){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://www.ladyzone.co.uk"));
    			startActivity(OpenWebSite);
            }
    	 if (groupPosition == 26  && childPosition == 10){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://www.ladybirdfitness.co.uk"));
    			startActivity(OpenWebSite);
            } 
    	 
    	 if (groupPosition == 26  && childPosition == 11){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://www.litefemalefitness.co.uk/The-Lite-Gym.aspx"));
    			startActivity(OpenWebSite);
            } 
    	 
    	 if (groupPosition == 26  && childPosition == 12){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://www.newriverwomen.co.uk"));
    			startActivity(OpenWebSite);
            } 
      	 
    	 if (groupPosition == 26  && childPosition == 13){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://www.paradisewalk.com"));
    			startActivity(OpenWebSite);
            } 
    	 
    	 if (groupPosition == 26  && childPosition == 14){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://www.resonancevt.co.uk"));
    			startActivity(OpenWebSite);
            } 
    	 
    	 if (groupPosition == 26  && childPosition == 15){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://www.womensfitnessrevolution.co.uk"));
    			startActivity(OpenWebSite);
            } 
    	 
    	 if (groupPosition == 26  && childPosition == 16){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://www.slimntone.co.uk"));
    			startActivity(OpenWebSite);
            } 
    	 
    	 if (groupPosition == 26  && childPosition == 17){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://www.sportscentre.org.uk/gyms.html?pgid=7"));
    			startActivity(OpenWebSite);
            } 
    	 
    	 if (groupPosition == 26  && childPosition == 18){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://www.styleandtone.co.uk/gym-article"));
    			startActivity(OpenWebSite);
            } 
    	 
    	 if (groupPosition == 26  && childPosition == 19){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://www.sundancegym.co.uk"));
    			startActivity(OpenWebSite);
            } 
    	 
    	 if (groupPosition == 26  && childPosition == 20){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://www.sunstonewomen.com/PageBuilder.asp?pageId=2&menuId=2"));
    			startActivity(OpenWebSite);
            } 
    	 
    	 if (groupPosition == 26  && childPosition == 21){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://www.tbfwomenonlygym.co.uk"));
    			startActivity(OpenWebSite);
            } 
    	 
    	 if (groupPosition == 26  && childPosition == 22){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://www.thestudiobath.co.uk"));
    			startActivity(OpenWebSite);
            } 
    	 
    	 if (groupPosition == 26  && childPosition == 23){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://www.weightlossresources.co.uk/exercise/healthclub/ladies_workout_express.htm"));
    			startActivity(OpenWebSite);
            } 
    	 
    	 if (groupPosition == 26  && childPosition == 24){
           	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
           	OpenWebSite.setData(Uri.parse("http://www.womeninmind.co.uk/gym"));
    			startActivity(OpenWebSite);
            } 
    	 
    	 if (groupPosition == 26  && childPosition == 25){
            	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
            	OpenWebSite.setData(Uri.parse("http://www.womenskettlebellgym.com"));
     			startActivity(OpenWebSite);
             } 
     	 
    	 if (groupPosition == 26  && childPosition == 26){
            	Intent OpenWebSite = new Intent(Intent.ACTION_VIEW);
            	OpenWebSite.setData(Uri.parse("http://www.womensworkout.co.uk"));
     			startActivity(OpenWebSite);
             } 
     	 
    	 
         return true; 
         
         
         
}
    
}
