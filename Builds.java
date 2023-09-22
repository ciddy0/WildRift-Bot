
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.Button;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Locale;

public class Builds extends ListenerAdapter {
        public void onMessageReceived(MessageReceivedEvent event){
          //gets the message
            String[] args = event.getMessage().getContentRaw().split(" ");

            //initializes variables
            String pathname = "images/";
            String champ = null;
            String champName = null;

            //build comamnd
            if (args[0].equalsIgnoreCase(Main.prefix + "build")){
              //checks to see if champ name is bigger than 1 one word
                if(args.length > 2 ){
                    champ = args[1].toLowerCase(Locale.ROOT)+"-"+args[2].toLowerCase(Locale.ROOT)+".png";
                    champName = args[1].toUpperCase(Locale.ROOT) + " " + args[2].toUpperCase(Locale.ROOT);
                }
                else{
                    champ = args[1].toLowerCase(Locale.ROOT)+".png";
                    champName = args[1].toUpperCase(Locale.ROOT);
                }

                //creates the pathname for the image
                pathname = pathname + champ;
                System.out.println(pathname);

                //creates a file of the image
                File file2 = new File(pathname);

                //creates new build embed
                EmbedBuilder build = new EmbedBuilder();

                //path of image
                String image = "attachment://" + champ;
                build.setImage(image);
                build.setColor(0x4169e1);

                build.setFooter("xoxo | source: WildRiftFire");

                //initializes variables
                String thumbnail = "";
                String source = "";
                build.setTimestamp(new Date().toInstant());

                //special case for nunu
                if (args[1].equalsIgnoreCase("nunu")){
                    build.setThumbnail("https://www.mobafire.com/images/champion/square/nunu-amp-willump.png");
                    source = "https://www.wildriftfire.com/guide/nunu-amp-willump";
                }
                else{
                    //gets thumbnail of champ
                    thumbnail = "https://www.mobafire.com/images/champion/square/" + champ;
                    build.setThumbnail(thumbnail);
                    //gets the link of champ
                    source = "https://www.wildriftfire.com/guide/"+champ.substring(0, champ.length()-4);
                }

                //building embed
                build.setTitle("__**"+ champName + "**__", source);
                build.setDescription("General build for the champion \nIf the image does not load please try the command again :)");

                //sends embed
                event.getChannel().sendMessageEmbeds(build.build())
                        .addFile(file2, champ)
                        .queue();
                

            }
        }


}
