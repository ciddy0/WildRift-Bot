package Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class Commands extends ListenerAdapter {
    public static String[][] players(){
        //creates a 2d array to store our users
        String[][] users = new String[20][10];
        //Cid
        users[0][0] = "cid";
        users[0][1] = "https://na.wildstats.gg/en/profile/TUmkNgTI5sLNOPVwdxDf";
        //smsh
        users[1][0] = "Smash";
        users[1][1] = "https://na.wildstats.gg/en/profile/aGIXhbvBt2L9VSpg16vm";
        //scorchy
        users[2][0] = "Scorchy";
        users[2][1] = "https://na.wildstats.gg/en/profile/56GGdRWdcTFAmNmCeuil";
        //cynthia
        users[3][0] = "Cynthia";
        users[3][1] = "https://na.wildstats.gg/en/profile/KFXpcWwzHz75k5WQKtJr";
        //Price
        users[4][0] = "Price";
        users[4][1] = "https://na.wildstats.gg/en/profile/Xer6p8XALqZYWRyWHkSw";
        //evie
        users[5][0] = "Evie";
        users[5][1]="https://na.wildstats.gg/en/profile/bJTtMFxC5YiAZmOM5qN0";
        //miruku
        users[6][0]="Miruku";
        users[6][1]="https://na.wildstats.gg/en/profile/9wnygxTyTdb1tlcfeszh";
        //kath
        users[7][0] = "Kath";
        users[7][1]="https://na.wildstats.gg/en/profile/Yv1ejaDJaUr7tX7Y2VdZ";
        //brat aka alpha aka aria
        users[8][0]="Brat";
        users[8][1]="https://na.wildstats.gg/en/profile/MxSyAsoSA7Wlj8nKLLdf";
        //royz
        users[9][0]="Royz";
        users[9][1]="https://na.wildstats.gg/en/profile/sKajfyQnZl6vxYxD5WJm";

        users[10][0] = "Kent";
        users[10][1] = "https://sea.wildstats.gg/en/profile/O9pvuIcQgo8aXCeRlEyG";

        users[11][0] = "Raigos";
        users[11][1] = "https://na.wildstats.gg/en/profile/eGEBhqsNfQdhY9dqzTDk";

        users[12][0] = "Hibiki";
        users[12][1] = "https://na.wildstats.gg/en/profile/Dcx4krELgZmy0q8CbbgZ";
        return users;
    }
    public void onMessageReceived(MessageReceivedEvent event) {

        String[] args = event.getMessage().getContentRaw().split(" ");
        Guild guild = event.getGuild();

        //info command
        if (args[0].equalsIgnoreCase(wildRiftBot.prefix + "info")) {
            //creates an embed for info
            EmbedBuilder info = new EmbedBuilder();
            info.setTitle("Information About Bot");
            info.setDescription("This bot is just meant for me and my friends\n If we are not friends then you can not search your stats\n SORRY\nCommands\n+lewd champ name ex: +lewd ahri\n+stats name");
            info.setFooter("xoxo",event.getMember().getUser().getAvatarUrl());
            info.setColor(0xffcccb);
            info.setImage("https://upload.wikimedia.org/wikipedia/commons/thumb/4/42/League_of_Legends_Wild_Rift_logo.png/640px-League_of_Legends_Wild_Rift_logo.png");

            //sends embed
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessageEmbeds(info.build()).queue();
            info.clear();
        }
        //command for stats
        if (args[0].equalsIgnoreCase(wildRiftBot.prefix + "stats")) {
            //creates the 2d array
            String[][] users = players();
            String url = "";
            //sets the url
            //itirrates through the array and checks if it matches user input
            for (int i = 0; i < 20; i++) {
                if (users[i][0].equalsIgnoreCase(args[1])) {
                    url = users[i][1];
                    break;
                }
            }
            //creates stat embed
            EmbedBuilder stats = new EmbedBuilder();
            stats.setTitle(wildRiftBot.getIgn(url));
            stats.setDescription(wildRiftBot.display(url));
            stats.setFooter("Created by Cid",event.getMember().getUser().getAvatarUrl());
            stats.setColor(0xCBC3E3);
            stats.setImage("https://support-wildrift.riotgames.com/hc/article_attachments/1500005648641/Just_Peachy_emote.png");

            //sends embed
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessageEmbeds(stats.build()).queue();
            stats.clear();
        }
        //command for users, shows available commands
        if (args[0].equalsIgnoreCase(wildRiftBot.prefix + "users")) {
            //creates a user embed
            EmbedBuilder users = new EmbedBuilder();
            users.setTitle("Users That Can See Stats");
            users.setImage("https://assets.gamepur.com/wp-content/uploads/2021/01/14060941/Yordles-850x478.jpg");
            users.setDescription("cid | smash | scorchy | cynthia | price | evie | miruku | brat | royz\n Use names EXACTLY how it is\n If you do not see your name dm your ign");
            users.setFooter("Created by Cid",event.getMember().getUser().getAvatarUrl());
            users.setColor(0xFF0000);

            //sends embed
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessageEmbeds(users.build()).queue();
            users.clear();
        }
        if (args[0].equalsIgnoreCase(wildRiftBot.prefix + "ali")) {
            //creates a gf embed
            EmbedBuilder gf = new EmbedBuilder();

            gf.setTitle("Aliyah Gomez");
            gf.setImage("https://scontent-lax3-1.cdninstagram.com/v/t51.2885-15/e35/252177451_566469841090904_1047000973489075008_n.jpg?_nc_ht=scontent-lax3-1.cdninstagram.com&_nc_cat=108&_nc_ohc=JYl6jFF0xZoAX8I0n1r&edm=AABBvjUBAAAA&ccb=7-4&oh=00_AT9CsMUYP1V2lzEEkGd6cZswj3wvaKrJoe7RvFdydA2s1g&oe=61C66741&_nc_sid=83d603");
            gf.setColor(0x89CFF0);
            gf.setFooter("Owned by Cid: 01-15-2021", event.getAuthor().getAvatarUrl());
            event.getChannel().sendMessageEmbeds(gf.build()).queue();
        }
    }
}