import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class Leaderboards extends ListenerAdapter {

    public static List<ArrayList<String>> top200(String region) throws IOException {
        List<ArrayList<String>> namesAndNumbers = null;
        if (region.equalsIgnoreCase("NA")) {
            //connects to the website
            Document doc = Jsoup.connect("https://na.wildstats.gg/en/leaderboard")
                    .header("Accept-Encoding", "gzip, deflate")
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
                    .maxBodySize(0)
                    .get();
            //selects the table
            Element table = doc.select("table").get(0);
            //selects the rows
            Elements rows = table.select("tr");

            //creates a new arrayList to store the names and LP
            namesAndNumbers = new ArrayList<ArrayList<String>>();


            for (int i = 1; i < rows.size(); i++) {
                Element row = rows.get(i);
                Elements cols = row.select("td");

                String name = cols.get(1).select("span.leaderboard_icon_name").text();
                String lp = cols.get(2).text();
                lp = lp.replaceAll(",", "");

                //adds the string to the Array
                namesAndNumbers.add(new ArrayList<String>(Arrays.asList(lp, name)));
            }

            //sorts the array
            Collections.sort(namesAndNumbers, new Comparator<ArrayList<String>>() {
                @Override
                public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                    return Integer.valueOf(o1.get(0)).compareTo(Integer.valueOf(o2.get(0)));
                }
            });


        }
        //returns an arraylist of the top200 players
        return namesAndNumbers;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        super.onMessageReceived(event);
        //gets message
        String[] args = event.getMessage().getContentRaw().split(" ");
        //NA leaderboards
        if (args[0].equalsIgnoreCase(Main.prefix+"leaderboards")&&args[1].equalsIgnoreCase("NA")){
        System.out.println("working");
        char[] chars = args[1].toCharArray();
        StringBuilder page = new StringBuilder();
        for(char c : chars){
          if(Character.isDigit(c)){
              page.append(c);
          }
        }
        System.out.println(page);
          //initialies variables to bring them into scope
        String need = null;
        String names = null;
        String lp = null;
        String player = null;
        List<ArrayList<String>> namesandLP = null;

        //gets top200 players
        try{
          namesandLP = top200("NA");
        }catch(IOException e){
          System.out.println("Whoops");
        }
          
        //new list to store top 10 players
        ArrayList<String> top10 = new ArrayList<>();

        //keeps count of where i is in the loop
        int count = 0;
          for(int i = namesandLP.size()-1; i>= 0;i--){
              count++;

              //only stores the top 10 players
              if(count<=10){
                  names = namesandLP.get(i).get(1);
                  lp = namesandLP.get(i).get(0);
                  player = String.format("%s  %s**LP**", names, lp);

                  //adds player
                  top10.add(player);
              }

              //gets the top 100 players lp
              if(count == 100){
                  //stores lp
                  need = namesandLP.get(i).get(0);
                  break;
              }
          }
        //string for the display
        String display = "";
        //standing
        int place = 1;
        for(int i = 0; i< top10.size();i++){
            display = display + "**" +place + ")**" + top10.get(i) + "\n";
            place++;
        }
        if(page.equals("2")){

          System.out.println("hi");
          int count2 = 0;
          String names2 = "";
          String lp2 = "";
          String player2 = "";
          ArrayList<String> top50 = new ArrayList<String>();
          for(int i = namesandLP.size()-1; i>= 0;i--){
            count2++;
            if(count2>10 && count2<=50){
              names2 = namesandLP.get(i).get(1);
              lp2 = namesandLP.get(i).get(0);
              player2 = String.format("%s  %s**LP**", names, lp);
              //adds player
              top50.add(player);
            }
          }
          String display2 = "";
          //standing
          int place2 = 11;
          for(int i = 0; i< top50.size();i++){
              display2 = display2 + "**" +place2 + ")**" + top50.get(i) + "\n";
              place2++;
          }
          EmbedBuilder leaderboards2 = new EmbedBuilder();
          leaderboards2.addField("11-50", display2, true);
          event.getChannel().sendMessageEmbeds(leaderboards2.build()).queue();
          leaderboards2.clear();
        }
        else{
            //creates a leaderboard embed
            EmbedBuilder leaderboards = new EmbedBuilder();
            leaderboards.setTitle("Leaderboards", "https://na.wildstats.gg/en/leaderboard");
            leaderboards.addField("**Top 10 Players**", display+"\n ", true);
            leaderboards.addField("**LP Needed for Challenger**", "Minimum LP: "+need, true);
            leaderboards.setDescription("Updates Leaderboard for NA Ranked Top 200, disregards rank and sorts based on LP\n\n__This may not be the final leaderboard on reset, it's just an estimate for the minimum LP you need based on current standings__");
            leaderboards.setFooter("xoxo | source: wildstats.gg");
            leaderboards.setColor(0xCBC3E3);
            leaderboards.setTimestamp(new Date().toInstant());
            //sends embed
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessageEmbeds(leaderboards.build()).queue();
            leaderboards.clear();
        }          
      }
    }
}
