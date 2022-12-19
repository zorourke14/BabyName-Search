/*
 * Zack O'Rourke, Logan May
 * CSC 220 - Lab 8
 * 12/5/2022
 */

public class NameRecord {

    private String name;
    private int[] rank = new int[END - START + 1];

    // instance varibales
    public static final int START = 1880;
    public static final int END = 1950;

    // Initializes the NameRecord object.
    public NameRecord(String name) {
        this.name = name;
    }

    // Returns the name
    public String getName() {
        return name;
    }

    // Sets the popularity rank of the name in the given year.
    public void setRank(int year, int rank) {
        this.rank[year] = rank;
    }

    // Returns the popularity rank of that specific year.
    public int getRank(int year) {
        return rank[year];
    }

    // finds the best year for a specific name
    public int bestYear() {
        int low = rank[0];
        int indexVal = 0;
        for (int i = 1; i < rank.length; i++) {
            if (rank[i] < low) {
                low = rank[i];
                indexVal = i;
            }
        }
        return indexVal + START;
    }
}