import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.lang.Math;

public class Utils
{
    public static List<Championship> readChampionshipsFromCSV(String fileName)
    {
        List<Championship> ListChampionship = new ArrayList<Championship>();
        try
        {
            File file = new File(fileName);
            Scanner sc = new Scanner(file);
            sc.nextLine();
            while (sc.hasNextLine())
            {
                String[] sp = (sc.nextLine().split(","));
                String[] sp2 = (sc.nextLine().split(","));
                Championship cs = new Championship(Integer.parseInt(sp[1]), sp[0], sp[2], sp[3], Integer.parseInt(sp[25].substring(0, sp[25].length() - 2)), Integer.parseInt(sp[26]));
                for (int i = 0; i < 21; i++)
                try
                {
                    Race r = new Race(sp[i + 4], sp[0], Integer.parseInt(sp[1]), Integer.parseInt(sp2[i + 4]), true);
                    cs.addRace(i + 1, r);
                }
                catch (Exception notFinished)
                {
                    Race r = new Race(sp[i + 4], sp[0], Integer.parseInt(sp[1]), 0, false);
                    cs.addRace(i + 1, r);
                }
            ListChampionship.add(cs);
            }
        }
        catch (FileNotFoundException e)
        {
            return ListChampionship;
        }
        return ListChampionship;
    }

    public static void sortChampionships(List<Championship> champs)
    {
        champs.sort(Championship::compareTo);
    }

    public static void printChampionships(List<Championship> champs)
    {
        for (int i = 0; i < champs.size(); i++)
            System.out.println(champs.get(i).toString());
    }

    public static List<Championship> filterByRacer(List<Championship> champs, String racer)
    {
        List<Championship> FilterR = new ArrayList<Championship>();
        for (int i = 0; i < champs.size(); i++)
            if (champs.get(i).getRacer().equals(racer))
                 FilterR.add(champs.get(i));
        return FilterR;

    }

static List<Championship> filterByTeam(List<Championship> champs, String teamName,String category)
{
List<Championship> ListChampionship = new ArrayList<Championship>();
  for(int i = 0; i < champs.size(); i++)
  {
    if(champs.get(i).getBike().equals(teamName) && champs.get(i).getCategory().equals(category))
    ListChampionship.add(champs.get(i));
  }
  return ListChampionship;
}

static int vf(String[] vf, String a)
    {
        int result = -1;
        for(int i = 0; i < vf.length; i++)
        {
            if(vf[i].equals(a))
            {
              result = i;
            }
        }
        return result;
    }




    static void printRacerStats(List<Championship> champs, String racer)
    {
      int[] pt = new int[3];
      for(int i = 0; i < champs.size(); i++)
      {
        for(int j = 1; j < 22; j++)
        {
        if(champs.get(i).getRacer().equals(racer))
         {
          if(champs.get(i).getRace(j).getPosition() < 4 && champs.get(i).getRace(j).getPosition() > 0)
            pt[champs.get(i).getRace(j).getPosition() - 1] += 1;
         }
        }
        }
         System.out.println("Racer: " + racer);
         System.out.println("1st: " + pt[0]);
         System.out.println("2nd: " + pt[1]);
         System.out.println("3rd: " + pt[2]);
    }

    private static void circuit_exchange(String[] a, int x, int y)
    {
        String m = a[x];
        a[x] = a[y];
        a[y] = m;
    }

    private static void points_exchange(int[] a, int x, int y)
    {
        int m = a[x];
        a[x] = a[y];
        a[y] = m;
    }

    public static void sortCircuintsPoints(String[] a, int[] b)
    {
        for (int i = 0; i < size(b); i++)
        {
           int j = i;
           while (j > 0 &&  b[j - 1] - b[j] > 0)
           {
            points_exchange(b, j-1, j);
            circuit_exchange(a, j-1, j);
               j--;
           }
        }
    }

    static int size(int[] a)
    {
      int result = 0;
      for(int i = 0; i < 50; i++)
      {
        if(a[i] != 0)
        result++;
      }
      return result;
    }

    static public void execute(List<Championship> champs, HashMap<String, Integer> circuits)
    {
        for(int i = 0; i < champs.size(); i++)
        {
               for(int j = 0; j < 21; j++)
                 if(!circuits.containsKey(champs.get(i).getRace(j + 1).getCircuit()))
                 circuits.put(champs.get(i).getRace(j + 1).getCircuit(), 0);        
       } 
    }

    public static int RankingsCompare(String rankings1, String rankings2, int Points1, int Points2) {
        int result = Points1 - Points2;
        if (result == 0)
            result = rankings2.compareTo(rankings1);
        return result;
    }

    private static void rankings_exchange (List<String> rankings, int x, int y)
    {
        String m = rankings.get(x);
        rankings.set(x, rankings.get(y));
        rankings.set(y, m);
    }

    private static void list_points_exchange(List<Integer> points, int x, int y)
    {
        int m = points.get(x);
        points.set(x, points.get(y));
        points.set(y, m);
    }

    public static void sortRankings (List<String> rankings, List<Integer> points)
    {
        for (int i = 0; i < points.size(); i++)
        {
           int j = i;
           while (j > 0 && RankingsCompare(rankings.get(j - 1), rankings.get(j), points.get(j - 1), points.get(j)) < 0)
           {
               rankings_exchange(rankings, j-1, j);
               list_points_exchange(points, j-1, j);
               j--;
           }
        }
    }



    static void printRacerCircuitRanking(List<Championship> champs, String racer)
    {   
        List<String> rankings = new ArrayList<>();
        List<Integer> points = new ArrayList<>();
        HashMap<String, Integer> circuits = new HashMap<String, Integer>();
        execute(champs, circuits);
        int k = 0;
        for(int i = 0; i < champs.size(); i++)
        {
            if(champs.get(i).getRacer().equals(racer))
                {
               for(int j = 0; j < 21; j++)
                 {
                if(champs.get(i).getRace(j + 1).getPosition() < 11 && champs.get(i).getRace(j + 1).getPosition() > 0)
                    {
                 int c = (int) (1024 / (Math.pow(2, champs.get(i).getRace(j + 1).getPosition() - 1)));
                 circuits.put(champs.get(i).getRace(j + 1).getCircuit(), circuits.get(champs.get(i).getRace(j + 1).getCircuit()) + c);
                    }
                }
                }
                }
                for (String i : circuits.keySet())
                 {
                    if(circuits.get(i) != 0)
                    {
                    rankings.add(k, i);
                    points.add(k, circuits.get(i)); 
                    k++;
                    }
                 } 
                 sortRankings(rankings, points);
              for (int i = 0; i < points.size(); i++)
                System.out.println(rankings.get(i) + " " + points.get(i));
    }

    public static int RacecompareTo(Race r1, Race r2) {
        int result = r2.getPosition() - r1.getPosition();
        if (result == 0)
            result = r1.getYear() - r2.getYear();
        if(result == 0)
            result = r2.getCircuit().compareTo(r1.getCircuit());
        return result;
    }

    private static void race_exchange (List<Race> race, int x, int y)
    {
        Race m = race.get(x);
        race.set(x, race.get(y));
        race.set(y, m);
    }

    public static void sortChampionships2 (List<Race> race)
    {
        for (int i = 0; i < race.size(); i++)
        {
           int j = i;
           while (j > 0 && RacecompareTo(race.get(j - 1), race.get(j)) < 0)
           {
               race_exchange(race, j-1, j);
               j--;
           }
        }
    }

    static List<Race> getBestRaces(List<Championship> champs, String racer) 
    {
        List<Race> Races = new ArrayList<Race>();
        for(int i = 0; i < champs.size(); i++)
        {
         if(champs.get(i).getRacer().equals(racer))
         {
           for(int j = 0; j < 21; j++)
            {
             if(champs.get(i).getRace(j + 1).getPosition() < 6 && champs.get(i).getRace(j + 1).getPosition() > 0)
               Races.add(champs.get(i).getRace(j + 1)); 
   
           }
         }
        }
        sortChampionships2(Races);
        return Races;
    }

    static List<String> getTeams(List<Championship> champs, String category)
    {
    List<String> team = new ArrayList<String>();
    String[] vf = new String[100];
    for(int i = 0; i < 100; i++)
    {
        vf[i] = "1";
    }
     for(int i = 0; i < champs.size(); i++)
     {
      if(champs.get(i).getCategory().equals(category) && vf(vf, champs.get(i).getBike()) == -1)
      {
        team.add(champs.get(i).getBike());
        vf[i] = champs.get(i).getBike();
      }
     }
     team.sort(Comparator.naturalOrder());
     return team;
    }
}

