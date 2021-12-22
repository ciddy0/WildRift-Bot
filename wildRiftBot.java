package Main;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;


public class wildRiftBot {
    //sets the prefix of the bot
    public static String prefix = "+";

    //ign method
    public static String getIgn(String url){
        String ign = "";
        try {
            Document document = Jsoup.connect(url).get();
            Elements igndoc = document.getElementsByClass("pt-0 pt-sm-4 text-center text-sm-left");
            Element tag = document.getElementById("playerTag");
            //name = "IGN: " + ign.text()+"#"+tag.text();
            ign = "IGN: " + igndoc.text()+"#"+tag.text();
            //System.out.println("IGN: " + igndoc.text()+"#"+tag.text());
        }catch (IOException e){
            e.printStackTrace();
        }
        return ign;
    }
    //level method
    public static String getLvl(String url){
        String lvl = "";
        try {
            Document document = Jsoup.connect(url).get();
            Elements lvlDoc = document.getElementsByClass("profile-level");
            lvl = lvlDoc.text();
            //System.out.println(lvlDoc.text());
        }catch (IOException e){
            e.printStackTrace();
        }
        return lvl;
    }
    //rank method
    public static String getRank(String url){
        String rank = "";
        String lp = null;
        try {
            Document document = Jsoup.connect(url).get();
            Elements rankDoc = document.getElementsByClass("badge badge-overlay text-white");
            Elements rp = document.getElementsByClass("badge badge-overlay text-gold");
            lp = rp.text().replaceAll("[^0-9]", "");
            rank = rankDoc.text() +" "+ lp + "LP";
            //System.out.println("Rank: " + rankDoc.text() +" "+ lp + "LP");

        }catch (IOException e){
            e.printStackTrace();
        }
        return rank;
    }
    //winrate method
    public static String getWinRate(String url){
        String winRate = "";
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select("script");
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

        }catch (IOException e){
            e.printStackTrace();
        }
        return winRate;
    }
    //kda method
    public static String getKda(String url){
        String kda = "";
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select("script");
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
        }catch(IOException e){
            e.printStackTrace();
        }
        return kda;
    }
    //total matches method
    public static String getTotalMatches(String url){
        String totalMatches= "";
        try{
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select("script");
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
        }catch(IOException e){
            e.printStackTrace();
        }
        return totalMatches;
    }
    //mvp methods
    public static String getMvps(String url){
        String mvps = "";
        try{
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select("script");
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

        }catch(IOException e){
            e.printStackTrace();
        }
        return mvps;
    }
    //displays all of the stats
    public static String display(String url){
        String lvl = getLvl(url);
        String rank = getRank(url);
        String winRate = getWinRate(url);
        String matches = getTotalMatches(url);
        String mvps = getMvps(url);
        String kda = getKda(url);
        return String.format(lvl + "\n" + rank + "\n" + "Rank Win Rate:" + winRate + "%%"+ "\nRank Matches:"+matches+"\nRank MVPs :first_place::"+mvps+"\nRank KDA:"+kda);
    }
    public static void main(String[] args) throws LoginException, IOException {
        //builds the bot
        JDA jda = JDABuilder.createDefault("token").build();
        //sets status to idle
        jda.getPresence().setStatus(OnlineStatus.IDLE);
        //sets activity to playing wild rift
        jda.getPresence().setActivity(Activity.playing("Wild Rift"));
        //commands
        jda.addEventListener(new Commands());
        jda.addEventListener(new Lewd());
        jda.addEventListener(new Builds());


    }
}
