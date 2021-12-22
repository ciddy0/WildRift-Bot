/*
Uses local image files
*/


package Main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;
import java.util.Date;
import java.util.Locale;

public class Builds extends ListenerAdapter {
        public void onMessageReceived(MessageReceivedEvent event){
            String[] args = event.getMessage().getContentRaw().split(" ");
            String pathname = "src/Main/";
            String champ = null;
            String champName = null;
            if (args[0].equalsIgnoreCase(wildRiftBot.prefix + "build")){
                if(args.length > 2 ){
                    champ = args[1].toLowerCase(Locale.ROOT)+"-"+args[2].toLowerCase(Locale.ROOT)+".png";
                    champName = args[1].toUpperCase(Locale.ROOT) + " " + args[2].toUpperCase(Locale.ROOT);
                }
                else{
                    champ = args[1].toLowerCase(Locale.ROOT)+".png";
                    champName = args[1].toUpperCase(Locale.ROOT);
                }
                pathname = pathname + champ;
                System.out.println(pathname);
                File file2 = new File(pathname);
                EmbedBuilder build = new EmbedBuilder();


                String image = "attachment://" + champ;
                build.setImage(image);
                build.setTitle(champName);
                build.setColor(0x4169e1);
                build.setFooter("xoxo");
                build.setTimestamp(new Date().toInstant());
                String thumbnail = "https://www.mobafire.com/images/champion/square/" + champ;
                build.setThumbnail(thumbnail);
                build.setDescription("This is just a basic build.\n You can get better builds from leaderboards\n If image does not load try the command again :)");
               
                event.getChannel().sendMessageEmbeds(build.build())
                        .addFile(file2, champ)
                        .queue();
            }
        }


}
