import java.util.TreeSet;
import java.util.ArrayList;
import java.util.TreeMap;
/**
 * This class simulates a horoscope of birth years from 1960 to 2019.
 * There are six signs represented by six flower's names.
 * Each flower sign is compatible with two other flower signs.
 *
 * @author (Andriele Sales)
 * @version (25/02/2026)
 * 
 * Q1 (a)
 */
public class Horoscope
{
    //A field that will display the TreeMap for signs. Q1 (a)
    public TreeMap <String, TreeSet<Integer>> signs;
    //A field that will display the TreeMap for matches. Q1 (a)
    public TreeMap <String, ArrayList<String>> matches;

    /**
     * Initialises signs and matches and creates the horoscope.
     * 
     * Q1 (b)
     */
    public Horoscope()
    {
        signs= new TreeMap<>();
        matches= new TreeMap<>();
        populateSigns();
        populateMatches();
    }

    /**
     * Creates all the years corresponding to each flower sign
     * and stores them on signs field using private methods.
     * 
     * Q1 (c)
     */
    private void populateSigns()
    {
        populateLily();
        populateRose();
        populateDahlias(); 
        populateTulip();
        populateSunflower(); 
        populateJasmine();
    }

    /**
     * Adds each of the six flower's name and its associated
     * compatible flowers.
     * 
     * Q1 (d)
     */
    private void populateMatches()
    {
        matches.put("Lily", new ArrayList<>());
        matches.put("Rose", new ArrayList<>());
        matches.put("Dahlias", new ArrayList<>());
        matches.put("Tulip", new ArrayList<>());
        matches.put("Sunflower", new ArrayList<>());
        matches.put("Jasmine", new ArrayList<>());

        matches.get("Lily").add("Tulip");
        matches.get("Lily").add("Jasmine");

        matches.get("Tulip").add("Lily");
        matches.get("Tulip").add("Dahlias");

        matches.get("Jasmine").add("Lily");
        matches.get("Jasmine").add("Sunflower");

        matches.get("Dahlias").add("Tulip");
        matches.get("Dahlias").add("Rose");

        matches.get("Sunflower").add("Rose");
        matches.get("Sunflower").add("Jasmine");

        matches.get("Rose").add("Sunflower");
        matches.get("Rose").add("Dahlias");

    }

    /**
     * Searches within each sign key values until it finds the sign
     * associated with the given year.
     * @param year The integer year to find its associated horoscope sign.
     * @return The String sign of the given year.
     * 
     * Q1 (e)
     */
    public String findSign(int year)
    {
        String sign = "";
        
        for (String aKey : signs.keySet()) {
            
            for (int number : signs.get(aKey)) {
                
                if (number == year) {
                    sign = aKey;
                }
            }
        } 
        
        return sign;
    }

    /**
     * Finds the sign for each given year and then searches
     * within matches to checks whether they are compatible.
     * 
     * @param year1 The first integer number to find flower sign.
     * @param year1 The second integer number to find flower sign.
     * @return True if the given years are compatible, otherwise false.
     * 
     * Q1 (f)
     */
    public Boolean areMatches(int year1, int year2)
    {
        String sign1 = findSign(year1);
        String sign2 = findSign(year2);
        
        for (String aKey : matches.keySet()) {
            
            for (String compatible : matches.get(aKey)) {
                
                if (aKey.equals(sign1) && compatible.equals(sign2)) {
                    return true;
                }
            }
        }
        
        return false;
    }

    /**
     * Creates an ordered list and stores all the years of signs
     * compatible with the given year.
     * @param The integer year to find all the matches.
     * @returns The TreeSet of type Integer of all compatible years.
     * 
     * Q1 (g)
     */
    public TreeSet<Integer> allYearMatches(int year)
    {
        TreeSet<Integer> sorted = new TreeSet<>();
        String sign = findSign(year);
        for (String aKey : matches.keySet()) {
            
            if (aKey.equals(sign)) {
                
                for (String compatible : matches.get(aKey)) {
                    
                    for (Integer number : signs.get(compatible)) {
                        sorted.add(number);
                    }
                }
            }
        }
        
        return sorted;
    }

    /**
     * Creates a sorted map to store all the years from 1960 to 2019
     * with its compatible signs as values.
     * @return A TreeMap with keys of type Integer 
     * and values of type TreeSet of type String
     * containing each year within the range
     * and its two compatible signs.
     *
     *Q1 h)
     */
    public TreeMap<Integer, TreeSet<String>> matchesByYear()
    {
        TreeMap<Integer, TreeSet<String>> yearMatches = new TreeMap<>();
        
        for (String aKey : signs.keySet()) {
            
            for (Integer year : signs.get(aKey)) {
                yearMatches.put(year, new TreeSet<>());
            }
        }

        for (Integer aKey : yearMatches.keySet()) {
            String sign = findSign(aKey);
            
            for (String matchesKey : matches.keySet()) {
                
                if (matchesKey.equals(sign)) {
                    
                    for (String compatible : matches.get(matchesKey)) {
                        yearMatches.get(aKey).add(compatible);
                    }
                }
            }
        }
        
        return yearMatches;
    }

    /**
     * Prints the name of the two flower signs compatible with a given year.
     *@param year The year integer to search for its compatible signs.
     *
     *Q1 i)
     */
    public void printCompatibleSignsFor(int year)
    {
        String sign = findSign(year);
        ArrayList<String> flowers = new ArrayList<>(); 
        
        for (String aKey : matches.keySet()) {
            
            if (aKey.equals(sign)) {
                
                for (String compatible : matches.get(aKey)) {
                    flowers.add(compatible);
                }
            }
        }
        
        System.out.println("Compatible signs for " + year + " are:");
        System.out.println(flowers.get(0));
        System.out.println(flowers.get(1));
    }

    /**
     * Adds Lily flower to signs and generates all the years 
     * by adding six years each time, starting from 1960 inclusive.
     */
    private void populateLily()
    {
        int start = 1960;
        signs.put("Lily", new TreeSet<>());
        
        while (start <= 2019) {
            signs.get("Lily").add(start);
            start = start + 6;
        }
    }

    /**
     * Adds Rose flower to signs and generates all the years 
     * by adding six years each time, starting from 1961 inclusive.
     */
    private void populateRose()
    {
        int start = 1960 + 1;
        signs.put("Rose", new TreeSet<>());
        
        while (start <= 2019) {
            signs.get("Rose").add(start);
            start = start + 6;
        }
    }

    /**
     * Adds Dahlias to signs and generates all the years 
     * by adding six years each time, starting from 1962 inclusive.
     */
    private void populateDahlias()
    {
        signs.put("Dahlias", new TreeSet<>());
        int start= 1960 + 2;
        
        while (start <= 2019) {
            signs.get("Dahlias").add(start);
            start = start + 6;
        }
    }

    /**
     * Adds Tulip flower to signs and generates all the years 
     * by adding six years each time, starting from 1963 inclusive.
     */
    private void populateTulip()
    {
        signs.put("Tulip", new TreeSet<>());
        int start = 1960+3;
        
        while (start <= 2019) {
            signs.get("Tulip").add(start);
            start = start + 6;
        }
    }

    /**
     * Adds Sunflower flower to signs and generates all the years 
     * by adding six years each time, starting from 1964 inclusive.
     */
    private void populateSunflower()
    {
        signs.put("Sunflower", new TreeSet<>());
        int start = 1960 + 4;
        
        while (start <= 2019) {
            signs.get("Sunflower").add(start);
            start = start + 6;
        }
    }

    /**
     * Adds Jasmine flower to signs and generates all the years 
     * by adding six years each time, starting from 1965 inclusive.
     */
    private void populateJasmine()
    {
        signs.put("Jasmine", new TreeSet<>());
        int start = 1960 + 5;
        
        while (start <= 2019) {
            signs.get("Jasmine").add(start);
            start= start + 6;
        }
    }
}