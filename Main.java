import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
public class Main {
    //sets the prefix of the bot
    public static String prefix = "+";

    //ign method
    public static String getIgn(Document doc){
        String name = null;
        Elements igndoc = doc.getElementsByClass("pt-0 pt-sm-4 text-center text-sm-left");
        Element tag = doc.getElementById("playerTag");
        //name = "IGN: " + ign.text()+"#"+tag.text();
        name = "IGN: " + igndoc.text()+"#"+tag.text();
        //System.out.println("IGN: " + igndoc.text()+"#"+tag.text());
        return name;
    }
    public static String getLvl(Document doc){
        String lvl = "";
        Elements lvlDoc = doc.getElementsByClass("profile-level");
        lvl = lvlDoc.text();
        return lvl;
    }
    public static String getRank(Document doc){
        String lp, rank;
        Elements rankDoc = doc.getElementsByClass("badge badge-overlay text-white");
        Elements rp = doc.getElementsByClass("badge badge-overlay text-gold");
        lp = rp.text().replaceAll("[^0-9]", "");
        if (lp.isEmpty()){
            rank = rankDoc.text();
        }
        else{
            rank = rankDoc.text() +" "+ lp + "LP";
        }

        //System.out.println("Rank: " + rankDoc.text() +" "+ lp + "LP");
        return rank;

    }
    public static String getWinRate(Document doc){
        String winRate = "";
        Elements elements = doc.select("script");
        Pattern parserPattern = Pattern.compile("'(?<BATTLE>[a-zA-Z]+)': [{](?<ATTRIBUTES>[\n 'a-zA-Z0-9+:\"0-9a-zA-Z,.]+)[};]");
        Matcher matcher = parserPattern.matcher(elements.toString());
        while (matcher.find()) {
            String battleType = matcher.group("BATTLE");
            if (battleType.equalsIgnoreCase("rankedBattles")) {
                //System.out.println("Battle Type: " + battleType);
                String[] attributesLines = matcher.group("ATTRIBUTES")
                        .replaceAll(",\n", "\n")
                        .split("\n");

                for (String line : attributesLines) {
                    String[] keyValue = line.trim().split(":");
                    if (keyValue.length != 2) continue;
                    String key = keyValue[0];
                    String value = keyValue[1];
                    if (keyValue[0].equalsIgnoreCase("'winRate'")) {
                        winRate = value.replace("' ", "");
                        //System.out.println("Ranked Win Rate:" + winrate + "%");
                        //System.out.println("\t" + key + ": " + value);
                    }
                    //System.out.println("\t" + key + " : " + value);
                }
            }
        }
        return winRate;
    }
    public static String getKda(Document doc){
        String kda = "";
        Elements elements = doc.select("script");
        Pattern parserPattern = Pattern.compile("'(?<BATTLE>[a-zA-Z]+)': [{](?<ATTRIBUTES>[\n 'a-zA-Z0-9+:\"0-9a-zA-Z,.]+)[};]");
        Matcher matcher = parserPattern.matcher(elements.toString());
        while (matcher.find()) {
            String battleType = matcher.group("BATTLE");
            if (battleType.equalsIgnoreCase("rankedBattles")) {
                //System.out.println("Battle Type: " + battleType);
                String[] attributesLines = matcher.group("ATTRIBUTES")
                        .replaceAll(",\n", "\n")
                        .split("\n");
                for (String line : attributesLines) {
                    String[] keyValue = line.trim().split(":");
                    if (keyValue.length != 2) continue;
                    String key = keyValue[0];
                    String value = keyValue[1];
                    if (keyValue[0].equalsIgnoreCase("'totalKda'")) {
                        kda = value.replace("' ", "");
                        //System.out.println("Average Rank KDA:" + kda);
                        //System.out.println("\t" + key + " : " + value);
                    }
                }
            }
        }
        return kda;
    }
    public static String getTotalMatches(Document doc){
        String totalMatches = "";
        Elements elements = doc.select("script");
        Pattern parserPattern = Pattern.compile("'(?<BATTLE>[a-zA-Z]+)': [{](?<ATTRIBUTES>[\n 'a-zA-Z0-9+:\"0-9a-zA-Z,.]+)[};]");
        Matcher matcher = parserPattern.matcher(elements.toString());
        while (matcher.find()) {
            String battleType = matcher.group("BATTLE");
            if (battleType.equalsIgnoreCase("rankedBattles")) {
                //System.out.println("Battle Type: " + battleType);
                String[] attributesLines = matcher.group("ATTRIBUTES")
                        .replaceAll(",\n", "\n")
                        .split("\n");
                for (String line : attributesLines) {
                    String[] keyValue = line.trim().split(":");
                    if (keyValue.length != 2) continue;
                    String key = keyValue[0];
                    String value = keyValue[1];
                    if (keyValue[0].equalsIgnoreCase("'totalBattles'")) {
                        totalMatches = value.replace("'", "");
                        totalMatches = value.replace("\"", "");
                        //System.out.println("Total Ranked games:"+totalGames);
                        //System.out.println("\t" + key + " : " + value);
                    }
                }
            }
        }
        return totalMatches;
    }
    public static String getMvps(Document doc){
        String mvps = "";
        Elements elements = doc.select("script");
        Pattern parserPattern = Pattern.compile("'(?<BATTLE>[a-zA-Z]+)': [{](?<ATTRIBUTES>[\n 'a-zA-Z0-9+:\"0-9a-zA-Z,.]+)[};]");
        Matcher matcher = parserPattern.matcher(elements.toString());
        while (matcher.find()) {
            String battleType = matcher.group("BATTLE");
            if (battleType.equalsIgnoreCase("rankedBattles")) {
                //System.out.println("Battle Type: " + battleType);
                String[] attributesLines = matcher.group("ATTRIBUTES")
                        .replaceAll(",\n", "\n")
                        .split("\n");
                for (String line : attributesLines) {
                    String[] keyValue = line.trim().split(":");
                    if (keyValue.length != 2) continue;
                    String key = keyValue[0];
                    String value = keyValue[1];
                    if (keyValue[0].equalsIgnoreCase("'mvpWins'")) {
                        mvps = value.replace("\"", "");
                        //System.out.println("Total Rank MVPS:"+ mvps);
                        //System.out.println("\t" + key + " : " + value);
                    }
                }
            }
        }
        return mvps;
    }
    public static String display(Document doc){
        String lvl = getLvl(doc);
        String rank = getRank(doc);
        String winRate = getWinRate(doc);
        String matches = getTotalMatches(doc);
        String mvps = getMvps(doc);
        String kda = getKda(doc);
        return String.format(lvl + "\n" + rank + "\n" + "Rank Win Rate:chart_with_upwards_trend::" + winRate + "%%"+ "\nRank Matches:bar_chart: :"+matches+"\nRank MVPs :first_place::"+mvps+"\nRank KDA:crossed_swords::"+kda);
    }
    public static Document Doc(String name) throws IOException {
        Map<String, String> cookies = new HashMap<String, String>();

        // First request.
        Connection connection1 = Jsoup.connect("https://na.wildstats.gg/en");
        for (Map.Entry<String, String> cookie : cookies.entrySet()) {
            connection1.cookie(cookie.getKey(), cookie.getValue());
        }
        Connection.Response response1 = connection1.execute();
        cookies.putAll(response1.cookies());
        Document document1 = response1.parse();
        //System.out.println(cookies); prints out cookies
        String keywords = document1.select("meta[name=csrf-token]").first()
                .attr("content");
        //System.out.println("csrf-token: " + keywords); prints out token

        Connection connection2 =    Jsoup.connect("https://na.wildstats.gg/en/profile/gameid").referrer("https://na.wildstats.gg/en").data("_token", keywords).data("gameid", name);
        for (Map.Entry<String, String> cookie : cookies.entrySet()) {
            connection2.cookie(cookie.getKey(), cookie.getValue());
        }
        Connection.Response response2 = connection2.execute();
        cookies.putAll(response2.cookies());
        Document document2 = response2.parse();
        //System.out.println(document2);
        return document2;
    }
    public static String getIcon(Document doc){
        String img = doc.select("div[class=champion_card_icon]").first().attr("style");
        String regex = "\\s*\\bbackground-image: url\\b\\s*";
        img = img.replaceAll(regex, "");
        img = img.replaceAll("[()]","");
        return img;
    }

    public static void main(String[] args) throws LoginException, IOException {
        
        //builds the bot
        String myKey = System.getenv("TOKEN");
        JDA jda = JDABuilder.createDefault(myKey).build();
        //sets status to idle
        jda.getPresence().setStatus(OnlineStatus.IDLE);
        //sets activity to playing wild rift
        jda.getPresence().setActivity(Activity.playing("Wild Rift"));
        //commands
        jda.addEventListener(new Commands());
        jda.addEventListener(new Lewd());
        jda.addEventListener(new Builds());
        jda.addEventListener(new Recent());
        jda.addEventListener(new Leaderboards());


        
        Scanner obj = new Scanner(System.in);
        System.out.println("ENTER IGN: ");
        String ign = obj.nextLine();
        Document doc = Doc(ign);
        System.out.println(getIgn(doc));
      
      

    }
}