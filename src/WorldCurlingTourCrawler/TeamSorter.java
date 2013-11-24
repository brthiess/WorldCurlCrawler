package WorldCurlingTourCrawler;

import java.util.ArrayList;

public class TeamSorter {
	ArrayList<Team> teams;
	  private Team[] numbers;
	  private Team[] helper;
	  private int number;
	  
	public TeamSorter(ArrayList<Team> teams) {
		this.teams = teams;
	}
	
	/**
	 * Sorts the teams by name
	 * @return
	 */
	public ArrayList<Team> sortTeamsByName() {
		Team[] primitiveTeams = new Team[teams.size()];
		for(int i = 0; i < teams.size(); i++) {
			primitiveTeams[i] = teams.get(i);
		}
		sort(primitiveTeams);
		for(int i = 0; i < teams.size(); i++) {
			teams.set(i, numbers[i]);
		}
		return teams;
	}
	
    public void sort(Team[] values) {
		    this.numbers = values;
		    number = values.length;
		    this.helper = new Team[number];
		    mergesort(0, number - 1);
		  }

    private void mergesort(int low, int high) {
		    // check if low is smaller then high, if not then the array is sorted
		    if (low < high) {
		      // Get the index of the element which is in the middle
		      int middle = low + (high - low) / 2;
		      // Sort the left side of the array
		      mergesort(low, middle);
		      // Sort the right side of the array
		      mergesort(middle + 1, high);
		      // Combine them both
		      merge(low, middle, high);
		    }
		  }

		  private void merge(int low, int middle, int high) {

		    // Copy both parts into the helper array
		    for (int i = low; i <= high; i++) {
		      helper[i] = numbers[i];
		    }

		    int i = low;
		    int j = middle + 1;
		    int k = low;
		    // Copy the smallest values from either the left or the right side back
		    // to the original array
		    while (i <= middle && j <= high) {
		      if (helper[i].getNameString().compareTo(helper[j].getNameString()) <= 0) {
		        numbers[k] = helper[i];
		        i++;
		      } else {
		        numbers[k] = helper[j];
		        j++;
		      }
		      k++;
		    }
		    // Copy the rest of the left side of the array into the target array
		    while (i <= middle) {
		      numbers[k] = helper[i];
		      k++;
		      i++;
		    }

		  }
		}

	
	/*
	
	public void mergeTeams(int low, int high) {
		 // check if low is smaller then high, if not then the array is sorted
	    if (low < high) {
	      // Get the index of the element which is in the middle
	      int middle = low + (high - low) / 2;
	      // Sort the left side of the array
	      mergeTeams(low, middle);
	      // Sort the right side of the array
	      mergeTeams(middle + 1, high);
	      // Combine them both
	      merge(low, middle, high);
	    }
	}
	
	public void merge(int low, int middle, int high) {
		// Copy both parts into the helper array
	    for (int i = low; i <= high; i++) {
	      helper.add(teams.get(i));
	    }

	    int i = low;
	    int j = middle + 1;
	    int k = low;
	    // Copy the smallest values from either the left or the right side back
	    // to the original array
	    while (i <= middle && j <= high) {
	      if (helper.get(i).getNameString().compareTo(helper.get(j).getNameString()) <= 0 ) {
	        teams.set(k, helper.get(i));
	        i++;
	      } else {
	        teams.set(k, helper.get(j));
	        j++;
	      }
	      k++;
	    }
	    // Copy the rest of the left side of the array into the target array
	    while (i <= middle) {
	      teams.set(k, helper.get(i));
	      k++;
	      i++;
	    }
	  }
		
	}

*/
